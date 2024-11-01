<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>社員登録</title>
</head>
<body>
	<h2>社員登録</h2>
	<form action="registerEmployee.do" method="post">
		<label for="empNo">社員番号:</label> <input type="text" id="empNo"
			name="empNo" value="${nextEmpNo}" readonly><br>雇用形態:</label> <input
			type="text" id="empForm" name="empForm"><br> <label
			for="name">名:</label> <input type="text" id="name" name="name"><br>
		<label for="hiredDate">채용일:</label> <input type="date" id="hiredDate"
			name="hiredDate"><br> <label for="retiredDate">퇴사일:</label>
		<input type="date" id="retiredDate" name="retiredDate"><br>

		<label for="dep">부서:</label> <input type="text" id="dep" name="dep"><br>

		<label for="position">직위:</label> <input type="text" id="position"
			name="position"><br> <label for="registrationNum">등록
			번호:</label> <input type="text" id="registrationNum" name="registrationNum"><br>

		<label for="address">주소:</label> <input type="text" id="address"
			name="address"><br> <label for="phone">전화 번호:</label> <input
			type="text" id="phone" name="phone"><br> <label
			for="email">이메일:</label> <input type="email" id="email" name="email"><br>

		<label for="other">기타:</label> <input type="text" id="other"
			name="other"><br> <label for="salary">급여:</label> <input
			type="text" id="salary" name="salary"><br> <input
			type="submit" value="등록">
	</form>
</body>
</html>