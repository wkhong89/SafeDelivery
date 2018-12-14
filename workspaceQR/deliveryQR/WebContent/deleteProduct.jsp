<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="OracleConnection.jsp"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.json.simple.*"%>
<%
	int success = 0;
	String phoneNumber = request.getParameter("phoneNumber");
	String password = request.getParameter("password");
	int num = Integer.parseInt(request.getParameter("num"));
	System.out.println(phoneNumber + " " + password + "  " + num);
	JSONArray arr = new JSONArray();
	try {
		sql = "delete  from delivery where r_phone = ? and passwd = ? and num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, phoneNumber);
		pstmt.setString(2, password);
		pstmt.setInt(3, num);

		int result = pstmt.executeUpdate();
		if (result > 0) {
			success = result;
			JSONObject obj = new JSONObject();

			obj.put("success", success);

			if (obj != null) {
				arr.add(obj);
			}
		}
		out.println(success);
	} catch (SQLException se) {
		out.println(se.getMessage());
	} finally {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
%>