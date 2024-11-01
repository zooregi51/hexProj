package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import attendance.dao.AttendanceRecordDao;
import attendance.model.AttendanceRecord;
import jdbc.connection.ConnectionProvider;

public class AttendanceRecordService {
    private AttendanceRecordDao attendanceRecordDao = new AttendanceRecordDao();

    public List<AttendanceRecord> getAttendanceRecordsByEmpno(int empno) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return attendanceRecordDao.selectByEmpno(conn, empno);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
