package certificate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import employee.service.EmployeePage;
import certificate.dao.CertificateDao;
import certificate.model.Certificate;
import jdbc.connection.ConnectionProvider;

public class CertificateIssuanceListService {

	private EmployeeDao employeeDao = new EmployeeDao();
	private int size = 30;

	public EmployeePage getEmployeePage(int pageNum) {
		int firstRow = 0;
		int endRow = 0;
		List<Employee> employee = null;
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = employeeDao.selectCount(conn);
			int hiredNum = employeeDao.selectCountHired(conn);
			int permanentNum = employeeDao.selectCountPermanent(conn);
			int contractNum = employeeDao.selectCountContract(conn);
			int temporaryNum = employeeDao.selectCountTemporary(conn);
			int dispatchedNum = employeeDao.selectCountDispatched(conn);
			int commissionedNum = employeeDao.selectCountCommissioned(conn);
			int dailyjobNum = employeeDao.selectCountDailyJob(conn);
			int retiredNum = employeeDao.selectCountRetired(conn);

			if (total > 0) {
				firstRow = (pageNum - 1) * size + 1;
				endRow = firstRow + size - 1;
				employee = employeeDao.select(conn, firstRow, endRow);
			}
			return new EmployeePage(total, pageNum, size, employee, hiredNum, permanentNum, contractNum, temporaryNum,
					dispatchedNum, commissionedNum, dailyjobNum, retiredNum);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public EmployeePage getEmployeePage(int pageNum, String searchForm) {
		int firstRow = 0;
		int endRow = 0;
		List<Employee> employee = null;
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = employeeDao.selectCount(conn);
			int hiredNum = employeeDao.selectCountHired(conn);
			int permanentNum = employeeDao.selectCountPermanent(conn);
			int contractNum = employeeDao.selectCountContract(conn);
			int temporaryNum = employeeDao.selectCountTemporary(conn);
			int dispatchedNum = employeeDao.selectCountDispatched(conn);
			int commissionedNum = employeeDao.selectCountCommissioned(conn);
			int dailyjobNum = employeeDao.selectCountDailyJob(conn);
			int retiredNum = employeeDao.selectCountRetired(conn);

			if (total > 0) {
				firstRow = (pageNum - 1) * size + 1;
				endRow = firstRow + size - 1;
				employee = employeeDao.select(conn, firstRow, endRow, searchForm);
			}
			return new EmployeePage(total, pageNum, size, employee, hiredNum, permanentNum, contractNum, temporaryNum,
					dispatchedNum, commissionedNum, dailyjobNum, retiredNum);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
