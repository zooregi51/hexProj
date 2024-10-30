package attendance.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import attendance.model.LeaveRecord;

public class LeaveRecordDao {

    public List<LeaveRecord> selectByEmpno(Connection conn, int empno) throws SQLException {
        String sql = "SELECT * FROM LeaveRecords WHERE EmployeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<LeaveRecord> list = new ArrayList<>();
                while (rs.next()) {
                    LeaveRecord record = new LeaveRecord(
                        rs.getInt("LeaveID"),
                        rs.getInt("EmployeeID"),
                        rs.getString("LeaveType"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("TotalDays"),
                        rs.getInt("DaysUsed"),
                        rs.getInt("RemainingDays"),
                        rs.getString("Remarks")
                    );
                    list.add(record);
                }
                return list;
            }
        }
    }
}
