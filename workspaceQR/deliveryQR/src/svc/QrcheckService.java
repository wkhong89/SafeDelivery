package svc;

import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.DeliveryDAO;
import vo.User;

public class QrcheckService {

	public User selectQR(String r_user) {
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		deliveryDAO.setConnection(con);
		
		User selectName = deliveryDAO.selectDeliveryList(r_user);
		
		close(con);
		return selectName;
	}

}
