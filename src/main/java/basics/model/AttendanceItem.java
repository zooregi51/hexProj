package basics.model;

public class AttendanceItem {
	private int id; // 고유 식별자
	private String name; // 근태항목 이름
	private String unit; // 단위 (일, 시간 등)
	private String groupCategory; // 그룹
	private String useFlag; // 사용 여부

	public AttendanceItem(int id, String name, String unit, String groupCategory, String useFlag) {
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.groupCategory = groupCategory;
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

	public String getGroupCategory() {
		return groupCategory;
	}

	public String getUseFlag() {
		return useFlag;
	}
}