package retire.model;

import java.util.Date;
//import java.util.Map;

public class Retirement {

    //사원관련
	private Integer empNo; // 사원 번호
	private String name; //이름
	private Date hiredDate;
	private String dep;
	private String position;

	//퇴사처리 관련
	private String retiredDate;
	private String retiredForm;
	private String retiredPhonenum;
    private String retiredId;

    public Integer getEmpNo() {
        return empNo;
    }
    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getHiredDate() {
        return hiredDate;
    }
    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }
    public String getDep() {
        return dep;
    }
    public void setDep(String dep) {
        this.dep = dep;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getRetiredDate() {
        return retiredDate;
    }
    public void setRetiredDate(String retiredDate) {
        this.retiredDate = retiredDate;
    }
    public String getRetiredForm() {
        return retiredForm;
    }
    public void setRetiredForm(String retiredForm) {
        this.retiredForm = retiredForm;
    }
    public String getRetiredPhonenum() {
        return retiredPhonenum;
    }
    public void setRetiredPhonenum(String retiredPhonenum) {
        this.retiredPhonenum = retiredPhonenum;
    }
    public String getRetiredId() {
        return retiredId;
    }
    public void setRetiredId(String retiredId) {
        this.retiredId = retiredId;
    }

}
