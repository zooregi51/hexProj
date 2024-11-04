package attendance.model;

import java.sql.Date;

public class AttendanceRecord {
    private int recordId;
    private int empno;
    private Date inputDate;
    private String attendanceType;
    private String attendancePeriod;
    private int attendanceDays;
    private int amount;
    private String remarks;

    // 생성자, getter 및 setter
    public AttendanceRecord(int recordId, int empno, Date inputDate, String attendanceType, String attendancePeriod, int attendanceDays, int amount, String remarks) {
        this.recordId = recordId;
        this.empno = empno;
        this.inputDate = inputDate;
        this.attendanceType = attendanceType;
        this.attendancePeriod = attendancePeriod;
        this.attendanceDays = attendanceDays;
        this.amount = amount;
        this.remarks = remarks;
    }

    // Getter 및 Setter 생략...
}
