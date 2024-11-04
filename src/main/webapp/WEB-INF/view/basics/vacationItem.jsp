<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>休暇項目管理</title>
</head>
<body>
	<h2>休暇項目設定</h2>

	<!-- 休暇sㄴ 항목 추가/수정 폼 -->
	<form action="vacationItem.do" method="post">
		<input type="hidden" name="action"
			value="${selectedItem != null ? 'update' : 'add'}"> <input
			type="hidden" name="id"
			value="${selectedItem != null ? selectedItem.id : 0}"> <label
			for="name">休暇名:</label> <input type="text" id="name" name="name"
			value="${selectedItem != null ? selectedItem.name : ''}" required><br>

		<label for="period">適用期間 (例: yyyy-MM-dd ~ yyyy-MM-dd):</label> <input
			type="text" id="period" name="period"
			value="${selectedItem != null ? selectedItem.period : ''}" required><br>

		<label for="useFlag">使用の選択 (Y/N):</label> <input type="text"
			id="useFlag" name="useFlag"
			value="${selectedItem != null ? selectedItem.useFlag : 'Y'}" required><br>

		<input type="submit" value="${selectedItem != null ? '保存' : '追加'}">
	</form>

	<hr>

	<!-- 등록된 휴가 항목 목록 표시 -->
	<h3>休暇項目</h3>
	<table border="1">
		<tr>
			<th>休暇名</th>
			<th>適用期間</th>
			<th>使用の選択 (Y/N)</th>
			<th>備考</th>
		</tr>
		<c:forEach var="item" items="${vacationItems}">
			<tr>
				<td>${item.name}</td>
				<td>${item.period}</td>
				<td>${item.useFlag}</td>
				<td>
					<form action="vacationItem.do" method="post"
						style="display: inline;">
						<input type="hidden" name="action" value="edit"> <input
							type="hidden" name="id" value="${item.id}"> <input
							type="submit" value="修正">
					</form>
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

	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
</body>
</html>