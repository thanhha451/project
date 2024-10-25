<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tiêu đề của trang của bạn</title>
<link href="/Mock/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/Mock/resources/css/custom.css" rel="stylesheet">
<link href="/Mock/resources/font-awesome-4.7.0/css/all.min.css" rel="stylesheet">

<script src="/Mock/resources/js/jquery-3.7.1.min.js"
	type="text/javascript"></script>
<script src="/Mock/resources/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row border border-dark border-3">
			<div class="col-12">
				<h5>Quản trị phân quyền hệ thống</h5>
			</div>
		</div>
		<div class="row p-3 border border-top-0 border-dark border-3">
			<!-- hiển thị list Item và link page-->
			 <jsp:include page="${pageList}" />
			
			<!-- role thông tin người dùng or phân quyền -->
			 <jsp:include page="${pageContent}" />
			
		</div>
		<!--hiển thị thông báo   -->
		<c:if test="${not empty updateMessage}">
			<div class="alert ${backgroundMessage } fade show" id="alertMessage"
				role="alert">
				<span>${updateMessage}</span>
			</div>
		</c:if>
	</div>
	<script type="text/javascript" src="/Mock/resources/js/customLayout.js"></script>
	<script type="text/javascript" src="/Mock/resources/js/customLayoutGroup.js"></script>
</body>
</html>
