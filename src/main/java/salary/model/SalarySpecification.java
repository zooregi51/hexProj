package salary.model;

public class SalarySpecification {
	private String empForm;
	private String empName;
	private Integer actualTotal;
	
	public SalarySpecification(String empForm, String empName, Integer actualTotal) {
		this.empForm = empForm;
		this.empName = empName;
		this.actualTotal = actualTotal;
	}
	public String getEmpForm() {
		return empForm;
	}
	public String getEmpName() {
		return empName;
	}
	public Integer getActualTotal() {
		return actualTotal;
	}
}
