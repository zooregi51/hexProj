<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!-- 상세 급여 대장 페이지-->
<!-- ledgerDetail 사원들의 상세 급여대장을 핸들러에서 세팅 -->
<!-- 詳細給与台帳ページ-->
<!--ledgerDetail社員の詳細給与台帳をハンドラーでセッティング-->
<%@include file="../includes/header.jsp"%>

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
<!-- ledgerDetail가 비어있으면 상세 급여 대장 내역이 없음을 출력 -->
<!--ledgerDetailが空だと、詳細給与台帳の内訳がないことを出力-->
<c:if test="${ledgerDetail.isEmpty()}">
	<tr>
		<td colspan="4">급여 목록이 없습니다.</td>
	</tr>
</c:if>
<!-- ledgerDetail에 들어있는 급여 내역만큼 출력 -->
<!--ledger Detailに入っている給与内訳だけ出力-->
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
<%@include file="../includes/footer.jsp"%>