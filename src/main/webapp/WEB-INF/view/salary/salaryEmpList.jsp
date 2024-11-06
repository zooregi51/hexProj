<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 급여 지급 사원 선택 페이지-->
<!-- emps 사원정보들의 리스트를 핸들러에서 세팅 -->
<!--給与支給社員選択ページ-->
<!--emps社員情報のリストをハンドラーでセッティング-->

<%@include file="../includes/header.jsp"%>
	<table border="1">
		<tr>
			<td>구분</td>
			<td>사원번호</td>
			<td>성명</td>
			<td>부서</td>
			<td>직위</td>
		</tr>
		<!-- emps가 비어있다면 급여가 미입력된 사원이 없습니다를 출력 -->
		<!-- empsが空の場合、給与が未入力の社員がいませんを出力-->
		<c:if test="${emps.isEmpty()}">
			<tr>
				<td colspan="4">급여가 미입력된 사원이 없습니다.</td>
			</tr>
		</c:if>
		<!-- emps에 들어있는 사원 수 만큼 사원 정보를 출력 -->
		<!--empsに入っている社員数だけ社員情報を出力-->
		<c:forEach var="emp" items="${emps}">
			<tr>
				<td>${emp.getEmpForm()}</td>
				<td>${emp.getEmpId()}</td>
				<!-- 이름에 앵커를 달아서 누르면 패러미터로 사원 번호를 주는 get 요청을 하도록 함 -->
				<!--名前にアンカーをつけて押すとパラメーターで社員番号をくれるget要請をするようにする-->
				<td><a href="salaryManage.do?type=new&empno=${emp.getEmpId()}">${emp.getEmpName()}</a></td>
				<td>${emp.getEmpDepart()}</td>
				<td>${emp.getEmpPos()}</td>
			</tr>
		</c:forEach>
	</table>
<%@include file="../includes/footer.jsp"%>