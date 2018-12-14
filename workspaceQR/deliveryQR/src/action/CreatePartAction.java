package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CreateDeliveryService;
import vo.ActionForward;
import vo.DeliveryCompany;

public class CreatePartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num=0;
		String name = request.getParameter("name");
		String companyUser = (String) request.getParameter("companyUser");
		String companyPhone = (String) request.getParameter("companyPhone");
		ActionForward forward=null;
		DeliveryCompany del = new DeliveryCompany();
		CreateDeliveryService createDeliveryService = new CreateDeliveryService();
		boolean createSuccess = createDeliveryService.createUser(name, companyUser, companyPhone);
		
		if(createSuccess==true) {
			num = createDeliveryService.selectNum();
		}
		
		if(num!= 0) {
			forward = new ActionForward();
			request.setAttribute("num",num);
			request.setAttribute("sub_menu","deliveryPartUpdate.jsp");
			forward.setUrl("layoutTest.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류가 발생하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
