package attendance.model;

import java.sql.Date;

public class LeaveRecord {
    private int leaveId;
    private int employeeId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private int totalDays;
    private int daysUsed;
    private int remainingDays;
    private String remarks;

    // 생성자
    public LeaveRecord(int leaveId, int employeeId, String leaveType, Date startDate, Date endDate, int totalDays, int daysUsed, int remainingDays, String remarks) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.daysUsed = daysUsed;
        this.remainingDays = remainingDays;
        this.remarks = remarks;
    }

    // Getter만 생성 (setter는 필요 없다는 요청에 따라 제외)
    public int getLeaveId() { return leaveId; }
    public int getEmployeeId() { return employeeId; }
    public String getLeaveType() { return leaveType; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public int getTotalDays() { return totalDays; }
    public int getDaysUsed() { return daysUsed; }
    public int getRemainingDays() { return remainingDays; }
    public String getRemarks() { return remarks; }
}
