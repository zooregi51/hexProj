package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import attendance.dao.EmployeeDao;
import attendance.model.Employee;
import jdbc.connection.ConnectionProvider;

public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao(); // DAO 인스턴스 생성

    // 직원 목록을 가져오는 메서드
    public List<Employee> getEmployeeList() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return employeeDao.selectAll(conn); // DAO 메서드 호출
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 처리
        }
    }
}
