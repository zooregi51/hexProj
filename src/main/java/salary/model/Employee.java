package salary.model;

import java.util.Date;

/*
 * Employee Model
 * 사원 정보에 대한 model
 * 
 * Integer empId     사원 번호
 * String empName    사원 이름
 * String empForm    사원의 형태(정규직/계약직)
 * String empDepart  사원의 부서
 * String empPos     사원의 직위
 * Date empHiredDate 사원 고용 날짜
 * Integer salary    사원 기본급
 * 
 * */

/*
* Employee Model
* 社員情報のモデル
*
* Integer empId     社員番号
* String empName    社員名
* String empForm    社員の形態(正規職/契約職)
* String empDepart  社員の部署
* String empPos     社員の職位
* Date empHiredDate 社員の雇用日
* Integer salary    社員基本給
*
* */

public class Employee {
	private Integer empId;
	private String empName;
	private String empForm;
	private String empDepart;
	private String empPos;
	private Date empHiredDate;
	private Integer salary;
	
	
	
	public Employee(Integer empId) {
		this.empId = empId;
	}
	public Employee(Integer empId, String empName, String empForm, String empDepart, String empPos,
			Date empHiredDate, Integer salary) {
		this.empId = empId;
		this.empName = empName;
		this.empForm = empForm;
		this.empDepart = empDepart;
		this.empPos = empPos;
		this.empHiredDate = empHiredDate;
		this.salary = salary;
	}
	public Integer getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public String getEmpForm() {
		return empForm;
	}
	public String getEmpDepart() {
		return empDepart;
	}
	public String getEmpPos() {
		return empPos;
	}
	public Date getEmpHiredDate() {
		return empHiredDate;
	}
	public Integer getSalary() {
		return salary;
	}
	
	
}