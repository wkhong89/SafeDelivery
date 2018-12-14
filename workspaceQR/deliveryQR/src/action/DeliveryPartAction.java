package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DeliveryPartService;
import svc.DeliveryCheckService;
import vo.ActionForward;
import vo.DeliveryCompany;

public class DeliveryPartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryCompany company = new DeliveryCompany();
		company.setWaybill(request.getParameter("number"));
		company.setCompany(request.getParameter("company"));
		company.setCompanyName(request.getParameter("companyName"));
		company.setCompanyPassword(request.getParameter("company_password"));
		String updateSuccess = null;
		DeliveryCheckService deliveryCheckService = new DeliveryCheckService();
		boolean checkSuccess = deliveryCheckService.checkService(company);
		if (checkSuccess) {
			DeliveryPartService deliveryPartService = new DeliveryPartService();
			updateSuccess = deliveryPartService.updateService(company);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�Է��� �����Ͽ����ϴ� - ������� ������ ���� �ʽ��ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}
		ActionForward forward = null;
		if (updateSuccess != null) {
			request.setAttribute("company", company);
			request.setAttribute("pw", updateSuccess);
			forward = new ActionForward();
			request.setAttribute("sub_menu", "successCompany.jsp");
			forward.setUrl("layoutTest.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�Է��� �����Ͽ����ϴ� - ���� �´� �ֹ���ȣ�� �����ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
