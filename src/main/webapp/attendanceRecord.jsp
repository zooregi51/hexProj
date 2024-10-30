<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사원 근태기록</title>
</head>
<body>
    <h2>사원 근태기록</h2>
    <table border="1">
        <thead>
            <tr>
                <th>번호</th>
                <th>입력일자</th>
                <th>근태항목</th>
                <th>근태기간</th>
                <th>근태일수</th>
                <th>금액</th>
                <th>적요</th>
                <th>수정/삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${attendanceRecords}">
                <tr>
                    <td>${record.recordId}</td>
                    <td>${record.inputDate}</td>
                    <td>${record.attendanceType}</td>
                    <td>${record.attendancePeriod}</td>
                    <td>${record.attendanceDays}</td>
                    <td>${record.amount}</td>
                    <td>${record.notes}</td>
                    <td>
                        <a href="/attendance/editRecord.do?recordId=${record.recordId}">수정</a> /
                        <a href="/attendance/deleteRecord.do?recordId=${record.recordId}">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
