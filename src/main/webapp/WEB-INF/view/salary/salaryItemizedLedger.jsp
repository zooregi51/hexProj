<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="salary.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>급여 상세 대장</title>
</head>
<body>

	<form action="salaryItemizedLedger.do" method="get">
		<label>조회기간</label> <select name="year">
			<option value="">선택</option>
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy" var="startYear" />
			<c:forEach begin="0" end="${startYear - 2005}" var="year" step="1">
				<option value="${startYear-year}">${startYear-year}</option>
			</c:forEach>
		</select> 년 
		<select name="item">
			<option value="salary">기본급</option>
            <option value="food">식비</option>
            <option value="childcare">보육수당</option>
            <option value="position_allowance">직책수당</option>
            <option value="continuos_service">근속수당</option>
            <option value="nightduty">당직수당</option>
            <option value="bonus">상여금</option>
            <option value="holiday">휴일수당</option>
          </select>
		<input type="submit" value="검색">
	</form>

	<table border="1">
		<tr>
			<td>구분</td>
			<td>성명</td>
			<td>부서</td>
			<td>직위</td>
			<c:forEach var="month" begin="1" end="12">
				<td>${year}-${month}</td>
			</c:forEach>
			<td>합계</td>
		</tr>
		<c:if test="${ledgerDetail.isEmpty()}">
			<tr>
				<td colspan="4">급여 목록이 없습니다.</td>
			</tr>
		</c:if>
		<%
			ArrayList<ItemizedLedger> mems = (ArrayList<ItemizedLedger>) request.getAttribute("itemizedLedger");
			ArrayList<Integer> sumRow = new ArrayList<Integer>();
			for(int i = 0; i < 12; ++i){
				sumRow.add(0);
			}
			for(int i = 0; i < mems.size(); ++i){
				 out.print("<tr>");
                 out.print("<td>" + mems.get(i).getEmpForm() + "</td>");
                 out.print("<td>" + mems.get(i).getEmpName() + "</td>");
                 out.print("<td>" + mems.get(i).getDep() + "</td>");
                 out.print("<td>" + mems.get(i).getPos() + "</td>");
                 
                 int total = 0;
                 for(int j = 0; j < 12; ++j){
                	 out.print("<td>" + mems.get(i).getYearSal().get(j) + "</td>");
                	 total += mems.get(i).getYearSal().get(j);
                	 sumRow.set(j, sumRow.get(j) + mems.get(i).getYearSal().get(j));
                 }
                 
                 out.print("<td>" + total + "</td>");
                 out.print("</tr>");
			}
			out.print("<tr><td>합계</td><td></td><td></td><td></td>");
			int total = 0;
            for (int j = 0; j < sumRow.size(); j++) {
                out.print("<td>" + sumRow.get(j) + "</td>");
                total += sumRow.get(j);
            }
            out.print("<td>" + total + "</td>");
            out.print("</tr>");
		%>
	</table>
</body>
</html>