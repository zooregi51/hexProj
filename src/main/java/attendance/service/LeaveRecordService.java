package attendance.service;

import java.sql.Connection;
import java.util.List;
import attendance.dao.LeaveRecordDao;
import attendance.model.LeaveRecord;
import jdbc.connection.ConnectionProvider;

public class LeaveRecordService {
    private LeaveRecordDao leaveRecordDao = new LeaveRecordDao();

    public List<LeaveRecord> getAllLeaveRecords() throws Exception {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return leaveRecordDao.selectAllRecords(conn);
        }
    }
}
