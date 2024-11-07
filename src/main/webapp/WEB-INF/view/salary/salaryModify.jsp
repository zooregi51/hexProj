<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 급여 변경 페이지 -->
<!-- salary 특정 사원의 급여 정보를 핸들러에서 세팅 -->
<!--給与変更ページ-->
<!-- salary 特定社員の給与情報をハンドラーでセッティング-->
<%@include file="../includes/header.jsp"%>
<!-- salary로 부터 급여 정보를 가져오고 입력된 내용을 post 요청하는 form -->
<!-- salaryから給与情報を取得し、入力された内容をpost要請するフォーム-->
<form action="salaryManage.do" method="post">
	<p>
		<input type="hidden" name="empno"
			value="${salary.getEmployee().getEmpId()}">
	</p>
	<p>
		<input type="hidden" name="type" value="update">
	</p>
	<p>
		基本給:<br> <input type="number" name="salary"
			value="${salary.getSalPayment().getSalBasicSalary()}">
	</p>
	<p>
		食費:<br> <input type="number" name="food"
			value="${salary.getSalPayment().getSalFood() }">
	</p>
	<p>
		保育手当:<br> <input type="number" name="childcare"
			value="${salary.getSalPayment().getSalChildCare()}">
	</p>
	<p>
		役職手当:<br> <input type="number" name="position"
			value="${salary.getSalPayment().getSalPositionSalary() }">
	</p>
	<p>
		勤続手当:<br> <input type="number" name="longservice"
			value="${salary.getSalPayment().getSalLongService() }">
	</p>
	<p>
		当直手当:<br> <input type="number" name="oncall"
			value="${salary.getSalPayment().getSalOncall() }">
	</p>
	<p>
		賞与:<br> <input type="number" name="bonus"
			value="${salary.getSalPayment().getSalBonus() }">
	</p>
	<p>
		休日手当:<br> <input type="number" name="holiday"
			value="${salary.getSalPayment().getSalHoliday() }">
	</p>
	<input type="submit" value="セーブ">
</form>
<%@include file="../includes/footer.jsp"%>