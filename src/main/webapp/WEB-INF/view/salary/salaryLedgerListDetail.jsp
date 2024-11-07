<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!-- 상세 급여 대장 페이지-->
<!-- ledgerDetail 사원들의 상세 급여대장을 핸들러에서 세팅 -->
<!-- 詳細給与台帳ページ-->
<!--ledgerDetail社員の詳細給与台帳をハンドラーでセッティング-->
<%@include file="../includes/header.jsp"%>

<table border="1">
	<tr>
		<td>区分</td>
		<td>氏名</td>
		<td>入社日</td>
		<td>部署</td>
		<td>職位</td>
		<td>基本給</td>
		<td>食費</td>
		<td>保育手当</td>
		<td>職責手当</td>
		<td>勤続手当</td>
		<td>当直手当</td>
		<td>賞与金</td>
		<td>休日手当</td>
		<td>支給総額</td>
	</tr>
	<!-- ledgerDetail가 비어있으면 상세 급여 대장 내역이 없음을 출력 -->
	<!--ledgerDetailが空だと、詳細給与台帳の内訳がないことを出力-->
	<c:if test="${ledgerDetail.isEmpty()}">
		<tr>
			<td colspan="4">給与リストがありません。</td>
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