package attendance.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.model.Employee;
import attendance.service.EmployeeService;
import mvc.command.CommandHandler;

public class ListEmployeeHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService(); // EmployeeService 인스턴스 생성

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 모든 사원 정보를 가져옴
        List<Employee> employeeList = employeeService.getEmployeeList();
        
        // 사원 정보를 request에 추가하여 JSP로 전달
        req.setAttribute("employeeList", employeeList);
        
        // employeeList.jsp로 포워딩 (정확한 경로)
        return "/employeeList.jsp";
    }
}
