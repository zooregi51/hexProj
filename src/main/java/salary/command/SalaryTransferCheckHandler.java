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

public class SalaryTransferCheckHandler implements CommandHandler {

	private SalaryTransferService transferSer = new SalaryTransferService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		LocalDate now = LocalDate.now();
		if(year == null)
			year = now.getYear() + "";	
		if(month == null)
			month = now.getMonth() + "";
		ArrayList<Salary> salaries = transferSer.getTransferedSalary(year + "/" + month);
		req.setAttribute("salaries", salaries);
		return "/WEB-INF/view/salary/salaryTransfer.jsp";
		
		
		
	}

}
