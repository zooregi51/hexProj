package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;
import salary.service.GetSalaryLedgerService;
import salary.service.SalaryTransferService;

public class SalaryTransferHandler implements CommandHandler {

	private SalaryTransferService transferSer = new SalaryTransferService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			ArrayList<Salary> salaries = transferSer.getSalary();
			req.setAttribute("salaries", salaries);
			return "/WEB-INF/view/salary/salaryTransfer.jsp";
			
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			int check = transferSer.transferSal();
			if(check == 1)
				return "/WEB-INF/view/salary/salaryTransferSuccess.jsp";
			return "/WEB-INF/view/salary/salaryTransferFailed.jsp";
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
			
	}

}
