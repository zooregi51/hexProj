<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>사원 조회</title>
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

<h2>사원 조회</h2>

<!-- 사원 리스트를 보여주는 테이블 -->
<table>
    <thead>
        <tr>
            <th>사원 번호</th>
            <th>사원 이름</th>
            <th>부서</th>
            <th>직위</th>
            <th>전화번호</th>
            <th>이메일</th>
            <th>기타 정보</th>
        </tr>
    </thead>
    <tbody>
        <!-- 사원 리스트 반복 출력 -->
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.empNo}</td>
                <td>${employee.name}</td>
                <td>${employee.dep}</td>
                <td>${employee.empForm}</td>
                <td>${employee.phone}</td>
                <td>${employee.email}</td>
                <td>${employee.other}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
