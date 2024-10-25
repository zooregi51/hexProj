package attendance.model;

import java.util.Date;

public class Employee {
    // 멤버 변수 선언
    private int empno;  // 사원번호
    private String empform;  // 고용 형태
    private String name;  // 이름
    private Date hireddate;  // 입사일
    private Date retireddate;  // 퇴사일
    private String dep;  // 부서
    private String position;  // 직위
    private String registrationnum;  // 주민등록번호
    private String address;  // 주소
    private String phone;  // 전화번호
    private String email;  // 이메일
    private String other;  // 기타 정보
    private int salary;  // 급여

    // 생성자
    public Employee(int empno, String empform, String name, Date hireddate, Date retireddate, String dep, 
                    String position, String registrationnum, String address, String phone, String email, 
                    String other, int salary) {
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

    // getter 메서드들만 사용합니다
    public int getEmpno() {
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

    public int getSalary() {
        return salary;
    }
}
