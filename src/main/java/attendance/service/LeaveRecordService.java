package attendance.service;

import java.sql.Connection;
import java.util.List;
import attendance.dao.LeaveRecordDao;
import attendance.model.LeaveRecord;
import jdbc.connection.ConnectionProvider;

public class LeaveRecordService {

    private LeaveRecordDao leaveRecordDao = new LeaveRecordDao();

    public List<LeaveRecord> getAllLeaveRecords() {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return leaveRecordDao.selectAllRecords(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
