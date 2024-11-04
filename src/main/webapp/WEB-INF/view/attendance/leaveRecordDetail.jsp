<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>휴가 조회 상세</title>
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
    <h2>휴가 조회 상세</h2>
    <table>
        <thead>
            <tr>
                <th>휴가 번호</th>
                <th>사원 번호</th>
                <th>휴가 유형</th>
                <th>총 일수</th>
                <th>사용 일수</th>
                <th>남은 일수</th>
                <th>휴가 기간</th>
                <th>입력일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${leaveRecords}">
                <tr>
                    <td>${record.leaveId}</td>
                    <td>${record.empno}</td>
                    <td>${record.leaveType}</td>
                    <td>${record.totalDays}</td>
                    <td>${record.daysUsed}</td>
                    <td>${record.remainingDays}</td>
                    <td>${record.leavePeriod}</td>
                    <td>${record.inputDate}</td>
                    <td>${record.remarks}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
