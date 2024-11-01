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
        <th>사원 코드</th>
        <th>근무 일자</th>
        <th>출근 시간</th>
        <th>퇴근 시간</th>
        <th>근무 유형</th>
        <th>승인 상태</th>
        <th>총 초과 근무 시간</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>

    <!-- 근태 기록 리스트를 반복해서 출력 -->
    <c:forEach var="record" items="${attendanceList}">
        <tr>
            <td>${record.recordId}</td>
            <td>${record.employeeId}</td>
            <td>${record.workDate}</td>
            <td>${record.startTime}</td>
            <td>${record.endTime}</td>
            <td>${record.workType}</td>
            <td>${record.approvalStatus}</td>
            <td>${record.totalOvertimeHours}</td>
            <td><a href="attendance.do?action=edit&recordId=${record.recordId}">수정</a></td>
            <td><a href="attendance.do?action=delete&recordId=${record.recordId}">삭제</a></td>
        </tr>
    </c:forEach>
</table>

<!-- 근태 기록 추가 폼 -->
<h3>새로운 근태 기록 추가</h3>
<form action="attendance.do?action=add" method="post">
    사원 코드: <input type="text" name="employeeId"><br>
    근무 일자: <input type="date" name="workDate"><br>
    출근 시간: <input type="time" name="startTime"><br>
    퇴근 시간: <input type="time" name="endTime"><br>
    근무 유형: <input type="text" name="workType"><br>
    승인 상태: <input type="text" name="approvalStatus"><br>
    총 초과 근무 시간: <input type="number" name="totalOvertimeHours"><br>
    <input type="submit" value="추가">
</form>

</body>
</html>
