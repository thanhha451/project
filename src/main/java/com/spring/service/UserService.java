package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.UserInformation;
import com.spring.repository.UserRepository;

/**
 * Lớp dịch vụ để quản lý thông tin người dùng.
 * 
 * @version 1.0
 * @since 06-06-2024
 * 
 * <p>Modification Logs:</p>
 * <pre>
 * DATE        AUTHOR            DESCRIPTION
 * -------------------------------------------
 * 06-06-2024  Le Thanh Ha       Create
 * </pre>
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Lưu thông tin người dùng vào cơ sở dữ liệu.
     *
     * @param userInformation Thông tin người dùng cần được lưu
     */
    @Transactional
    public void saveUser(UserInformation userInformation) {
        userRepository.save(userInformation);
    }

    /**
     * Cập nhật thông tin người dùng trong cơ sở dữ liệu.
     *
     * @param userInformation Thông tin người dùng cần được cập nhật
     */
    public void updateUser(UserInformation userInformation) {
        // Logic để cập nhật người dùng
        userRepository.save(userInformation);
    }

    /**
     * Lấy danh sách tất cả người dùng từ cơ sở dữ liệu.
     *
     * @return Danh sách tất cả người dùng
     */
    @Transactional
    public List<UserInformation> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Tìm kiếm người dùng dựa trên ID.
     *
     * @param userID ID của người dùng cần tìm
     * @return Thông tin người dùng có ID chỉ định
     */
    @Transactional
    public UserInformation findByID(int userID) {
        return userRepository.findById(userID).orElse(null);
    }

    /**
     * Tìm kiếm người dùng dựa trên tên đăng nhập (username).
     *
     * @param userName Tên đăng nhập của người dùng cần tìm
     * @return Danh sách người dùng có tên đăng nhập chỉ định
     */
    @Transactional
    public List<UserInformation> findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    /**
     * Xóa thông tin người dùng khỏi cơ sở dữ liệu.
     *
     * @param userID ID của người dùng cần xóa
     */
    @Transactional
    public void deleteUser(int userID) {
        userRepository.deleteById(userID);;
    }

    /**
     * Tìm kiếm người dùng dựa trên trường/đơn vị.
     *
     * @param school Trường/đơn vị của người dùng cần tìm
     * @return Danh sách người dùng thuộc trường/đơn vị chỉ định
     */
    @Transactional
    public List<UserInformation> findUserBySchool(String school) {
        return userRepository.findBySchool(school);
    }
}
