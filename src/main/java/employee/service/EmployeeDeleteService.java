package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.service.PermissionDeniedException;
import employee.dao.EmployeeDao;
import employee.model.Employee;
import jdbc.connection.ConnectionProvider;

public class EmployeeDeleteService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public void multiDelete(EmployeeDeleteRequest deleteRequest) {
		
		try (Connection conn = ConnectionProvider.getConnection()){
			
			if(!canDelete(deleteRequest.getEmpno(), article)) {
				throw new PermissionDeniedException();
			}
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
	
	private boolean canDelete(int empno,Employee employee) {
		return employee.getEmpno().equals(empno);
	}
}
