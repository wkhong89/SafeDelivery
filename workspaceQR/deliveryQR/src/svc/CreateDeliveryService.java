package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.DeliveryDAO;

public class CreateDeliveryService {

	public boolean createUser(String name, String companyUser, String companyPhone) {
		
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		
		deliveryDAO.setConnection(con);
		boolean insertSuccess=false;
		int checkSuccess = deliveryDAO.insertDelivery(name, companyUser, companyPhone);
		if (checkSuccess!= 0) {
			insertSuccess=true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return insertSuccess;
	}

	public int selectNum() {
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		
		deliveryDAO.setConnection(con);
		
		int num=deliveryDAO.selectNumDelivery();
		
		return num;
	}

}
