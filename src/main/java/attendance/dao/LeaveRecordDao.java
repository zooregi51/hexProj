package attendance.dao;

import attendance.model.LeaveRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRecordDao {
    private Connection conn;

    public LeaveRecordDao(Connection conn) {
        this.conn = conn;
    }

    public List<LeaveRecord> selectAllRecords() throws SQLException {
        List<LeaveRecord> records = new ArrayList<>();
        String sql = "SELECT leave_id, empno, leave_type, total_days, days_used, remaining_days FROM LeaveRecords";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getInt("leave_id"),
                    rs.getInt("empno"),
                    rs.getString("leave_type"),
                    rs.getInt("total_days"),
                    rs.getInt("days_used"),
                    rs.getInt("remaining_days")
                );
                records.add(record);
            }
        }
        return records;
    }
}
