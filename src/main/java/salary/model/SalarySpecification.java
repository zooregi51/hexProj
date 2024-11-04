package salary.model;

public class SalarySpecification {
	private String empForm;
	private String empName;
	private Integer actualTotal;
	private String dep;
	private Integer empNo;
	
	public SalarySpecification(String empForm, String empName, Integer actualTotal, String dep, Integer empNo) {
		this.empForm = empForm;
		this.empName = empName;
		this.actualTotal = actualTotal;
		this.dep = dep;
		this.empNo = empNo;
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
	public Integer getEmpNo() {
		return empNo;
	}
	public String getDep() {
		return dep;
	}
}
