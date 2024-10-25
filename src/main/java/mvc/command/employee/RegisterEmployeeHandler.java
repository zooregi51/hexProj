package mvc.command.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.employee.Employee;
import mvc.service.employee.RegisterEmployeeService;

public class RegisterEmployeeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newEmployeeForm.jsp";
	private RegisterEmployeeService employeeService = new RegisterEmployeeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// GET 방식일 경우 등록 폼을 보여줌
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		// POST 방식일 경우 제출된 데이터를 처리
		else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		// 허용되지 않는 요청 방식인 경우
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// 등록 폼을 처리하는 메서드
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW; // 등록 폼 페이지로 이동
	}

	// 제출된 데이터를 처리하는 메서드
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors); // 에러 정보를 담을 맵 생성

		// 사원 객체 생성
		Employee employeeReq = createEmployee(req);
		// 에러 검증
		employeeReq.validate(errors);

		if (!errors.isEmpty()) { // 에러가 있으면 폼 페이지로 다시 이동
			return FORM_VIEW;
		}

		// 사원 정보를 성공적으로 처리한 후 성공 페이지로 이동
		req.setAttribute("newEmployeeNo", employeeReq.getEmpNo()); // 임시로 사원 번호 설정
		return "/WEB-INF/view/newEmployeeSuccess.jsp"; // 성공 페이지로 이동
	}

	private Employee createEmployee(HttpServletRequest req) {
		// date 부분에 Date 변환 코드 적용
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredDate = null;
		Date retiredDate = null;

		try {
			// 채용일을 문자열에서 Date로 변환
			hiredDate = dateFormat.parse(req.getParameter("hiredDate"));
			retiredDate = dateFormat.parse(req.getParameter("retiredDate"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 사원 객체를 반환 (new Employee 안에 모든 데이터를 넣음)
		return new Employee(Integer.parseInt(req.getParameter("empNo")), req.getParameter("empForm"),
				req.getParameter("name"), hiredDate, retiredDate, // 변환된 Date 객체를 이 자리에 넣음
				req.getParameter("dep"), req.getParameter("registrationNum"), req.getParameter("address"),
				req.getParameter("phone"), req.getParameter("email"), req.getParameter("other"),
				Integer.parseInt(req.getParameter("salary")));
	}
}