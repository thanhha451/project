<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String groupIDParam = request.getParameter("groupIDUpdate");
    String actionURL = "/Mock/nhom/luu-thong-tin";
    if (groupIDParam != null && !groupIDParam.isEmpty()) {
        actionURL += "?groupIDUpdate=" + groupIDParam;
    }
%>
<div class="col-9 ">
	<form action="<%= actionURL %>" method="post">
	<input type="hidden" name="action" id="action" value="">
		<div class="row p-2">
			<div class="col-12 text-divider border border-dark border-3 p-2">
				<h5 class="tittle">Phân quyền chức năng theo nhóm</h5>
				<div class="row">
					<div class="col-6 mt-3 ps-3">
						<h6>Các chức năng theo hệ thống</h6>
						   <div class="table-container">
							<table id="serviceOfSystem" class="table table-striped table-bordered bordered-3 border-dark">
								<thead class="table-secondary">
									<tr>
										<th class="col-8">Tên chức năng</th>
										<th>Phân cấp</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="service" items="${listServiceOfSystem}">
										<tr>
											<td class="col-2"><input type="hidden" value="${service.serviceGroupID }"/>${service.service}</td>
											<td class="col-10">${service.hierarchy}</td>
										</tr>
									</c:forEach>
									<c:choose>
										<c:when test="${fn:length(listServiceGroup) < 12}">
											<c:forEach var="i" begin="1" end="${11 - fn:length(listServiceOfSystem)}">
												<tr>
													<td class="col-2">&nbsp;</td>
													<td class="col-10">&nbsp;</td>
												</tr>
											</c:forEach>
										</c:when>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-6 mt-3 " style="padding-bottom: 6px">
						<h6>Phân quyền nhóm: ${group.groupName}</h6>
						   <div class="table-container " >
					            <table id="serviceOfGroup" class="table table-striped table-bordered border-3 border-dark justify-content-center" style="justify-content: center">
					                <thead class="table-secondary">
					                    <tr>
					                        <th class="col-3">Phân cấp</th>
					                        <th class="col-6">Tên chức năng</th>
					                        <th class="col-3">Chỉ xem</th>
					                    </tr>
					                </thead>
					                <tbody>
					                    <c:forEach var="serviceOfGroup" items="${listServiceOfGroup}" varStatus="loop">
										    <tr>
										        <td class="col-3">
										            <input type="hidden" name="serviceGroups[${loop.index}].serviceGroupID" value="${serviceOfGroup.serviceGroupID}" />
										            ${serviceOfGroup.hierarchy }
										        </td>
										        <td class="col-6">${serviceOfGroup.service }</td>
										        <td class="col-3">
											        <label class="container">
													  <input <c:if test="${serviceOfGroup.onlyView == true}">checked</c:if> name="serviceGroups[${loop.index}].onlyView"  type="checkbox" >
													  <span class="checkmark"></span>
													</label>
										        </td>
										    </tr>
										</c:forEach>
										<c:choose>
											<c:when test="${fn:length(listServiceOfGroup) < 12}">
												<c:forEach var="i" begin="1" end="${11 - fn:length(listServiceOfGroup)}">
													<tr>
														<td class="col-3">&nbsp;</td>
														<td class="col-6">&nbsp;</td>
														<td class="col-3">&nbsp;</td>
													</tr>
												</c:forEach>
											</c:when>
										</c:choose>
					                </tbody>
					            </table>
					      </div>
					</div>
				</div>
			</div>
		</div>
	<div class="row justify-content-end"   style="margin-top: 12px">
			<div class="col-9   ">
				<div class="row  justify-content-end ">
					<div class="col-auto ">
						<button type="button" class="btn btn-secondary" id="openFormBtn">Thêm nhóm mới</button>
					</div>
					<div class="col-auto ">
						<button class="btn btn-secondary" onclick="setAction('update')">Cập nhật nhóm</button>
					</div>
					<div class="col-auto ">
						<button class="btn btn-secondary" onclick="setAction('delete')">Xóa nhóm</button>
					</div>
					<div class="col-auto">
						<button type="button" class="btn btn-secondary close">
							<i class="fa-regular fa-circle-xmark"></i> Đóng lại
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- Hidden contextMenu left -->
	<div id="contextMenu">
		<ul>
			<li id="moveOne">Chuyển</li>
			<li id="moveAll">Chuyển tất cả sang</li>
			<li id="moveBackOne">Chuyển lại</li>
			<li id="moveBackAll">Chuyển lại tất cả</li>
		</ul>
	</div>
	<!-- xác nhật đóng  -->
	<div class="confirmClose border row" style="display: none">
	    <h6>Xác nhận đóng màn hình</h6>
	    <div class="col-6">
	        <button class="btn btn-secondary confirm-button" onclick="window.location.href = '/Mock/';">Đồng ý</button>
	    </div>
	    <div class="col-6">
	        <button class="btn btn-secondary cancel-button">Hủy</button>
	    </div>
	</div>

	<!-- Modal -->
    <div class="modal fade" id="addNewModal" tabindex="-1"  aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title col-11" id="addNewModalLabel">Thêm mới nhóm</h5>
                    <button id="closeModalAddGroupnew" type="button" class=" col-1" >
                        <i class="fa-solid fa-xmark"></i>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/Mock/nhom/them-nhom-moi" method="post" id="addNewForm">
                        <div class="form-group">
                            <label class="fw-bold" for="name ">Tên nhóm mới:</label>
                            <input type="text" class="form-control" id="name" name="groupName" required>
                        </div>
                        <button type="submit" class="btn btn-secondary mt-2">Xác nhận</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
</div>