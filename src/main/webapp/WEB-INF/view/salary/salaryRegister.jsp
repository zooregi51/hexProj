<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 입력</title>
</head>
<body>
	<form action="salaryManage.do" method="post">
		<p>
			<input type="hidden" name="empno" value="${emp.getEmpId()}">
		</p>
		<p>
			<input type="hidden" name="type" value="new">
		</p>
		<p>
			기본급 : <br>
			<input type="number" name="salary" value="${emp.getSalary()}">
		</p>
		<p>
			식비 : <br>
			<input type="number" name="food" value=0>
		</p>
		<p>
			보육수당 : <br>
			<input type="number" name="childcare" value=0>
		</p>
		<p>
			직책수당 : <br>
			<input type="number" name="position" value=0>
		</p>
		<p>
			근속수당 : <br>
			<input type="number" name="longservice" value=0>
		</p>
		<p>
			당직수당 : <br>
			<input type="number" name="oncall" value=0>
		</p>
		<p>
			상여금 : <br>
			<input type="number" name="bonus" value=0>
		</p>
		<p>
			휴일수당 : <br>
			<input type="number" name="holiday" value=0>
		</p>
		<input type="submit" value="저장">
	</form>
</body>
</html>