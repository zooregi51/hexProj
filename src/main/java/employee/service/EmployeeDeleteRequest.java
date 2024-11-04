package employee.service;

public class EmployeeDeleteRequest {

	private int empno;
	private String name;
	private String dep;
	private String position;
	
	public EmployeeDeleteRequest(int empno, String name, String dep, String position) {
		this.empno = empno;
		this.name = name;
		this.dep = dep;
		this.position = position;
	}
	public int getEmpno() {
		return empno;
	}
	public String getName() {
		return name;
	}
	public String getDep() {
		return dep;
	}
	public String getPosition() {
		return position;
	}
}
