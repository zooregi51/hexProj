package attendance.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import attendance.model.AttendanceRecord;

public class AttendanceRecordDao {

    public List<AttendanceRecord> selectByEmpno(Connection conn, int empno) throws SQLException {
        String sql = "SELECT * FROM AttendanceRecords WHERE EmployeeID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<AttendanceRecord> list = new ArrayList<>();
                while (rs.next()) {
                    AttendanceRecord record = new AttendanceRecord(
                        rs.getInt("RecordID"),
                        rs.getInt("EmployeeID"),
                        rs.getDate("EntryDate"),
                        rs.getString("WorkType"),
                        rs.getString("StartTime") + " ~ " + rs.getString("EndTime"),
                        rs.getInt("WorkDays"),
                        rs.getInt("Amount"),
                        rs.getString("Remarks")
                    );
                    list.add(record);
                }
                return list;
            }
        }
    }
}
