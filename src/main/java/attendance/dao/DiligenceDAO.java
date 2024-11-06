package attendance.dao;

import attendance.model.DiligenceRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiligenceDAO {
    private Connection conn;

    public DiligenceDAO(Connection conn) {
        this.conn = conn;
    }

    public List<DiligenceRecord> getAllDiligenceRecords() throws SQLException {
        List<DiligenceRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM DiligenceRecords";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DiligenceRecord record = new DiligenceRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getString("DiligenceType"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate"),
                    rs.getInt("DiligenceDays"),
                    rs.getString("Remarks")
                );
                records.add(record);
            }
        }
        return records;
    }
}
