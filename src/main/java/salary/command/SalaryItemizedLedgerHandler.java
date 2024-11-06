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

/*
 * 항목별 대장 페이지 핸들러
 * /salary/salaryItemizedLedger.do의 요청이 들어오면 처리하는 핸들러
 * 급여 항목, 연도를 설정하면 해당 급여 항목에 대한 선택한 년도의 1월부터 12월까지의 급여 대장이 출력된다
 * item 변수     - 급여 항목
 * 				  요청을 받았을 때 해당 값이 없으면
 *                초기값은 기본급으로 설정
 * year 변수     - 연도
 *                요청을 받았을 때 해당 값이 없으면
 *                초기값은 올해로 설정
 * 
 * */

/*
* 項目別台帳ページハンドラ
* /salary/salaryItemizedLedger.do の要請が入ってきたら処理するハンドラ
* 給与項目、年度を設定すると、当該給与項目について選択した年度の1月から12月までの給与台帳が出力される
* item変数 - 給与項目
* 要請を受けたときに、該当する値がない場合は
* 初期値は基本給に設定
* year変数 - 年度
* 要請を受けたときに、該当する値がない場合は
* 初期値は今年に設定
*
* */

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
