<%@page import="vo.DeliveryCompany"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	margin: 0;
	padding: 0;
	text-align: center;
}

h1 {
	display: inline-block;
	width: 500px;
	text-align: center;
}

.main {
	position: absolute;
	width: 500px;
	height: 400px;
	left: 50%;
	top: 50%;
	margin-left: -250px;
	margin-top: -200px;
}

td {
	width: 200px;
}

.sub {
	width: 300px;
	margin-left: 50px;
}
.red{
color: red;
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<%
		DeliveryCompany company = (DeliveryCompany) request.getAttribute("company");
		String user_password = (String) request.getAttribute("pw");
%>
<body>

	<div class="main">
	<h2>입력하신 정보의 값은 다음과 같습니다.</h2>
		<br>
		<br>
		<div class="right">
			<table>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2"><h1>택배사 정보</h1></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td colspan="2">비밀번호는 택배회사 측 택배기사 이외에는 <br>그 누구에게도 알려주시면 안됩니다.</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td> <br></td>
				</tr>
				<tr>
					<td><label>택배사 명</label></td>
					<td><%=company.getCompanyName() %></td>
				</tr>
				<tr>
					<td> <br></td>
				</tr>
				<tr>
					<td><label>배송번호</label></td>
					<td><%= company.getCompany()%></td>
				</tr>
				<tr>
					<td><label>비밀번호</label></td>
					<td><%=user_password%></td>
				</tr>
				<tr>
					<td> <br></td>
				</tr>
				<tr>
				<td colspan="2"><hr></td>
				</tr>
			</table>
			
		</div>
		<br>
	</div>
</body>
</html>