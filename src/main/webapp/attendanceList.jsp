<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>근태 기록 목록</title>
</head>
<body>
    <h1>근태 기록 목록</h1>
    <table border="1">
        <thead>
            <tr>
                <th>기록 ID</th>
                <th>사원 ID</th>
                <th>근무일자</th>
                <th>출근 시간</th>
                <th>퇴근 시간</th>
                <th>근무 유형</th>
                <th>승인 상태</th>
                <th>총 초과 근무 시간</th>
                <th>기능</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${records}">
                <tr>
                    <td>${record.recordId}</td>
                    <td>${record.employeeId}</td>
                    <td>${record.workDate}</td>
                    <td>${record.startTime}</td>
                    <td>${record.endTime}</td>
                    <td>${record.workType}</td>
                    <td>${record.approvalStatus}</td>
                    <td>${record.totalOvertimeHours}</td>
                    <td>
                        <a href="attendance.do?action=edit&recordId=${record.recordId}">수정</a>
                        <a href="attendance.do?action=delete&recordId=${record.recordId}" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="attendance.do?action=add">새 근태 기록 추가</a>
</body>
</html>
