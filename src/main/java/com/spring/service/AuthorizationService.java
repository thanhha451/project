package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Authorization;
import com.spring.repository.AuthorizationRepository;

/**
 * AuthorizationService để quản lý các quyền truy cập và phân quyền nhóm.
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
public class AuthorizationService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    /**
     * Lưu danh sách các quyền truy cập.
     *
     * @param authorizations Danh sách các quyền truy cập cần lưu
     */
    public void saveAuthorizations(List<Authorization> authorizations) {
        authorizationRepository.saveAll(authorizations);
    }

    /**
     * Lấy danh sách các quyền truy cập theo ID nhóm.
     *
     * @param groupID ID của nhóm
     * @return Danh sách các quyền truy cập thuộc nhóm đã chỉ định
     */
    @Transactional
    public List<Authorization> findAllByGroupID(int groupID) {
        return authorizationRepository.findByIdGroupID(groupID);
    }

    /**
     * Xóa các quyền truy cập theo ID nhóm.
     *
     * @param groupID ID của nhóm cần xóa quyền truy cập
     */
    @Transactional
    public void deleteByGroupID(int groupID) {
        authorizationRepository.deleteByGroupId(groupID);
    }
}
