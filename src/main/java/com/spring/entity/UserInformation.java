package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Đối tượng đại diện cho thông tin của một người dùng trong hệ thống.
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
public class UserInformation {

    /** ID của người dùng */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    
    /** Tên đăng nhập của người dùng */
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;
    
    /** Mật khẩu của người dùng */
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    
    /** Họ và tên của người dùng */
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Họ tên không được để trống")
    private String name;
    
    /** Chức vụ của người dùng */
    @Column(name = "position", columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Chức vụ không được để trống")
    private String position;
    
    /** Trường/đơn vị của người dùng */
    @Column(name = "school", columnDefinition = "nvarchar(255)")
    @NotBlank(message = "Trường/đơn vị không được để trống")
    private String school;

    /** Nhóm quyền của người dùng */
    @ManyToOne
    @JoinColumn(name = "role")
    private GroupPermissions groupPermissions;

    /**
     * Constructor mặc định.
     */
    public UserInformation() {
        super();
    }

    /**
     * Constructor với các tham số.
     * 
     * @param userID            ID của người dùng
     * @param userName          Tên đăng nhập của người dùng
     * @param password          Mật khẩu của người dùng
     * @param name              Họ và tên của người dùng
     * @param position          Chức vụ của người dùng
     * @param school            Trường/đơn vị của người dùng
     * @param groupPermissions  Nhóm quyền của người dùng
     */
    public UserInformation(int userID, String userName, String password, String name, String position, String school,
            GroupPermissions groupPermissions) {
        super();
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.position = position;
        this.school = school;
        this.groupPermissions = groupPermissions;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public GroupPermissions getGroupPermissions() {
		return groupPermissions;
	}

	public void setGroupPermissions(GroupPermissions groupPermissions) {
		this.groupPermissions = groupPermissions;
	}

    // Getters và setters
}
