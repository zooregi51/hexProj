package mvc.command.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.service.employee.EmployeeService;
import mvc.model.employee.Employee;

import java.util.List;

public class EmployeeHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Employee> employeeList = employeeService.getEmployeeList();
        req.setAttribute("employeeList", employeeList);
        return "/WEB-INF/view/employeeList.jsp";  // 사원 리스트를 보여주는 JSP로 이동
    }
}
