package attendance.model;

import java.sql.Date;
import java.sql.Time;

public class AttendanceRecord {

    private int recordId;               // 근태 기록 번호
    private String employeeId;           // 사원 코드
    private Date workDate;               // 근무 일자
    private Time startTime;              // 출근 시간
    private Time endTime;                // 퇴근 시간
    private String workType;             // 근무 유형
    private String approvalStatus;       // 승인 상태
    private int totalOvertimeHours;      // 총 초과 근무 시간
    private Date inputDate;              // 입력 일자
    private String attendanceType;       // 근태 항목
    private String attendancePeriod;     // 근태 기간
    private int attendanceDays;          // 근태 일수
    private int amount;                  // 금액
    private String notes;                // 적요

    // 전체 필드를 포함한 생성자 (수정 및 조회용)
    public AttendanceRecord(int recordId, String employeeId, Date workDate, Time startTime, Time endTime, 
                            String workType, String approvalStatus, int totalOvertimeHours, Date inputDate, 
                            String attendanceType, String attendancePeriod, int attendanceDays, int amount, String notes) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
        this.inputDate = inputDate;
        this.attendanceType = attendanceType;
        this.attendancePeriod = attendancePeriod;
        this.attendanceDays = attendanceDays;
        this.amount = amount;
        this.notes = notes;
    }

    // recordId 없이 생성하는 생성자 (추가용)
    public AttendanceRecord(String employeeId, Date workDate, Time startTime, Time endTime, 
                            String workType, String approvalStatus, int totalOvertimeHours, Date inputDate, 
                            String attendanceType, String attendancePeriod, int attendanceDays, int amount, String notes) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workType = workType;
        this.approvalStatus = approvalStatus;
        this.totalOvertimeHours = totalOvertimeHours;
        this.inputDate = inputDate;
        this.attendanceType = attendanceType;
        this.attendancePeriod = attendancePeriod;
        this.attendanceDays = attendanceDays;
        this.amount = amount;
        this.notes = notes;
    }

    // Getter 메서드
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

    public Date getInputDate() {
        return inputDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public String getAttendancePeriod() {
        return attendancePeriod;
    }

    public int getAttendanceDays() {
        return attendanceDays;
    }

    public int getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }
    
    // Optional: toString 메서드
    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "recordId=" + recordId +
                ", employeeId='" + employeeId + '\'' +
                ", workDate=" + workDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", workType='" + workType + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", totalOvertimeHours=" + totalOvertimeHours +
                ", inputDate=" + inputDate +
                ", attendanceType='" + attendanceType + '\'' +
                ", attendancePeriod='" + attendancePeriod + '\'' +
                ", attendanceDays=" + attendanceDays +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                '}';
    }
}
