package attendance.model;

import java.sql.Date;
import java.sql.Time;

public class AttendanceRecord {

    private int recordId;  // 기록 번호
    private String employeeId;  // 사원 코드
    private Date workDate;  // 근무 일자
    private Time startTime;  // 출근 시간
    private Time endTime;  // 퇴근 시간
    private String workType;  // 근무 유형
    private String approvalStatus;  // 승인 상태
    private int totalOvertimeHours;  // 총 초과 근무 시간

    // 전체 필드 초기화 생성자 (수정용)
    public AttendanceRecord(int recordId, String employeeId, Date workDate, Time startTime, Time endTime, String workType, String approvalStatus, int totalOvertimeHours) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    // 추가용 생성자 (recordId 없이)
    public AttendanceRecord(String employeeId, Date workDate, Time startTime, Time endTime, String workType, String approvalStatus, int totalOvertimeHours) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    // Getter 메서드들
    public int getRecordId() {
        return recordId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
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
