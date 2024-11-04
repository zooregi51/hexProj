package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDao;
import employee.model.Employee;
import jdbc.connection.ConnectionProvider;

public class EmployeeDeleteService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public int multiDelete(String[] empdelete) {
		try (Connection conn = ConnectionProvider.getConnection()){
			for(int i=0;i<empdelete.length;i++)
			{
				Employee employee=employeeDao.selectById(conn, Integer.parseInt(empdelete[i]));
				if(!canDelete(Integer.parseInt(empdelete[i]), employee)) {
					throw new PermissionDeniedException();
				}
			}
			int res = employeeDao.multiDelete(conn, empdelete);
			return res;
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
	//브랜치 테스트
	
	private boolean canDelete(int empno,Employee employee) {
		return employee.getEmpno().equals(empno);
	}
}
