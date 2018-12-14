package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class postForward2Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		ActionForward forward = new ActionForward();
		forward.setUrl("posturl://qrdelivery2?post_id="+post_id);
		forward.setRedirect(true);
		return forward;
	}
}