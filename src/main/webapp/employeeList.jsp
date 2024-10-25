<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사원 리스트</title>
</head>
<body>
    <h2>사원 리스트</h2>
    <table border="1">
        <thead>
            <tr>
                <th>사원번호</th>
                <th>고용 형태</th>
                <th>이름</th>
                <th>부서</th>
                <th>직위</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td>${employee.empno}</td>
                    <td>${employee.empform}</td>
                    <td>${employee.name}</td>
                    <td>${employee.dep}</td>
                    <td>${employee.position}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
