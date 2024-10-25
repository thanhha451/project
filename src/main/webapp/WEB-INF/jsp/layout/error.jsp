<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }
        #image-1 {
            display: block;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
         body, html {
        height: 100%;
        margin: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #f0f0f0; /* Màu nền của trang */
    }

    .centered-link {
        background-color: graytext; /* Màu nền của phần nội dung */
        padding: 20px; /* Khoảng cách giữa nội dung và khung */
        border-radius: 10px; /* Bo tròn viền khung */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho khung */
        position: absolute;
        top: 65%;
	    left: 45%;
	    margin-left: -50px;
        z-index: 0;
    }

    .centered-link {
        text-decoration: none;
        font-size: 24px;
        color: black;
    }
    .home{
    	position: relative;
    }
    
    
    </style>
</head>
<body>
    <div class="home">
    	<img id="image-1" class="fluid" alt="" src="/Mock/resources/img/error.jpg"/>
    	<a href="/Mock/" class="centered-link">Trở lại trang chủ</a>
    </div>
</body>
</html>
