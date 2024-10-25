package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.EmployeeService;
import mvc.command.CommandHandler;

public class EmployeeAttendanceRecordHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String empno = req.getParameter("empno");
        
        // empno를 통해 해당 사원의 근태기록을 조회
        req.setAttribute("attendanceRecords", employeeService.getAttendanceRecords(Integer.parseInt(empno)));
        
        // 근태기록 JSP로 포워딩
        return "/employeeAttendance.jsp";
    }
}
