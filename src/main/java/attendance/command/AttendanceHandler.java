package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import attendance.service.AttendanceService;
import attendance.model.AttendanceRecord;
import java.sql.Date;
import java.util.List;

public class AttendanceHandler implements CommandHandler {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            return processList(req, res);  // 근태 기록 조회
        } else if (action.equals("add")) {
            return processAdd(req, res);   // 근태 기록 추가
        } else if (action.equals("edit")) {
            return processEdit(req, res);  // 근태 기록 수정
        } else if (action.equals("delete")) {
            return processDelete(req, res);  // 근태 기록 삭제
        }
        return null;
    }

    private String processList(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<AttendanceRecord> records = attendanceService.getAttendanceRecords();  // 전체 근태 기록을 가져옴
        req.setAttribute("records", records);
        return "/WEB-INF/view/attendanceList.jsp";  // 조회 결과를 출력할 JSP 경로
    }

    private String processAdd(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return "/WEB-INF/view/addAttendanceForm.jsp";  // 근태 기록 추가 폼 JSP 경로
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            String employeeId = req.getParameter("employeeId");
            Date workDate = Date.valueOf(req.getParameter("workDate"));  // yyyy-MM-dd 형식이어야 함
            String startTime = req.getParameter("startTime");  // HH:mm:ss 형식이어야 함
            String endTime = req.getParameter("endTime");
            String workType = req.getParameter("workType");
            String approvalStatus = req.getParameter("approvalStatus");
            int totalOvertimeHours = Integer.parseInt(req.getParameter("totalOvertimeHours"));

            // AttendanceRecord에 맞는 생성자 호출
            AttendanceRecord newRecord = new AttendanceRecord(employeeId, workDate, startTime, endTime, workType, approvalStatus, totalOvertimeHours);

            attendanceService.addAttendance(newRecord);  // 근태 기록 추가 서비스 호출
            return "redirect:/attendance.do?action=list";  // 추가 후 근태 기록 리스트로 리다이렉트
        }
        return null;
    }

    private String processEdit(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            String recordId = req.getParameter("recordId");
            AttendanceRecord record = attendanceService.getAttendanceById(recordId);  // 수정할 근태 기록을 가져옴
            req.setAttribute("record", record);
            return "/WEB-INF/view/editAttendanceForm.jsp";  // 수정 폼 JSP 경로 반환
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            String recordId = req.getParameter("recordId");
            String employeeId = req.getParameter("employeeId");
            Date workDate = Date.valueOf(req.getParameter("workDate"));
            String startTime = req.getParameter("startTime");
            String endTime = req.getParameter("endTime");
            String workType = req.getParameter("workType");
            String approvalStatus = req.getParameter("approvalStatus");
            int totalOvertimeHours = Integer.parseInt(req.getParameter("totalOvertimeHours"));

            AttendanceRecord updatedRecord = new AttendanceRecord(employeeId, workDate, startTime, endTime, workType, approvalStatus, totalOvertimeHours);
            attendanceService.updateAttendance(recordId, updatedRecord);  // 근태 기록 수정 서비스 호출
            return "redirect:/attendance.do?action=list";  // 수정 후 근태 기록 리스트로 리다이렉트
        }
        return null;
    }

    private String processDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String recordId = req.getParameter("recordId");
        attendanceService.deleteAttendance(recordId);  // 근태 기록 삭제 서비스 호출
        return "redirect:/attendance.do?action=list";  // 삭제 후 근태 기록 리스트로 리다이렉트
    }
}
