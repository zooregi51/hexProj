package basics.model;

import java.util.Date;
import java.util.Map;

// Employee 클래스는 사원의 정보를 관리하는 데이터 구조를 나타냅니다.
// Employeeクラスは従業員の情報を管理するデータ構造を表します。

public class Employee {

	private Integer empNo; // 사원 번호. 社員番号。
	private String empForm; // 고용 형태. 雇用形態。
	private String name; // 사원 이름. 氏名。
	private Date hiredDate; // 채용일. 入社日。
	private Date retiredDate; // 퇴사일. 退社日。
	private String dep; // 부서. 部署。
	private String position; // 직위. 職位。
	private String registrationNum; // 주민등록번호. 個人ID番号。
	private String address; // 주소. 現在所。
	private String phone; // 전화번호. 携帯番号。
	private String email; // 이메일. E-maill。
	private String other; // 기타 정보. 備考。
	private Integer salary; // 급여. 給与。

	// 생성자: 사원 정보를 받아 초기화.
	// コンストラクタ: 従業員情報を受け取り初期化。
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
	// Getterメソッド: 各フィールドを外部から読み取れるように提供。
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
		// 필수 필드 검증을 수행하여 오류가 있으면 errors 맵에 추가합니다.
		// 必須フィールドの検証を行い、エラーがあればerrorsマップに追加します。
		// 이 메서드는 사용자가 입력한 데이터가 유효한지 확인하는 데 사용됩니다.
		// このメソッドは、ユーザーが入力したデータが有効かどうかを確認するために使用されます。
	}

}
