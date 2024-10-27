package attendance.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.model.AttendanceRecord;
import attendance.service.AttendanceService;
import mvc.command.CommandHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.model.AttendanceRecord;
import attendance.service.AttendanceService;
import mvc.command.CommandHandler;
import java.util.List;


public class AttendanceRecordHandler implements CommandHandler {

    private AttendanceService attendanceService = new AttendanceService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String employeeId = req.getParameter("employeeId");
        List<AttendanceRecord> records = attendanceService.getAttendanceListByEmployeeId(employeeId);

        req.setAttribute("attendanceRecords", records);
        return "/attendanceRecord.jsp";
    }
}
