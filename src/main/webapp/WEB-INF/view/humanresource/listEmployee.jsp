<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp"%>
<h1>社員の現況/管理</h1>
<form action="deleteEmployee.do" method="post" id="multidelete">
<table border="1" width="1000" height="100" align="left">
	<tr align="center">
		<td bgcolor=#F642C0 ><font color="white">在職者</font></td>
		<td bgcolor=#79CEFF><font color="white">正社員</font></td>
		<td bgcolor=#79CEFF><font color="white">契約職</font></td>
		<td bgcolor=#79CEFF><font color="white">臨時職</font></td>
		<td bgcolor=#79CEFF><font color="white">派遣職</font></td>
		<td bgcolor=#79CEFF><font color="white">委嘱職</font></td>
		<td bgcolor=#79CEFF><font color="white">日雇い</font></td>
		<td bgcolor=#B5B5B5><font color="white">退社者</font></td>
		<td bgcolor=#545454><font color="white">全体</font></td>
	
	</tr>
	<tr align="center">
		<td><a href="list.do?pageNo=${employeePage.startPage }&searchForm=재직자">
		${employeePage.getHiredNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=정규직">
		${employeePage.getPermanentNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=계약직">
		${employeePage.getContractNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=임시직">
		${employeePage.getTemporaryNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=파견직">
		${employeePage.getDispatchedNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=위촉직">
		${employeePage.getCommissionedNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=일용직">
		${employeePage.getDailyjobNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}&searchForm=퇴직자">
		${employeePage.getRetiredNum() }</a></td>
		<td><a href="list.do?pageNo=${employeePage.startPage}">
		${employeePage.getTotal() }</a></td>
</table>
<br/>
<table border="1" width="1000">
<thead>
	<tr align="center">
		<td><input type='checkbox' name="all" onclick="allChk(this.checked);"/></td>
		<td>区分</td>
		<td>入社日</td>
		<td>社員番号</td>
		<td>氏名</td>
		<td>部署</td>
		<td>職位</td>
		<td>住民番号</td>
		<td>携帯電話</td>
		<td>メール</td>
		<td>退社日</td>
		<td>状態</td>
	</tr>
</thead>
<tbody>
<c:if test="${employeePage.hasNoEmployees() }">
	<tr>
		<td colspan="4">社員がいません。</td>
	</tr>
</c:if>
<c:forEach var="employee" items="${employeePage.employee }">
	<tr align="center">
		<td><input type="checkbox" name="checkedempno" value="${employee.empno }"/></td>
		<td>${employee.empform }</td>
		<td>${employee.hireddate }</td>
		<td>No-1400${employee.empno }</td>
		<td>${employee.name }</td>
		<td>${employee.dep }</td>
		<td>${employee.position }</td>
		<td>${employee.registrationnum }</td>
		<td>${employee.phone }</td>
		<td>${employee.email }</td>
		<td>${employee.retireddate }</td>
		<c:if test="${employee.retireddate != null}">
			<td>退職</td>
		</c:if>
		<c:if test="${employee.retireddate == null}">
			<td>在職</td>
		</c:if>
	</tr>
</c:forEach>
<c:if test="${employeePage.hasEmployees() }">
	<tr>
		<td colspan="12">
			<c:if test="${employeePage.startPage > 5 }">
			<a href="list.do?pageNo=${employeePage.startPage-5 }">[이전]</a>
			</c:if>
			<c:forEach var="pNo" begin="${employeePage.startPage }" end="${employeePage.endPage }">
			<a href="list.do?pageNo=${pNo }">[${pNo }]</a>
			</c:forEach>
			<c:if test="${employeePage.endPage<employeePage.totalPages }">
			<a href="list.do?pageNo=${employeePage.startPage+5 }">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</tbody>
</table>
${ctxPath = pageContext.request.contextPath ; ''}
<button type="button" onclick="location.href='${ctxPath}/registerEmployee.do'">新規社員登録</button>
<input type="submit" value="選択削除">
</form>
<%@include file="../includes/footer.jsp"%>