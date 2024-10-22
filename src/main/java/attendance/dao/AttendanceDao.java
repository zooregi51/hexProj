package attendance.dao;

import attendance.model.AttendanceRecord;
import jdbc.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {

    // 전체 근태 기록 조회
    public List<AttendanceRecord> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM AttendanceRecords";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<AttendanceRecord> records = new ArrayList<>();

            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord(
                        rs.getString("recordId"),
                        rs.getString("employeeId"),
                        rs.getDate("workDate"),  // workDate는 DATE 타입
                        rs.getString("startTime"),  // startTime은 VARCHAR2
                        rs.getString("endTime"),  // endTime은 VARCHAR2
                        rs.getString("workType"),
                        rs.getString("approvalStatus"),
                        rs.getInt("totalOvertimeHours")  // totalOvertimeHours는 NUMBER 타입
                );
                records.add(record);
            }

            return records;
        }
    }

    // 근태 기록 추가
    public void insert(Connection conn, AttendanceRecord record) throws SQLException {
        String sql = "INSERT INTO AttendanceRecords (recordId, employeeId, workDate, startTime, endTime, workType, approvalStatus, totalOvertimeHours) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, record.getRecordId());
            pstmt.setString(2, record.getEmployeeId());
            pstmt.setDate(3, record.getWorkDate());  // workDate는 DATE 타입
            pstmt.setString(4, record.getStartTime());  // startTime은 VARCHAR2
            pstmt.setString(5, record.getEndTime());  // endTime은 VARCHAR2
            pstmt.setString(6, record.getWorkType());
            pstmt.setString(7, record.getApprovalStatus());
            pstmt.setInt(8, record.getTotalOvertimeHours());  // totalOvertimeHours는 NUMBER 타입
            pstmt.executeUpdate();
        }
    }

    // 특정 근태 기록 조회 (ID로)
    public AttendanceRecord selectById(Connection conn, String recordId) throws SQLException {
        String sql = "SELECT * FROM AttendanceRecords WHERE recordId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recordId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new AttendanceRecord(
                            rs.getString("recordId"),
                            rs.getString("employeeId"),
                            rs.getDate("workDate"),  // workDate는 DATE 타입
                            rs.getString("startTime"),  // startTime은 VARCHAR2
                            rs.getString("endTime"),  // endTime은 VARCHAR2
                            rs.getString("workType"),
                            rs.getString("approvalStatus"),
                            rs.getInt("totalOvertimeHours")  // totalOvertimeHours는 NUMBER 타입
                    );
                }
                return null;
            }
        }
    }

    // 근태 기록 수정
    public void update(Connection conn, String recordId, AttendanceRecord updatedRecord) throws SQLException {
        String sql = "UPDATE AttendanceRecords SET employeeId = ?, workDate = ?, startTime = ?, endTime = ?, workType = ?, approvalStatus = ?, totalOvertimeHours = ? " +
                     "WHERE recordId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedRecord.getEmployeeId());
            pstmt.setDate(2, updatedRecord.getWorkDate());  // workDate는 DATE 타입
            pstmt.setString(3, updatedRecord.getStartTime());  // startTime은 VARCHAR2
            pstmt.setString(4, updatedRecord.getEndTime());  // endTime은 VARCHAR2
            pstmt.setString(5, updatedRecord.getWorkType());
            pstmt.setString(6, updatedRecord.getApprovalStatus());
            pstmt.setInt(7, updatedRecord.getTotalOvertimeHours());  // totalOvertimeHours는 NUMBER 타입
            pstmt.setString(8, recordId);
            pstmt.executeUpdate();
        }
    }

    // 근태 기록 삭제
    public void delete(Connection conn, String recordId) throws SQLException {
        String sql = "DELETE FROM AttendanceRecords WHERE recordId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recordId);
            pstmt.executeUpdate();
        }
    }
}
