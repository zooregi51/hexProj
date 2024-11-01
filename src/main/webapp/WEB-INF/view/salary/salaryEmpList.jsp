<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여지급 사원 선택</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>구분</td>
			<td>사원번호</td>
			<td>성명</td>
			<td>부서</td>
			<td>직위</td>
		</tr>
		<c:if test="${emps.isEmpty()}">
			<tr>
				<td colspan="4">급여가 미입력된 사원이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="emp" items="${emps}">
			<tr>
				<td>${spec.getEmpForm()}</td>
				<td>${spec.getEmpId()}</td>
				<td><a href="salaryManage.do?type=new&empno=${emp.getEmpId()}">${emp.getEmpName()}</a></td>
				<td>${spec.getEmpDepart()}</td>
				<td>${spec.getEmpPos()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>