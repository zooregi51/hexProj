package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.ItemizedLedger;
import salary.model.Salary;
import salary.model.SalarySpecification;
import salary.service.GetSalarySpecificationService;

public class SalaryItemizedLedgerHandler implements CommandHandler {

private GetSalarySpecificationService specSer = new GetSalarySpecificationService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String item = req.getParameter("item");
		String year = req.getParameter("year");
		LocalDate now = LocalDate.now();
		if(year == null || year.equals(""))
			year = now.getYear() + "";
		if(item == null || item.equals(""))
			item = "salary";
		ArrayList<ItemizedLedger> spec = specSer.getItemLedger(item, year);
		req.setAttribute("itemizedLedger", spec);
		req.setAttribute("year", year);
		return "/WEB-INF/view/salary/salaryItemizedLedger.jsp?item=" + item + "year=" + year;
		
	}

}
