package attendance.service;

import attendance.dao.AttendanceDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AttendanceService {

    private AttendanceDao attendanceDao = new AttendanceDao();  // DAO 인스턴스 생성

    // 전체 근태 기록 조회
    public List<AttendanceRecord> getAttendanceRecords() throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return attendanceDao.selectAll(conn);  // DAO를 통해 데이터 조회
        }
    }

    // 근태 기록 추가
    public void addAttendance(AttendanceRecord record) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.insert(conn, record);  // DAO를 통해 데이터 삽입
        }
    }

    // 근태 기록 수정
    public void updateAttendance(String recordId, AttendanceRecord updatedRecord) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.update(conn, recordId, updatedRecord);  // 수정 DAO 호출
        }
    }

    // 특정 근태 기록 조회 (ID로)
    public AttendanceRecord getAttendanceById(String recordId) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return attendanceDao.selectById(conn, recordId);  // DAO를 통해 특정 ID 데이터 조회
        }
    }

    // 근태 기록 삭제
    public void deleteAttendance(String recordId) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.delete(conn, recordId);  // 삭제 DAO 호출
        }
    }
}
