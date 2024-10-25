package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import attendance.model.Employee;
import jdbc.JdbcUtil;

public class EmployeeDao {

    // 사원 ID(empno)로 사원 정보를 조회하는 메서드
    public Employee selectById(Connection conn, int empno) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM Employee WHERE empno = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 생성자를 사용하여 Employee 객체 생성
                    employee = new Employee(
                        rs.getInt("empno"), 
                        rs.getString("empform"), 
                        rs.getString("name"), 
                        rs.getDate("hireddate"), 
                        rs.getDate("retireddate"), 
                        rs.getString("dep"), 
                        rs.getString("position"), 
                        rs.getString("registrationnum"), 
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"), 
                        rs.getString("other"), 
                        rs.getInt("salary")
                    );
                }
            }
        }
        return employee;
    }

    // 사원 전체 리스트를 조회하는 메서드
    public List<Employee> selectAll(Connection conn) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // 생성자를 사용하여 Employee 객체 생성
                Employee employee = new Employee(
                    rs.getInt("empno"), 
                    rs.getString("empform"), 
                    rs.getString("name"), 
                    rs.getDate("hireddate"), 
                    rs.getDate("retireddate"), 
                    rs.getString("dep"), 
                    rs.getString("position"), 
                    rs.getString("registrationnum"), 
                    rs.getString("address"), 
                    rs.getString("phone"), 
                    rs.getString("email"), 
                    rs.getString("other"), 
                    rs.getInt("salary")
                );
                employeeList.add(employee); // 리스트에 추가
            }
        }
        return employeeList;
    }

    // 사원 정보를 데이터베이스에 삽입하는 메서드
    public void insert(Connection conn, Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (empno, empform, name, hireddate, retireddate, dep, position, registrationnum, address, phone, email, other, salary) " +
                     "VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getEmpform());
            pstmt.setString(2, employee.getName());
            pstmt.setDate(3, new java.sql.Date(employee.getHireddate().getTime()));
            pstmt.setDate(4, employee.getRetireddate() != null ? new java.sql.Date(employee.getRetireddate().getTime()) : null);
            pstmt.setString(5, employee.getDep());
            pstmt.setString(6, employee.getPosition());
            pstmt.setString(7, employee.getRegistrationnum());
            pstmt.setString(8, employee.getAddress());
            pstmt.setString(9, employee.getPhone());
            pstmt.setString(10, employee.getEmail());
            pstmt.setString(11, employee.getOther());
            pstmt.setInt(12, employee.getSalary());
            
            pstmt.executeUpdate();
        }
    }

    // 사원 정보를 업데이트하는 메서드
    public void update(Connection conn, Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET empform = ?, name = ?, hireddate = ?, retireddate = ?, dep = ?, position = ?, registrationnum = ?, address = ?, phone = ?, email = ?, other = ?, salary = ? " +
                     "WHERE empno = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getEmpform());
            pstmt.setString(2, employee.getName());
            pstmt.setDate(3, new java.sql.Date(employee.getHireddate().getTime()));
            pstmt.setDate(4, employee.getRetireddate() != null ? new java.sql.Date(employee.getRetireddate().getTime()) : null);
            pstmt.setString(5, employee.getDep());
            pstmt.setString(6, employee.getPosition());
            pstmt.setString(7, employee.getRegistrationnum());
            pstmt.setString(8, employee.getAddress());
            pstmt.setString(9, employee.getPhone());
            pstmt.setString(10, employee.getEmail());
            pstmt.setString(11, employee.getOther());
            pstmt.setInt(12, employee.getSalary());
            pstmt.setInt(13, employee.getEmpno());
            
            pstmt.executeUpdate();
        }
    }

    // 사원 정보를 삭제하는 메서드
    public void delete(Connection conn, int empno) throws SQLException {
        String sql = "DELETE FROM Employee WHERE empno = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            pstmt.executeUpdate();
        }
    }
}
