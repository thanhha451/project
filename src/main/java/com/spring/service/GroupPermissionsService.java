package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.GroupPermissions;
import com.spring.repository.GroupPermissionsRepository;

/**
 * Lớp dịch vụ để quản lý nhóm phân quyền.
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
public class GroupPermissionsService {

    @Autowired
    private GroupPermissionsRepository groupPermissionsRepository;

    /**
     * Lấy danh sách tất cả nhóm phân quyền.
     *
     * @return Danh sách tất cả nhóm phân quyền
     */
    @Transactional
    public List<GroupPermissions> getListGroup() {
        return groupPermissionsRepository.findAll();
    }

    /**
     * Lưu thông tin một nhóm mới.
     *
     * @param groupPermissions Thông tin của nhóm cần lưu
     */
    @Transactional
    public void saveGroup(GroupPermissions groupPermissions) {
        groupPermissionsRepository.save(groupPermissions);
    }

    /**
     * Xóa một nhóm phân quyền theo GroupID.
     *
     * @param groupID ID của nhóm cần xóa
     */
    @Transactional
    public void deleteGroupByGroupID(int groupID) {
        groupPermissionsRepository.delete(findGroupByID(groupID));
    }

    /**
     * Lấy thông tin của một nhóm dựa trên ID.
     *
     * @param id ID của nhóm cần lấy thông tin
     * @return Thông tin của nhóm phân quyền, hoặc null nếu không tìm thấy
     */
    @Transactional
    public GroupPermissions findGroupByID(int id) {
        return groupPermissionsRepository.findById(id).orElse(null);
    }

    /**
     * Lấy danh sách tất cả các nhóm trừ nhóm có ID chỉ định.
     *
     * @param id ID của nhóm cần loại trừ khỏi danh sách
     * @return Danh sách các nhóm trừ nhóm có ID chỉ định
     */
    @Transactional
    public List<GroupPermissions> findAllGroupByNotID(int id) {
        return groupPermissionsRepository.findAllGroupByNotID(id);
    }
}
