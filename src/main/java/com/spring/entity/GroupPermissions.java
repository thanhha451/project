package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Đối tượng đại diện cho các nhóm quyền trong hệ thống.
 * 
 * @version 1.0
 * @since 06-06-2024
 * 
 * <p>Modification Logs:</p>
 * <pre>
 * DATE         AUTHOR      DESCRIPTION
 * --------------------------------------------------
 * 06-06-2024  Le Thanh Ha  Create
 * </pre>
 */
@Entity
public class GroupPermissions {

    /** ID của nhóm quyền */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupID;

    /** Tên của nhóm quyền */
    @Column(name = "groupName", columnDefinition = "nvarchar(255)")
    private String groupName;

    /** Danh sách các quyền của nhóm */
    @OneToMany(mappedBy = "groupPermissions")
    private List<Authorization> authorizations;

    /**
     * Constructor mặc định.
     */
    public GroupPermissions() {
        super();
    }

    /**
     * Constructor với các tham số.
     *
     * @param groupID       ID của nhóm quyền
     * @param groupName     Tên của nhóm quyền
     * @param authorizations   Danh sách các quyền của nhóm
     */
    public GroupPermissions(int groupID, String groupName, List<Authorization> authorizations) {
        super();
        this.groupID = groupID;
        this.groupName = groupName;
        this.authorizations = authorizations;
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
     * Đặt ID của nhóm quyền.
     *
     * @param groupID ID của nhóm quyền
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * Lấy tên của nhóm quyền.
     *
     * @return Tên của nhóm quyền
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Đặt tên của nhóm quyền.
     *
     * @param groupName Tên của nhóm quyền
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Lấy danh sách các quyền của nhóm.
     *
     * @return Danh sách các quyền của nhóm
     */
    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    /**
     * Đặt danh sách các quyền của nhóm.
     *
     * @param authorizations Danh sách các quyền của nhóm
     */
    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
