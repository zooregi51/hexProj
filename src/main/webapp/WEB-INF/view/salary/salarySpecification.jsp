<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 명세서</title>
</head>
<body>

	<form action="salarySpec.do" method="get">
		<label>조회기간</label> <select name="year">
			<option value="">선택</option>
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy" var="startYear" />
			<c:forEach begin="0" end="${startYear - 2005}" var="year" step="1">
				<option value="${startYear-year}">${startYear-year}</option>
			</c:forEach>
		</select> 년 <select name="month">
			<option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
		</select>월 <input type="submit" value="검색">
	</form>

	<table border="1">
		<tr>
			<td>구분</td>
			<td>성명</td>
			<td>실지급액</td>
		</tr>
		<c:if test="${specs.isEmpty()}">
			<tr>
				<td colspan="4">이체된 급여가 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="salary" items="${specs}">
			<tr>
				<td>${salary.getEmpForm()}</td>
				<td><a
					href="salarySpec.do?empNo=${salary.getEmpNo()}&year=${year}
					&month=${month}">${salary.getEmpName()}</a></td>
				<td>${salary.getActualTotal()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>