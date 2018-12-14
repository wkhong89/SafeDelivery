<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="OracleConnection.jsp"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.json.simple.*"%>
<%
	String phoneNumber = request.getParameter("phoneNumber");
	String password = request.getParameter("password");
	String product = request.getParameter("product");
	System.out.println(phoneNumber + " " + password);
	JSONArray arr = new JSONArray();
	try {
		sql = "select * from delivery where r_phone = ? and passwd = ? and product = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, phoneNumber);
		pstmt.setString(2, password);
		pstmt.setString(3, product);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			String r_user = rs.getString("r_user");
			String r_address = rs.getString("r_sub_add");
			String r_phone = rs.getString("r_phone");
			String s_user = rs.getString("s_user");
			String s_address = rs.getString("s_address");
			String s_phone = rs.getString("s_phone");
			String o_user = rs.getString("o_user");
			String o_phone = rs.getString("o_phone");
			String company = rs.getString("company");
			String company_name = rs.getString("company_name");
			String android_id = rs.getString("android_id");
			String waybill = rs.getString("waybill");
			System.out.println(android_id);
			JSONObject obj = new JSONObject();

			obj.put("r_user", r_user);
			obj.put("r_address", r_address);
			obj.put("r_phone", r_phone);
			obj.put("s_user", s_user);
			obj.put("s_address", s_address);
			obj.put("s_phone", s_phone);
			obj.put("o_user", o_user);
			obj.put("o_phone", o_phone);
			obj.put("company", company);
			obj.put("company_name", company_name);
			obj.put("android_id", android_id);
			obj.put("waybill", waybill);
			obj.put("product", product);

			if (obj != null) {
				arr.add(obj);
			}
		}
		out.println(arr);
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