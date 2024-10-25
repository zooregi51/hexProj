package attendance.model;

import java.sql.Date;

public class Employee {
    private Integer empNo;
    private String empForm;
    private String name;
    private Date hiredDate;
    private Date retireddate;
    private String dep;
    private String position;
    private String registrationNum;
    private String address;
    private String phone;
    private String email;
    private String other;
    private Integer salary;

    public Employee(Integer empNo, String empForm, String name, Date hiredDate, Date retireddate,
                    String dep, String position, String registrationNum, String address, 
                    String phone, String email, String other, Integer salary) {
        this.empNo = empNo;
        this.empForm = empForm;
        this.name = name;
        this.hiredDate = hiredDate;
        this.retireddate = retireddate;
        this.dep = dep;
        this.position = position;
        this.registrationNum = registrationNum;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.other = other;
        this.salary = salary;
    }

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

    public Date getRetireddate() {
        return retireddate;
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
}
