package attendance.model;

public class LeaveRecord {
    private int leaveId;
    private int empno;
    private String leaveType;
    private int totalDays;
    private int daysUsed;
    private int remainingDays;

    public LeaveRecord(int leaveId, int empno, String leaveType, int totalDays, int daysUsed, int remainingDays) {
        this.leaveId = leaveId;
        this.empno = empno;
        this.leaveType = leaveType;
        this.totalDays = totalDays;
        this.daysUsed = daysUsed;
        this.remainingDays = remainingDays;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public int getEmpno() {
        return empno;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getDaysUsed() {
        return daysUsed;
    }

    public int getRemainingDays() {
        return remainingDays;
    }
}
