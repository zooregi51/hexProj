package employee.model;
import java.util.Date;

public class Employee {

	private Integer employee_Id;
	private String employee_Form;
	private String employee_KrName;
	private String employee_EnName;
	private Date employee_Date;
	private Date employee_ResignDate;
	private String employee_Department;
	private String employee_Position;
	private Date employee_ResidentNumber;
	private String employee_Address;
	private Integer employee_HomeNumber;
	private Integer employee_MobileNumber;
	private String employee_Email;
	private String employee_Sns;
	private String employee_Ex;
	
	public Employee(Integer employee_Id, String employee_Form, String employee_KrName, String employee_EnName,
			Date employee_Date, Date employee_ResignDate, String employee_Department, String employee_Position,
			Date employee_ResidentNumber, String employee_Address, Integer employee_HomeNumber,
			Integer employee_MobileNumber, String employee_Email, String employee_Sns, String employee_Ex) {

		this.employee_Id = employee_Id;
		this.employee_Form = employee_Form;
		this.employee_KrName = employee_KrName;
		this.employee_EnName = employee_EnName;
		this.employee_Date = employee_Date;
		this.employee_ResignDate = employee_ResignDate;
		this.employee_Department = employee_Department;
		this.employee_Position = employee_Position;
		this.employee_ResidentNumber = employee_ResidentNumber;
		this.employee_Address = employee_Address;
		this.employee_HomeNumber = employee_HomeNumber;
		this.employee_MobileNumber = employee_MobileNumber;
		this.employee_Email = employee_Email;
		this.employee_Sns = employee_Sns;
		this.employee_Ex = employee_Ex;
	}

	public Integer getEmployee_Id() {
		return employee_Id;
	}

	public String getEmployee_Form() {
		return employee_Form;
	}

	public String getEmployee_KrName() {
		return employee_KrName;
	}

	public String getEmployee_EnName() {
		return employee_EnName;
	}

	public Date getEmployee_Date() {
		return employee_Date;
	}

	public Date getEmployee_ResignDate() {
		return employee_ResignDate;
	}

	public String getEmployee_Department() {
		return employee_Department;
	}

	public String getEmployee_Position() {
		return employee_Position;
	}

	public Date getEmployee_ResidentNumber() {
		return employee_ResidentNumber;
	}

	public String getEmployee_Address() {
		return employee_Address;
	}

	public Integer getEmployee_HomeNumber() {
		return employee_HomeNumber;
	}

	public Integer getEmployee_MobileNumber() {
		return employee_MobileNumber;
	}

	public String getEmployee_Email() {
		return employee_Email;
	}

	public String getEmployee_Sns() {
		return employee_Sns;
	}

	public String getEmployee_Ex() {
		return employee_Ex;
	}
}
