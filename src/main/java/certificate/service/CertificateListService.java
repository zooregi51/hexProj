package certificate.service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import employee.service.EmployeePage;
import certificate.dao.*;
import certificate.model.*;
import jdbc.connection.ConnectionProvider;
public class CertificateListService {

	private CertificateDao certificateDao=new CertificateDao();
	private int size=30;
	
	public CertificatePage getCertificatePage(int pageNum) {
		int firstRow=0;
		int endRow=0;
		List<Certificate> certificate=null;
		try (Connection conn = ConnectionProvider.getConnection()){
			int total=certificateDao.selectCount(conn);
			if(total>0) {
				firstRow=(pageNum-1)*size+1;
				endRow=firstRow+size-1;
				certificate=certificateDao.select(conn, firstRow, endRow);
			}
			return new CertificatePage(total,pageNum,size,certificate);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
}
