package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import attendance.model.AttendanceRecord;

public class AttendanceDao {

    // 모든 근태 기록을 가져오는 메서드
    public List<AttendanceRecord> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM AttendanceRecords";  // SQL 쿼리문
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<AttendanceRecord> list = new ArrayList<>();
            while (rs.next()) {
                // ResultSet에서 데이터를 읽어 AttendanceRecord 객체로 변환
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
            return list;  // 결과 리스트 반환
        }
    }

    // 새로운 근태 기록을 추가하는 메서드
    public void insert(Connection conn, AttendanceRecord record) throws SQLException {
        String sql = "INSERT INTO AttendanceRecords (EmployeeID, WorkDate, StartTime, EndTime, WorkType, ApprovalStatus, TotalOvertimeHours) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";  // SQL 쿼리문
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, record.getEmployeeId());
            pstmt.setDate(2, record.getWorkDate());
            pstmt.setTime(3, record.getStartTime());
            pstmt.setTime(4, record.getEndTime());
            pstmt.setString(5, record.getWorkType());
            pstmt.setString(6, record.getApprovalStatus());
            pstmt.setInt(7, record.getTotalOvertimeHours());
            pstmt.executeUpdate();  // 쿼리 실행
        }
    }

    // 근태 기록을 수정하는 메서드
    public void update(Connection conn, AttendanceRecord record) throws SQLException {
        String sql = "UPDATE AttendanceRecords SET WorkDate = ?, StartTime = ?, EndTime = ?, WorkType = ?, ApprovalStatus = ?, TotalOvertimeHours = ? "
                + "WHERE RecordID = ?";  // SQL 쿼리문
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, record.getWorkDate());
            pstmt.setTime(2, record.getStartTime());
            pstmt.setTime(3, record.getEndTime());
            pstmt.setString(4, record.getWorkType());
            pstmt.setString(5, record.getApprovalStatus());
            pstmt.setInt(6, record.getTotalOvertimeHours());
            pstmt.setInt(7, record.getRecordId());
            pstmt.executeUpdate();  // 쿼리 실행
        }
    }

    // 근태 기록을 삭제하는 메서드
    public void delete(Connection conn, int recordId) throws SQLException {
        String sql = "DELETE FROM AttendanceRecords WHERE RecordID = ?";  // SQL 쿼리문
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recordId);
            pstmt.executeUpdate();  // 쿼리 실행
        }
    }
}
