package employee.service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import jdbc.connection.ConnectionProvider;

public class EmployeeListService {

	private EmployeeDao employeeDao = new EmployeeDao();
	private int size=30;
	
	public EmployeePage getEmployeePage(int pageNum) {
		int firstRow=0;
		int endRow=0;
		List<Employee> employee=null;
		try (Connection conn = ConnectionProvider.getConnection()){
			int total=employeeDao.selectCount(conn);
			int hiredNum=employeeDao.selectCountHired(conn);
			if(total>0) {
				firstRow=(pageNum-1)*size+1;
				endRow=firstRow+size-1;
				employee=employeeDao.select(conn, firstRow, endRow);
			}
			return new EmployeePage(total,pageNum,size,employee,hiredNum);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
	}

}
