package employee.model;
import java.util.Date;

public class Employee {

	private Integer empno;
	private String empform;
	private String name;
	private Date hireddate;
	private Date retireddate;
	private String dep;
	private String position;
	private String registrationnum;
	private String address;
	private String phone;
	private String email;
	private String other;
	private Integer salary;
	
	public Employee(Integer empno, String empform, String name, Date hireddate, Date retireddate, String dep,
			String position, String registrationnum, String address, String phone, String email, String other,
			Integer salary) {
		this.empno = empno;
		this.empform = empform;
		this.name = name;
		this.hireddate = hireddate;
		this.retireddate = retireddate;
		this.dep = dep;
		this.position = position;
		this.registrationnum = registrationnum;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.other = other;
		this.salary = salary;
	}
	public Integer getEmpno() {
		return empno;
	}
	public String getEmpform() {
		return empform;
	}
	public String getName() {
		return name;
	}
	public Date getHireddate() {
		return hireddate;
	}
	public Date getRetireddate() {
		return retireddate;
	}
	public String getDep() {
		return dep;
	}
	public String getPosition() {
		return position;
	}
	public String getRegistrationnum() {
		return registrationnum;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getOther() {
		return other;
	}
	public Integer getSalary() {
		return salary;
	}
	
	
}
