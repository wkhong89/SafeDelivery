<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	margin: 0;
	padding: 0;
}

.divCenter {
	position: absolute;
	width: 350px;
	height: 320px;
	left: 50%;
	top: 50%;
	margin-left: -175px;
	margin-top: -180px;
	text-align: center;
}
</style>
<meta charset=UTF-8>
<title></title>
</head>
<body>

	<div class="divCenter">
		<h1>정상적으로 저장되었습니다. <br>밑의 QR코드를 입력하시면 바로 정보 확인이 가능해집니다.</h1>
		<img src="images/${num%7-1}d.png">
	</div>
</body>
</html>