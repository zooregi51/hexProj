package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import attendance.model.Employee;

public class EmployeeDao {

    // 모든 사원 리스트 가져오기
    public List<Employee> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Employee";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
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
                list.add(employee);
            }
            return list;
        }
    }
}
