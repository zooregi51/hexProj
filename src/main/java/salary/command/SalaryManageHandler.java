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
		LocalDate now = LocalDate.now();
		String year, month;
		year = now.getYear() + "";
		month = (now.getMonth().getValue() >= 10 ? now.getMonth().getValue() : "0" + now.getMonth().getValue()) + "";
		if (req.getMethod().equalsIgnoreCase("GET")) {
			int empno = Integer.parseInt(req.getParameter("empno") == null ? "0" : req.getParameter("empno"));
			String type = req.getParameter("type");
			
			if (type != null && type.equals("new")) {
				if (empno != 0) {
					// Get + empno + new = 급여등록페이지
					Employee e = manageSer.getEmployee(empno);
					req.setAttribute("emp", e);
					return "/WEB-INF/view/salary/salaryRegister.jsp";
				}
				// Get + new = 사원리스트
				ArrayList<Employee> emps = manageSer.getEmployeeList(year, month);
				req.setAttribute("emps", emps);
				return "/WEB-INF/view/salary/salaryEmpList.jsp";
			} else if (type != null && type.equals("update")) {

				// Get 요청 + empno + update = 급여 정보 수정
				Salary s = manageSer.getSalary(req.getParameter("empno"), year, month);
				req.setAttribute("salary", s);
				return "/WEB-INF/view/salary/salaryModify.jsp";

			} else {
				// Get = 이번달 급여 리스트
				ArrayList<SalarySpecification> salaries = specSer.getSalarySpecification(year, month);
				req.setAttribute("salaries", salaries);
				return "/WEB-INF/view/salary/salaryList.jsp";
			}
		}

		else if (req.getMethod().equalsIgnoreCase("POST")) {
			int empno = Integer.parseInt((String) req.getParameter("empno"));
			String type = (String) req.getParameter("type");
			String salNum = now.getYear() + "-" + (now.getMonthValue() >= 10 ? now.getMonthValue() : "0" + now.getMonthValue()) + "-" + empno;
			
			Salary s = new Salary(salNum, new Employee(empno),
					new Payment(Integer.parseInt((String) req.getParameter("salary")),
							Integer.parseInt((String) req.getParameter("food")),
							Integer.parseInt((String) req.getParameter("childcare")),
							Integer.parseInt((String) req.getParameter("position")),
							Integer.parseInt((String) req.getParameter("longservice")),
							Integer.parseInt((String) req.getParameter("oncall")),
							Integer.parseInt((String) req.getParameter("bonus")),
							Integer.parseInt((String) req.getParameter("holiday"))));
			int result = 0;
			if (type.equals("new"))// Post + new = 입력
				result = manageSer.insertSalary(s);
			else// Post + 수정 = 수정
				result = manageSer.updateSalary(s);
			if (result == 1) {
				ArrayList<SalarySpecification> salaries = specSer.getSalarySpecification(year, month);
				req.setAttribute("salaries", salaries);
				return "/WEB-INF/view/salary/salaryList.jsp";
			}
			return "/WEB-INF/view/salary/salarySaveFailed.jsp";
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

}
