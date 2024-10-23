package attendance.model;

import java.sql.Date;

public class AttendanceRecord {

    private String recordId;        // 기록 ID
    private String employeeId;      // 사원 ID
    private Date workDate;          // 근무일자 (DATE 타입)
    private String startTime;       // 출근 시간 (VARCHAR2)
    private String endTime;         // 퇴근 시간 (VARCHAR2)
    private String workType;        // 근무 유형
    private String approvalStatus;  // 승인 상태 (optional)
    private int totalOvertimeHours;  // 총 초과 근무시간 (NUMBER)

    // 생성자 (모든 필드 포함)
    public AttendanceRecord(String recordId, String employeeId, Date workDate, String startTime, String endTime, String workType, String approvalStatus, int totalOvertimeHours) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    // 생성자 (필수 필드만 포함)
    public AttendanceRecord(String employeeId, Date workDate, String startTime, String endTime, String workType, String approvalStatus, int totalOvertimeHours) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    // Getter 메서드들
    public String getRecordId() {
        return recordId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getWorkType() {
        return workType;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public int getTotalOvertimeHours() {
        return totalOvertimeHours;
    }
}
