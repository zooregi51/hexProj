package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import attendance.dao.AttendanceDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

public class AttendanceService {

    private AttendanceDao attendanceDao = new AttendanceDao();

    public List<AttendanceRecord> getAttendanceList() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return attendanceDao.selectAll(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAttendance(AttendanceRecord attendanceRecord) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.insert(conn, attendanceRecord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAttendance(AttendanceRecord attendanceRecord) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.update(conn, attendanceRecord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAttendance(int recordId) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            attendanceDao.delete(conn, recordId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
