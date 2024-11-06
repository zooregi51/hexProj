package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import certificate.service.CertificatePage;
import employee.service.EmployeeListService;
import employee.service.EmployeePage;
import mvc.command.CommandHandler;

public class EmployeeListHandler implements CommandHandler {

	private EmployeeListService employeeListService = new EmployeeListService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal=req.getParameter("pageNo"); //요청에서 pagoNo라는 이름의 파라미터를 가져와서 pageNoVal에 넣겟다
		String searchForm=req.getParameter("searchForm");
		int pageNo=1;
		if(pageNoVal!=null) {
			pageNo=Integer.parseInt(pageNoVal);
		}
		if(searchForm!=null) {
			EmployeePage employeePage = employeeListService.getEmployeePage(pageNo,searchForm);
			req.setAttribute("employeePage", employeePage);
			return "/WEB-INF/view/humanresource/listEmployee.jsp";
		}
		EmployeePage employeePage = employeeListService.getEmployeePage(pageNo);
		req.setAttribute("employeePage", employeePage);
		return "/WEB-INF/view/humanresource/listEmployee.jsp";
	}

}
