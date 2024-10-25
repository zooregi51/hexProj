package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import attendance.service.EmployeeService;
import mvc.command.CommandHandler;
import java.util.List;
import attendance.model.Employee;

public class EmployeeHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService(); // 서비스 인스턴스 생성

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 직원 목록 가져오기
        List<Employee> employeeList = employeeService.getEmployeeList();
        req.setAttribute("employeeList", employeeList); // request 객체에 리스트 저장
        return "/employeeList.jsp"; // JSP 페이지로 이동
    }
}
