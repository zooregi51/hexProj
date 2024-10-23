<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>근태 기록 수정</title>
</head>
<body>
    <h1>근태 기록 수정</h1>
    <form action="attendance.do?action=edit" method="post">
        <input type="hidden" name="recordId" value="${record.recordId}">

        <label for="employeeId">사원 ID:</label>
        <input type="text" name="employeeId" id="employeeId" value="${record.employeeId}" required><br>

        <label for="workDate">근무일자 (yyyy-mm-dd):</label>
        <input type="date" name="workDate" id="workDate" value="${record.workDate}" required><br>

        <label for="startTime">출근 시간 (hh:mm:ss):</label>
        <input type="time" name="startTime" id="startTime" value="${record.startTime}" required><br>

        <label for="endTime">퇴근 시간 (hh:mm:ss):</label>
        <input type="time" name="endTime" id="endTime" value="${record.endTime}" required><br>

        <label for="workType">근무 유형:</label>
        <input type="text" name="workType" id="workType" value="${record.workType}" required><br>

        <label for="approvalStatus">승인 상태:</label>
        <input type="text" name="approvalStatus" id="approvalStatus" value="${record.approvalStatus}"><br>

        <label for="totalOvertimeHours">총 초과 근무 시간:</label>
        <input type="number" name="totalOvertimeHours" id="totalOvertimeHours" value="${record.totalOvertimeHours}"><br>

        <input type="submit" value="근태 기록 수정">
    </form>
</body>
</html>
