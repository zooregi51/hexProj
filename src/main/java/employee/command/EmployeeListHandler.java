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
		// TODO Auto-generated method stub
		String pageNoVal=req.getParameter("pageNo"); //요청에서 pagoNo라는 이름의 파라미터를 가져오가서 pageNoVal에 넣겟다
		int pageNo=1;
		if(pageNoVal!=null) {
			pageNo=Integer.parseInt(pageNoVal);
		}
		EmployeePage employeePage = employeeListService.getEmployeePage(pageNo);
		req.setAttribute("employeePage", employeePage);
		return "/WEB-INF/view/listEmployee.jsp";
	}

}
