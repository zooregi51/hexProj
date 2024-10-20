package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;
import salary.service.GetSalaryLedgerService;

public class SalaryLedgerHandler implements CommandHandler {

	private GetSalaryLedgerService ledgerSer = new GetSalaryLedgerService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String year = req.getParameter("year");
		String term = req.getParameter("month");
		
		if(term != null) {
			// 해당 기간의 상세 급여대장 출력
			ArrayList<Salary> ledgerDetail = ledgerSer.getDetailLedger(term);
			req.setAttribute("ledgerDetail", ledgerDetail);
			return "/WEB-INF/view/salary/salaryLedgerListDetail.jsp?term=" + term;
		} else if(year != null) {
			// 해당 년도의 급여대장 출력
			ArrayList<SalaryLedgerMonth> ledgerMonth = ledgerSer.getYearLedgerMonth(Integer.parseInt(year));
			req.setAttribute("ledgerMonth", ledgerMonth);
			return "/WEB-INF/view/salary/salaryLedgerList.jsp?year=" + year;
		} else {
			// 기본값(현재 년도) 급여대장 출력
			// 급여대장 model에 
			LocalDate now = LocalDate.now();
			ArrayList<SalaryLedgerMonth> ledgerMonth = ledgerSer.getYearLedgerMonth(now.getYear());
			req.setAttribute("ledgerMonth", ledgerMonth);
			return "/WEB-INF/view/salary/salaryLedgerList.jsp";
		}
		
	}

}
