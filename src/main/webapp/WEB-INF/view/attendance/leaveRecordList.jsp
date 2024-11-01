<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>휴가 조회</title>
</head>
<body>
    <h2>휴가 조회</h2>
    <table border="1" width="100%">
        <tr>
            <th>구분</th>
            <th>사원번호</th>
            <th>성명</th>
            <th>부서</th>
            <th>직위</th>
            <th>휴가항목</th>
            <th>전체</th>
            <th>사용</th>
            <th>잔여</th>
        </tr>
        <c:forEach var="record" items="${leaveRecords}">
            <tr>
                <td>${record.employmentType}</td>
                <td>No-${record.empno}</td>
                <td>${record.name}</td>
                <td>${record.dep}</td>
                <td>${record.position}</td>
                <td>${record.leaveType}</td>
                <td>${record.totalDays}</td>
                <td>${record.daysUsed}</td>
                <td>${record.remainingDays}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
