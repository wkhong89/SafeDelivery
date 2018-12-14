<%@page import="vo.User"%>
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
	margin-left: -250px;
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
.imgSize{
	width : 120px;
	height: 120px;
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<%
	User user = (User) request.getAttribute("user");
%>
<body>

	<div class="main">
	<h2>입력하신 정보의 값은 다음과 같습니다.</h2>
	<p class = "red">QR코드를 입력하지 않을 시 <br>정보는 사라지므로 QR코드를 꼭 찍어주세요</p>
		<div class="left">
			<table>
				<%
					if (user.getO_user().equals(user.getR_user())) {
				%><p class = "red">주문자와 수령인의 정보가 동일합니다</p>
				<%
					} else {
				%>

				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2"><h1>주문자 정보</h1></td>
				</tr>
				<tr>
					<td colspan="2">* 수령인과 정보가 같을 시 입력하지 않으셔도 됩니다.</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td><label>이름 </label></td>
					<td><%=user.getO_user()%></td>
				</tr>
				<tr>
					<td><label>전화번호 </label></td>
					<td><%=user.getO_phone()%></td>
				</tr>
			</table>
		</div>
		<%
			}
		%>
		<div class="right">
			<table>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td colspan="2"><h1>수령인 정보</h1></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td><label>이름 </label></td>
					<td><%=user.getR_user()%></td>
				</tr>
				<tr>
					<td><label>주소 </label></td>
					<td><%=user.getR_address()%></td>
				</tr>
				<tr>
					<td><label>전화번호 </label></td>
					<td><%=user.getR_phone()%></td>
				</tr>
			</table>
			<%int number = user.getNum();%>
			<img src="images/<%=number%>.png" class = "imgSize"><br><%=user.getR_user() %>
			</form>
		</div>
		<br>
		<button onclick="location.href='cheakAndroidID.qr'" >확인</button>
	</div>
</body>
</html>