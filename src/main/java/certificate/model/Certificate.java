package certificate.model;
import java.util.Date;
import employee.model.Employee;

public class Certificate {

	private Integer certificate_Id;
	private String certificate_Register;
	private String certificate_Purpose;
	private Integer employee_Id;
	private String employee_Form;
	private String employee_KrName;
	private String employee_Department;
	private String employee_Position;
	private Date certificate_Date;	
	
	public String getEmployee_Form() {
		return employee_Form;
	}
	public String getEmployee_KrName() {
		return employee_KrName;
	}
	public String getEmployee_Department() {
		return employee_Department;
	}
	public String getEmployee_Position() {
		return employee_Position;
	}

	public Integer getCertificate_Id() {
		return certificate_Id;
	}
	public String getCertificate_Register() {
		return certificate_Register;
	}
	public String getCertificate_Purpose() {
		return certificate_Purpose;
	}
	public Integer getEmployee_Id() {
		return employee_Id;
	}
	public Date getCertificate_Date() {
		return certificate_Date;
	}
	public Certificate(Integer certificate_Id, String certificate_Register, String certificate_Purpose,
			Integer employee_Id, String employee_Form, String employee_KrName, String employee_Department,
			String employee_Position, Date certificate_Date) {
		this.certificate_Id = certificate_Id;
		this.certificate_Register = certificate_Register;
		this.certificate_Purpose = certificate_Purpose;
		this.employee_Id = employee_Id;
		this.employee_Form = employee_Form;
		this.employee_KrName = employee_KrName;
		this.employee_Department = employee_Department;
		this.employee_Position = employee_Position;
		this.certificate_Date = certificate_Date;
	}

	
}
