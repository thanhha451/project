package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.dto.ServiceAndViewDTO;
import com.spring.entity.ServiceGroup;

/**
 * Interface Repository để quản lý các thực thể ServiceGroup.
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
@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroup, Integer> {
	
	/**
	 * Tìm kiếm chức năng của nhóm theo ID nhóm.
	 * 
	 * @param groupID ID của nhóm
	 * @return danh sách các chức năng của nhóm
	 */
	@Query(value=" SELECT s.serviceGroupID AS serviceGroupID, s.service AS service,s.hierarchy AS hierarchy, a.onlyView AS onlyView "
			+ "FROM ServiceGroup s "
			+ "JOIN AuthorizationTable a ON a.serviceGroupID = s.serviceGroupID "
			+ "JOIN GroupPermissions g ON a.groupID = g.groupID "
			+ "WHERE g.groupID = :groupID", nativeQuery = true)
	List<ServiceAndViewDTO> findServiceGroupByGroupID(@Param("groupID") int  groupID);
	
	/**
	 * Tìm kiếm những chức năng không thuộc nhóm theo ID nhóm.
	 * 
	 * @param groupID ID của nhóm
	 * @return danh sách các chức năng không thuộc nhóm
	 */
	@Query(value="SELECT DISTINCT s.* "
			+ "FROM ServiceGroup s "
			+ "LEFT JOIN AuthorizationTable a ON a.serviceGroupID = s.serviceGroupID "
			+ "LEFT JOIN GroupPermissions g ON g.groupID = a.groupID "
			+ "WHERE s.serviceGroupID NOT IN ( "
			+ "    SELECT serviceGroupID  "
			+ "    FROM AuthorizationTable  "
			+ "    WHERE groupID = :groupID )", nativeQuery = true)
	List<ServiceGroup> findServiceGroupByNotGroupID(@Param("groupID") int  groupID);		
	
}
