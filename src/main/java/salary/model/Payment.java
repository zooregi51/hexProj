package salary.model;

/*
 * Payment Model
 * 지불 모델
 * 
 * Integer salBasicSalary    기본급
 * Integer salFood           식비
 * Integer salChildCare      보육수당
 * Integer salPositionSalary 직책수당
 * Integer salLongService    근속수당
 * Integer salOncall         당직수당
 * Integer salBonus          상여금
 * Integer salHoliday        휴일수당
 * 
 * 
 * */

/*
* Payment Model
* 支払いモデル
*
* Integer salBasicSalary    基本給
* Integer salFood           食費
* Integer salChildCare      保育手当
* Integer salPositionSalary 職責手当
* Integer salLongService    勤続手当
* Integer salOncall         当直手当
* Integer salBonus          ボーナス
* Integer salHoliday        休日手当
*
*
* */

public class Payment {
	private Integer salBasicSalary;
	private Integer salFood;
	private Integer salChildCare;
	private Integer salPositionSalary;
	private Integer salLongService;
	private Integer salOncall;
	private Integer salBonus;
	private Integer salHoliday;
	public Payment(Integer salBasicSalary, Integer salFood, Integer salChildCare, Integer salPositionSalary,
			Integer salLongService, Integer salOncall, Integer salBonus, Integer salHoliday) {
		this.salBasicSalary = salBasicSalary;
		this.salFood = salFood;
		this.salChildCare = salChildCare;
		this.salPositionSalary = salPositionSalary;
		this.salLongService = salLongService;
		this.salOncall = salOncall;
		this.salBonus = salBonus;
		this.salHoliday = salHoliday;
	}
	public Integer getSalBasicSalary() {
		return salBasicSalary;
	}
	public Integer getSalFood() {
		return salFood;
	}
	public Integer getSalChildCare() {
		return salChildCare;
	}
	public Integer getSalPositionSalary() {
		return salPositionSalary;
	}
	public Integer getSalLongService() {
		return salLongService;
	}
	public Integer getSalOncall() {
		return salOncall;
	}
	public Integer getSalBonus() {
		return salBonus;
	}
	public Integer getSalHoliday() {
		return salHoliday;
	}
	
	public Integer getSumPayment() {
		return salFood + salBasicSalary + salBonus + salChildCare 
				+ salHoliday + salLongService + salOncall 
				+ salPositionSalary;
	}
}