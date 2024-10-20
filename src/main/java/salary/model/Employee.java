package salary.model;

import java.util.Date;

public class Employee {
	private String empId;
	private String empName;
	private String empForm;
	private String empDepart;
	private String empPos;
	private Date empHiredDate;
	public Employee(String empId, String empName, String empForm, String empDepart, String empPos,
			Date empHiredDate) {
		this.empId = empId;
		this.empName = empName;
		this.empForm = empForm;
		this.empDepart = empDepart;
		this.empPos = empPos;
		this.empHiredDate = empHiredDate;
	}
	public String getEmpId() {
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
	
	
}