package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.ServiceAndViewDTO;
import com.spring.entity.ServiceGroup;
import com.spring.repository.ServiceGroupRepository;

/**
 * Lớp dịch vụ để quản lý các nhóm dịch vụ.
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
public class ServiceGroupService {

    @Autowired
    private ServiceGroupRepository serviceGroupRepository;

    /**
     * Lấy danh sách tất cả các nhóm dịch vụ.
     *
     * @return Danh sách tất cả các nhóm dịch vụ
     */
    @Transactional
    public List<ServiceGroup> findAllServiceGroup() {
        return serviceGroupRepository.findAll();
    }

    /**
     * Lấy danh sách các nhóm dịch vụ dựa trên ID của nhóm phân quyền.
     *
     * @param groupID ID của nhóm phân quyền
     * @return Danh sách các nhóm dịch vụ thuộc nhóm phân quyền có ID chỉ định
     */
    @Transactional
    public List<ServiceAndViewDTO> findServiceGroupByGroupID(int groupID) {
        return serviceGroupRepository.findServiceGroupByGroupID(groupID);
    }

    /**
     * Lấy danh sách các nhóm dịch vụ mà không thuộc nhóm phân quyền có ID chỉ định.
     *
     * @param groupID ID của nhóm phân quyền
     * @return Danh sách các nhóm dịch vụ không thuộc nhóm phân quyền có ID chỉ định
     */
    @Transactional
    public List<ServiceGroup> findServiceGroupByNotGroupID(int groupID) {
        return serviceGroupRepository.findServiceGroupByNotGroupID(groupID);
    }
}
