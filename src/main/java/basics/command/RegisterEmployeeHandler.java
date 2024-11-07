package basics.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.Employee;
import basics.service.RegisterEmployeeService;
import mvc.command.CommandHandler;

// RegisterEmployeeHandler는 사원 등록을 처리하는 CommandHandler입니다.
// RegisterEmployeeHandlerは従業員登録を処理するCommandHandlerです。

public class RegisterEmployeeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/basics/newEmployeeForm.jsp"; // 등록 폼 JSP 경로.
	// 登録フォームJSPのパス。
	private RegisterEmployeeService employeeService = new RegisterEmployeeService(); // 사원 등록 서비스.
	// 従業員登録サービス。

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// GET 방식일 경우 등록 폼을 보여줌.
		// GETメソッドの場合、登録フォームを表示。
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res); // GET 요청 처리 - 폼 보여주기.
			// GETリクエスト処理 - フォーム表示。
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // POST 요청 처리 - 폼 제출 처리.
			// POSTリクエスト処理 - フォームの提出処理。
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 허용되지 않는 방식의 요청 처리.
			// 許可されていないメソッドのリクエスト処理。
			return null;
		}
	}

	// 등록 폼을 처리하는 메서드. (GET 요청 처리)
	// 登録フォームを処理するメソッド。 (GETリクエスト処理)
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		int nextEmpNo = employeeService.getNextEmpNo(); // 다음 사원 번호 조회.
		// 次の従業員番号を取得。
		req.setAttribute("nextEmpNo", nextEmpNo); // JSP로 전달.
		// JSPに送る。
		return FORM_VIEW; // 폼 페이지로 이동.
		// フォームページに移動。
	}

	// 제출된 데이터를 처리하는 메서드. (POST 요청 처리)
	// 提出されたデータを処理するメソッド。(POSTリクエスト処理)
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 폼에서 입력된 데이터를 Employee 객체로 변환.
		// フォームに入力されたデータをEmployeeオブジェクトに変換。
		Employee employeeReq = createEmployee(req);

		// 사원 정보 데이터베이스에 저장.
		// 従業員情報をデータベースに保存。
		employeeService.employee(employeeReq);

		// 등록 완료 후 성공 페이지로 이동.
		// 登録完了後、成功ページに移動。
		req.setAttribute("newEmployeeNo", employeeReq.getEmpNo());
		return "/WEB-INF/view/basics/newEmployeeSuccess.jsp";
	}

	// 폼 데이터로부터 Employee 객체를 생성하는 메서드.
	// フォームデータからEmployeeオブジェクトを生成するメソッド。
	private Employee createEmployee(HttpServletRequest req) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredDate = null;
		Date retiredDate = null;
		Integer salary = null;

		try {
			// 채용일과 퇴사일을 문자열에서 Date 객체로 변환.
			// 採用日と退職日を文字列からDateオブジェクトに変換。
			hiredDate = dateFormat.parse(req.getParameter("hiredDate"));
			retiredDate = dateFormat.parse(req.getParameter("retiredDate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// salary 값을 빈 문자열일 경우 null로 처리.
		// salaryの値が空文字の場合はnullとして処理。
		try {
			String salaryStr = req.getParameter("salary");
			if (salaryStr != null && !salaryStr.trim().isEmpty()) {
				salary = Integer.parseInt(salaryStr); // 입력된 경우만 변환하여 설정.
				// 入力された場合のみ変換して設定。
			}
		} catch (NumberFormatException e) {
			// 변환 실패 시 null 값 그대로 유지.
			// 変換失敗時はnull値のまま保持。
			e.printStackTrace();
		}

		// Employee 객체 생성 및 반환.
		// Employeeオブジェクトの生成と返却。
		return new Employee(Integer.parseInt(req.getParameter("empNo")), // 사원 번호
				req.getParameter("empForm"), // 고용 형태. 雇用形態。
				req.getParameter("name"), // 이름. 氏名。
				hiredDate, // 채용일. 入社日。
				retiredDate, // 퇴사일. 退社日。
				req.getParameter("dep"), // 부서. 部署。
				req.getParameter("position"), // 직위. 職位。
				req.getParameter("registrationNum"), // 주민등록번호. 個人ID番号。
				req.getParameter("address"), // 주소. 現在所。
				req.getParameter("phone"), // 전화 번호. 携帯番号。
				req.getParameter("email"), // 이메일. E-maill。
				req.getParameter("other"), // 기타 정보. 備考。
				salary // 급여. 給与。
		);
	}
}