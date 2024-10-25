package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import attendance.model.Employee;

public class EmployeeDao {

    // 직원 목록을 가져오는 메서드
    public List<Employee> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT empno, empform, name, dep, position FROM Employee"; // SQL 쿼리 작성
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            List<Employee> list = new ArrayList<>();
            while (rs.next()) {
                // ResultSet에서 데이터를 읽어 Employee 객체로 변환
                Employee employee = new Employee(
                    rs.getInt("empno"), 
                    rs.getString("empform"), 
                    rs.getString("name"), 
                    rs.getString("dep"),
                    rs.getString("position")
                );
                list.add(employee); // 리스트에 추가
            }
            return list; // 결과 리스트 반환
        }
    }
}
