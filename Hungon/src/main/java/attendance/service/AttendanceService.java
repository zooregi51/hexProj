package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendance.dao.AttendanceDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

public class AttendanceService {

    private AttendanceDao attendanceDao = new AttendanceDao();

    // 전체 근태 기록을 가져오는 메서드
    public List<AttendanceRecord> getAttendanceList() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return attendanceDao.selectAll(conn); // DAO 호출
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 처리
        }
    }

    // 새로운 근태 기록을 추가하는 메서드
    public void addAttendance(AttendanceRecord attendanceRecord) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.insert(conn, attendanceRecord); // DAO 호출
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 처리
        }
    }

    // 기존 근태 기록을 수정하는 메서드
    public void updateAttendance(AttendanceRecord attendanceRecord) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.update(conn, attendanceRecord); // DAO 호출
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 처리
        }
    }

    // 근태 기록을 삭제하는 메서드
    public void deleteAttendance(int recordId) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.delete(conn, recordId); // DAO 호출
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 처리
        }
    }
}
