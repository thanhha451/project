<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="col-3 p-2">
	<ul class="nav nav-tabs">
		<li class="nav-item ${buttonUserGroup }">
			<a class="nav-link active ${backgroundUser } " href="/Mock/nguoi-dung/thong-tin-nguoi-dung" id="actionUser">
				Người sử dụng
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link active ${backgroundGroup}" href="/Mock/nhom/phan-quyen-nhom" id="actionGroup">
				Phân quyền nhóm
			</a>
		</li>
	</ul>
	<div class="tab-content border  border-3 border-dark" style="padding: 10px 8px 11px 8px">
		<c:choose>
			<c:when test="${listUser == null }">
				<h5 class="p-2 border border-bottom-0 border-3 border-dark  mb-0">
					Danh sách nhóm phân quyền
				</h5>
			</c:when>
		</c:choose>
		<div id="user" class="tab-pane fade show active border border-3 border-dark " >
			<div class="list-group ${listUser != null ? 'listUser' : 'listGroup'}">
				<c:forEach var="item" items="${listUser != null ? listUser : listGroup}">
					<c:choose>
						<c:when test="${listUser != null}">
							<a href="<c:choose>
							            <c:when test="${not empty school}">
							                /Mock/nguoi-dung/thong-tin-nguoi-dung?userIDUpdate=${item.userID}&amp;searchSchool=${school}
							            </c:when>
							            <c:otherwise>
							                /Mock/nguoi-dung/thong-tin-nguoi-dung?userIDUpdate=${item.userID}
							            </c:otherwise>
							        </c:choose>"
							    class="list-group-item list-group-item-action ${item.userID == userID ? 'bg-primary bg-gradient' : ''}">
							    ${item.name}
							</a>
						</c:when>
						<c:otherwise>
							<a href="/Mock/nhom/phan-quyen-nhom?groupIDUpdate=${item.groupID}"
								class="list-group-item list-group-item-action">${item.groupName}
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- Thêm các mục trống nếu danh sách có ít hơn 10 phần tử -->
				<c:choose>
					<c:when test="${listUser != null}">
						<c:if test="${fn:length(listUser) < 13 }">
							<c:forEach var="i" begin="1" end="${12 - fn:length(listUser)}">
								<a class="list-group-item list-group-item-action">&nbsp;</a>
							</c:forEach>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${fn:length(listGroup) < 12 or fn:length(listGroup) < 12}">
							<c:forEach var="i" begin="1" end="${11 - fn:length(listGroup)}">
								<a class="list-group-item list-group-item-action">&nbsp;</a>
							</c:forEach>
						</c:if>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
	<div class="align-items-center row ">
		<div class=col-12>
			<form action="/Mock/nguoi-dung/tim-kiem" id="form-1" class="col-12">
				<div class="row align-items-center">
					<div class="col-7">
						<select id="inputSchool" name="school" class="form-select mt-3 mb-3 border border-3 border-dark" aria-label="Default select example">
							<option value="">-Tất cả đơn vị--</option>
							<option value="Trường mầm non ABC"
								<c:if test="${school == 'Trường mầm non ABC'}">selected</c:if>>
								Trường mầm non ABC
							</option>
							<option value="Trường mầm non Cây Xanh"
								<c:if test="${school == 'Trường mầm non Cây Xanh'}">selected</c:if>>
								Trường mầm non Cây Xanh
							</option>
							<option value="Trường mầm non Hoa Mai"
								<c:if test="${school == 'Trường mầm non Hoa Mai'}">selected</c:if>>
								Trường mầm non Hoa Mai
							</option>
						</select>
					</div>
					<div class="col-4   ">
						<button class="form-control">Search</button>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>