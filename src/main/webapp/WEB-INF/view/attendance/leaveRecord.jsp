<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>휴가 기록 조회</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>휴가 기록 조회 - 사원번호: ${empno}</h2>
    <table>
        <thead>
            <tr>
                <th>휴가 번호</th>
                <th>휴가 유형</th>
                <th>시작일</th>
                <th>종료일</th>
                <th>총 일수</th>
                <th>사용 일수</th>
                <th>남은 일수</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${leaveRecords}">
                <tr>
                    <td>${record.leaveId}</td>
                    <td>${record.leaveType}</td>
                    <td>${record.startDate}</td>
                    <td>${record.endDate}</td>
                    <td>${record.totalDays}</td>
                    <td>${record.daysUsed}</td>
                    <td>${record.remainingDays}</td>
                    <td>${record.remarks}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
