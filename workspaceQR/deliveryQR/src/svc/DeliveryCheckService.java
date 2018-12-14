package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.DeliveryDAO;
import vo.DeliveryCompany;

public class DeliveryCheckService {

	public boolean checkService(DeliveryCompany company) {
		
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		deliveryDAO.setConnection(con);
		
		boolean checkSuccess = deliveryDAO.checkPart(company);
		close(con);
		return checkSuccess;
	}

}
