package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.dao.EmployeeDao;
import employee.service.EmployeeDeleteService;
import mvc.command.CommandHandler;

public class EmployeeDeleteHandler implements CommandHandler {
	private static final String FORM_VIEW="/WEB-INF/view/humanresource/deleteEmployee.jsp";
	private EmployeeDeleteService employeeDeleteService = new EmployeeDeleteService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] empdelete=req.getParameterValues("checkedempno");
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDeleteService.multiDelete(empdelete);
		
		return FORM_VIEW;
	}

}
