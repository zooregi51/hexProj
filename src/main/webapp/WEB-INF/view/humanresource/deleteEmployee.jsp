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
<%
	String[] empdelete=request.getParameterValues("checkedempno");
	EmployeeDao employeeDao = new EmployeeDao();
	int res=employeeDao.multiDelete(empdelete);
%>
<form action="delete.do" method="post">
</body>
</html>