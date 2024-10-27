<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>근태 기록 관리</title>
</head>
<body>

<h2>근태 기록 관리</h2>

<!-- 근태 기록 리스트를 보여주는 테이블 -->
<table border="1">
    <tr>
        <th>번호</th>
        <th>사원 번호</th>
        <th>입력 일자</th>
        <th>근태 항목</th>
        <th>근태 기간 (시작시간)</th>
        <th>근태 기간 (종료시간)</th>
        <th>근태 일수</th>
        <th>금액</th>
        <th>적요</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>

    <!-- 근태 기록 리스트를 반복해서 출력 -->
    <c:forEach var="record" items="${attendanceList}">
        <tr>
            <td>${record.recordId}</td>
            <td>${record.employeeId}</td>
            <td>${record.entryDate}</td>
            <td>${record.workType}</td>
            <td>${record.startTime}</td>
            <td>${record.endTime}</td>
            <td>${record.workDays}</td>
            <td>${record.amount}</td>
            <td>${record.remarks}</td>
            <td><a href="attendance.do?action=edit&recordId=${record.recordId}">수정</a></td>
            <td><a href="attendance.do?action=delete&recordId=${record.recordId}">삭제</a></td>
        </tr>
    </c:forEach>
</table>

<!-- 근태 기록 추가 폼 -->
<h3>새로운 근태 기록 추가</h3>
<form action="attendance.do?action=add" method="post">
    사원 번호: <input type="number" name="employeeId" required><br>
    입력 일자: <input type="date" name="entryDate" required><br>
    근태 항목: <input type="text" name="workType" required><br>
    근태 시작 시간: <input type="time" name="startTime" required><br>
    근태 종료 시간: <input type="time" name="endTime" required><br>
    근태 일수: <input type="number" name="workDays" step="0.5" required><br>
    금액: <input type="number" name="amount"><br>
    적요: <input type="text" name="remarks"><br>
    <input type="submit" value="추가">
</form>

</body>
</html>
