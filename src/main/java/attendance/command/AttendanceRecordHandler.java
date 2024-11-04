package attendance.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.model.AttendanceRecord;
import mvc.command.CommandHandler;
import attendance.service.AttendanceRecordService;

public class AttendanceRecordHandler implements CommandHandler {

    private AttendanceRecordService attendanceRecordService = new AttendanceRecordService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        int empno = Integer.parseInt(req.getParameter("empno"));
        
        // empno에 따른 근태 기록 가져오기
        List<AttendanceRecord> attendanceRecords = attendanceRecordService.getAttendanceRecordsByEmpno(empno);
        
        req.setAttribute("attendanceRecords", attendanceRecords); // JSP에 전달할 데이터 설정
        req.setAttribute("empno", empno); // empno를 JSP에 전달
        
        return "/WEB-INF/view/attendance/attendanceRecord.jsp"; // 수정된 경로로 반환
    }
}
