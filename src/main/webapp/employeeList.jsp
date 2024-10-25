<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 리스트</title>
</head>
<body>
<h2>사원 리스트</h2>

<table border="1">
    <tr>
        <th>사원번호</th>
        <th>고용 형태</th>
        <th>이름</th>
        <th>부서</th>
        <th>직위</th>
        <th>근태 기록</th>
    </tr>

    <c:forEach var="employee" items="${employeeList}">
        <tr>
            <td>${employee.empNo}</td>
            <td>${employee.empForm}</td>
            <td>${employee.name}</td>
            <td>${employee.dep}</td>
            <td>${employee.position}</td>
            <td><a href="attendance.do?action=manage&empNo=${employee.empNo}">관리</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
