<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제 증명서 발급대장</title>
</head>
<body>

<table border="1">
	<tr>
		<td>발급번호</td>
		<td>발급대장</td>
		<td>발급용도</td>
		<td>구분</td>
		<td>성명</td>
		<td>부서</td>
		<td>직위</td>
		<td>발급일</td>
	</tr>
<c:if test="${certificatePage.hasCertificates() }">
	<tr>
		<td colspan="4">제 증명서 발급대장이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="certificate" items="${certificatePage.certificate }">
	<tr>
		<td>${certificate.certificate_Id }</td>
		<td>${certificate.certificate_Register }</td>
		<td>${certificate.certificate_Purpose }</td>
		<td>${certificate.employee_Form }</td>
		<td>${certificate.employee_KrName }</td>
		<td>${certificate.employee_Department }</td>
		<td>${certificate.employee_Position }</td>
		<td>${certificate.certificate_Date }</td>
	</tr>
</c:forEach>
<c:if test="${certificatePage.hasCertificates() }">
	<tr>
		<td colspan="4">
			<c:if test="${certificatePage.startPage > 5 }">
			<a href="list.do>pageNo=${certificatePage.startPage-5 }">[이전]</a>
			</c:if>
			<c:forEach var="pNo" begin="${certificatePage.startPage }" end="${certificatePage.endPage }">
			<a href="list.do?pageNo=${pNo }">[${pNo }]</a>
			</c:forEach>
			<c:if test="${certificatePage.endPage<certificatePage.totalPages }">
			<a href="list.do?pageNo=${certificatePage.startPage+5 }">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>