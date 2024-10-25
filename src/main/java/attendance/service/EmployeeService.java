package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendance.dao.EmployeeDao;
import attendance.model.Employee;
import jdbc.connection.ConnectionProvider;

public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

    // 사원 리스트 가져오기
    public List<Employee> getEmployeeList() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return employeeDao.selectAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
