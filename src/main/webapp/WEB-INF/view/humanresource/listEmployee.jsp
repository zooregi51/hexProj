<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="humanResourceCss.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>사원현황 관리</title>
</head>
<body>
<h1>사원현황/관리</h1>
<form action="deleteEmployee.do" method="post" id="multidelete">
<table border="1" width="1000" height="100" align="left">
	<tr align="center">
		<td bgcolor=#F642C0 ><font color="white">재직자</font></td>
		<td bgcolor=#79CEFF><font color="white">정규직</font></td>
		<td bgcolor=#79CEFF><font color="white">계약직</font></td>
		<td bgcolor=#79CEFF><font color="white">임시직</font></td>
		<td bgcolor=#79CEFF><font color="white">파견직</font></td>
		<td bgcolor=#79CEFF><font color="white">위촉직</font></td>
		<td bgcolor=#79CEFF><font color="white">일용직</font></td>
		<td bgcolor=#B5B5B5><font color="white">퇴사자</font></td>
		<td bgcolor=#545454><font color="white">전체</font></td>
	
	</tr>
	<tr align="center">
		<td><a href="list.do?pageNo=${employeePage.startPage }&searchForm=재직자">
		${employeePage.getHiredNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=정규직">
		${employeePage.getPermanentNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=계약직">
		${employeePage.getContractNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=임시직">
		${employeePage.getTemporaryNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=파견직">
		${employeePage.getDispatchedNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=위촉직">
		${employeePage.getCommissionedNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=일용직">
		${employeePage.getDailyjobNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=퇴직자">
		${employeePage.getRetiredNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}">
		${employeePage.getTotal() }</a></td>
</table>
<br/>
<table border="1" width="1000">
<thead>
	<tr align="center">
		<td><input type='checkbox' name="all" onclick="allChk(this.checked);"/></td>
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
		<td colspan="12">
			<c:if test="${employeePage.startPage > 5 }">
			<a href="list.do?pageNo=${employeePage.startPage-5 }">[이전]</a>
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
<button type="button" onclick="location.href='list.do'">신규사원 등록</button>
<input type="submit" value="선택 삭제">
</form>

</body>
</html>