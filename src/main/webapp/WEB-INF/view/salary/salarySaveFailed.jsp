<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 급여 저장 실패 페이지 -->
<!-- 급여 실패 시 이동되는 페이지 -->
<!--給与保存失敗ページ-->
<!--給与失敗時に移動されるページ-->
<%@include file="../includes/header.jsp"%>

保存に失敗しました。
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/salary/salaryManage.do">[給与管理画面に移動]</a>
<%@include file="../includes/footer.jsp"%>