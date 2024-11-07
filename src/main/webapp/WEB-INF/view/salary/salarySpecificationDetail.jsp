<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!-- 급여 세부 명세서 페이지 -->
<!-- specDetail 특정 기간 특정 사원의 급여 정보를 핸들러에서 세팅 -->
<!-- 給与詳細明細書ページ -->
<!--specDetail特定期間特定社員の給与情報をハンドラーでセッティング-->

<%@include file="../includes/header.jsp"%>

<!-- 급여 명세서 css -->
<!--給与明細書css-->
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid #000;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.highlight {
	background-color: #ffff99;
}
</style>
<!-- 특정 사원의 상세 급여 명세서를 테이블로 생성해서 보여줌 -->
<!-- 特定社員の詳細給与明細書をテーブルで生成して見せる -->
	<h1>${specDetail.getSalNum().substring(0, 4)} 년 ${specDetail.getSalNum().substring(5, 7)} 月給明細書</h1>
	<table>
		<tr>
			<th>姓名</th>
			<td>${specDetail.getEmployee().getEmpName()}</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>部署</th>
			<td>${specDetail.getEmployee().getEmpDepart()}</td>
			<td>職級</td>
			<td>${specDetail.getEmployee().getEmpPos()}</td>
		</tr>
		<tr>
			<th>入社日</th>
			<td>${specDetail.getEmployee().getEmpHiredDate()}</td>
			<th>給与支給日</th>
			<td>${specDetail.getTransferDate()}</td>
		</tr>
	</table>

	<h2>給与内訳</h2>
	<table>
		<thead>
			<tr>
				<th>項目名</th>
				<th>金額</th>
				<th>算出式又は算出方法</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>基本給</td>
				<td>${specDetail.getSalPayment().getSalBasicSalary()}</td>
				<td></td>
			</tr>
			<c:if test="${specDetail.getSalPayment().getSalFood() != 0}">
				<tr>
					<td>食費</td>
					<td>${specDetail.getSalPayment().getSalFood()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalChildCare() != 0}">
				<tr>
					<td>保育費</td>
					<td>${specDetail.getSalPayment().getSalChildCare()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalPositionSalary() != 0}">
				<tr>
					<td>職責手当</td>
					<td>${specDetail.getSalPayment().getSalPositionSalary()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalLongService() != 0}">
				<tr>
					<td>근속수당</td>
					<td>${specDetail.getSalPayment().getSalLongService()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalOncall() != 0}">
				<tr>
					<td>勤続手当</td>
					<td>${specDetail.getSalPayment().getSalOncall()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalBonus() != 0}">
				<tr>
					<td>ボーナス</td>
					<td>${specDetail.getSalPayment().getSalBonus()}</td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${specDetail.getSalPayment().getSalHoliday() != 0}">
				<tr>
					<td>休日手当</td>
					<td>${specDetail.getSalPayment().getSalHoliday()}</td>
					<td></td>
				</tr>
			</c:if>
			<tr>
				<td class="highlight">合計</td>
				<td class="highlight">${specDetail.getSalPayment().getSumPayment()}</td>
				<td></td>
			</tr>
		</tbody>

	</table>
	<p>貴下の苦労に感謝し、ご苦労様でした。</p>
<%@include file="../includes/footer.jsp"%>