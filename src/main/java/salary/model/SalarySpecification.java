package salary.model;

/*
 * SalarySpecification Model
 * 급여 명세 model
 * 
 * String empForm       사원의 형태
 * String empName       사원 이름
 * Integer actualTotal  지급 총액
 * String dep           사원의 부서
 * Integer empNo        사원 번호
 * 
 * */

/*
* SalarySpecification Model
* 給与明細モデル
*
* String empForm      社員の形態
* String empName      社員名
* Integer actualTotal 支給総額
* String dep          社員の部署
* Integer empNo       社員番号
*
* */

public class SalarySpecification {
	private String empForm;
	private String empName;
	private Integer actualTotal;
	private String dep;
	private Integer empNo;
	
	public SalarySpecification(String empForm, String empName, Integer actualTotal, String dep, Integer empNo) {
		this.empForm = empForm;
		this.empName = empName;
		this.actualTotal = actualTotal;
		this.dep = dep;
		this.empNo = empNo;
	}
	public String getEmpForm() {
		return empForm;
	}
	public String getEmpName() {
		return empName;
	}
	public Integer getActualTotal() {
		return actualTotal;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public String getDep() {
		return dep;
	}
}
