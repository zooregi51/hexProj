package certificate.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import certificate.service.CertificateListService;
import certificate.service.CertificatePage;
import mvc.command.CommandHandler;

public class CertificateListHandler implements CommandHandler {

	private CertificateListService certificateListService = new CertificateListService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal=req.getParameter("pageNo"); //요청에서 pagoNo라는 이름의 파라미터를 가져오가서 pageNoVal에 넣겟다
		int pageNo=1;
		if(pageNoVal!=null) {
			pageNo=Integer.parseInt(pageNoVal);
		}
		CertificatePage certificatePage = certificateListService.getCertificatePage(pageNo);
		req.setAttribute("certificatePage", certificatePage);
		return "/WEB-INF/view/listCertificate.jsp";
	}

}
