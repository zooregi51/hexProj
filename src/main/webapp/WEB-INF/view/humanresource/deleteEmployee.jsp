<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="employee.dao.EmployeeDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Delete</title>
</head>
<body>

<script type="text/javascript">
	alert("체크된 글 모두 삭제 성공");
	location.href="list.do";
</script>

<script type="text/javascript">
	alert("체크된 글 모두 삭제 실패");
	location.href="list.do"
</script>

</body>
</html>