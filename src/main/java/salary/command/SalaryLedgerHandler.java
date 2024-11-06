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
 * 급여대장 페이지
 * /salary/salLedger.do의 요청이 들어오면 처리하는 핸들러
 * GET 요청 + term parameter가 들어왔을 때 해당 상세 급여대장을 출력하는 서비스를 실행한다.
 * GET 요청 + year parameter가 들어왔을 때 해당 년도의 달별 급여대장을 출력하는 서비스를 실행한다.
 * GET 요청만 들어왔을 때 현재 년도를 가져오고 현재 년도의 달별 급여대장을 출력하는 서비스를 실행한다.
 * 
 * String year req에 들어있는 year라는 패러미터에서 값을 꺼내옴
 * String term req에 들어있는 term라는 패러미터에서 값을 꺼내옴
 * ArrayList<Salary> ledgerDetail 해당 달의 사원들의 상세 급여대장을 DB로부터 저장하는 자료형으로서 view에 ledgerDetail로 세팅
 * ArrayList<SalaryLedgerMonth> ledgerMonth 해당 달의 급여대장을 DB로부터 저장하는 자료형으로서 view에 ledgerMonth로 세팅
 */

/*
* 給与台帳ページ
* /salary/salLedger.do の要請が入ってきたら処理するハンドラ
* GET要請+term parameterが入ってきた時、該当の詳細給与台帳を出力するサービスを実行する。
* GET要請+year parameterが入ってきた時、該当年度の月別給与台帳を出力するサービスを実行する。
* GET要請だけが入ってきた時、現在年度を持ってきて現在年度の月別給与台帳を出力するサービスを実行する。
*
* String year reqに入っている yearというパラメータから値を取り出す
* String term reqに入っている term というパラメータから値を取り出す
* ArrayList<Salary>ledgerDetail該当月の社員の詳細給与台帳をDBから保存する資料型としてviewにledgerDetailでセッティング
* ArrayList<SalaryLedgerMonth>ledgerMonth該当月の給与台帳をDBから保存する資料型としてviewにledgerMonthでセッティング
*/

public class SalaryLedgerHandler implements CommandHandler {

	private GetSalaryLedgerService ledgerSer = new GetSalaryLedgerService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String year = req.getParameter("year");
		String term = req.getParameter("term");
		
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
			LocalDate now = LocalDate.now();
			ArrayList<SalaryLedgerMonth> ledgerMonth = ledgerSer.getYearLedgerMonth(now.getYear());
			req.setAttribute("ledgerMonth", ledgerMonth);
			return "/WEB-INF/view/salary/salaryLedgerList.jsp";
		}
		
	}

}
