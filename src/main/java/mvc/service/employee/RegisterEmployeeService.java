package mvc.service.employee;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import mvc.dao.employee.EmployeeDao;
import mvc.model.employee.Employee;

public class RegisterEmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    // 사원 정보를 데이터베이스에 저장하는 메서드
    public Integer employee(Employee emp) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            conn.setAutoCommit(false);  // 트랜잭션 시작

            int savedEmployee = employeeDao.insert(conn, emp); // 사원 정보 저장
            if (savedEmployee == 0) {
                throw new RuntimeException("fail to insert employee");
            }

            conn.commit();  // 성공 시 트랜잭션 커밋
            return savedEmployee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 가장 큰 empNo에 1을 더한 값을 반환하는 메서드
    public int getNextEmpNo() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            int lastEmpNo = employeeDao.getLastEmpNo(conn);  // 가장 큰 empNo 조회
            return lastEmpNo + 1;  // 가장 큰 empNo에 1을 더한 값 반환
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}