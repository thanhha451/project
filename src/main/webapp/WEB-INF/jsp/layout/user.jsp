
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String userIDParam = request.getParameter("userIDUpdate");
	String searchSchool = request.getParameter("searchSchool");
    String actionURL = "/Mock/nguoi-dung/luu-thong-tin";
    if (userIDParam != null && !userIDParam.isEmpty() && searchSchool == null) {
        actionURL += "?userIDUpdate=" + userIDParam;
    } else if(userIDParam != null && !userIDParam.isEmpty() && searchSchool != null){
    	actionURL += "?userIDUpdate=" + userIDParam + "&searchSchool=" + searchSchool;
    }
%>
<div class="col-9  user">
<form action="<%= actionURL %>" method="post">
		<input type="hidden" name="action" id="action" value="">
		<input type="hidden" class="userID" name="userIDUpdate" id="action" value="${userID }">
		<div class="row p-2">
			<div class="col-12 text-divider border border-dark border-3 p-2">
				<h6 class="tittle">Thông tin người sử dụng </h6>
				<div class="row mt-3 ">
					<div class="col-6 ">
						<div class="form-group row">
							<label for="inputUser" class="col-sm-5 col-form-label text-start px-5">Tên đăng nhập</label>
							<div class="col-sm-7">
								<input type="text" maxlength="25" value="${user.userName }" class="form-control" name="userName" id="inputUser">
								<c:if test="${not empty userNameError}">
				                    <div class="error">${userNameError}</div>
				                </c:if>
							</div>
						</div>
						<div class="form-group row mt-2">
							<label for="inputPassword" class="col-5 col-form-label text-start px-5">Mật khẩu</label>
							<div class="col-sm-7">
								<input type="password" value="${user.password }" maxlength="25" class="form-control" name="password" id="inputPassword">
								 <c:if test="${not empty passwordError}">
				                    <div class="error">${passwordError}</div>
				                </c:if>
							</div>
						</div>
						<div class="form-group row mt-2">
							<label for="inputPassword2"
								class="col-5 col-form-label text-start px-5">Nhắc lại mật khẩu</label>
							<div class="col-sm-7">
								<input type="password" value="${password2!= null ? password2 : user.password}" maxlength="25" class="form-control" name="password2" id="inputPassword2">
								<c:if test="${not empty password2Error}">
				               <div class="error">${password2Error}</div>
				         </c:if>
							</div>
						</div>
						<div class="form-group row mt-2">
							<label for="inputName" class="col-5 col-form-label text-start px-5">Họ và tên</label>
							<div class="col-sm-7">
								<input type="text" value="${user.name }"  maxlength="25" class="form-control" name="name" id="inputName">
								 <c:if test="${not empty nameError}">
				                    <div class="error">${nameError.defaultMessage}</div>
				                 </c:if>
							</div>
						</div>
						<div class="form-group row mt-2">
							<label for="inputPosition" class="col-5 col-form-label text-start px-5">Chức vụ</label>
							<div class="col-sm-7">
								<select id="inputPosition" name="position" class="form-select" aria-label="Default select example">
									<option value="">Chọn chức vụ</option>
									<option value="hiệu trưởng" <c:if test="${user.position == 'hiệu trưởng'}">selected</c:if>>Hiệu trưởng</option>
									<option value="phó hiệu trưởng" <c:if test="${user.position == 'phó hiệu trưởng'}">selected</c:if>>Phó hiệu trưởng</option>
									<option value="giáo viên" <c:if test="${user.position == 'giáo viên'}">selected</c:if>>Giáo viên</option>
									<option value="IT" <c:if test="${user.position == 'IT'}">selected</c:if>>IT</option>
								</select>
								<c:if test="${not empty positionError}">
				                    <div class="error">${positionError.defaultMessage}</div>
				                </c:if>
							</div>
						</div>
						<div class="form-group row mt-2">
							<label for="inputSchool" class="col-5 col-form-label text-start px-5">Trường/Đơn vị</label>
							<div class="col-sm-7">
								<select id="inputSchool" name="school" class="form-select" aria-label="Default select example">
									<option value="">Chọn trường/đơn vị</option>
									<option value="Trường mầm non ABC" <c:if test="${user.school == 'Trường mầm non ABC'}">selected</c:if>>Trường mầm non ABC</option>
			                        <option value="Trường mầm non Cây Xanh" <c:if test="${user.school == 'Trường mầm non Cây Xanh'}">selected</c:if>>Trường mầm non Cây Xanh</option>
			                        <option value="Trường mầm non Hoa Mai" <c:if test="${user.school == 'Trường mầm non Hoa Mai'}">selected</c:if>>Trường mầm non Hoa Mai</option>
								</select>
								 <c:if test="${not empty schoolError}">
				                    <div class="error">${schoolError.defaultMessage}</div>
				                </c:if>
							</div>
						</div>
					</div>
	
					<div class="col-6">
						<div class=row>
							<div class="col-6  " style="padding-left: 7rem">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> 
									<label class="form-check-label" for="flexCheckDefault"> Quản trị hệ thống </label>
								</div>
	
								<div class="form-check mt-3">
									<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault"> Khóa tài khoản </label>
								</div>
								<div class="form-check mt-3">
									<input class="form-check-input" type="checkbox" value="" id="showPassword"> 
									<label class="form-check-label" for="flexCheckDefault">Hiện PassWord </label>
								</div>
	
								<div class="form-check mt-3">
									<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> 
									<label class="form-check-label" for="flexCheckDefault"> Cập nhật học phí </label>
								</div>
							</div>
							<div class=col-6>
								<div class="col-12 border border-dark  border-3 p-1">
									<h6>Lớp phụ trách</h6>
								</div>
								<div class="col-12 border border-top-0 border-dark  border-3" style="height: 100%"></div>
								<div class="col-12 border border-top-0 border-dark  border-3" style="height: 20%"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row p-2 mt-2">
			<div class="col-12 text-divider border border-dark border-3 p-2">
				<h6 class="tittle">Nhóm quuyền sử dụng</h6>
				<div class="row px-4 mt-3 align-items-center">
					<div class="col-5  table-not-group text-divider border border-dark border-3 ">
						<h6 class="tittle">Không thuộc nhóm</h6>
						<div class="table-group ">
							<table class="table custom-table table-user table-not-in-group  table-striped table-hover">
								<thead class="table-secondary">
									<tr>
										<th class=col-2 scope="col"><div>ID</div></th>
										<th class=col-10 scope="col"><div>Tên nhóm quyền</div></th>
									</tr>
								</thead>
								<tbody class="table-body-container">
									<c:forEach var="permission" items="${listGroup}">
										<tr>
											<td class="col-2">${permission.groupID}</td>
											<td class="col-10">${permission.groupName}</td>
										</tr>
									</c:forEach>
									<c:choose>
										<c:when test="${fn:length(listGroup) < 3}">
											<c:forEach var="i" begin="1" end="${3 - fn:length(listGroup)}">
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
					<div class="col-2">
				        <button id="moveRightBtn" type="button" class="btn btn-light form-control mb-4 w-100">
				            <i class="fas fa-chevron-right"></i> 
				            <i class="fas fa-chevron-right"></i>
				        </button>
				        <button id="moveLeftBtn" type="button" class="btn btn-light w-100">
				            <i class="fas fa-chevron-left"></i> 
				            <i class="fas fa-chevron-left"></i>
				        </button>
				         <c:if test="${not empty groupIDError}">
				               <div class="error">${groupIDError}</div>
				         </c:if>
			        </div>
					<div class="col-5 text-divider border border-dark border-3 ">
						<h6 class="tittle">Thuộc nhóm</h6>
						<table class="table table-in-group table-user  table-striped table-hover">
							<thead class="table-secondary">
								<tr>
									<th scope="col" class="col-2">ID</th>
									<th scope="col" class="col-10">Tên nhóm quyền</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${user.groupPermissions.groupID != null ? user.groupPermissions.groupID :  '&nbsp;'}</td>
									<td>${user.groupPermissions.groupName != null ? user.groupPermissions.groupName :  '&nbsp;'}</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
						<input value="${user.groupPermissions.groupID }" type="hidden" id="group" name="groupID" >
					</div>
				</div>
	
			</div>
		</div>
		<div class="row justify-content-end " style="margin-top: 12px">
			<div class="col-8  ">
				<div class="row  justify-content-end ">
					<div class="col-auto">
						<button type="submit" class="btn btn-secondary"
							onclick="setAction('add')">Thêm mới</button>
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-secondary"
							onclick="setAction('update')">Cập nhật</button>
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-secondary"
							onclick="setAction('delete')">Xóa</button>
					</div>
					<div class="col-auto">
						<button type="button" class="btn btn-secondary"
							onclick="window.location.href = '/Mock/';">
							<i class="fa-regular fa-circle-xmark"></i> Đóng lại
						</button>
					</div>

				</div>
			</div>
		</div>
	</form>
</div>
