package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.GroupPermissions;
import com.spring.entity.UserInformation;
import com.spring.service.GroupPermissionsService;
import com.spring.service.UserService;

/**
 * UserController xử lý các yêu cầu liên quan đến người dùng và phân quyền người dùng.
 * 
 * @version 1.0
 * @date 06-06-2024
 * 
 * <p>Modification Logs:</p>
 * <pre>
 * DATE                 AUTHOR            DESCRIPTION
 * --------------------------------------------------------------------
 * 06-06-2024           Le Thanh Ha       Create
 * </pre>
 * 
 * @author le thanh ha (04/05/2001)
 */
@Controller
@RequestMapping("/nguoi-dung")
public class UserController {
    @Autowired
    private GroupPermissionsService groupPermissionsService;
    @Autowired
    private UserService userService;

    /**
     * Hiển thị trang thông tin người dùng.
     *
     * @param userId ID của người dùng cần cập nhật (tùy chọn)
     * @param searchSchool Chuỗi tìm kiếm trường học (tùy chọn)
     * @param model Đối tượng Model để thêm các thuộc tính
     * @return Tên của trang hiển thị
     */
    @GetMapping("/thong-tin-nguoi-dung")
    public String thongTinNguoiDung(
            @RequestParam(name = "userIDUpdate", required = false) Integer userId, 
            @RequestParam(name = "searchSchool", required = false) String searchSchool, 
            Model model) {
        // Xử lý khi có userId
        if (userId != null) {
            UserInformation userInformation = userService.findByID(userId);
            model.addAttribute("user", userInformation);
            model.addAttribute("userID", userInformation.getUserID());
            model.addAttribute("listGroup", groupPermissionsService.findAllGroupByNotID(userInformation.getGroupPermissions().getGroupID()));
        } else {
            model.addAttribute("listGroup", groupPermissionsService.getListGroup());
        }
        // Xử lý tìm kiếm theo trường học
        if (searchSchool != null) {
            model.addAttribute("listUser", userService.findUserBySchool(searchSchool));
            model.addAttribute("school", searchSchool);          
        } else {
            model.addAttribute("listUser", userService.getAllUser());
        }
        // Thêm các thuộc tính khác vào trang web
        model.addAttribute("pageContent","/WEB-INF/jsp/layout/user.jsp");
        model.addAttribute("pageList","/WEB-INF/jsp/layout/listItems.jsp");
        model.addAttribute("formUser", new UserInformation());
        model.addAttribute("backgroundUser","bg-light");
        model.addAttribute("backgroundGroup", "bg-secondary");
        return "layout/home";
    }

