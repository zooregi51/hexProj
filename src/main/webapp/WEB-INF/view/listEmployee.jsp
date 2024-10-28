<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원현황 관리</title>
</head>
<body>
<div style="text-align:center;vertical-align:middle;
width:160px;height:40px;margin-right:5px">
재직자
</div>
<div style="text-align:center;vertical-align:middle;
width:160px;height:40px;margin-right:5px">
${employeePage.getHiredNum() }
</div>
<br/>
<table border="1">
<thead>
	<tr>
		<td><input type='checkbox' id="allCheck" onclick="allChk(this);"/></td>
		<td>구분</td>
		<td>입사일</td>
		<td>사원번호</td>
		<td>성명</td>
		<td>부서</td>
		<td>직위</td>
		<td>주민번호</td>
		<td>휴대폰</td>
		<td>이메일</td>
		<td>퇴사일</td>
		<td>상태</td>
	</tr>
</thead>
<tbody>
<c:if test="${employeePage.hasNoEmployees() }">
	<tr>
		<td colspan="4">사원이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="employee" items="${employeePage.employee }">
	<tr>
		<td><input type="checkbox" name="checkedempno" value="${employee.empno }"/></td>
		<td>${employee.empform }</td>
		<td>${employee.hireddate }</td>
		<td>No-1400${employee.empno }</td>
		<td>${employee.name }</td>
		<td>${employee.dep }</td>
		<td>${employee.position }</td>
		<td>${employee.registrationnum }</td>
		<td>${employee.phone }</td>
		<td>${employee.email }</td>
		<td>${employee.retireddate }</td>
		<c:if test="${employee.retireddate != null}">
			<td>퇴직</td>
		</c:if>
		<c:if test="${employee.retireddate == null}">
			<td>재직</td>
		</c:if>
	</tr>
</c:forEach>
<c:if test="${employeePage.hasEmployees() }">
	<tr>
		<td colspan="4">
			<c:if test="${employeePage.startPage > 5 }">
			<a href="list.do>pageNo=${employeePage.startPage-5 }">[이전]</a>
			</c:if>
			<c:forEach var="pNo" begin="${employeePage.startPage }" end="${employeePage.endPage }">
			<a href="list.do?pageNo=${pNo }">[${pNo }]</a>
			</c:forEach>
			<c:if test="${employeePage.endPage<employeePage.totalPages }">
			<a href="list.do?pageNo=${employeePage.startPage+5 }">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</tbody>
</table>
</body>
</html>