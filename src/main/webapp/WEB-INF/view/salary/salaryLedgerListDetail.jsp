<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 상세 대장</title>
</head>
<body>

<table border="1">
	<tr>
		<td>구분</td>
		<td>성명</td>
		<td>입사일</td>
		<td>부서</td>
		<td>직위</td>
		<td>기본급</td>
		<td>식비</td>
		<td>보육수당</td>
		<td>직책수당</td>
		<td>근속수당</td>
		<td>당직수당</td>
		<td>상여금</td>
		<td>휴일수당</td>
		<td>지급총액</td>
	</tr>
<c:if test="${ledgerDetail.isEmpty()}">
	<tr>
		<td colspan="4">급여 목록이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="salary" items="${ledgerDetail}">
	<tr>
		<td>${salary.getEmployee().getEmpForm()}</td>
		<td>${salary.getEmployee().getEmpName()}</td>
		<td>${salary.getEmployee().getEmpHiredDate()}</td>
		<td>${salary.getEmployee().getEmpDepart()}</td>
		<td>${salary.getEmployee().getEmpPos()}</td>
		<td>${salary.getSalPayment().getSalBasicSalary()}</td>
		<td>${salary.getSalPayment().getSalFood()}</td>
		<td>${salary.getSalPayment().getSalChildCare()}</td>
		<td>${salary.getSalPayment().getSalPositionSalary()}</td>
		<td>${salary.getSalPayment().getSalLongService()}</td>
		<td>${salary.getSalPayment().getSalOncall()}</td>
		<td>${salary.getSalPayment().getSalBonus()}</td>
		<td>${salary.getSalPayment().getSalHoliday()}</td>
		<td>${salary.getSalPayment().getSalBasicSalary()+salary.getSalPayment().getSalFood()
		+ salary.getSalPayment().getSalChildCare() + salary.getSalPayment().getSalPositionSalary()
		+ salary.getSalPayment().getSalPositionSalary() + salary.getSalPayment().getSalLongService()
		+ salary.getSalPayment().getSalOncall() + salary.getSalPayment().getSalHoliday()}</td>		
	</tr>
</c:forEach>
</table>
</body>
</html>