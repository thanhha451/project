package com.spring.dto;

import java.util.List;

/**
 * ServiceGroupDTO là một lớp chứa danh sách các nhóm dịch vụ.
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
public class ServiceGroupDTO {

    /**
     * Danh sách các nhóm dịch vụ.
     */
    private List<ServiceGroup> serviceGroups;

    /**
     * ServiceGroup là một lớp lồng bên trong ServiceGroupDTO,
     * đại diện cho một nhóm dịch vụ cụ thể.
     */
    public static class ServiceGroup {
        /**
         * ID của nhóm dịch vụ.
         */
        private int serviceGroupID;
        
        /**
         * Biến chỉ định liệu chỉ có quyền xem.
         */
        private boolean onlyView;
        
        /**
         * Lấy trạng thái quyền xem của nhóm dịch vụ.
         *
         * @return true nếu chỉ có quyền xem, ngược lại false
         */
        public boolean isOnlyView() {
            return onlyView;
        }

        /**
         * Đặt trạng thái quyền xem của nhóm dịch vụ.
         *
         * @param onlyView giá trị true nếu chỉ có quyền xem, ngược lại false
         */
        public void setOnlyView(boolean onlyView) {
            this.onlyView = onlyView;
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
    }

    /**
     * Lấy danh sách các nhóm dịch vụ.
     *
     * @return danh sách các nhóm dịch vụ
     */
    public List<ServiceGroup> getServiceGroups() {
        return serviceGroups;
    }

    /**
     * Đặt danh sách các nhóm dịch vụ.
     *
     * @param serviceGroups danh sách các nhóm dịch vụ
     */
    public void setServiceGroups(List<ServiceGroup> serviceGroups) {
        this.serviceGroups = serviceGroups;
    }
}
