<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 급여 이체 확인 페이지 -->
<!-- salaries 이체된 않은 급여 정보들을 핸들러에서 세팅 -->
<!--給与振込確認ページ-->
<!-- salaries 振替されていない給与情報をハンドラーでセッティング-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 이체</title>
</head>
<body>
<!-- 기간 설정하는 폼 -->
<!-- 시작 날짜, 끝 날짜 설정 -->
<!-- 期間設定するフォーム --->
<!--開始日、終了日の設定-->
<form action="salaryTransferCheck.do" method="get">
	<label>조회기간</label>
	<input type="date"
        name="stDate"
        max="2077-12-31"
        min="2000-01-01"/>
    <label>~</label>
    <input type="date"
        name="edDate"
        max="2077-12-31"
        min="2000-01-01"/>
    <input type="submit" value="검색">
</form>


<table border="1">
	<tr>		
		<td>이체일자</td>
		<td>예금주</td>
		<td>이체금액</td>
		<td>처리결과</td>
	</tr>
	<!-- salaries가 비어있으면 급여 목록이 없음을 출력 -->
	<!-- salariesが空いていれば給与目録がないことを出力-->
<c:if test="${salaries.isEmpty()}">
	<tr>
		<td colspan="4">급여이체 데이터가 없습니다.</td>
	</tr>
</c:if>
<!-- salaries에 들어있는 급여 내역만큼 출력 -->
<!-- salariesに入っている給与内訳だけ出力 --->
<c:forEach var="salary" items="${salaries}">
	<tr>
		<td>${salary.getEmployee().getEmpName()}</td>
		<td>${salary.getEmployee().getEmpName()}</td>
		<td>${salary.getSalPayment().getSalBasicSalary()+salary.getSalPayment().getSalFood()
		+ salary.getSalPayment().getSalChildCare() + salary.getSalPayment().getSalPositionSalary()
		+ salary.getSalPayment().getSalPositionSalary() + salary.getSalPayment().getSalLongService()
		+ salary.getSalPayment().getSalOncall() + salary.getSalPayment().getSalHoliday()}</td>		
		<td>이체완료</td>
	</tr>
</c:forEach>
</table>
</body>
</html>