package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Salary;
import salary.model.SalarySpecification;
import salary.service.GetSalarySpecificationService;

public class SalarySpecificationHandler implements CommandHandler {

private GetSalarySpecificationService specSer = new GetSalarySpecificationService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String empNo = req.getParameter("empNo");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		
		if(empNo != null) {
			// 해당 기간 선택한 직원의 상세 급여명세서 출력
			Salary spec = specSer.getSalary(empNo, year, month);
			req.setAttribute("specDetail", spec);
			return "/WEB-INF/view/salary/salarySpecificationDetail.jsp?empNo=" + empNo + "year=" + year + "month=" + month;
		} else {
			LocalDate now = LocalDate.now();
			if(year == null)
				year = now.getYear() + "";	
			if(month == null)
				month = now.getMonth() + "";
			ArrayList<SalarySpecification> spec = specSer.getSalarySpecification(year, month);
			req.setAttribute("specDetail", spec);
			return "/WEB-INF/view/salary/salarySpecification.jsp?year=" + year + "month=" + month;
		}
		
	}

}
