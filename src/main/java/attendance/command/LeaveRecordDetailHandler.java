package attendance.command;

import attendance.service.LeaveRecordService;
import attendance.model.LeaveRecord;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class LeaveRecordDetailHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";  // 데이터베이스 URL
        String dbUser = "system";  // 데이터베이스 사용자 이름
        String dbPassword = "1234";  // 데이터베이스 비밀번호

        int empno = Integer.parseInt(req.getParameter("empno"));

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            LeaveRecordService service = new LeaveRecordService(conn);
            List<LeaveRecord> leaveRecords = service.getLeaveRecordsByEmployee(empno);  // 특정 사원의 기록을 가져오는 메서드
            req.setAttribute("leaveRecords", leaveRecords);
        }

        return "/WEB-INF/view/attendance/leaveRecordDetail.jsp";
    }
}
