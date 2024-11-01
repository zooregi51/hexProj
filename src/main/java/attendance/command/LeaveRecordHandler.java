package attendance.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.model.LeaveRecord;
import attendance.service.LeaveRecordService;
import mvc.command.CommandHandler;

public class LeaveRecordHandler implements CommandHandler {

    private LeaveRecordService leaveRecordService = new LeaveRecordService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<LeaveRecord> leaveRecords = leaveRecordService.getAllLeaveRecords();
        req.setAttribute("leaveRecords", leaveRecords);
        return "/WEB-INF/view/leaveRecordList.jsp";
    }
}
