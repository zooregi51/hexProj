<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 급여 이체 실패 페이지 -->
<!-- 급여 이체 실패 시 이동되는 페이지 -->
<!--給与振込失敗ページ-->
<!--給与振込失敗時に移動されるページ-->
<%@include file="../includes/header.jsp"%>

이체에 실패했습니다.
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/salary/salaryTransfer.do">[이체 화면으로 이동]</a>
<%@include file="../includes/footer.jsp"%>