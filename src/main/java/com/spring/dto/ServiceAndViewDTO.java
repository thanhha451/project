package com.spring.dto;

/**
 * ServiceAndViewDTO là một giao diện (interface) mô tả các phương thức liên quan đến
 * dịch vụ và quyền xem của người dùng trong một hệ thống.
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
 * @see SomeOtherRelatedClass
 * @see AnotherRelatedClass
 * 
 */
public interface ServiceAndViewDTO {

    /**
     * Lấy ID của nhóm dịch vụ.
     *
     * @return ID của nhóm dịch vụ
     */
    int getServiceGroupID();

    /**
     * Lấy tên dịch vụ.
     *
     * @return tên của dịch vụ
     */
    String getService();

    /**
     * Lấy thông tin về cấp bậc hoặc hệ thống phân cấp của dịch vụ.
     *
     * @return chuỗi mô tả hệ thống phân cấp của dịch vụ
     */
    String getHierarchy();
    
    /**
     * Kiểm tra xem dịch vụ chỉ có quyền xem hay không.
     *
     * @return true nếu dịch vụ chỉ có quyền xem, ngược lại false
     */
    boolean getOnlyView();
}
