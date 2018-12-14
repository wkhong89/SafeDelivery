<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QR 안심택배</title>
<%
	String sub_menu = (String) request.getAttribute("sub_menu");
	if (sub_menu == null) {
		sub_menu = "userPart.jsp";
	}
%>
<style>
body {
	background-image: url('images/background.png');
	background-repeat: no-repeat;
	background-size: 100%;
}

#wrap {
	width: 1000px;
	height: 850px;
	margin: auto;
	background-image: url('images/back.png');
}

#title {
	height: 150px;
	background-image: url('images/safe.png');
	background-repeat: no-repeat;
	background-size: 1000px 150px;
}

#subBar {
	height: 30px;
}

#main {
	height: 570px;
	width: 1000px;
	background: #F3F4F4;
}

#footer {
	height: 100px;
	width: 1000px;
}
</style>
</head>
<body>
	<br>
	<section id="wrap">
		<header id="title"> </header>
		<section id="subBar">
			<jsp:include page="title.jsp"></jsp:include>
		</section>
		<section id="main">
			<jsp:include page="<%=sub_menu%>"></jsp:include>
		</section>
		<footer id="bottom">
			<section id="footer">
				<jsp:include page="bottom.jsp"></jsp:include>
			</section>
		</footer>
	</section>
</body>
</html>