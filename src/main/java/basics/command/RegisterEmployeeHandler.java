package basics.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basics.model.Employee;
import basics.service.RegisterEmployeeService;
import mvc.command.CommandHandler;

public class RegisterEmployeeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newEmployeeForm.jsp"; // 등록 폼 JSP 경로
	private RegisterEmployeeService employeeService = new RegisterEmployeeService(); // 사원 등록 서비스

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// GET 방식일 경우 등록 폼을 보여줌
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res); // GET 요청 처리 - 폼 보여주기
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // POST 요청 처리 - 폼 제출 처리
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 허용되지 않는 방식의 요청 처리
			return null;
		}
	}

	// 등록 폼을 처리하는 메서드 (GET 요청 처리)
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		int nextEmpNo = employeeService.getNextEmpNo(); // 다음 사원 번호 조회
		req.setAttribute("nextEmpNo", nextEmpNo); // JSP로 전달
		return FORM_VIEW; // 폼 페이지로 이동
	}

	// 제출된 데이터를 처리하는 메서드 (POST 요청 처리)
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 폼에서 입력된 데이터를 Employee 객체로 변환
		Employee employeeReq = createEmployee(req);

		// 사원 정보 데이터베이스에 저장
		employeeService.employee(employeeReq);

		// 등록 완료 후 성공 페이지로 이동
		req.setAttribute("newEmployeeNo", employeeReq.getEmpNo());
		return "/WEB-INF/view/newEmployeeSuccess.jsp";
	}

	// 폼 데이터로부터 Employee 객체를 생성하는 메서드
	private Employee createEmployee(HttpServletRequest req) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredDate = null;
		Date retiredDate = null;
		Integer salary = null;

		try {
			// 채용일과 퇴사일을 문자열에서 Date 객체로 변환
			hiredDate = dateFormat.parse(req.getParameter("hiredDate"));
			retiredDate = dateFormat.parse(req.getParameter("retiredDate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 // salary 값을 빈 문자열일 경우 null로 처리
		try {
			String salaryStr = req.getParameter("salary");
			if (salaryStr != null && !salaryStr.trim().isEmpty()) {
				salary = Integer.parseInt(salaryStr); // 입력된 경우만 변환하여 설정
			}
		} catch (NumberFormatException e) {
			// 변환 실패 시 null 값 그대로 유지
			e.printStackTrace();
		}

		return new Employee(Integer.parseInt(req.getParameter("empNo")), // 사원 번호
				req.getParameter("empForm"), // 고용 형태
				req.getParameter("name"), // 이름
				hiredDate, // 채용일
				retiredDate, // 퇴사일
				req.getParameter("dep"), // 부서
				req.getParameter("position"), // 직위
				req.getParameter("registrationNum"), // 등록 번호
				req.getParameter("address"), // 주소
				req.getParameter("phone"), // 전화 번호
				req.getParameter("email"), // 이메일
				req.getParameter("other"), // 기타 정보
				salary // 급여
		);
	}
}