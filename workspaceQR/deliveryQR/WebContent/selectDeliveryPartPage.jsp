<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="OracleConnection.jsp"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.json.simple.*"%>
<%
	String name = request.getParameter("name");
	System.out.print(name);
	JSONArray arr = new JSONArray();
	try {
		sql = "select * from deliverypart where delivery_android = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			String delivery_name = rs.getString("delivery_name");
			JSONObject obj = new JSONObject();

			obj.put("delivery_name", delivery_name);

			if (obj != null) {
				arr.add(obj);
			}
		}
		out.print(arr);
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