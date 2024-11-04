package certificate.model;
import java.util.Date;
import employee.model.Employee;

public class Certificate {

	private Integer certificate_Id;
	private String certificate_Register;
	private String certificate_Purpose;
	private Integer empno;
	private String empform;
	private String name;
	private String dep;
	private String position;
	private Date certificate_Date;	
	

	public Integer getEmpno() {
		return empno;
	}
	public String getEmpform() {
		return empform;
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
	public Integer getCertificate_Id() {
		return certificate_Id;
	}
	public String getCertificate_Register() {
		return certificate_Register;
	}
	public String getCertificate_Purpose() {
		return certificate_Purpose;
	}

	public Date getCertificate_Date() {
		return certificate_Date;
	}
	public Certificate(Integer certificate_Id, String certificate_Register, String certificate_Purpose, Integer empno,
			String empform, String name, String dep, String position, Date certificate_Date) {
		this.certificate_Id = certificate_Id;
		this.certificate_Register = certificate_Register;
		this.certificate_Purpose = certificate_Purpose;
		this.empno = empno;
		this.empform = empform;
		this.name = name;
		this.dep = dep;
		this.position = position;
		this.certificate_Date = certificate_Date;
	}

}
