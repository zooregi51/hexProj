<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 관리</title>
</head>
<body>
	<a href="salaryManage.do?type=new">신규추가</a>
	<table border="1">
		<tr>
			<td>구분</td>
			<td>성명</td>
			<td>부서</td>
			<td>지급총액</td>
		</tr>
		<c:if test="${salaries.isEmpty()}">
			<tr>
				<td colspan="4">급여입력 내역이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="spec" items="${salaries}">
			<tr>
				<td>${spec.getEmployee().getEmpForm()}</td>
				<td><a href="salaryManage.do?type=update&empno=${spec.getEmployee().getEmpId}">${spec.getEmployee().getEmpName()}</a></td>
				<td>${spec.getEmployee().getEmpDepart()}</td>
				<td>${spec.getPaymentTotal()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>