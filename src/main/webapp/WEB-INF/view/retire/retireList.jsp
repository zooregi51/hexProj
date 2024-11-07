<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>社員退職処理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f5;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        span {
            color: skyblue;
        }
        .search-container {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<%@include file="../includes/header.jsp"%>
    <h2>社員退職処理</h2>

    <div class="search-container">
        <form action="/retire/retireList.do" method="GET">
            <select name="schType" id="schType">
                <option value="empNo">社員番号</option>
                <option value="name">氏名</option>
            </select>
            <input type="text" name="schText"  id="schText"	placeholder="検索語を入力してください" />
            <button type="submit">検索</button>
        </form>
    </div>

    <table>
        <tr>
            <th>社員番号</th>
            <th>氏名</th>
            <th>部署</th>
            <th>職位</th>
            <th>入社日</th>
        </tr>
        <c:if test="${list.isEmpty()}">
            <tr>
                <td colspan="5">社員がいません</td>
            </tr>
        </c:if>
        <c:forEach var="item" items="${list}">
            <tr onclick="javascript:fnOpenPopup('${item.getEmpNo()}');">
                <td>${item.getEmpNo()}</td>
                <td>${item.getName()}</td>
                <td>${item.getDep()}</td>
                <td>${item.getPosition()}</td>
                <td>${item.getHiredDate()}</td>
            </tr>
        </c:forEach>
    </table>
    <script type="text/javascript">
    
        //퇴직처리 팝업창 호출
        function fnOpenPopup(empNo){
        	let _empNo = empNo;
            window.open("/retire/retireState.do?empNo="+_empNo, "retireState", "width=550,height=400");
        }
    </script>
    <%@include file="../includes/footer.jsp"%>
