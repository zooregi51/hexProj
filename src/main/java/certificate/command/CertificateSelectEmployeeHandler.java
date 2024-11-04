package certificate.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class CertificateSelectEmployeeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/certificate/issuanceCertificates.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String empnoVal = req.getParameter("empno"); // 요청에서 pagoNo라는 이름의 파라미터를 가져와서 pageNoVal에 넣겟다
		int empno;
		if (empnoVal != null) {
			empno = Integer.parseInt(empnoVal);
		}
		return FORM_VIEW;
	}

}
