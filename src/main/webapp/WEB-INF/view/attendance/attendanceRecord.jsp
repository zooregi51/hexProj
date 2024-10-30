<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사원별 근태 기록</title>
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
    <h2>${empno}번 사원의 근태 기록</h2>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>입력일자</th>
                <th>근태항목</th>
                <th>근태기간</th>
                <th>근태일수</th>
                <th>금액</th>
                <th>적요</th>
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
                    <td>${record.remarks}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
