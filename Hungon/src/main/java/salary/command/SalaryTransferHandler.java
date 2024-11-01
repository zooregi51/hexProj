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

/*
 * 급여이체 페이지
 * /salary/salaryTransfer.do의 요청이 들어오면 처리하는 핸들러
 * 
 * GET 요청이 들어왔을 때 현재 급여가 등록되어 있는 목록을 출력하는 서비스를 실행한다.
 * ArrayList<Salary> salaries
 * 사원들의 미지급된 급여를 DB로부터 저장하는 자료형으로서 view에 salaries로 세팅
 * 
 * POST 요청이 들어왔을 때 등록되어 있는 급여를 이체하는 서비스를 실행한다.
 * 1건이상 UPDATE가 된다면 1이상이 return 되며
 * 0이 return된다면 query 실행이 실패 or update된 건이 0건이란 의미이므로 fail을 출력하도록 함
 */

public class SalaryTransferHandler implements CommandHandler {

	private SalaryTransferService transferSer = new SalaryTransferService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			ArrayList<Salary> salaries = transferSer.getSalary();
			req.setAttribute("salaries", salaries);
			return "/WEB-INF/view/salary/salaryTransfer.jsp";
			
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			int result = transferSer.transferSal();
			if(result > 0)
				return "/WEB-INF/view/salary/salaryTransferSuccess.jsp";
			else
				return "/WEB-INF/view/salary/salaryTransferFail.jsp";
				
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
			
	}

}
