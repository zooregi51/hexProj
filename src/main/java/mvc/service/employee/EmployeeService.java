package mvc.service.employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import mvc.model.employee.Employee;
import mvc.model.employee.EmployeeDAO;

public class EmployeeService {

    private EmployeeDAO employeeDao = new EmployeeDAO();

    // 전체 사원 리스트를 가져오는 메서드
    public List<Employee> getEmployeeList() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return employeeDao.selectAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
