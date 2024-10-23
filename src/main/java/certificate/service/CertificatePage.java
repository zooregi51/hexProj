package certificate.service;
import java.util.List;

import certificate.model.Certificate;
public class CertificatePage {

	private int total;
	private int currentPage;
	private List<Certificate> certificate;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public CertificatePage(int total, int currentPage, int size, List<Certificate> certificate) {
		this.total=total;
		this.currentPage=currentPage;
		this.certificate=certificate;
		if(total==0) {
			totalPages=0;
			startPage=0;
			endPage=0;
		}else {
			totalPages=total/size;
			if(total%size >0) {
				totalPages++;
			}
		}
		int modVal=currentPage%5;
		startPage=currentPage/5*5+1;
		if(modVal==0)startPage-=5;
		
		endPage=startPage+4;
		if(endPage>totalPages)endPage=totalPages;
	}
	public boolean hasNoCertificates() {
		return total==0;
	}
	public boolean hasCertificates() {
		return total>0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Certificate> getCertificate() {
		return certificate;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	
}
