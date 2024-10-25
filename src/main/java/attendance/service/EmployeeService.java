package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendance.dao.EmployeeDao;
import attendance.model.Employee;
import jdbc.connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class EmployeeService {
    
    private EmployeeDao employeeDao = new EmployeeDao();
    
    // 모든 사원을 조회하는 서비스 메서드
    public List<Employee> getEmployeeList() {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // 데이터베이스 연결 가져오기
            return employeeDao.selectAll(conn); // DAO를 통해 모든 사원 리스트 가져오기
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // 데이터베이스 연결 닫기
        }
    }
    
    // 사원 ID로 특정 사원을 조회하는 서비스 메서드
    public Employee getEmployeeById(int empno) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // 데이터베이스 연결 가져오기
            return employeeDao.selectById(conn, empno); // DAO를 통해 사원 정보 가져오기
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // 데이터베이스 연결 닫기
        }
    }
    
    // 새로운 사원을 추가하는 서비스 메서드
    public void addEmployee(Employee employee) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // 데이터베이스 연결 가져오기
            employeeDao.insert(conn, employee); // DAO를 통해 사원 정보 삽입
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // 데이터베이스 연결 닫기
        }
    }

    // 사원 정보를 업데이트하는 서비스 메서드
    public void updateEmployee(Employee employee) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // 데이터베이스 연결 가져오기
            employeeDao.update(conn, employee); // DAO를 통해 사원 정보 업데이트
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // 데이터베이스 연결 닫기
        }
    }

    // 사원을 삭제하는 서비스 메서드
    public void deleteEmployee(int empno) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection(); // 데이터베이스 연결 가져오기
            employeeDao.delete(conn, empno); // DAO를 통해 사원 정보 삭제
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // 데이터베이스 연결 닫기
        }
    }
}
