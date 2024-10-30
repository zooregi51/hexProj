package attendance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import attendance.dao.LeaveRecordDao;
import attendance.model.LeaveRecord;
import jdbc.connection.ConnectionProvider;

public class LeaveRecordService {
    private LeaveRecordDao leaveRecordDao = new LeaveRecordDao();

    public List<LeaveRecord> getLeaveRecordsByEmpno(int empno) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return leaveRecordDao.selectByEmpno(conn, empno);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
