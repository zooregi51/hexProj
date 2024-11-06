<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 급여 이체 페이지 -->
<!-- salaries 이체되지 않은 급여 정보들을 핸들러에서 세팅 -->
<!--給与振込ページ-->
<!-- salaries 振替されていない給与情報をハンドラーでセッティング-->

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
	<!-- salaries가 비어있으면 급여 목록이 없음을 출력 -->
	<!-- salariesが空いていれば給与目録がないことを出力-->
<c:if test="${salaries.isEmpty()}">
	<tr>
		<td colspan="4">이체할 급여가 없습니다.</td>
	</tr>
</c:if>
<!-- salaries에 들어있는 급여 내역만큼 출력 -->
<!-- salariesに入っている給与内訳だけ出力 --->
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
<!-- salaries가 비어있지 않으면 이체할 수 있는 버튼을 출력 -->
<!-- salariesが空いていなければ振り替えられるボタンを出力-->
<c:if test="${!salaries.isEmpty()}">
<form action="salaryTransfer.do" method="post">
<input type="submit" value="급여이체하기">
</form>
</c:if>
</body>
</html>