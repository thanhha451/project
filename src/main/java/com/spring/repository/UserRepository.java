package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.entity.UserInformation;

/**
 * Interface Repository để quản lý các thực thể UserInformation.
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
@Repository
public interface UserRepository extends JpaRepository<UserInformation, Integer> {

    /**
     * Tìm kiếm người dùng theo tên đăng nhập.
     * 
     * @param userName tên đăng nhập của người dùng
     * @return danh sách người dùng có tên đăng nhập tương ứng
     */
    @Query(value="SELECT * FROM UserInformation WHERE userName = :userName", nativeQuery = true)
    List<UserInformation> findUserByUserName(@Param("userName") String  userName);
    
    /**
     * Tìm kiếm người dùng theo Trường/đơn vị.
     * 
     * @param school Trường/đơn vị của người dùng
     * @return danh sách người dùng thuộc Trường/đơn vị cụ thể
     */
    List<UserInformation> findBySchool(String school);
}
