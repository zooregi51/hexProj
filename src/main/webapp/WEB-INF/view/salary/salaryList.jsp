<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 급여 관리 페이지 -->
<!-- salaries 사원들의 급여 정보를 핸들러에서 세팅 -->
<!--給与管理ページ-->
<!-- salaries 社員たちの給与情報をハンドラーでセッティング-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 관리</title>
</head>
<body>
	<!-- 신규 추가를 위한 사원 목록으로 이동하기 위한 앵커 -->
	<!-- 新規追加のための社員リストに移動するためのアンカー -->
	<a href="salaryManage.do?type=new">[신규추가]</a>
	<table border="1">
		<tr>
			<td>구분</td>
			<td>성명</td>
			<td>부서</td>
			<td>지급총액</td>
		</tr>
		<!-- salaries가 비어있으면 급여입력 내역이 없음을 출력 -->
		<!-- salariesが空だと給与入力内訳がないことを出力-->
		<c:if test="${salaries.isEmpty()}">
			<tr>
				<td colspan="4">급여입력 내역이 없습니다.</td>
			</tr>
		</c:if>
		<!-- salaries가 들어있는 수 만큼 출력 -->
		<!-- salariesが入っている数だけ出力-->
		<c:forEach var="spec" items="${salaries}">
			<tr>
				<td>${spec.getEmpForm()}</td>
				<!-- 사원 이름에 앵커를 달아서 누르면 패러미터로 사원 번호, update를 주는 get 요청을 하도록 함 -->
				<!--社員の名前にアンカーをつけて押すとパラメーターで社員番号、updateを与えるget要請をするようにする-->
				<td><a href="salaryManage.do?type=update&empno=${spec.getEmpNo()}">${spec.getEmpName()}</a></td>
				<td>${spec.getDep()}</td>
				<td>${spec.getActualTotal()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>