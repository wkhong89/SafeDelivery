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

h1 {
	display: inline-block;
	width: 400px;
	text-align: center;
}

.main {
	position: absolute;
	width: 400px;
	height: 300px;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	margin-top: -180px;
}

td {
	width: 200px;
}

.sub {
	width: 300px;
	margin-left: 50px;
}
</style>
<meta charset=UTF-8>
<title>QR 안심택배</title>
</head>
<% String name = (String) request.getAttribute("companyName"); %>
<body>	
	<div class="main">
		<form method="post" action="CreatePart.qr?name=<%=name%>">
				<table>
					<tr>
						<td colspan="2"><hr> </td>
					</tr>
					<tr>
						<td colspan="2"><h1><%=name%> 기사 추가하기</h1></td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td><label for="companyUser">택배기사 이름</label> </td>
						<td><input type="text" name="companyUser"></td>
					</tr>
					<tr>
						<td><label for="companyPhone">택배기사 전화번호</label> </td>
						<td><input type="text" name="companyPhone"></td>
					</tr>
					
				</table>
			<br>
			<div>
				<input type="submit" value="신청하기" class="sub">
			</div>
			<br>
			<div><hr></div>
		</form>
		<br>
			<div><button onclick = "location.href='deliveryindex.qr'">배송번호 입력하기</button></div>
	</div>
</body>
</html>