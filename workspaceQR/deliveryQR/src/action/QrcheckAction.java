package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QrcheckService;
import vo.ActionForward;
import vo.User;

public class QrcheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();	
		String r_user = (String)session.getAttribute("r_user");
		QrcheckService qrcheckService = new QrcheckService();
		User user = qrcheckService.selectQR(r_user);
		
		ActionForward forward = null;
		if(user.getNum() != 0) {
			request.setAttribute("user", user);
			forward = new ActionForward();
			request.setAttribute("sub_menu","success.jsp");
			forward.setUrl("layoutTest.jsp");
		}
		return forward;
	}

}
