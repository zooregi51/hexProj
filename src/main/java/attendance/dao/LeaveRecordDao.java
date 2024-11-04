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

    // 전체 휴가 기록을 조회하는 메서드
    public List<LeaveRecord> selectAllRecords() throws SQLException {
        List<LeaveRecord> records = new ArrayList<>();
        String sql = "SELECT leave_id, empno, leave_type, total_days, days_used, remaining_days, leave_period, input_date, remarks FROM LeaveRecords";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getInt("leave_id"),
                    rs.getInt("empno"),
                    rs.getString("leave_type"),
                    rs.getInt("total_days"),
                    rs.getInt("days_used"),
                    rs.getInt("remaining_days"),
                    rs.getString("leave_period"),
                    rs.getDate("input_date"),
                    rs.getString("remarks")
                );
                records.add(record);
            }
        }
        return records;
    }

    // 특정 사원의 휴가 기록을 조회하는 메서드
    public List<LeaveRecord> selectByEmployee(int empno) throws SQLException {
        List<LeaveRecord> records = new ArrayList<>();
        String sql = "SELECT leave_id, empno, leave_type, total_days, days_used, remaining_days, leave_period, input_date, remarks FROM LeaveRecords WHERE empno = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LeaveRecord record = new LeaveRecord(
                        rs.getInt("leave_id"),
                        rs.getInt("empno"),
                        rs.getString("leave_type"),
                        rs.getInt("total_days"),
                        rs.getInt("days_used"),
                        rs.getInt("remaining_days"),
                        rs.getString("leave_period"),
                        rs.getDate("input_date"),
                        rs.getString("remarks")
                    );
                    records.add(record);
                }
            }
        }
        return records;
    }
}
