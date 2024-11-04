package attendance.command;

import attendance.service.LeaveRecordService;
import attendance.model.LeaveRecord;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class LeaveRecordHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";  // 데이터베이스 URL
        String dbUser = "system";  // 데이터베이스 사용자 이름
        String dbPassword = "1234";  // 데이터베이스 비밀번호

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            LeaveRecordService service = new LeaveRecordService(conn);
            List<LeaveRecord> leaveRecords = service.getAllLeaveRecords();
            req.setAttribute("leaveRecords", leaveRecords);
        }
//sex
        return "/WEB-INF/view/attendance/leaveRecordList.jsp";
    }
}
