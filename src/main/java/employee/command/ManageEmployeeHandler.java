package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import employee.service.EmployeeService;
import employee.model.Employee;
import command.CommandHandler;
import java.util.List;

public class ManageEmployeeHandler implements CommandHandler {

    private static final String FORM_VIEW = "/WEB-INF/view/manageEmployee.jsp"; // 사원 관리 페이지 뷰 경로
    private EmployeeService employeeService = new EmployeeService(); // 사원 관련 서비스

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) { // GET 요청 처리
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) { // POST 요청 처리 (저장)
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 잘못된 요청 메서드일 경우
            return null;
        }
    }

    // 사원 목록을 보여주는 폼 처리
    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        List<Employee> employeeList = employeeService.getEmployeeList(); // 사원 목록을 가져옴
        req.setAttribute("employeeList", employeeList); // 사원 목록을 요청에 저장
        return FORM_VIEW; // 사원 관리 페이지로 이동
    }

    // 사원 추가, 수정, 삭제 처리
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        String action = req.getParameter("action"); // 'add', 'edit', 'delete' 액션을 파라미터로 받음
        String employeeId = req.getParameter("employeeId"); // 사원 ID를 파라미터로 받음

        if ("add".equals(action)) {
            handleAdd(req); // 사원 추가 처리
        } else if ("edit".equals(action)) {
            handleEdit(req, employeeId); // 사원 수정 처리
        } else if ("delete".equals(action)) {
            handleDelete(employeeId); // 사원 삭제 처리
        }

        return "redirect:/manageEmployee.do"; // 처리 후 사원 관리 페이지로 리다이렉트
    }

    // 사원 추가 처리
    private void handleAdd(HttpServletRequest req) {
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String position = req.getParameter("position");
        String hireDate = req.getParameter("hireDate");
        String employmentType = req.getParameter("employmentType");

        Employee newEmployee = new Employee(name, department, position, hireDate, employmentType); // 새로운 사원 객체 생성
        employeeService.addEmployee(newEmployee); // 서비스 호출하여 사원 추가
    }

    // 사원 수정 처리
    private void handleEdit(HttpServletRequest req, String employeeId) {
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String position = req.getParameter("position");
        String hireDate = req.getParameter("hireDate");
        String employmentType = req.getParameter("employmentType");

        Employee updatedEmployee = new Employee(employeeId, name, department, position, hireDate, employmentType); // 수정된 사원 객체 생성
        employeeService.updateEmployee(updatedEmployee); // 서비스 호출하여 사원 수정
    }

    // 사원 삭제 처리
    private void handleDelete(String employeeId) {
        employeeService.deleteEmployee(employeeId); // 서비스 호출하여 사원 삭제
    }//나는 바보다 재원이형은 천재다
}
