package salary.model;

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
	
}