package attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import attendance.model.LeaveRecord;

public class LeaveRecordDao {

    public List<LeaveRecord> selectAllRecords(Connection conn) throws Exception {
        String sql = "SELECT e.empform AS employmentType, e.empno, e.name, e.dep, e.position, " +
                     "l.leavetype, l.totaldays, l.daysused, l.remainingdays " +
                     "FROM Employee e " +
                     "JOIN LeaveRecords l ON e.empno = l.employeeid";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<LeaveRecord> leaveRecords = new ArrayList<>();
            while (rs.next()) {
                LeaveRecord record = new LeaveRecord(
                    rs.getString("employmentType"),
                    rs.getInt("empno"),
                    rs.getString("name"),
                    rs.getString("dep"),
                    rs.getString("position"),
                    rs.getString("leavetype"),
                    rs.getInt("totaldays"),
                    rs.getInt("daysused"),
                    rs.getInt("remainingdays")
                );
                leaveRecords.add(record);
            }
            return leaveRecords;
        }
    }
}
