package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.DeliveryDAO;

public class CheckDeliveryID {

	public boolean selectDeiliveryID(String companyName, String password) {
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		deliveryDAO.setConnection(con);
		
		boolean checkSuccess = deliveryDAO.checkDeliveryPart(companyName,password);
		
		return checkSuccess;
	}

}
