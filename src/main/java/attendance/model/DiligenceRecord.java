package attendance.model;

import java.sql.Date;

public class DiligenceRecord {
    private int recordId;
    private int employeeId;
    private String diligenceType;
    private Date startDate;
    private Date endDate;
    private int diligenceDays;
    private String remarks;

    public DiligenceRecord(int recordId, int employeeId, String diligenceType, Date startDate, Date endDate, int diligenceDays, String remarks) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.diligenceType = diligenceType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diligenceDays = diligenceDays;
        this.remarks = remarks;
    }

    // Getters
    public int getRecordId() { return recordId; }
    public int getEmployeeId() { return employeeId; }
    public String getDiligenceType() { return diligenceType; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public int getDiligenceDays() { return diligenceDays; }
    public String getRemarks() { return remarks; }
}
