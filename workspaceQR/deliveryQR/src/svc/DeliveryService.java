package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DeliveryDAO;
import static db.JdbcUtil.*;
import vo.User;

public class DeliveryService {

	public int deliveryPro(User user) {
		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		deliveryDAO.setConnection(con);
		
		ArrayList<User> deliveryList = new ArrayList<User>();
		int successCount = deliveryDAO.insertDeliveryList(user);
		if (successCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return successCount;
	}

}
