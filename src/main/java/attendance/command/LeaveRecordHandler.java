package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.service.LeaveRecordService;
import mvc.command.CommandHandler;
import java.util.List;
import attendance.model.LeaveRecord;

public class LeaveRecordHandler implements CommandHandler {
    private LeaveRecordService leaveRecordService = new LeaveRecordService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<LeaveRecord> leaveRecords = leaveRecordService.getAllLeaveRecords();
        req.setAttribute("leaveRecords", leaveRecords);
        return "/WEB-INF/view/attendance/leaveRecordList.jsp";
    }
}
