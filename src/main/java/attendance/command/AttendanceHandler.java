package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.model.AttendanceRecord;
import attendance.service.AttendanceService;
import mvc.command.CommandHandler;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AttendanceHandler implements CommandHandler {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String action = req.getParameter("action");
        
        // 액션에 따라 처리 분기
        if ("list".equals(action)) {
            return processList(req, res);  // 근태 기록 리스트 처리
        } else if ("add".equals(action)) {
            return processAdd(req, res);   // 근태 기록 추가 처리
        } else if ("edit".equals(action)) {
            return processEdit(req, res);  // 근태 기록 수정 처리
        } else if ("delete".equals(action)) {
            return processDelete(req, res); // 근태 기록 삭제 처리
        } else {
            return "/attendanceList.jsp"; // 기본 리스트 페이지로 이동
        }
    }

    // 근태 기록 리스트를 처리하는 메서드
    private String processList(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<AttendanceRecord> attendanceList = attendanceService.getAttendanceList();
        req.setAttribute("attendanceList", attendanceList); // 리스트를 request에 저장
        return "/attendanceList.jsp"; // JSP로 포워딩
    }

    // 근태 기록 추가를 처리하는 메서드
    private String processAdd(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String employeeId = req.getParameter("employeeId");
        Date workDate = Date.valueOf(req.getParameter("workDate"));
        Time startTime = Time.valueOf(req.getParameter("startTime"));
        Time endTime = Time.valueOf(req.getParameter("endTime"));
        String workType = req.getParameter("workType");
        String approvalStatus = req.getParameter("approvalStatus");
        int totalOvertimeHours = Integer.parseInt(req.getParameter("totalOvertimeHours"));

        AttendanceRecord newRecord = new AttendanceRecord(
                employeeId, workDate, startTime, endTime, workType, approvalStatus, totalOvertimeHours);

        attendanceService.addAttendance(newRecord); // 서비스 호출하여 추가

        return "redirect:/attendance.do?action=list"; // 추가 후 리스트로 리다이렉트
    }

    // 근태 기록 수정을 처리하는 메서드
    private String processEdit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        int recordId = Integer.parseInt(req.getParameter("recordId"));
        String employeeId = req.getParameter("employeeId");
        Date workDate = Date.valueOf(req.getParameter("workDate"));
        Time startTime = Time.valueOf(req.getParameter("startTime"));
        Time endTime = Time.valueOf(req.getParameter("endTime"));
        String workType = req.getParameter("workType");
        String approvalStatus = req.getParameter("approvalStatus");
        int totalOvertimeHours = Integer.parseInt(req.getParameter("totalOvertimeHours"));

        AttendanceRecord updatedRecord = new AttendanceRecord(
                recordId, employeeId, workDate, startTime, endTime, workType, approvalStatus, totalOvertimeHours);

        attendanceService.updateAttendance(updatedRecord); // 서비스 호출하여 수정

        return "redirect:/attendance.do?action=list"; // 수정 후 리스트로 리다이렉트
    }

    // 근태 기록 삭제를 처리하는 메서드
    private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
        int recordId = Integer.parseInt(req.getParameter("recordId"));
        attendanceService.deleteAttendance(recordId); // 서비스 호출하여 삭제
        return "redirect:/attendance.do?action=list"; // 삭제 후 리스트로 리다이렉트
    }
}
