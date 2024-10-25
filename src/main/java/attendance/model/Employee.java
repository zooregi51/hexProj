package attendance.model;

public class Employee {
    private Integer empNo; // 사원 번호
    private String empForm; // 고용 형태
    private String name; // 사원 이름
    private String dep; // 부서
    private String position; // 직위

    // 생성자 (기본 정보)
    public Employee(Integer empNo, String empForm, String name, String dep, String position) {
        this.empNo = empNo;
        this.empForm = empForm;
        this.name = name;
        this.dep = dep;
        this.position = position;
    }

    // Getter 메서드들
    public Integer getEmpNo() {
        return empNo;
    }

    public String getEmpForm() {
        return empForm;
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
