package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Salary;
import salary.model.SalarySpecification;
import salary.service.GetSalarySpecificationService;

/*
 * 급여명세서 페이지 핸들러
 * /salary/salarySpec.do의 요청이 들어오면 처리하는 핸들러
 * 사원번호, 연도, 달을 받아 해당 사원의 급여 명세서를 출력하는 페이지
 * 
 * empNo가 없을 경우
 * 		- year와 month 값을 확인하여 없으면 초기값(올해, 이번달)을 세팅하고
 * 		  해당 년, 달에 이체된 급여 목록을 DB에서 받아 
 * 		  사원들의 급여 리스트로 출력
 * empNo가 있을 경우
 * 		- 해당 사원의 선택한 년, 달에 이체된 상세 급여 정보를
 *        DB에서 받아 급여 명세서 테이블로 출력
 * 
 * empno 변수 - 사원번호
 * year, month 변수 - 년, 월
 *                   해당 값이 없을 경우 
 *                   올해 이번달로 세팅
 * 
 * */

/*
* 給与明細書ページハンドラー
* /salary/salarySpec.do の要請が入ってきたら処理するハンドラ
* 社員番号、年度、月を受けて当該社員の給与明細書を出力するページ
*
* empNoがない場合
* - yearとmonth値を確認していなければ初期値（今年、今月）をセットし
* 当該年、月に振り込まれた給与リストをDBから受け取り
* 社員の給与リストとして出力
* empNoがある場合
* - 当該社員の選択した年、月に振り込まれた詳細な給与情報を
* DBから受け取り給与明細書テーブルに出力
*
* empno変数 - 社員番号
* year, month変数 - 年, 月
* 該当する値がない場合
* 今年、今月でセッティング
*
* */

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
			req.setAttribute("specs", spec);
			req.setAttribute("year", year);
			req.setAttribute("month", month);
			return "/WEB-INF/view/salary/salarySpecification.jsp?year=" + year + "month=" + month;
		}
		
	}

}
