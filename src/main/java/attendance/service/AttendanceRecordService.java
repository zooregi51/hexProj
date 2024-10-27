package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import attendance.dao.AttendanceRecordDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

public class AttendanceRecordService {
    private AttendanceRecordDao recordDao = new AttendanceRecordDao();

    public List<AttendanceRecord> getAttendanceListByEmployeeId(String employeeId) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return recordDao.selectByEmployeeId(conn, employeeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAttendanceRecord(AttendanceRecord record) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            recordDao.insert(conn, record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAttendanceRecord(AttendanceRecord record) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            recordDao.update(conn, record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAttendanceRecord(int recordId) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            recordDao.delete(conn, recordId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
