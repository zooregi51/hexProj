<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 급여 이체 성공 페이지 -->
<!-- 급여 이체 성공 시 이동되는 페이지 -->
<!--給与振込成功ページ-->
<!--給与振込成功時に移動されるページ-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

이체에 성공했습니다.
<br>
${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/salary/salaryTransferCheck.do">[이체 내역 확인 으로 이동]</a>
</body>
</html>