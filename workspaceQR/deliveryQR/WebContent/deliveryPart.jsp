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
.space{
	font-size: 30px;
}
.div_right{
	text-align: right;
}
.px_up {
	font-size: 2px;
}
</style>
<meta charset=UTF-8>
<title>QR 안심택배</title>
</head>
<body>	
	<div class="main">
		<div class = "div_right"><button onclick = "location.href='deliveryCreate.qr'">택배기사 정보 추가</button></div>
		<p class = "px_up">　</p>
		<form method="post" action="deliveryPart.qr">
			<div class="right">
				<table>
					<tr>
						<td colspan="2"><hr> </td>
					</tr>
					<tr>
						<td colspan="2"><h1>택배사 입력하기</h1></td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td><label for="number">주문번호 입력</label></td>
						 <td><input type="text" name="number"></td>
					</tr>
					<tr>
						<td><label for="company">배송번호 입력</label></td>
						<td><input type="text" name="company"></td>
					</tr>
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td colspan ="2"><p class = "space">　</td>
					</tr>
					<tr><td colspan = "2"><hr></tr>
					<tr>
						<td colspan="2"><h1>택배사 정보 확인</h1></td>
					</tr>
					
					<tr>
						<td colspan="2"><hr></td>
					</tr>
					<tr>
						<td><label for="companyName">택배사 입력</label> </td>
						<td><input type="text" name="companyName"></td>
					</tr>
					<tr>
						<td><label for="company_password">택배사 비밀번호</label></td>
						<td><input type="password" name="company_password"></td>
					</tr>
					<tr><td colspan = "2"><hr></td></tr>
				</table>
			</div>
			<br>
			<div>
				<input type="submit" value="신청하기" class="sub">
			</div>
			<br>
		</form>
			
	</div>
</body>
</html>