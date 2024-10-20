package salary.model;

public class SalaryLedgerMonth {
	private String yearMonth; // 귀속연월
	private String term; // 정산기간
	private String givingDate; // 지급일
	private Integer empNum; // 인원
	private Integer paymentTotal; //지급총액
	
	public SalaryLedgerMonth(String yearMonth, String term, String givingDate, Integer empNum, Integer paymentTotal) {
		this.yearMonth = yearMonth;
		this.term = term;
		this.givingDate = givingDate;
		this.empNum = empNum;
		this.paymentTotal = paymentTotal;
	}
	public String getYearMonth() { 
		return yearMonth;
	}
	public String getTerm() {
		return term;
	}
	public String getGivingDate() {
		return givingDate;
	}
	public Integer getEmpNum() {
		return empNum;
	}
	public Integer getPaymentTotal() {
		return paymentTotal;
	}
	
	
}