package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Employee;
import salary.model.Payment;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;
import salary.model.SalarySpecification;
import salary.service.GetSalaryLedgerService;
import salary.service.GetSalarySpecificationService;
import salary.service.SalaryManageService;
import salary.service.SalaryTransferService;

public class SalaryManageHandler implements CommandHandler {

	private SalaryManageService manageSer = new SalaryManageService();
	private GetSalarySpecificationService specSer = new GetSalarySpecificationService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			int empno = Integer.parseInt(req.getParameter("empno") == null ? "0" : req.getParameter("empno"));
			String type = req.getParameter("type");
			if(type != null) {
				if(type == "new") {
					if(empno != 0) {
						// Get + empno + new = 급여등록페이지
						Employee e = manageSer.getEmployee(empno);
						req.setAttribute("emp", e);
						return "/WEB-INF/view/salary/salaryRegister.jsp";
					}
					// Get + new = 사원리스트
					ArrayList<Employee> emps = manageSer.getEmployeeList();
					req.setAttribute("emps", emps);
					return "/WEB-INF/view/salary/salaryEmpList.jsp";
				}
				else {
					LocalDate now = LocalDate.now();
					String year, month;
					year = now.getYear() + "";
					month = now.getMonth() + "";
					if(type == "update") {
						// Get 요청 + empno + update = 급여 정보 수정
						Salary s = manageSer.getSalary(req.getParameter("empno"), year, month);
						req.setAttribute("salary", s);
						return "/WEB-INF/view/salary/salaryChange.jsp";
					}
					// Get = 이번달 급여 리스트
					ArrayList<SalarySpecification> salaries = specSer.getSalarySpecification(year, month);
					req.setAttribute("salaries", salaries);
					return "/WEB-INF/view/salary/salaryList.jsp";
				}
			}
			
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			int empno = Integer.parseInt((String) req.getAttribute("empno"));
			String type = (String) req.getAttribute("type");
			
			Salary s = new Salary((String)req.getAttribute("salnum"), new Employee(empno, null, null, null, null, null, null), 
					new Payment(Integer.parseInt((String)req.getAttribute("salary")), 
							Integer.parseInt((String)req.getAttribute("food")), 
							Integer.parseInt((String)req.getAttribute("childcare")), 
							Integer.parseInt((String)req.getAttribute("position")), 
							Integer.parseInt((String)req.getAttribute("longservice")), 
							Integer.parseInt((String)req.getAttribute("oncall")), 
							Integer.parseInt((String)req.getAttribute("bonus")), 
							Integer.parseInt((String)req.getAttribute("holiday"))));
			
			int result = 0;
			if(type == "new")// Post + new = 입력
				result = manageSer.insertSalary(s);
			else// Post + 수정 = 수정
				result = manageSer.updateSalary(s);
			if(result == 1) 
				return "/WEB-INF/view/salary/salarySaveSuccess.jsp";
			return "/WEB-INF/view/salary/salarySaveFailed.jsp";
		} 
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		return null;
	}

}
