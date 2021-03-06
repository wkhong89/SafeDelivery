package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CheakAndroidIDService;
import vo.ActionForward;

public class CheakAndroidIDAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CheakAndroidIDService  cheakAndroidIDService = new CheakAndroidIDService();
		int checkID = 0;
		checkID = cheakAndroidIDService.selectAndroidID();
		ActionForward forward = null;
		if(checkID != 0) {
			forward = new ActionForward();
			request.setAttribute("sub_menu","finishUser.jsp");
			request.setAttribute("waybill", checkID);
			forward.setUrl("layoutTest.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('QR을 찍은 후 확인버튼을 눌러주세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
