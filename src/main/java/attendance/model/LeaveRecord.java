package attendance.model;

import java.sql.Date;

public class LeaveRecord {
    private int leaveId;
    private int empno;
    private String leaveType;
    private int totalDays;
    private int daysUsed;
    private int remainingDays;
    private String leavePeriod; // 휴가 기간 필드 추가
    private Date inputDate;
    private String remarks;

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

    // Getters gg
    public int getLeaveId() { return leaveId; }
    public int getEmpno() { return empno; }
    public String getLeaveType() { return leaveType; }
    public int getTotalDays() { return totalDays; }
    public int getDaysUsed() { return daysUsed; }
    public int getRemainingDays() { return remainingDays; }
    public String getLeavePeriod() { return leavePeriod; } // 추가된 getter
    public Date getInputDate() { return inputDate; }
    public String getRemarks() { return remarks; }
}
