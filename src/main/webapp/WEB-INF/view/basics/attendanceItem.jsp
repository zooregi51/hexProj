<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<h2>勤怠項目設定</h2>

<!-- 근태 항목 추가/수정 폼 -->
<!-- 勤怠項目の追加/修正フォーム -->
<form action="attendanceItem.do" method="post">
	<input type="hidden" name="action"
		value="${selectedItem != null ? 'update' : 'add'}"> <input
		type="hidden" name="id"
		value="${selectedItem != null ? selectedItem.id : 0}">
	<!-- 근태 항목 이름 입력 -->
	<!-- 勤怠項目名の入力 -->
	<label for="name">勤怠項目名:</label> <input type="text" id="name"
		name="name" value="${selectedItem != null ? selectedItem.name : ''}"
		required><br>

	<!-- 단위 입력 (예: 일, 시간) -->
	<!-- 単位の入力 (例: 日、時間) -->
	<label for="unit">単位 (例: 日、時間)</label> <input type="text" id="unit"
		name="unit" value="${selectedItem != null ? selectedItem.unit : ''}"
		required><br>

	<!-- 그룹 선택 -->
	<!-- グループの選択 -->
	<label for="groupCategory">グループ管理:</label> <select id="groupCategory"
		name="groupCategory" required>
		<option value="休暇"
			${selectedItem != null && selectedItem.groupCategory == '休暇' ? 'selected="selected"' : ''}>休暇</option>
		<option value="残業"
			${selectedItem != null && selectedItem.groupCategory == '残業' ? 'selected="selected"' : ''}>残業</option>
		<option value="遅刻・早退"
			${selectedItem != null && selectedItem.groupCategory == '遅刻・早退' ? 'selected="selected"' : ''}>遅刻・早退</option>
		<option value="特別勤務"
			${selectedItem != null && selectedItem.groupCategory == '特別勤務' ? 'selected="selected"' : ''}>特別勤務</option>
		<option value="その他"
			${selectedItem != null && selectedItem.groupCategory == '"その他"' ? 'selected="selected"' : ''}>"その他"</option>
	</select><br>

	<!-- 사용 여부 설정 (Y/N) -->
	<!-- 使用有無の設定 (Y/N) -->
	<label for="useFlag">使用有無 (Y/N):</label> <input type="text"
		id="useFlag" name="useFlag"
		value="${selectedItem != null ? selectedItem.useFlag : 'Y'}" required><br>

	<input type="submit" value="${selectedItem != null ? '保存' : '追加'}">
</form>

<hr>

<!-- 등록된 근태 항목 목록 표시 -->
<!-- 登録済みの勤怠項目リストの表示 -->
<h3>勤怠項目</h3>
<table border="1">
	<tr>
		<th>勤怠名</th>
		<!-- 근태 항목 이름 -->
		<th>単位</th>
		<!-- 단위 -->
		<th>グループ</th>
		<!-- 그룹 -->
		<th>使用有無</th>
		<!-- 사용 여부 -->
		<th>備考</th>
		<!-- 비고 -->
	</tr>
	<c:forEach var="item" items="${attendanceItems}">
		<tr>
			<td>${item.name}</td>
			<td>${item.unit}</td>
			<td>${item.groupCategory}</td>
			<td>${item.useFlag}</td>
			<td>
				<form action="attendanceItem.do" method="post"
					style="display: inline;">
					<input type="hidden" name="action" value="edit"> <input
						type="hidden" name="id" value="${item.id}"> <input
						type="submit" value="修正">
				</form>
				<form action="attendanceItem.do" method="post"
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