package attendance.model;

import java.sql.Date;

public class LeaveRecord {
    private int leaveId;  // 휴가 ID / 休暇ID
    private int empno;  // 사원 번호 / 社員番号
    private String leaveType;  // 휴가 유형 (예: 연차, 병가 등) / 休暇の種類（例：年次、病気休暇など）
    private int totalDays;  // 총 일수 / 総日数
    private int daysUsed;  // 사용 일수 / 使用日数
    private int remainingDays;  // 남은 일수 / 残り日数
    private String leavePeriod;  // 휴가 기간 / 休暇期間
    private Date inputDate;  // 입력 일자 / 入力日
    private String remarks;  // 비고 / 備考

    // 생성자: 모든 필드를 초기화합니다 / コンストラクタ：すべてのフィールドを初期化します
    public LeaveRecord(int leaveId, int empno, String leaveType, int totalDays, int daysUsed, int remainingDays, String leavePeriod, Date inputDate, String remarks) {
        this.leaveId = leaveId;
        this.empno = empno;
        this.leaveType = leaveType;
        this.totalDays = totalDays;
        this.daysUsed = daysUsed;
        this.remainingDays = remainingDays;
        this.leavePeriod = leavePeriod;
        this.inputDate = inputDate;
        this.remarks = remarks;
    }

    // Getters

    public int getLeaveId() { return leaveId; }  // 휴가 ID 반환 / 休暇IDを返します
    public int getEmpno() { return empno; }  // 사원 번호 반환 / 社員番号を返します
    public String getLeaveType() { return leaveType; }  // 휴가 유형 반환 / 休暇の種類を返します
    public int getTotalDays() { return totalDays; }  // 총 일수 반환 / 総日数を返します
    public int getDaysUsed() { return daysUsed; }  // 사용 일수 반환 / 使用日数を返します
    public int getRemainingDays() { return remainingDays; }  // 남은 일수 반환 / 残り日数を返します
    public String getLeavePeriod() { return leavePeriod; }  // 휴가 기간 반환 / 休暇期間を返します
    public Date getInputDate() { return inputDate; }  // 입력 일자 반환 / 入力日を返します
    public String getRemarks() { return remarks; }  // 비고 반환 / 備考を返します
}
