package basics.model;

public class AttendanceItem {
	private int id; // 고유 식별자. 데이터베이스에서 각 휴가 항목을 구분하기 위한 기본 키로 사용됨.
	private String name; // 휴가이름.
	private String unit; // 단위.
	private String group; // 그룹.
	private VacationItem vacation; // VacationItem 객체로 외래 키 설정.
	private String useFlag; // 사용여부

	public AttendanceItem(int id, String name, String unit, String group, VacationItem vacation, String useFlag) {
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.group = group;
		this.vacation = vacation;
		this.useFlag = useFlag;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public String getGroup() {
		return group;
	}

	public VacationItem getVacation() {
		return vacation;
	}

	public String getUseFlag() {
		return useFlag;
	}

}