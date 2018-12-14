package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.DeliveryDAO;
import vo.DeliveryCompany;

public class DeliveryPartService {

	public String updateService(DeliveryCompany company) {

		Connection con = getConnection();
		DeliveryDAO deliveryDAO = DeliveryDAO.getinstance();
		deliveryDAO.setConnection(con);

		String updateSuccess = deliveryDAO.updatePart(company);
		if (updateSuccess != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return updateSuccess;
	}

}
