package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.model.AttendanceRecord;
import attendance.service.AttendanceService;
import mvc.command.CommandHandler;
import java.util.List;

public class AttendanceHandler implements CommandHandler {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String action = req.getParameter("action");

        if ("list".equals(action)) {
            return processList(req, res);
        } else if ("add".equals(action)) {
            return processAdd(req, res);
        } else if ("edit".equals(action)) {
            return processEdit(req, res);
        } else if ("delete".equals(action)) {
            return processDelete(req, res);
        } else {
            return "/attendanceList.jsp";
        }
    }

    private String processList(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<AttendanceRecord> attendanceList = attendanceService.getAttendanceList();
        req.setAttribute("attendanceList", attendanceList);
        return "/attendanceList.jsp";
    }

    private String processAdd(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 추가 처리 코드 구현
        return "redirect:/attendance/list.do";
    }

    private String processEdit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 수정 처리 코드 구현
        return "redirect:/attendance/list.do";
    }

    private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 삭제 처리 코드 구현
        return "redirect:/attendance/list.do";
    }

    // 추가, 수정, 삭제 메서드는 필요에 따라 구현합니다.
}
