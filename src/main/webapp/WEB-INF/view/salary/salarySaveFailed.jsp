<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 급여 저장 실패 페이지 -->
<!-- 급여 실패 시 이동되는 페이지 -->
<!--給与保存失敗ページ-->
<!--給与失敗時に移動されるページ-->
<%@include file="../includes/header.jsp"%>

저장에 실패했습니다.
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/salary/salaryManage.do">[급여 관리 화면으로 이동]</a>
<%@include file="../includes/footer.jsp"%>