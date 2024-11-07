<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<h2>休暇項目設定</h2>

<!-- 휴가 항목 추가/수정 폼 -->
<!-- 休暇項目の追加/修正フォーム -->
<form action="vacationItem.do" method="post">
	<input type="hidden" name="action"
		value="${selectedItem != null ? 'update' : 'add'}"> <input
		type="hidden" name="id"
		value="${selectedItem != null ? selectedItem.id : 0}">

	<!-- 휴가 이름 입력 필드 -->
	<!-- 休暇名の入力フィールド -->
	<label for="name">休暇名:</label> <input type="text" id="name" name="name"
		value="${selectedItem != null ? selectedItem.name : ''}" required><br>

	<!-- 적용 기간 입력 필드 (예시 형식: yyyy-MM-dd ~ yyyy-MM-dd) -->
	<!-- 適用期間の入力フィールド (例: yyyy-MM-dd ~ yyyy-MM-dd) -->
	<label for="period">適用期間 (例: yyyy-MM-dd ~ yyyy-MM-dd):</label> <input
		type="text" id="period" name="period"
		value="${selectedItem != null ? selectedItem.period : ''}" required><br>

	<!-- 사용 여부 입력 필드 (Y/N) -->
	<!-- 使用有無の入力フィールド (Y/N) -->
	<label for="useFlag">使用有無 (Y/N):</label> <input type="text"
		id="useFlag" name="useFlag"
		value="${selectedItem != null ? selectedItem.useFlag : 'Y'}" required><br>

	<input type="submit" value="${selectedItem != null ? '保存' : '追加'}">
</form>

<hr>

<!-- 등록된 휴가 항목 목록 표시 -->
<!-- 登録済みの休暇項目リストの表示 -->
<h3>休暇項目</h3>
<table border="1">
	<tr>
		<th>休暇名</th>
		<!-- 휴가 이름 -->
		<th>適用期間</th>
		<!-- 적용 기간 -->
		<th>使用有無 (Y/N)</th>
		<!-- 사용 여부 -->
		<th>備考</th>
		<!-- 비고 -->
	</tr>

	<!-- 등록된 모든 휴가 항목을 테이블로 표시 -->
	<!-- 登録済みの全ての休暇項目をテーブルとして表示 -->
	<c:forEach var="item" items="${vacationItems}">
		<tr>
			<td>${item.name}</td>
			<!-- 휴가 이름 표시 -->
			<td>${item.period}</td>
			<!-- 적용 기간 표시 -->
			<td>${item.useFlag}</td>
			<!-- 사용 여부 표시 -->
			<td>
				<!-- 수정 버튼 폼 --> <!-- 修正ボタンのフォーム -->
				<form action="vacationItem.do" method="post"
					style="display: inline;">
					<input type="hidden" name="action" value="edit"> <input
						type="hidden" name="id" value="${item.id}"> <input
						type="submit" value="修正">
				</form> <!-- 삭제 버튼 폼 --> <!-- 削除ボタンのフォーム -->
				<form action="vacationItem.do" method="post"
					style="display: inline;">
					<input type="hidden" name="action" value="delete"> <input
						type="hidden" name="id" value="${item.id}"> <input
						type="submit" value="削除">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<!-- 메시지가 있는 경우 표시 -->
<!-- メッセージがある場合は表示 -->
<c:if test="${not empty message}">
	<p>${message}</p>
</c:if>
<%@include file="../includes/footer.jsp"%>