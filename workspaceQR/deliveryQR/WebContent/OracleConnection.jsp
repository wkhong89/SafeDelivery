<%@ page language="java" import="java.sql.*"%>

<%
	//  DB_URL에는 자신의 Oracle 서버 Endpoint 주소를 적습니다.
	String DB_URL = "jdbc:oracle:thin:@203.244.145.214:1521:XE";
	String DB_USER = "daegu";
	String DB_PASSWORD = "dkdlxl";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}
	catch (SQLException e) {
		out.println(e);
	}
%>
