package com.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Đối tượng Authorization đại diện cho quyền truy cập của một nhóm vào một dịch vụ cụ thể.
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
 */
@Entity
@Table(name = "AuthorizationTable")
public class Authorization implements Serializable {
    
    /**
     * ID của Authorization.
     */
    @EmbeddedId
    private AuthorizationId id;
    
    /**
     * Trạng thái chỉ xem hoặc không.
     */
    private boolean onlyView;

    /**
     * Nhóm dịch vụ liên quan.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "serviceGroupID", insertable = false, updatable = false)
    private ServiceGroup serviceGroup;
    
    /**
     * Nhóm quyền liên quan.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "groupID", insertable = false, updatable = false)
    private GroupPermissions groupPermissions;
    
    /**
     * Constructor mặc định.
     */
    public Authorization() {
        super();
    }
    
    /**
     * Constructor với các tham số.
     * 
     * @param id                ID của Authorization
     * @param onlyView          chỉ xem hoặc không
     * @param serviceGroup      nhóm dịch vụ
     * @param groupPermissions  nhóm quyền
     */
    public Authorization(AuthorizationId id, boolean onlyView, ServiceGroup serviceGroup,
            GroupPermissions groupPermissions) {
        super();
        this.id = id;
        this.onlyView = onlyView;
        this.serviceGroup = serviceGroup;
        this.groupPermissions = groupPermissions;
    }
    
    /**
     * Lấy ID của Authorization.
     * 
     * @return ID của Authorization
     */
    public AuthorizationId getId() {
        return id;
    }

    /**
     * Đặt ID cho Authorization.
     * 
     * @param id ID của Authorization
     */
    public void setId(AuthorizationId id) {
        this.id = id;
    }

    /**
     * Lấy trạng thái chỉ xem hoặc không.
     * 
     * @return true nếu chỉ xem, ngược lại false
     */
    public boolean isOnlyView() {
        return onlyView;
    }

    /**
     * Đặt trạng thái chỉ xem hoặc không.
     * 
     * @param onlyView true nếu chỉ xem, ngược lại false
     */
    public void setOnlyView(boolean onlyView) {
        this.onlyView = onlyView;
    }

    /**
     * Lấy nhóm dịch vụ liên quan.
     * 
     * @return Nhóm dịch vụ
     */
    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }

    /**
     * Đặt nhóm dịch vụ liên quan.
     * 
     * @param serviceGroup Nhóm dịch vụ
     */
    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    /**
     * Lấy nhóm quyền liên quan.
     * 
     * @return Nhóm quyền
     */
    public GroupPermissions getGroupPermissions() {
        return groupPermissions;
    }

    /**
     * Đặt nhóm quyền liên quan.
     * 
     * @param groupPermissions Nhóm quyền
     */
    public void setGroupPermissions(GroupPermissions groupPermissions) {
        this.groupPermissions = groupPermissions;
    }
}
