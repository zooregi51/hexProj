<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第証明書発行台帳</title>
</head>
<body>
<h1>第証明書発行台帳</h1>
<table border="1" width="1000">
	<tr align="center">
		<td>発行番号</td>
		<td>発行台帳</td>
		<td>発行用途</td>
		<td>区分</td>
		<td>氏名</td>
		<td>部署</td>
		<td>職位</td>
		<td>発行日</td>
	</tr>
<c:if test="${certificatePage.hasNoCertificates() }">
	<tr>
		<td colspan="4">証明書発行台帳がありません</td>
	</tr>
</c:if>
<c:forEach var="certificate" items="${certificatePage.certificate }">
	<tr align="center">
		<td>${certificate.certificate_Id }</td>
		<td>${certificate.certificate_Register }</td>
		<td>${certificate.certificate_Purpose }</td>
		<td>${certificate.empform }</td>
		<td>${certificate.name }</td>
		<td>${certificate.dep }</td>
		<td>${certificate.position }</td>
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