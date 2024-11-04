package salary.model;

import java.util.Date;

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
