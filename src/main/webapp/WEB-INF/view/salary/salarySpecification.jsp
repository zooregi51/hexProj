<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 급여 명세서 페이지 -->
<!-- specs 특정 기간 사원의 급여 정보를 핸들러에서 세팅 -->
<!--給与明細ページ-->
<!--specs 特定期間社員の給与情報をハンドラーでセッティング-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 명세서</title>
</head>
<body>
	
	<!-- 연도, 달 설정하는 폼 -->
	<!-- 年、月を設定するフォーム-->
	<form action="salarySpec.do" method="get">
		<!-- 연도는 2005년 부터 현재 년도까지 검색 가능 -->
		<!-- 年度は2005年から現在年度まで検索可能 -->
		<label>조회기간</label> <select name="year">
			<option value="">선택</option>
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy" var="startYear" />
			<c:forEach begin="0" end="${startYear - 2005}" var="year" step="1">
				<option value="${startYear-year}">${startYear-year}</option>
			</c:forEach>
		</select> 년
		<!-- 달 선택 --> 
		<!--月選択-->
		<select name="month">
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
		<!-- specs가 비어있으면 급여 목록이 없음을 출력 -->
		<!--specsが空いていれば給与目録がないことを出力-->
		<c:if test="${specs.isEmpty()}">
			<tr>
				<td colspan="4">이체된 급여가 없습니다.</td>
			</tr>
		</c:if>
		<!-- specs에 들어있는 급여 내역만큼 출력 -->
		<!--specsに入っている給与内訳だけ出力-->
		<c:forEach var="salary" items="${specs}">
			<tr>
				<td>${salary.getEmpForm()}</td>
				<!-- 사원 이름에 앵커를 달아서 누르면 패러미터로 사원 번호, 연도를 주는 get 요청을 하도록 함 -->
				<!--社員の名前にアンカーをつけて押すとパラメーターで社員番号、年度を与えるget要請をするようにする-->
				<td><a
					href="salarySpec.do?empNo=${salary.getEmpNo()}&year=${year}
					&month=${month}">${salary.getEmpName()}</a></td>
				<td>${salary.getActualTotal()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>