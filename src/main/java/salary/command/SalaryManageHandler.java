package salary.command;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import salary.model.Employee;
import salary.model.Payment;
import salary.model.Salary;
import salary.model.SalaryLedgerMonth;
import salary.model.SalarySpecification;
import salary.service.GetSalaryLedgerService;
import salary.service.GetSalarySpecificationService;
import salary.service.SalaryManageService;
import salary.service.SalaryTransferService;

/*
 * 급여관리 핸들러
 * /salary/salaryManage.do의 요청이 들어오면 처리하는 핸들러
 * 급여 입력 및 이전에 입력했던 급여의 수정을 하는 페이지
 * 
 * GET 요청이 들어왔을 경우
 * 		type을 확인한다
 * 			- new 일 경우
 * 			  사원번호 항목을 확인
 * 				- 사원번호가 있을 경우
 * 				  사원번호 세팅 후 등록 form 페이지로 이동
 * 			    - 사원번호가 없을 경우
 *                DB에서 이번 달의 급여 입력 목록이 없는 사원 목록을 가져와 
 *                리스트를 세팅하고 사원 목록 출력 페이지로 이동
 *          - update 일 경우
 *              - 사원번호를 확인하여 DB로 부터 해당 사원의 이번 달 급여 정보를 가져오고
 *                급여 정보 세팅 후 수정 form 페이지로 이동
 *          - 없을 경우
 *            이번 달의 급여 목록을 출력
 * POST 요청이 들어왔을 경우
 * 		type을 확인한다
 * 			- new 일 경우
 * 			  정보를 받아와 DB에 insert 후
 * 			  성공 시 리스트 조회 페이지로 이동
 * 			  실패 시 실패 페이지로 이동
 * 			- update일 경우
 * 			  정보를 받아와 해당 사원번호의 값을 수정하고
 *            성공 시 리스트 조회 페이지로 이동
 * 			  실패 시 실패 페이지로 이동
 * 
 * year, month 변수 - 올해 이번달을 세팅
 * empno 변수       - 사원번호
 *                   해당 값이 없으면 0으로 세팅
 * type  변수       - 해당 요청이 신규인지, 수정인지 확인하는 변수
 * */

/*
* 給与管理ハンドラ
* /salary/salaryManage.do の要請が入ってきたら処理するハンドラ
* 給与入力および以前入力した給与の修正を行うページ
*
* GET要請が入ってきた場合
* typeを確認する
* - newの場合
* 社員番号項目を確認
* - 社員番号がある場合
* 社員番号をセットした後、登録フォームページに移動
* - 社員番号がない場合
* DBから今月の給与入力リストがない社員リストを取得する
* リストをセットし、社員リストの出力ページに移動
* - updateの場合
* - 社員番号を確認してDBから該当社員の今月の給与情報を持ってきて
* 給与情報のセッティング後、修正formページへ移動
* - ない場合
* 今月の給与一覧を出力
* POSTリクエストが入ってきた場合
* typeを確認する
* - newの場合
* 情報を受け取り、DBにinsertした後
* 成功するとリスト照会ページに移動
* 失敗した場合、失敗ページに移動
* - updateの場合
* 情報を受け取り、当該社員番号の値を修正し
* 成功するとリスト照会ページに移動
* 失敗した場合、失敗ページに移動
*
* year, month変数 - 今年の今月をセット
* empno変数 - 社員番号
* 該当する値がなければ0に設定
* type変数 - 該当要請が新規なのか、修正なのかを確認する変数
* */

public class SalaryManageHandler implements CommandHandler {

	private SalaryManageService manageSer = new SalaryManageService();
	private GetSalarySpecificationService specSer = new GetSalarySpecificationService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		LocalDate now = LocalDate.now();
		String year, month;
		year = now.getYear() + "";
		month = (now.getMonth().getValue() >= 10 ? now.getMonth().getValue() : "0" + now.getMonth().getValue()) + "";
		if (req.getMethod().equalsIgnoreCase("GET")) {
			int empno = Integer.parseInt(req.getParameter("empno") == null ? "0" : req.getParameter("empno"));
			String type = req.getParameter("type");
			
			if (type != null && type.equals("new")) {
				if (empno != 0) {
					// Get + empno + new = 급여등록페이지
					Employee e = manageSer.getEmployee(empno);
					req.setAttribute("emp", e);
					return "/WEB-INF/view/salary/salaryRegister.jsp";
				}
				// Get + new = 사원리스트
				ArrayList<Employee> emps = manageSer.getEmployeeList(year, month);
				req.setAttribute("emps", emps);
				return "/WEB-INF/view/salary/salaryEmpList.jsp";
			} else if (type != null && type.equals("update")) {

				// Get 요청 + empno + update = 급여 정보 수정
				Salary s = manageSer.getSalary(req.getParameter("empno"), year, month);
				req.setAttribute("salary", s);
				return "/WEB-INF/view/salary/salaryModify.jsp";

			} else {
				// Get = 이번달 급여 리스트
				ArrayList<SalarySpecification> salaries = specSer.getSalarySpecification(year, month);
				req.setAttribute("salaries", salaries);
				return "/WEB-INF/view/salary/salaryList.jsp";
			}
		}

		else if (req.getMethod().equalsIgnoreCase("POST")) {
			Integer empno = Integer.parseInt((String) req.getParameter("empno"));
			String type = (String) req.getParameter("type");
			String salNum = now.getYear() + "-" + (now.getMonthValue() >= 10 ? now.getMonthValue() : "0" + now.getMonthValue()) + "-" + empno;
			
			Salary s = new Salary(salNum, new Employee(empno),
					new Payment(Integer.parseInt((String) req.getParameter("salary")),
							Integer.parseInt((String) req.getParameter("food")),
							Integer.parseInt((String) req.getParameter("childcare")),
							Integer.parseInt((String) req.getParameter("position")),
							Integer.parseInt((String) req.getParameter("longservice")),
							Integer.parseInt((String) req.getParameter("oncall")),
							Integer.parseInt((String) req.getParameter("bonus")),
							Integer.parseInt((String) req.getParameter("holiday"))));
			int result = 0;
			if (type.equals("new"))// Post + new = 입력
				result = manageSer.insertSalary(s);
			else// Post + 수정 = 수정
				result = manageSer.updateSalary(s);
			if (result == 1) {
				ArrayList<SalarySpecification> salaries = specSer.getSalarySpecification(year, month);
				req.setAttribute("salaries", salaries);
				return "/WEB-INF/view/salary/salaryList.jsp";
			}
			return "/WEB-INF/view/salary/salarySaveFailed.jsp";
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

}
