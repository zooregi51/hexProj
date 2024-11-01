package attendance.model;

public class LeaveRecord {
    private String employmentType;
    private int empno;
    private String name;
    private String dep;
    private String position;
    private String leaveType;
    private int totalDays;
    private int daysUsed;
    private int remainingDays;

    public LeaveRecord(String employmentType, int empno, String name, String dep, String position,
                       String leaveType, int totalDays, int daysUsed, int remainingDays) {
        this.employmentType = employmentType;
        this.empno = empno;
        this.name = name;
        this.dep = dep;
        this.position = position;
        this.leaveType = leaveType;
        this.totalDays = totalDays;
        this.daysUsed = daysUsed;
        this.remainingDays = remainingDays;
    }

    // Getter 메소드들
    public String getEmploymentType() { return employmentType; }
    public int getEmpno() { return empno; }
    public String getName() { return name; }
    public String getDep() { return dep; }
    public String getPosition() { return position; }
    public String getLeaveType() { return leaveType; }
    public int getTotalDays() { return totalDays; }
    public int getDaysUsed() { return daysUsed; }
    public int getRemainingDays() { return remainingDays; }
}
