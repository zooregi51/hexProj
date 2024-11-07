<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 급여 변경 페이지 -->
<!-- emp 특정 사원의 정보를 핸들러에서 세팅 -->
<!--給与変更ページ-->
<!-- emp 特定社員の情報をハンドラーでセッティング-->
<%@include file="../includes/header.jsp"%>
<!-- emp로 부터 사원 정보를 가져오고 입력된 내용을 post 요청하는 form -->
<!-- empから社員情報を取得し、入力された内容をpost要請するフォーム-->
<form action="salaryManage.do" method="post">
	<p>
		<input type="hidden" name="empno" value="${emp.getEmpId()}">
	</p>
	<p>
		<input type="hidden" name="type" value="new">
	</p>
	<p>
		基本給:<br> <input type="number" name="salary"
			value="${emp.getSalary()}">
	</p>
	<p>
		食費:<br> <input type="number" name="food" value=0>
	</p>
	<p>
		保育手当:<br> <input type="number" name="childcare" value=0>
	</p>
	<p>
		役職手当:<br> <input type="number" name="position" value=0>
	</p>
	<p>
		勤続手当:<br> <input type="number" name="longservice" value=0>
	</p>
	<p>
		当直手当:<br> <input type="number" name="oncall" value=0>
	</p>
	<p>
		賞与:<br> <input type="number" name="bonus" value=0>
	</p>
	<p>
		休日手当:<br> <input type="number" name="holiday" value=0>
	</p>
	<input type="submit" value="セーブ">
</form>
<%@include file="../includes/footer.jsp"%>