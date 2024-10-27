package attendance.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import attendance.model.Employee;
import jdbc.JdbcUtil;

public class EmployeeDao {

    // 사원 전체 리스트를 조회하는 메서드
    public List<Employee> selectAll(Connection conn) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT empno, empform, name, dep, position FROM Employee";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("empno"),
                    rs.getString("empform"),
                    rs.getString("name"),
                    null, // hireddate는 필요 없으므로 null로 설정
                    null, // retireddate는 필요 없으므로 null로 설정
                    rs.getString("dep"),
                    rs.getString("position"),
                    null, // registrationnum은 필요 없으므로 null로 설정
                    null, // address는 필요 없으므로 null로 설정
                    null, // phone은 필요 없으므로 null로 설정
                    null, // email은 필요 없으므로 null로 설정
                    null, // other는 필요 없으므로 null로 설정
                    0 // salary는 필요 없으므로 0으로 설정
                );
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}
