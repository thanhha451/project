package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Đối tượng đại diện cho một nhóm dịch vụ.
 * 
 * @version 1.0
 * @since 06-06-2024
 * 
 * <p>Modification Logs:</p>
 * <pre>
 * DATE        AUTHOR       DESCRIPTION
 * -----------------------------------------
 * 06-06-2024  Le Thanh Ha  Create
 * </pre>
 */
@Entity
public class ServiceGroup {

    /** ID của nhóm dịch vụ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceGroupID;
    
    /** Tên dịch vụ */
    @Column(name = "service", columnDefinition = "nvarchar(255)")
    private String service;
    
    /** Cấp bậc */
    private String hierarchy;
    
    /** Danh sách các quyền */
    @OneToMany(mappedBy = "serviceGroup")
    private List<Authorization> authorizations;

    /**
     * Constructor mặc định.
     */
    public ServiceGroup() {
        super();
    }

    /**
     * Constructor với các tham số.
     * 
     * @param serviceGroupID   ID của nhóm dịch vụ
     * @param service          Tên dịch vụ
     * @param hierarchy        Cấp bậc
     * @param authorizations   Danh sách các quyền
     */
    public ServiceGroup(int serviceGroupID, String service, String hierarchy, List<Authorization> authorizations) {
        super();
        this.serviceGroupID = serviceGroupID;
        this.service = service;
        this.hierarchy = hierarchy;
        this.authorizations = authorizations;
    }

    /**
     * Lấy ID của nhóm dịch vụ.
     *
     * @return ID của nhóm dịch vụ
     */
    public int getServiceGroupID() {
        return serviceGroupID;
    }

    /**
     * Đặt ID của nhóm dịch vụ.
     *
     * @param serviceGroupID ID của nhóm dịch vụ
     */
    public void setServiceGroupID(int serviceGroupID) {
        this.serviceGroupID = serviceGroupID;
    }

    /**
     * Lấy tên dịch vụ.
     *
     * @return Tên dịch vụ
     */
    public String getService() {
        return service;
    }

    /**
     * Đặt tên dịch vụ.
     *
     * @param service Tên dịch vụ
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Lấy cấp bậc của nhóm dịch vụ.
     *
     * @return Cấp bậc
     */
    public String getHierarchy() {
        return hierarchy;
    }

    /**
     * Đặt cấp bậc của nhóm dịch vụ.
     *
     * @param hierarchy Cấp bậc
     */
    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Lấy danh sách các quyền của nhóm dịch vụ.
     *
     * @return Danh sách các quyền
     */
    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    /**
     * Đặt danh sách các quyền của nhóm dịch vụ.
     *
     * @param authorizations Danh sách các quyền
     */
    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
