package salary.model;

import java.util.Date;

/*
 * Salary Model
 * 급여 모델
 * 
 * String salNum        급여 번호
 * Employee employee    사원 정보
 * Payment salPayment   지불 정보
 * String transferDate  이체 날짜
 * 
 * */

/*
* Salary Model
* 給与モデル
*
* String salNum       給与番号
* Employee employee   社員情報
* Payment salPayment  支払い情報
* String transferDate 振替日
*
* */

public class Salary {
	
	private String salNum;
	private Employee employee;
	// payment
	private Payment salPayment;
	private String transferDate;
	
	public Salary(String salNum, Employee employee, Payment salPayment, String transferDate) {
		this.salNum = salNum;
		this.employee = employee;
		this.salPayment = salPayment;
		this.transferDate = transferDate;
	}

	public Salary(String salNum, Employee employee, Payment payment) {
		this.salNum = salNum;
		this.employee = employee;
		this.salPayment = payment;
		this.transferDate = "";
	}

	public String getSalNum() {
		return salNum;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public Payment getSalPayment() {
		return salPayment;
	}

	public String getTransferDate() {
		return transferDate;
	}
	
	
}
