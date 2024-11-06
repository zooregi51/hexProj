<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 수정</title>
</head>
<body>
	<form action="salaryManage.do" method="post">
		<p>
			<input type="hidden" name="empno" value="${salary.getEmployee().getEmpId()}">
		</p>
		<p>
			<input type="hidden" name="type" value="update">
		</p>
		<p>
			기본급 : <br>
			<input type="number" name="salary" value="${salary.getSalPayment().getSalBasicSalary()}">
		</p>
		<p>
			식비 : <br>
			<input type="number" name="food" value="${salary.getSalPayment().getSalFood() }">
		</p>
		<p>
			보육수당 : <br>
			<input type="number" name="childcare" value="${salary.getSalPayment().getSalChildCare()}">
		</p>
		<p>
			직책수당 : <br>
			<input type="number" name="position" value="${salary.getSalPayment().getSalPositionSalary() }">
		</p>
		<p>
			근속수당 : <br>
			<input type="number" name="longservice" value="${salary.getSalPayment().getSalLongService() }">
		</p>
		<p>
			당직수당 : <br>
			<input type="number" name="oncall" value="${salary.getSalPayment().getSalOncall() }">
		</p>
		<p>
			상여금 : <br>
			<input type="number" name="bonus" value="${salary.getSalPayment().getSalBonus() }">
		</p>
		<p>
			휴일수당 : <br>
			<input type="number" name="holiday" value="${salary.getSalPayment().getSalHoliday() }">
		</p>
		<input type="submit" value="저장">
	</form>
</body>
</html>