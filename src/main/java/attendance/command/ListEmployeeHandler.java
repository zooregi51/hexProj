package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import attendance.model.Employee;
import attendance.service.EmployeeService;
import mvc.command.CommandHandler;

public class ListEmployeeHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Employee> employeeList = employeeService.getEmployeeList();
        req.setAttribute("employeeList", employeeList);
        return "/employeeList.jsp"; // JSP 경로
    }
}
