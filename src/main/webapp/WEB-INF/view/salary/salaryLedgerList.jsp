<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 급여 대장 페이지-->
<!-- ledgerMonth 사원들의 상세 급여대장을 핸들러에서 세팅 -->
<!-- 給与台帳ページ-->
<!--ledgerMonth社員の詳細給与台帳をハンドラーでセッティング-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 대장</title>
</head>
<body>
	<!-- 연도, 상세 급여 설정하는 폼 -->
	<!-- 年度、詳細給与を設定するフォーム-->
	<form action="salLedger.do" method="get">
		<!-- 연도는 2005년 부터 현재 년도까지 검색 가능 -->
		<!-- 年度は2005年から現在年度まで検索可能 -->
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
		<!-- ledgerMonth가 비어있으면 급여 대장 내역이 없음을 출력 -->
		<!--ledgerMonthが空だと給与台帳の内訳がないことを出力-->
		<c:if test="${ledgerMonth.isEmpty()}">
			<tr>
				<td colspan="4">급여대장 내역이 없습니다.</td>
			</tr>
		</c:if>
		<!-- ledgerMonth에 들어있는 급여 대장 내역만큼 출력 -->
		<!--ledgerMonthに入っている給与台帳の内訳だけ出力-->
		<c:forEach var="spec" items="${ledgerMonth}">
			<tr>
				<!-- 귀속 년도에 앵커를 달아서 누르면 패러미터로 귀속 년도를 주는 get 요청을 하도록 함 -->
				<!--帰属年にアンカーをつけて押すとパラメータで帰属年を与えるget要請をするようにする-->
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