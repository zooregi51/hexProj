package mvc.model.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // 전체 사원 리스트 조회
    public List<Employee> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT empNo, empForm, name, hiredDate, dep, registrationNum, address, phone, email, other, salary FROM Employee";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Employee> employeeList = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("empNo"),
                        rs.getString("empForm"),
                        rs.getString("name"),
                        rs.getDate("hiredDate"),
                        rs.getString("dep"),
                        rs.getString("registrationNum"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("other"),
                        rs.getInt("salary")
                );
                employeeList.add(employee);
            }
            return employeeList;
        }//뷁
    }
}
