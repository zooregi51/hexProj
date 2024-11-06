package attendance.command;

import attendance.model.DiligenceRecord;
import attendance.service.DiligenceService;
import mvc.command.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DiligenceHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String dbUser = "system";
        String dbPassword = "1234";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            DiligenceService service = new DiligenceService(conn);
            List<DiligenceRecord> diligenceRecords = service.getAllRecords();
            req.setAttribute("diligenceRecords", diligenceRecords);
        }
        return "/WEB-INF/view/attendance/Diligence.jsp";
    }
}
