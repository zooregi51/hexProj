package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.service.LeaveRecordService;
import attendance.model.LeaveRecord;
import mvc.command.CommandHandler;
import java.util.ArrayList;
import java.util.List;

public class LeaveRecordHandler implements CommandHandler {

    private LeaveRecordService leaveRecordService = new LeaveRecordService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String empnosStr = req.getParameter("empnos");
        List<LeaveRecord> allLeaveRecords = new ArrayList<>();

        // empnos 파라미터가 제공되지 않은 경우, 단일 empno 파라미터 확인
        if (empnosStr == null || empnosStr.trim().isEmpty()) {
            String empnoStr = req.getParameter("empno");
            if (empnoStr == null || empnoStr.trim().isEmpty()) {
                req.setAttribute("error", "사원 번호가 제공되지 않았습니다.");
                return "/WEB-INF/view/error.jsp";
            }

            try {
                int empno = Integer.parseInt(empnoStr.trim());
                allLeaveRecords = leaveRecordService.getLeaveRecordsByEmpno(empno);
                req.setAttribute("empno", empno); // 단일 empno를 JSP에 전달
            } catch (NumberFormatException e) {
                req.setAttribute("error", "유효하지 않은 사원 번호가 포함되어 있습니다.");
                return "/WEB-INF/view/error.jsp";
            }
        } else {
            // 여러 사원 번호가 포함된 경우
            String[] empnoArray = empnosStr.split(",");
            List<Integer> empnos = new ArrayList<>();
            for (String empnoStr : empnoArray) {
                try {
                    empnos.add(Integer.parseInt(empnoStr.trim()));
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "유효하지 않은 사원 번호가 포함되어 있습니다.");
                    return "/WEB-INF/view/error.jsp";
                }
            }

            // 각 empno에 따른 휴가 기록 가져오기
            for (int empno : empnos) {
                allLeaveRecords.addAll(leaveRecordService.getLeaveRecordsByEmpno(empno));
            }
            req.setAttribute("empnos", empnosStr); // JSP에 전달할 empnos 문자열
        }

        req.setAttribute("leaveRecords", allLeaveRecords); // JSP에 전달할 데이터 설정
        return "/WEB-INF/view/attendance/leaveRecord.jsp";
    }
}
