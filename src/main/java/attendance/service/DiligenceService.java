package attendance.service;

import attendance.dao.DiligenceDAO;
import attendance.model.DiligenceRecord;
import java.sql.Connection;
import java.util.List;

public class DiligenceService {
    private DiligenceDAO diligenceDAO;

    public DiligenceService(Connection conn) {
        this.diligenceDAO = new DiligenceDAO(conn);
    }

    public List<DiligenceRecord> getAllRecords() throws Exception {
        return diligenceDAO.getAllDiligenceRecords();
    }
}
