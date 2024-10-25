package salary.model;

public class Salary {
	
	private String salNum;
	private Employee employee;
	// payment
	private Payment salPayment;
	
	public Salary(String salNum, Employee employee, Payment salPayment) {
		this.salNum = salNum;
		this.employee = employee;
		this.salPayment = salPayment;
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
	
	
}
