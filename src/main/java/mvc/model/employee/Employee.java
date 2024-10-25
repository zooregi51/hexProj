package mvc.model.employee;

import java.util.Date;
import java.util.Map;

public class Employee {

	private Integer empNo; // 사원 번호
	private String empForm; // 고용 형태
	private String name; // 사원 이름
	private Date hiredDate; // 채용일
	private Date retiredDate; // 퇴사일
	private String dep; // 부서
	private String position; // 직위
	private String registrationNum; // 주민등록번호
	private String address; // 주소
	private String phone; // 전화번호
	private String email; // 이메일
	private String other; // 기타 정보
	private Integer salary; // 급여

	// 생성자: 사원 정보를 받아 초기화.

	public Employee(Integer empNo, String empForm, String name, Date hiredDate, Date retiredDate, String dep,
			String position, String registrationNum, String address, String phone, String email, String other,
			Integer salary) {
		this.empNo = empNo;
		this.empForm = empForm;
		this.name = name;
		this.hiredDate = hiredDate;
		this.retiredDate = retiredDate;
		this.dep = dep;
		this.position = position;
		this.registrationNum = registrationNum;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.other = other;
		this.salary = salary;
	}

	// Getter 메서드 각 필드를 외부에서 읽을 수 있게 제공.
	public Integer getEmpNo() {
		return empNo;
	}

	public String getEmpForm() {
		return empForm;
	}

	public String getName() {
		return name;
	}

	public Date getHiredDate() {
		return hiredDate;
	}


	public Date getRetireDate() {
		return retiredDate;
	}
  
	public String getDep() {
		return dep;
	}

	public String getPosition() {
		return position;
	}

	public String getRegistrationNum() {
		return registrationNum;
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

	public void validate(Map<String, Boolean> errors) {
		// TODO Auto-generated method stub

	}

}

