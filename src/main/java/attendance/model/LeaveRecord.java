package attendance.model;

public class LeaveRecord {
    private int recordID;
    private int employeeID;
    private String leaveType;
    private int totalDays;
    private int daysUsed;
    private int remainingDays;

    // Constructor, getters
    public LeaveRecord(int recordID, int employeeID, String leaveType, int totalDays, int daysUsed, int remainingDays) {
        this.recordID = recordID;
        this.employeeID = employeeID;
        this.leaveType = leaveType;
        this.totalDays = totalDays;
        this.daysUsed = daysUsed;
        this.remainingDays = remainingDays;
    }

    public int getRecordID() { return recordID; }
    public int getEmployeeID() { return employeeID; }
    public String getLeaveType() { return leaveType; }
    public int getTotalDays() { return totalDays; }
    public int getDaysUsed() { return daysUsed; }
    public int getRemainingDays() { return remainingDays; }
}