    /**
     * Xử lý lưu thông tin người dùng.
     *
     * @param userInformation Đối tượng UserInformation chứa thông tin người dùng
     * @param result Đối tượng BindingResult để kiểm tra lỗi
     * @param model Đối tượng Model để thêm các thuộc tính
     * @param groupID ID của nhóm phân quyền (tùy chọn)
     * @param password2 Mật khẩu xác nhận
     * @param action Hành động (add, update, delete)
     * @param userId ID của người dùng cần cập nhật (tùy chọn)
     * @param redirectAttributes Đối tượng RedirectAttributes để thêm các thuộc tính chuyển hướng
     * @return Tên của trang hiển thị sau khi xử lý
     */
    @PostMapping("/luu-thong-tin")
    public String luuThongTin(@Valid @ModelAttribute("formUser") UserInformation userInformation, BindingResult result,
                              Model model, @RequestParam(name = "groupID", required = false, defaultValue = "0") Integer groupID, 
                              @RequestParam("password2") String password2, @RequestParam("action") String action,
                              @RequestParam(value = "userIDUpdate", required = false) Integer userId,
                              RedirectAttributes redirectAttributes, 
                              @RequestParam(name = "searchSchool", required = false) String searchSchool) {
        boolean isUpdateAction = "update".equals(action);
        boolean isAddAction = "add".equals(action);
        boolean isDeleteAction = "delete".equals(action);
        boolean userExists = false;
        // kiểm tra xem đã chọn người dùng để cập nhật chưa
        if (isUpdateAction && userId == null) {
            redirectAttributes.addFlashAttribute("updateMessage",
                    "Cập nhật thất bại ! <br> Vui lòng chọn người dùng cần cập nhật.");
            redirectAttributes.addFlashAttribute("backgroundMessage", "alert-danger");
            return "redirect:/nguoi-dung/thong-tin-nguoi-dung";
        } 
        // Thực hiện hành động xóa
        if (isDeleteAction) {
            if (userId != null) {
                // Xóa user ra khỏi database
                userService.deleteUser(userId);
                // Thêm dòng thông báo xóa thành công
                redirectAttributes.addFlashAttribute("updateMessage", "Xóa người dùng thành công!");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-success");
            } else {
                // Thêm dòng thông báo xóa thất bại
                redirectAttributes.addFlashAttribute("updateMessage", "Xóa người dùng thất bại!");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-danger");
            }
            return "redirect:/nguoi-dung/thong-tin-nguoi-dung";
        }
        // Validate của form User
        boolean isInformationForm = true;
        if (result.hasErrors()) {
            // Truyền thông điệp lỗi cho từng trường vào model
            redirectAttributes.addFlashAttribute("userNameError",
                    result.getFieldError("userName") != null ? result.getFieldError("userName").getDefaultMessage() : null);
            redirectAttributes.addFlashAttribute("passwordError",
                    result.getFieldError("password") != null ? result.getFieldError("password").getDefaultMessage() : null);
            redirectAttributes.addFlashAttribute("nameError", result.getFieldError("name"));
            redirectAttributes.addFlashAttribute("positionError", result.getFieldError("position"));
            redirectAttributes.addFlashAttribute("schoolError", result.getFieldError("school"));
            isInformationForm = false;
        }
        // Kiểm tra group ID nhập chưa
        if (groupID == 0) {
            redirectAttributes.addFlashAttribute("groupIDError", "Vui lòng chọn phân quyền cho người dùng");
            isInformationForm = false;
        }
        // Kiểm tra password 2 của người dùng có chính xác
        if (password2 == null || password2.isEmpty()) {
            redirectAttributes.addFlashAttribute("password2Error", "Xác nhận mật khẩu không được để trống");
            isInformationForm = false;
        } else if (!password2.equals(userInformation.getPassword())) {
            redirectAttributes.addFlashAttribute("password2Error", "Xác nhận mật khẩu không khớp");
            isInformationForm = false;
        }
        // Set group và lưu thông tin mới của người dùng
        GroupPermissions groupPermissions = groupPermissionsService.findGroupByID(groupID);

        if (isUpdateAction || isAddAction) {            
            // Xử lí các hành động cập nhật và thêm
            if (isUpdateAction && userId != null) {
                // Lấy thông tin người dùng hiện tại từ cơ sở dữ liệu
                UserInformation currentUser = userService.findByID(userId);
                // Kiểm tra xem tên người dùng mới có khác với tên người dùng hiện tại hay không
                if (!userInformation.getUserName().equals(currentUser.getUserName())) {
                    // Nếu khác, kiểm tra xem tên người dùng mới có tồn tại trong hệ thống hay không
                    userExists = !userService.findUserByUserName(userInformation.getUserName()).isEmpty();
                }
            } else if (isAddAction) {
                // Kiểm tra userName đã tồn tại chưa
                userExists = !userService.findUserByUserName(userInformation.getUserName()).isEmpty();
            }  
            if (userExists) {
                redirectAttributes.addFlashAttribute("userNameError", "Username đã tồn tại");
                isInformationForm = false;
            }
            if (!isInformationForm) {
                // Truyền lại group cho userInformation
                userInformation.setGroupPermissions(groupPermissions);
                
                // Thêm các thuộc tính cho trang web
                redirectAttributes.addFlashAttribute("user", userInformation);
                redirectAttributes.addFlashAttribute("userID", userId);
                redirectAttributes.addFlashAttribute("password2", password2);
                redirectAttributes.addFlashAttribute("updateMessage", isUpdateAction ? "Cập nhật thất bại !" : "Thêm mới thất bại !");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-danger");
                return "redirect:/nguoi-dung/thong-tin-nguoi-dung";
            }

            UserInformation existingUser = isUpdateAction ? userService.findByID(userId) : new UserInformation();
            if (existingUser != null) {
                existingUser.setUserName(userInformation.getUserName());
                existingUser.setPassword(userInformation.getPassword());
                existingUser.setName(userInformation.getName());
                existingUser.setPosition(userInformation.getPosition());
                existingUser.setSchool(userInformation.getSchool());
                existingUser.setGroupPermissions(groupPermissions);
                if (isUpdateAction) {
                    userService.updateUser(existingUser);
                } else {
                    userService.saveUser(existingUser);
                }
                redirectAttributes.addFlashAttribute("updateMessage", isUpdateAction ? "Cập nhật thành công !" : "Thêm mới thành công!");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-success");
            }
        } else {
            redirectAttributes.addFlashAttribute("updateMessage", "Cập nhật thất bại !");
            redirectAttributes.addFlashAttribute("backgroundMessage", "alert-danger");
        }

        return "redirect:/nguoi-dung/thong-tin-nguoi-dung";
    }
    
    /**
     * Tìm kiếm người dùng theo trường/đơn vị.
     *
     * @param school Chuỗi đại diện cho trường/đơn vị
     * @param model Đối tượng Model để thêm các thuộc tính
     * @return Tên của trang hiển thị sau khi xử lý
     */
    @GetMapping("/tim-kiem")
    public String timKiem(@RequestParam("school") String school, Model model) {
        if (school == null || school.isEmpty()) {
            model.addAttribute("listGroup", groupPermissionsService.getListGroup());
            // Thêm các thuộc tính khác vào trang web
            model.addAttribute("pageContent", "/WEB-INF/jsp/layout/user.jsp");
            model.addAttribute("pageList", "/WEB-INF/jsp/layout/listItems.jsp");
            model.addAttribute("formUser", new UserInformation());
            model.addAttribute("listUser", userService.getAllUser());
            model.addAttribute("backgroundUser","bg-light");
            model.addAttribute("backgroundGroup", "bg-secondary");
        } else {
            model.addAttribute("listGroup", groupPermissionsService.getListGroup());
            // Thêm các thuộc tính khác vào trang web
            model.addAttribute("pageContent", "/WEB-INF/jsp/layout/user.jsp");
            model.addAttribute("pageList", "/WEB-INF/jsp/layout/listItems.jsp");
            model.addAttribute("formUser", new UserInformation());
            model.addAttribute("listUser", userService.findUserBySchool(school));
            model.addAttribute("backgroundUser","bg-light");
            model.addAttribute("backgroundGroup", "bg-secondary");
            model.addAttribute("school", school);
        }
        return "layout/home";
    }
}
