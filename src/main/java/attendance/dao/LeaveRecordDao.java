package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import attendance.model.LeaveRecord;

public class LeaveRecordDao {

    public List<LeaveRecord> selectAllRecords(Connection conn) throws Exception {
        String sql = "SELECT RecordID, EmployeeID, LeaveType, TotalDays, DaysUsed, RemainingDays FROM LeaveRecords";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<LeaveRecord> records = new ArrayList<>();
            while (rs.next()) {
                records.add(new LeaveRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getString("LeaveType"),
                    rs.getInt("TotalDays"),
                    rs.getInt("DaysUsed"),
                    rs.getInt("RemainingDays")
                ));
            }
            return records;
        }
    }
}
