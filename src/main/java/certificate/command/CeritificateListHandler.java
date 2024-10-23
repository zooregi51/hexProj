package certificate.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import certificate.service.CertificateListService;
import certificate.service.CertificatePage;
import mvc.command.CommandHandler;

public class CeritificateListHandler implements CommandHandler {

	private CertificateListService certificateListService = new CertificateListService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal=req.getParameter("pageNo");
		int pageNo=1;
		if(pageNoVal!=null) {
			pageNo=Integer.parseInt(pageNoVal);
		}
		CertificatePage certificatePage = certificateListService.getCertificatePage(pageNo);
		req.setAttribute("certificatePage", certificatePage);
		return "/WEB-INF/view/listCertificate.jsp";
	}

}
