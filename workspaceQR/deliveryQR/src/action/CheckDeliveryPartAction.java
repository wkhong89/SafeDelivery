package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CheakAndroidIDService;
import svc.CheckDeliveryID;
import vo.ActionForward;

public class CheckDeliveryPartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String companyName = request.getParameter("companyName");
		String password = request.getParameter("company_password");
		CheckDeliveryID checkDeliveryID = new CheckDeliveryID();
		boolean checkID = checkDeliveryID.selectDeiliveryID(companyName, password);
		if(checkID == true) {
			forward = new ActionForward();
			request.setAttribute("companyName", companyName);
			request.setAttribute("sub_menu","createPart.jsp");
			forward.setUrl("layoutTest.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('택배사 정보가 맞지않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	return forward;
	}
}
