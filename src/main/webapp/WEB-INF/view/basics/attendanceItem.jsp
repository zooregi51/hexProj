<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>근태 항목 관리</title>
</head>
<body>
	<h2>근태 항목 관리</h2>

	<!-- 근태 항목 추가/수정 폼 -->
	<form action="attendanceItem.do" method="post">
		<input type="hidden" name="action"
			value="${selectedItem != null ? 'update' : 'add'}"> <input
			type="hidden" name="id"
			value="${selectedItem != null ? selectedItem.id : 0}"> <label
			for="name">근태 항목 이름:</label> <input type="text" id="name" name="name"
			value="${selectedItem != null ? selectedItem.name : ''}" required><br>

		<label for="unit">단위 (예: 일, 시간):</label> <input type="text" id="unit"
			name="unit" value="${selectedItem != null ? selectedItem.unit : ''}"
			required><br> <label for="groupCategory">그룹 관리:</label>
		<select id="groupCategory" name="groupCategory" required>
			<option value="휴가"
				${selectedItem != null && selectedItem.groupCategory == '휴가' ? 'selected="selected"' : ''}>휴가</option>
			<option value="연장근무"
				${selectedItem != null && selectedItem.groupCategory == '연장근무' ? 'selected="selected"' : ''}>연장근무</option>
			<option value="지각조퇴"
				${selectedItem != null && selectedItem.groupCategory == '지각조퇴' ? 'selected="selected"' : ''}>지각조퇴</option>
			<option value="특근"
				${selectedItem != null && selectedItem.groupCategory == '특근' ? 'selected="selected"' : ''}>특근</option>
			<option value="기타"
				${selectedItem != null && selectedItem.groupCategory == '기타' ? 'selected="selected"' : ''}>기타</option>
		</select><br> <label for="useFlag">사용 여부 (Y/N):</label> <input type="text"
			id="useFlag" name="useFlag"
			value="${selectedItem != null ? selectedItem.useFlag : 'Y'}" required><br>

		<input type="submit" value="${selectedItem != null ? '저장' : '추가'}">
	</form>

	<hr>

	<!-- 등록된 근태 항목 목록 표시 -->
	<h3>등록된 근태 항목</h3>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>단위</th>
			<th>그룹</th>
			<th>사용 여부</th>
			<th>작업</th>
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
							type="submit" value="수정">
					</form>
					<form action="attendanceItem.do" method="post"
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