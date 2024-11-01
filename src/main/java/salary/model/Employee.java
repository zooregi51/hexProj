package salary.model;

import java.util.Date;

public class Employee {
	private Integer empId;
	private String empName;
	private String empForm;
	private String empDepart;
	private String empPos;
	private Date empHiredDate;
	private Integer salary;
	
	
	
	public Employee(Integer empId) {
		this.empId = empId;
	}
	public Employee(Integer empId, String empName, String empForm, String empDepart, String empPos,
			Date empHiredDate, Integer salary) {
		this.empId = empId;
		this.empName = empName;
		this.empForm = empForm;
		this.empDepart = empDepart;
		this.empPos = empPos;
		this.empHiredDate = empHiredDate;
		this.salary = salary;
	}
	public Integer getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public String getEmpForm() {
		return empForm;
	}
	public String getEmpDepart() {
		return empDepart;
	}
	public String getEmpPos() {
		return empPos;
	}
	public Date getEmpHiredDate() {
		return empHiredDate;
	}
	public Integer getSalary() {
		return salary;
	}
	
	
}