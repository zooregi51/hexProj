package certificate.service;

import java.sql.Connection;
import java.sql.SQLException;

import certificate.dao.CertificateDao;
import certificate.model.Certificate;
import employee.dao.EmployeeDao;
import employee.model.Employee;
import employee.service.EmployeeNotFoundException;
import jdbc.connection.ConnectionProvider;

public class ReadCertificateHandler {

	private CertificateDao certificateDao = new CertificateDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public CertificateData getCertificate(int certificateNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Certificate certificate=certificateDao.selectById(conn, certificateNum);
			if(certificate==null) {
				throw new CertificateNotFoundException();
			}
			Employee employee = employeeDao.selectById(conn, certificateNum);
			if(employee ==null) {
				throw new EmployeeNotFoundException();
			}
			return new CertificateData(certificate, employee);
		} catch (SQLException e) {
			throw new RuntimeException();
			// TODO: handle exception
		}
		
	}
}
