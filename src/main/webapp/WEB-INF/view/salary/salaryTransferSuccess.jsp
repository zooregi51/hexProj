<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 급여 이체 성공 페이지 -->
<!-- 급여 이체 성공 시 이동되는 페이지 -->
<!--給与振込成功ページ-->
<!--給与振込成功時に移動されるページ-->

<%@include file="../includes/header.jsp"%>

振込に成功しました。
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/salary/salaryTransferCheck.do">[振込内訳の確認に移動]</a>
<%@include file="../includes/footer.jsp"%>