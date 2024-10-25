package salary.command;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		String startDate = req.getParameter("stDate");
		String endDate = req.getParameter("edDate");
		
		LocalDate now = LocalDate.now();
		if(endDate == null || endDate == "") {
			endDate = now.getYear() + "" + 
					(now.getMonthValue() >= 10 ? now.getMonthValue() : "0" + now.getMonthValue()) + 
					((now.getDayOfMonth()) >= 10 ? (now.getDayOfMonth()) : "0" + (now.getDayOfMonth()));
		}

		endDate = endDate.replaceAll("-", "");
		if(startDate == null || startDate == "") {
			startDate = AddDate(endDate, -1, 0, 0);
		}
		
		startDate = startDate.replaceAll("-", "");
		endDate = AddDate(endDate, 0, 0, 1);
		startDate = (startDate).substring(2, 4) + "-" + (startDate).substring(4, 6) + "-" + (startDate).substring(6);
		endDate = (endDate).substring(2, 4) + "-" + (endDate).substring(4, 6) + "-" + (endDate).substring(6);
		
		ArrayList<Salary> salaries = transferSer.getTransferedSalary(startDate, endDate);
		
		req.setAttribute("salaries", salaries);
		return "/WEB-INF/view/salary/salaryTransferCheck.jsp";
		
	}
	public String AddDate(String strDate, int year, int month, int day) throws Exception {
		
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        
		Calendar cal = Calendar.getInstance();
        
		Date dt = dtFormat.parse(strDate);
        
		cal.setTime(dt);
        
		cal.add(Calendar.YEAR,  year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE,  day);
        
		return dtFormat.format(cal.getTime());
	}

}
