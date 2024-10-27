<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>휴가 항목 관리</title>
</head>
<body>
	<h2>휴가 항목 관리</h2>

	<!-- 휴가 항목 추가/수정 폼 -->
	<form action="vacationItem.do" method="post">
		<input type="hidden" name="action"
			value="${selectedItem != null ? 'update' : 'add'}"> <input
			type="hidden" name="id"
			value="${selectedItem != null ? selectedItem.id : 0}"> <label
			for="name">휴가 항목 이름:</label> <input type="text" id="name" name="name"
			value="${selectedItem != null ? selectedItem.name : ''}" required><br>

		<label for="period">적용 기간 (예: yyyy-MM-dd ~ yyyy-MM-dd):</label> <input
			type="text" id="period" name="period"
			value="${selectedItem != null ? selectedItem.period : ''}" required><br>

		<label for="useFlag">사용 여부 (Y/N):</label> <input type="text"
			id="useFlag" name="useFlag"
			value="${selectedItem != null ? selectedItem.useFlag : 'Y'}" required><br>

		<input type="submit" value="${selectedItem != null ? '저장' : '추가'}">
	</form>

	<hr>

	<!-- 등록된 휴가 항목 목록 표시 -->
	<h3>등록된 휴가 항목</h3>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>적용 기간</th>
			<th>사용 여부</th>
			<th>작업</th>
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
							type="submit" value="수정">
					</form>
					<form action="vacationItem.do" method="post"
						style="display: inline;">
						<input type="hidden" name="action" value="delete"> <input
							type="hidden" name="id" value="${item.id}"> <input
							type="submit" value="삭제">
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