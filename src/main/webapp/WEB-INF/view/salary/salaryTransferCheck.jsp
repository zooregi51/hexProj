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
<form action="salaryTransferCheck.do" method="get">
	<label>조회기간</label>
	<input type="date"
        name="stDate"
        max="2077-12-31"
        min="2000-01-01"/>
    <label>~</label>
    <input type="date"
        name="edDate"
        max="2077-12-31"
        min="2000-01-01"/>
    <input type="submit" value="검색">
</form>


<table border="1">
	<tr>		
		<td>이체일자</td>
		<td>예금주</td>
		<td>이체금액</td>
		<td>처리결과</td>
	</tr>
<c:if test="${salaries.isEmpty()}">
	<tr>
		<td colspan="4">급여이체 데이터가 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="salary" items="${salaries}">
	<tr>
		<td>${salary.getTransferDate()}</td>
		<td>${salary.getEmployee().getEmpName()}</td>
		<td>${salary.getSalPayment().getSalBasicSalary()+salary.getSalPayment().getSalFood()
		+ salary.getSalPayment().getSalChildCare() + salary.getSalPayment().getSalPositionSalary()
		+ salary.getSalPayment().getSalPositionSalary() + salary.getSalPayment().getSalLongService()
		+ salary.getSalPayment().getSalOncall() + salary.getSalPayment().getSalHoliday()}</td>		
		<td>이체완료</td>
	</tr>
</c:forEach>
</table>
</body>
</html>