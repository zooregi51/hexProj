<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 이체</title>
</head>
<body>

<table border="1">
	<tr>
		<td>성명</td>
		<td>부서</td>
		<td>직위</td>
		<td>실지급액</td>
	</tr>
<c:if test="${salaries.isEmpty()}">
	<tr>
		<td colspan="4">이체할 급여가 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="salary" items="${salaries}">
	<tr>
		<td>${salary.getEmployee().getEmpName()}</td>
		<td>${salary.getEmployee().getEmpDepart()}</td>
		<td>${salary.getEmployee().getEmpPos()}</td>
		<td>${salary.getSalPayment().getSalBasicSalary()+salary.getSalPayment().getSalFood()
		+ salary.getSalPayment().getSalChildCare() + salary.getSalPayment().getSalPositionSalary()
		+ salary.getSalPayment().getSalPositionSalary() + salary.getSalPayment().getSalLongService()
		+ salary.getSalPayment().getSalOncall() + salary.getSalPayment().getSalHoliday()}</td>		
	</tr>
</c:forEach>
</table>
<c:if test="${!salaries.isEmpty()}">
<form action="salaryTransfer.do" method="post">
<input type="submit" value="급여이체하기">
</form>
</c:if>
</body>
</html>