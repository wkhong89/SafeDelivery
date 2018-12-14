<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="OracleConnection.jsp"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.json.simple.*"%>
<%
	String name = request.getParameter("name");
	String waybill = request.getParameter("waybill");
	JSONArray arr = new JSONArray();
	try {
		sql = "select * from delivery where company_name=? and waybill=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, waybill);
		rs = pstmt.executeQuery();
		if (rs.next()) {

			JSONObject obj = new JSONObject();

			String r_phone = rs.getString("r_phone");
			String password = rs.getString("passwd");
			String product = rs.getString("product");

			System.out.print(r_phone + password + product);

			obj.put("phoneNumber", r_phone);
			obj.put("password", password);
			obj.put("product", product);

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