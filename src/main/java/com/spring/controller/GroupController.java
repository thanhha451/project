package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.ServiceAndViewDTO;
import com.spring.dto.ServiceGroupDTO;
import com.spring.entity.Authorization;
import com.spring.entity.AuthorizationId;
import com.spring.entity.GroupPermissions;
import com.spring.service.AuthorizationService;
import com.spring.service.GroupPermissionsService;
import com.spring.service.ServiceGroupService;

/**
 * GroupController xử lý các yêu cầu liên quan đến nhóm và phân quyền nhóm.
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
 */
@Controller
@RequestMapping("/nhom")
public class GroupController {

    @Autowired
    private GroupPermissionsService groupPermissionsService;

    @Autowired
    private ServiceGroupService groupService;

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * Hiển thị trang phân quyền nhóm.
     *
     * @param groupID ID của nhóm cần cập nhật (tùy chọn)
     * @param model   Đối tượng Model để thêm các thuộc tính
     * @return Tên của trang hiển thị
     */
    @GetMapping("/phan-quyen-nhom")
    public String phanQuyenNhom(@RequestParam(name = "groupIDUpdate", required = false) Integer groupID, Model model) {
        if (groupID != null) {
            // Lấy dữ liệu các chức năng của hệ thống từ groupID 
            GroupPermissions groupPermissions = groupPermissionsService.findGroupByID(groupID);
            model.addAttribute("group", groupPermissions);

            // Lấy chức năng của hệ thống mà nhóm chưa có
            model.addAttribute("listServiceOfSystem", groupService.findServiceGroupByNotGroupID(groupID));

            // Lấy dữ liệu các chức năng của phân quyền nhóm từ groupID 
            List<ServiceAndViewDTO> serviceGroups = groupService.findServiceGroupByGroupID(groupID);
            model.addAttribute("listServiceOfGroup", serviceGroups);
        }
        // Thêm đối tượng ServiceGroupDTO vào model
        model.addAttribute("serviceGroupDTO", new ServiceGroupDTO());
        // Thêm các thuộc tính liên quan đến giao diện vào model
        model.addAttribute("pageContent", "/WEB-INF/jsp/layout/group.jsp");
        model.addAttribute("pageList", "/WEB-INF/jsp/layout/listItems.jsp");
        model.addAttribute("backgroundGroup", "bg-light");
        model.addAttribute("backgroundUser","bg-secondary");
        model.addAttribute("listGroup", groupPermissionsService.getListGroup());
        return "layout/home";
    }

    /**
     * Xử lý lưu thông tin nhóm và phân quyền nhóm.
     *
     * @param serviceGroupDTO       Đối tượng ServiceGroupDTO chứa thông tin phân quyền nhóm
     * @param model                 Đối tượng Model để thêm các thuộc tính
     * @param groupID               ID của nhóm cần cập nhật (tùy chọn)
     * @param action                Hành động (add, update, delete)
     * @param redirectAttributes    Đối tượng RedirectAttributes để thêm các thuộc tính chuyển hướng
     * @return Tên của trang hiển thị sau khi xử lý
     */
    @PostMapping("/luu-thong-tin")
    public String handleServiceGroups(@ModelAttribute("serviceGroupDTO") ServiceGroupDTO serviceGroupDTO, Model model,
                                      @RequestParam(name = "groupIDUpdate", required = false) Integer groupID,
                                      @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
       boolean isDeleteAction ="delete".equals(action);
        // Kiểm tra nếu hành động là 'delete'
        if (isDeleteAction) {
            if (groupID != null) {
                // Thêm dòng thông báo xóa nhóm thành công
                redirectAttributes.addFlashAttribute("updateMessage", "Xóa nhóm thành công!");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-success");

                // Xóa nhóm ở bảng authorization và group
                authorizationService.deleteByGroupID(groupID);
                groupPermissionsService.deleteGroupByGroupID(groupID);
            } else {
                // Thêm dòng thông báo xóa nhóm thất bại
                redirectAttributes.addFlashAttribute("updateMessage", "Xóa nhóm thất bại!");
                redirectAttributes.addFlashAttribute("backgroundMessage", "alert-danger");
            }
            return "redirect:/nhom/phan-quyen-nhom";
        }

        // Xử lý trường hợp thêm mới hoặc cập nhật
        if (groupID == null) {
            // Xử lý thêm mới
            model.addAttribute("serviceGroupDTO", new ServiceGroupDTO());
            model.addAttribute("pageContent", "/WEB-INF/jsp/layout/group.jsp");
            model.addAttribute("pageList", "/WEB-INF/jsp/layout/listItems.jsp");
            model.addAttribute("backgroundGroup", "bg-light");
            model.addAttribute("backgroundUser","bg-secondary");
            model.addAttribute("listGroup", groupPermissionsService.getListGroup());

            // Thêm dòng thông báo cập nhật thất bại
            model.addAttribute("updateMessage", "Cập nhật thất bại!");
            model.addAttribute("backgroundMessage", "alert-danger");
            return "layout/home";
        } else {
            // Kiểm tra nếu serviceGroupDTO không có dữ liệu
            if (serviceGroupDTO.getServiceGroups() == null || serviceGroupDTO.getServiceGroups().isEmpty()) {
                authorizationService.deleteByGroupID(groupID);
                return "redirect:/nhom/phan-quyen-nhom?groupIDUpdate=" + groupID;
            }
            // Xử lý cập nhật
            authorizationService.deleteByGroupID(groupID);
            List<Authorization> authorizations = new ArrayList<>();
            for (ServiceGroupDTO.ServiceGroup group : serviceGroupDTO.getServiceGroups()) {
                if (group.getServiceGroupID() != 0) {
                    Authorization authorization = new Authorization();
                    AuthorizationId authorizationId = new AuthorizationId();
                    authorizationId.setGroupID(groupID);
                    authorizationId.setServiceGroupID(group.getServiceGroupID());
                    authorization.setId(authorizationId);
                    authorization.setOnlyView(group.isOnlyView());
                    authorizations.add(authorization);
                }
            }
            // Lưu các thông tin phân quyền vào database
            authorizationService.saveAuthorizations(authorizations);
            model.addAttribute("serviceGroups", serviceGroupDTO.getServiceGroups());

            // Thêm dòng thông báo cập nhật thành công
            redirectAttributes.addFlashAttribute("updateMessage", "Cập nhật thành công!");
            redirectAttributes.addFlashAttribute("backgroundMessage", "alert-success");
            return "redirect:/nhom/phan-quyen-nhom?groupIDUpdate=" + groupID;
        }
    }

    /**
     * Lưu thông tin nhóm mới.
     *
     * @param model     Đối tượng Model để thêm các thuộc tính
     * @param groupName Tên của nhóm mới
     * @return Tên của trang hiển thị sau khi thêm nhóm mới
     */
    @PostMapping("/them-nhom-moi")
    public String themNhomMoi(Model model, @RequestParam("groupName") String groupName) {
        // Tạo đối tượng GroupPermissions mới và đặt tên nhóm
        GroupPermissions groupPermissions = new GroupPermissions();
        groupPermissions.setGroupName(groupName);
        // Lưu nhóm mới vào database
        groupPermissionsService.saveGroup(groupPermissions);

        return "redirect:/nhom/phan-quyen-nhom";
    }

}
