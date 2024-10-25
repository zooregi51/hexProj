<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사원 리스트</title>
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
    <h2>사원 리스트</h2>
    <table>
        <thead>
            <tr>
                <th>구분</th>
                <th>사원번호</th>
                <th>성명</th>
                <th>부서</th>
                <th>직위</th>
                <th>근태기록</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td>${employee.empform}</td>
                    <td>No-${employee.empno}</td>
                    <td>${employee.name}</td>
                    <td>${employee.dep}</td>
                    <td>${employee.position}</td>
                    <td><a href="/attendance/record.do?empno=${employee.empno}">관리</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
