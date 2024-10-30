package retire.model;

import java.util.Date;
import java.util.Map;

public class Employee {

    private Integer empNo;        // 사원 번호
    private String empForm;       // 고용 형태
    private String name;          // 사원 이름
    private Date hiredDate;       // 채용일
    private String dep;           // 부서
    private String registrationNum; // 주민등록번호
    private String address;       // 주소
    private String phone;         // 전화번호
    private String email;         // 이메일
    private String other;         // 기타 정보
    private Integer salary;       // 급여

    // 생성자: 모든 필드를 초기화
    public Employee(Integer empNo, String empForm, String name, Date hiredDate, String dep,
                    String registrationNum, String address, String phone, 
                    String email, String other, Integer salary) {
        this.empNo = empNo;
        this.empForm = empForm;
        this.name = name;
        this.hiredDate = hiredDate;
        this.dep = dep;
        this.registrationNum = registrationNum;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.other = other;
        this.salary = salary;
    }

    // Getter 메서드들
    public Integer getEmpNo() { return empNo; }
    public String getEmpForm() { return empForm; }
    public String getName() { return name; }
    public Date getHiredDate() { return hiredDate; }
    public String getDep() { return dep; }
    public String getRegistrationNum() { return registrationNum; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getOther() { return other; }
    public Integer getSalary() { return salary; }

    // toString 메서드 (객체의 내용을 쉽게 확인하기 위해 추가)
    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", empForm='" + empForm + '\'' +
                ", name='" + name + '\'' +
                ", hiredDate=" + hiredDate +
                ", dep='" + dep + '\'' +
                ", registrationNum='" + registrationNum + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", other='" + other + '\'' +
                ", salary=" + salary +
                '}';
    }
}
