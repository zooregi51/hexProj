package basics.model;

// VacationItem 클래스는 휴가 항목의 데이터 구조를 나타내며,
// 고유 식별자(id), 이름(name), 기간(period), 사용 여부(useFlag)를 포함합니다.
// VacationItemクラスは休暇項目のデータ構造を表し、固有識別子（id）、名前（name）、期間（period）、使用可否（useFlag）を含みます。

public class VacationItem {
	private int id; // 고유 식별자. 데이터베이스에서 각 휴가 항목을 구분하기 위한 기본 키로 사용됨.
	// 固有識別子。データベースで各休暇項目を区別するための主キーとして使用
	private String name; // 휴가이름. 休暇名
	private String period; // 적용기간. 適用期間
	private String useFlag; // 사용여부. 使用有無

	// 생성자: 휴가 항목 정보를 받아 초기화
	// コンストラクタ: 休暇項目情報を受け取り初期化
	public VacationItem(int id, String name, String period, String useFlag) {
		this.id = id;
		this.name = name;
		this.period = period;
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

	public String getPeriod() {
		return period;
	}

	public String getUseFlag() {
		return useFlag;
	}

}
