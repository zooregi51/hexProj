<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>휴가 조회</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>휴가 조회</h2>
    <table>
        <thead>
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
        </thead>
        <tbody>
            <c:forEach var="record" items="${leaveRecords}">
                <tr>
                    <td>정규직</td> <!-- 예시로 정규직 고정 -->
                    <td>No-${record.employeeID}</td>
                    <td>김철수</td> <!-- 사원의 이름으로 예시 -->
                    <td>컨텐츠팀</td> <!-- 부서명 예시 -->
                    <td>사원</td> <!-- 직위 예시 -->
                    <td>${record.leaveType}</td>
                    <td>${record.totalDays}</td>
                    <td>${record.daysUsed}</td>
                    <td>${record.remainingDays}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
