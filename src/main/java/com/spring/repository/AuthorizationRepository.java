package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.entity.Authorization;

/**
 * Interface Repository để quản lý các thực thể Authorization.
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
public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {

    /**
     * Truy vấn danh sách các quyền theo ID nhóm.
     * 
     * @param groupID ID của nhóm
     * @return danh sách các quyền
     */
    List<Authorization> findByIdGroupID(int groupID);
    
    /**
     * Xóa các quyền theo ID nhóm.
     * 
     * @param groupID ID của nhóm
     */
    @Modifying
    @Query(value = "DELETE FROM AuthorizationTable WHERE groupID = :groupID", nativeQuery = true)
    void deleteByGroupId(@Param("groupID") Integer groupID);
}
