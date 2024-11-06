<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 대장</title>
</head>
<body>
	<form action="salLedger.do" method="get">
		<select name="year">
			<option value="">선택</option>
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy" var="startYear" />
			<c:forEach begin="0" end="${startYear - 2005}" var="year" step="1">
				<option value="${startYear-year}">${startYear-year}</option>
			</c:forEach>
		</select>
		<input type="submit" value="검색">
	</form>
	<table border="1">
		<tr>
			<td>귀속연월</td>
			<td>정산기간</td>
			<td>지급일</td>
			<td>인원</td>
			<td>지급총액</td>
		</tr>
		<c:if test="${specs.isEmpty()}">
			<tr>
				<td colspan="4">급여대장 내역이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="spec" items="${ledgerMonth}">
			<tr>
				<td><a href="salLedger.do?term=${spec.getYearMonth()}">${spec.getYearMonth()}</a></td>
				<td>${spec.getTerm()}</td>
				<td>${spec.getGivingDate()}</td>
				<td>${spec.getEmpNum()}</td>
				<td>${spec.getPaymentTotal()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>