package attendance.model;

import java.sql.Date;

public class DiligenceRecord {
    private int recordId; // 근태 기록 ID / 勤怠記録ID
    private int employeeId; // 사원 ID (Employee 테이블의 외래 키) / 社員ID（Employeeテーブルの外部キー）
    private String diligenceType; // 근태 유형 (예: 연차, 병가 등) / 勤怠の種類（例：年次、病気休暇など）
    private Date startDate; // 근태 시작일 / 勤怠開始日
    private Date endDate; // 근태 종료일 / 勤怠終了日
    private int diligenceDays; // 근태 일수 / 勤怠日数
    private String remarks; // 비고 (추가 정보) / 備考（追加情報）

    public DiligenceRecord(int recordId, int employeeId, String diligenceType, Date startDate, Date endDate, int diligenceDays, String remarks) {
        this.recordId = recordId; // 근태 기록 ID 초기화 / 勤怠記録IDを初期化
        this.employeeId = employeeId; // 사원 ID 초기화 / 社員IDを初期化
        this.diligenceType = diligenceType; // 근태 유형 초기화 / 勤怠の種類を初期化
        this.startDate = startDate; // 근태 시작일 초기화 / 勤怠開始日を初期化
        this.endDate = endDate; // 근태 종료일 초기화 / 勤怠終了日を初期化
        this.diligenceDays = diligenceDays; // 근태 일수 초기화 / 勤怠日数を初期化
        this.remarks = remarks; // 비고 초기화 / 備考を初期化
    }

    // Getters
    public int getRecordId() { return recordId; } // 근태 기록 ID 반환 / 勤怠記録IDを返します
    public int getEmployeeId() { return employeeId; } // 사원 ID 반환 / 社員IDを返します
    public String getDiligenceType() { return diligenceType; } // 근태 유형 반환 / 勤怠の種類を返します
    public Date getStartDate() { return startDate; } // 근태 시작일 반환 / 勤怠開始日を返します
    public Date getEndDate() { return endDate; } // 근태 종료일 반환 / 勤怠終了日を返します
    public int getDiligenceDays() { return diligenceDays; } // 근태 일수 반환 / 勤怠日数を返します
    public String getRemarks() { return remarks; } // 비고 반환 / 備考を返します
}
