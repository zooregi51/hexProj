<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="salary.model.*"%>
<!-- 항목별 급여 대장 페이지-->
<!-- itemizedLedger 사원들의 항목별 급여대장을 핸들러에서 세팅 -->
<!-- 項目別給与台帳ページ-->
<!--itemized Ledger 社員の項目別給与台帳をハンドラーでセッティング-->

<%@include file="../includes/header.jsp"%>
	<!-- 연도, 상세 급여 설정하는 폼 -->
	<!-- 年度、詳細給与を設定するフォーム-->
	<form action="salaryItemizedLedger.do" method="get">
		<label>조회기간</label> 
		<!-- 연도는 2005년 부터 현재 년도까지 검색 가능 -->
		<!-- 年度は2005年から現在年度まで検索可能 -->
		<select name="year">
			<option value="">選択</option>
			<jsp:useBean id="now" class="java.util.Date" />
			<fmt:formatDate value="${now}" pattern="yyyy" var="startYear" />
			<c:forEach begin="0" end="${startYear - 2005}" var="year" step="1">
				<option value="${startYear-year}">${startYear-year}</option>
			</c:forEach>
		</select> 년/年 
		<!-- 상세 급여 8가지 상세급여 중 하나 선택 가능 -->
		<!-- 詳細給与8つの詳細給与の中から1つを選択可能-->
		<select name="item">
			<option value="salary">基本給</option>
            <option value="food">食費</option>
            <option value="childcare">保育手当</option>
            <option value="position_allowance">職責手当</option>
            <option value="continuos_service">勤続手当</option>
            <option value="nightduty">当直手当</option>
            <option value="bonus">ボーナス</option>
            <option value="holiday">休日手当</option>
          </select>
		<input type="submit" value="検索">
	</form>

	<table border="1">
		<tr>
			<td>区分</td>
			<td>姓名</td>
			<td>部署</td>
			<td>職位</td>
			<c:forEach var="month" begin="1" end="12">
				<td>${year}-${month}</td>
			</c:forEach>
			<td>合計</td>
		</tr>
		<!-- itemizedLedger가 비어있으면 급여 목록이 없음을 출력 -->
		<!-- itemized Ledgerが空いていれば給与リストがないことを出力-->
		<c:if test="${itemizedLedger.isEmpty()}">
			<tr>
				<td colspan="4">給与リストがありません。</td>
			</tr>
		</c:if>
		<!-- 월 단위 합과 총 합을 계산하기 위한 스크립트 -->
		<!-- 月単位合計と総合計を計算するためのスクリプト-->
		<%
			ArrayList<ItemizedLedger> mems = (ArrayList<ItemizedLedger>) request.getAttribute("itemizedLedger");
			ArrayList<Integer> sumRow = new ArrayList<Integer>();
			for(int i = 0; i < 12; ++i){
				sumRow.add(0);
			}
			
			// 사원 정보는 출력
			// 사원의 급여 정보는 개인의 합의 경우 total이라는 지역 변수로 따로 저장 후 같은 행에 마지막에 출력
			// 월별 합은 sumRow라는 arrayList를 만들어 저장
			// 社員情報は出力
			// 社員の給与情報は個人の合意の場合、totalという地域変数で別に保存し、同じ行に最後に出力
			// 月ごとの合計はsumRowというarrayListを作って保存
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
			out.print("<tr><td>合計</td><td></td><td></td><td></td>");
			// 월별 합 및 총합 출력
			// 月別合計及び総合計出力
			int total = 0;
            for (int j = 0; j < sumRow.size(); j++) {
                out.print("<td>" + sumRow.get(j) + "</td>");
                total += sumRow.get(j);
            }
            out.print("<td>" + total + "</td>");
            out.print("</tr>");
		%>
	</table>
<%@include file="../includes/footer.jsp"%>