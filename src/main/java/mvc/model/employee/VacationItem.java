package mvc.model.employee;

public class VacationItem {
	private int id;
	private String name;
	private String period; // "시작일 ~ 종료일" 형식으로 저장
	private String useFlag;

	public VacationItem(int id, String name, String period, String useFlag) {
		this.id = id;
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
