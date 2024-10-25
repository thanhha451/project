package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.entity.GroupPermissions;

/**
 * Interface Repository để quản lý các thực thể GroupPermissions.
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
public interface GroupPermissionsRepository extends JpaRepository<GroupPermissions, Integer> {

    /**
     * Truy vấn tất cả các nhóm trừ nhóm có ID nhất định.
     * 
     * @param groupID ID của nhóm cần loại trừ
     * @return danh sách các nhóm trừ nhóm có ID nhất định
     */
    @Query(value="SELECT * FROM GroupPermissions WHERE NOT groupID = :groupID", nativeQuery = true)
    List<GroupPermissions> findAllGroupByNotID(@Param("groupID") int groupID);
}
