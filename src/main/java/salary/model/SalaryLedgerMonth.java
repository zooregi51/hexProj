package salary.model;

import java.util.Date;

/*
 * SalaryLedgerMonth Model
 * 월별 급여 대장 model
 * 
 * String yearMonth     귀속연월
 * String term          정산기간
 * Date givingDate      지급일
 * Integer empNum       사원수
 * Integer paymentTotal 지급총액
 * 
 * */

/*
* SalaryLedgerMonth Model
* 月次給与台帳モデル
*
* String yearMonth      帰属年月
* String term           精算期間
* Date givingDate       支給日
* Integer empNum        社員数
* Integer paymentTotal  支給総額
*
* */

public class SalaryLedgerMonth {
	private String yearMonth;
	private String term;
	private Date givingDate;
	private Integer empNum;
	private Integer paymentTotal;
	
	public SalaryLedgerMonth(String yearMonth, String term, Date givingDate, Integer empNum, Integer paymentTotal) {
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
	public Date getGivingDate() {
		return givingDate;
	}
	public Integer getEmpNum() {
		return empNum;
	}
	public Integer getPaymentTotal() {
		return paymentTotal;
	}
	
	
}