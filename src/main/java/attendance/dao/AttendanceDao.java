package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import attendance.model.AttendanceRecord;

public class AttendanceDao {

    public List<AttendanceRecord> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM AttendanceRecords";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<AttendanceRecord> list = new ArrayList<>();
            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord(
                        rs.getInt("RecordID"),
                        rs.getString("EmployeeID"),
                        rs.getDate("WorkDate"),
                        rs.getTime("StartTime"),
                        rs.getTime("EndTime"),
                        rs.getString("WorkType"),
                        rs.getString("ApprovalStatus"),
                        rs.getInt("TotalOvertimeHours")
                );
                list.add(record);
            }
            return list;
        }
    }

    public void insert(Connection conn, AttendanceRecord record) throws SQLException {
        String sql = "INSERT INTO AttendanceRecords (EmployeeID, WorkDate, StartTime, EndTime, WorkType, ApprovalStatus, TotalOvertimeHours) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, record.getEmployeeId());
            pstmt.setDate(2, record.getWorkDate());
            pstmt.setTime(3, record.getStartTime());
            pstmt.setTime(4, record.getEndTime());
            pstmt.setString(5, record.getWorkType());
            pstmt.setString(6, record.getApprovalStatus());
            pstmt.setInt(7, record.getTotalOvertimeHours());
            pstmt.executeUpdate();
        }
    }

    public void update(Connection conn, AttendanceRecord record) throws SQLException {
        String sql = "UPDATE AttendanceRecords SET WorkDate = ?, StartTime = ?, EndTime = ?, WorkType = ?, ApprovalStatus = ?, TotalOvertimeHours = ? WHERE RecordID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, record.getWorkDate());
            pstmt.setTime(2, record.getStartTime());
            pstmt.setTime(3, record.getEndTime());
            pstmt.setString(4, record.getWorkType());
            pstmt.setString(5, record.getApprovalStatus());
            pstmt.setInt(6, record.getTotalOvertimeHours());
            pstmt.setInt(7, record.getRecordId());
            pstmt.executeUpdate();
        }
    }

    public void delete(Connection conn, int recordId) throws SQLException {
        String sql = "DELETE FROM AttendanceRecords WHERE RecordID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recordId);
            pstmt.executeUpdate();
        }
    }
}
