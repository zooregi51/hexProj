package vacation.model;

public class VacationItem {
	private int id; // 고유 식별자. 데이터베이스에서 각 휴가 항목을 구분하기 위한 기본 키로 사용됨.
	private String name; //휴가이름.
	private String period; // 적용기간.
	private String useFlag; //사용여부.

	public VacationItem(int id, String name, String period, String useFlag) {
		this.id = id; // 데이터베이스에서 각 항목을 고유하게 식별할 수 있도록 사용.
		this.name = name;
		this.period = period;
		this.useFlag = useFlag;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPeriod() {
		return period;
	}

	public String getUseFlag() {
		return useFlag;
	}

}
