package attendance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.service.EmployeeService;
import mvc.command.CommandHandler;
import java.util.List;
import attendance.model.Employee;

public class EmployeeHandler implements CommandHandler {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Employee> employeeList = employeeService.getEmployeeList();
        req.setAttribute("employeeList", employeeList);  // Employee 리스트를 request에 저장
        return "/employeeList.jsp";  // JSP 페이지로 포워딩
    }
}
