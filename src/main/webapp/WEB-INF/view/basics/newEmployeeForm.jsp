<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<h2>社員登録</h2>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ja.js"></script>
<!-- 사원 등록 폼 -->
<!-- 社員登録フォーム -->
<form action="registerEmployee.do" method="post">
	<label for="empNo">社員番号:</label> <input type="text" id="empNo"
		name="empNo" value="${nextEmpNo}" readonly><br>雇用形態:</label> <input
		type="text" id="empForm" name="empForm"><br> <label
		for="name">氏名:</label> <input type="text" id="name" name="name"><br>

	<!-- 입사일 날짜 선택 필드 -->
	<!-- 入社日のカレンダー入力 -->
	<label for="hiredDate">入社日:</label> <input type="text" id="hiredDate"
		name="hiredDate"><br>

	<!-- 퇴사일 날짜 선택 필드 -->
	<!-- 退社日のカレンダー入力 -->
	<label for="retiredDate">退社日:</label> <input type="text"
		id="retiredDate" name="retiredDate"><br> <label for="dep">部署:</label>
	<input type="text" id="dep" name="dep"><br> <label
		for="position">職位:</label> <input type="text" id="position"
		name="position"><br> <label for="registrationNum">個人ID番号:
	</label> <input type="text" id="registrationNum" name="registrationNum"><br>

	<label for="address">現在所:</label> <input type="text" id="address"
		name="address"><br> <label for="phone">携帯番号:</label> <input
		type="text" id="phone" name="phone"><br> <label
		for="email">E-mail:</label> <input type="email" id="email"
		name="email"><br> <label for="other">備考:</label> <input
		type="text" id="other" name="other"><br> <label
		for="salary">給与:</label> <input type="text" id="salary" name="salary"><br>
	<input type="submit" value="登録">

</form>

<!-- 날짜 입력 필드를 일본어 형식으로 설정 -->
<!-- 日付入力フィールドを日本語の形式に設定 -->
<script>
	flatpickr("#hiredDate", {
		locale : "ja"
	});
	flatpickr("#retiredDate", {
		locale : "ja"
	});
</script>
<%@include file="../includes/footer.jsp"%>