<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제 증명서 발급</title>
</head>
<body>
<h1>제 증명서 발급</h1>
<table border="1" width="600" style="float:left">
	<thead>
		<tr align="center">
			<td>구분</td>
			<td>성명</td>
			<td>직위</td>
			<td>상태</td>
			<td>선택</td>
		</tr>
	</thead>
	<tbody>
	<c:if test="${employeePage.hasNoEmployees() }">
	<tr>
		<td colspan="4">사원이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="employee" items="${employeePage.employee }">
	<tr align="center">
		<td>${employee.empform }</td>
		<td>${employee.name }</td>
		<td>${employee.position }</td>
		<c:if test="${employee.retireddate != null}">
			<td>퇴직</td>
		</c:if>
		<c:if test="${employee.retireddate == null}">
			<td>재직</td>
		</c:if>
		<td><a href="issuance.do?empno=${employee.empno }">선택</a>
	</tr>
</c:forEach>
	</tbody>
</table>

</body>
</html>