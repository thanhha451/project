package com.spring.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * Đối tượng AuthorizationId đại diện cho ID của Authorization.
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
@Embeddable
public class AuthorizationId implements Serializable {
	
    /**
     * ID của nhóm dịch vụ.
     */
    private int serviceGroupID;
    
    /**
     * ID của nhóm quyền.
     */
    private int groupID;

    /**
     * Constructor mặc định.
     */
    public AuthorizationId() {
    }

    /**
     * Constructor với các tham số.
     * 
     * @param serviceGroupID ID của nhóm dịch vụ
     * @param groupID        ID của nhóm quyền
     */
    public AuthorizationId(int serviceGroupID, int groupID) {
        this.serviceGroupID = serviceGroupID;
        this.groupID = groupID;
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
     * Đặt ID cho nhóm dịch vụ.
     * 
     * @param serviceGroupID ID của nhóm dịch vụ
     */
    public void setServiceGroupID(int serviceGroupID) {
        this.serviceGroupID = serviceGroupID;
    }

    /**
     * Lấy ID của nhóm quyền.
     * 
     * @return ID của nhóm quyền
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Đặt ID cho nhóm quyền.
     * 
     * @param groupID ID của nhóm quyền
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * So sánh hai đối tượng `AuthorizationId` để kiểm tra xem chúng có bằng nhau hay không.
     * 
     * @param o Đối tượng cần so sánh
     * @return true nếu hai đối tượng bằng nhau, ngược lại false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationId that = (AuthorizationId) o;
        return serviceGroupID == that.serviceGroupID && groupID == that.groupID;
    }

    /**
     * Tạo mã băm cho đối tượng `AuthorizationId`.
     * 
     * @return Mã băm của đối tượng
     */
    @Override
    public int hashCode() {
        return Objects.hash(serviceGroupID, groupID);
    }
}
