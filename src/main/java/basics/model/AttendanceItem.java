package basics.model;

// AttendanceItem 클래스는 근태 항목의 데이터 구조를 나타내며,
// 근태 항목의 속성(id, name, unit, groupCategory, useFlag)을 가지고 있습니다.
// AttendanceItemクラスは、勤怠項目のデータ構造を表し、属性(id, name, unit, groupCategory, useFlag)を持ちます。

public class AttendanceItem {
	private int id; // 고유 식별자. 固有識別子。
	private String name; // 근태항목 이름. 勤怠項目名。
	private String unit; // 단위. (일, 시간 등) 単位。（日、時間など）
	private String groupCategory; // 그룹. グループ。
	private String useFlag; // 사용 여부. 使用有無。

	// 근태 항목 객체 생성자.
	// 勤怠項目オブジェクトのコンストラクタ。
	public AttendanceItem(int id, String name, String unit, String groupCategory, String useFlag) {
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.groupCategory = groupCategory;
		this.useFlag = useFlag;
	}

	// Getter 메서드들
	// Getterメソッド
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