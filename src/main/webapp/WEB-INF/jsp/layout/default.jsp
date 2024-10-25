<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Centered Link with Background</title>
<style>
    body, html {
        height: 100%;
        margin: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #f0f0f0; /* Màu nền của trang */
    }

    .centered-link-container {
        background-color: graytext; /* Màu nền của phần nội dung */
        padding: 20px; /* Khoảng cách giữa nội dung và khung */
        border-radius: 10px; /* Bo tròn viền khung */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho khung */
    }

    .centered-link {
        text-decoration: none;
        font-size: 24px;
        color: black;
    }
</style>
</head>
<body>
    <div class="centered-link-container">
        <a href="/Mock/nguoi-dung/thong-tin-nguoi-dung" class="centered-link">Quản lí người dùng</a>
    </div>
</body>
</html>
