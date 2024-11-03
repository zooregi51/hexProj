package attendance.service;

import attendance.dao.LeaveRecordDao;
import attendance.model.LeaveRecord;
import java.sql.Connection;
import java.util.List;

public class LeaveRecordService {
    private LeaveRecordDao leaveRecordDao;

    public LeaveRecordService(Connection conn) {
        this.leaveRecordDao = new LeaveRecordDao(conn);
    }

    public List<LeaveRecord> getAllLeaveRecords() throws Exception {
        return leaveRecordDao.selectAllRecords();
    }
}
