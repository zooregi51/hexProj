<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원현황 관리</title>
<script type="text/javascript">
// 1.모두 체크
  function allChk(obj){
      var chkObj = document.getElementsByName("checkedempno");
      var rowCnt = chkObj.length - 1;
      var check = obj.checked;
      if (check) {﻿
          for (var i=0; i<=rowCnt; i++){
           if(chkObj[i].type == "checkbox")
               chkObj[i].checked = true;
          }
      } else {
          for (var i=0; i<=rowCnt; i++) {
           if(chkObj[i].type == "checkbox"){
               chkObj[i].checked = false;
           }
          }
      }
  } 

//﻿2. 체크박스 선택된 것 삭제 처리 (N개) 
   function fn_userDel(){

  var userid = "";
  var memberChk = document.getElementsByName("checkedempno");
  var chked = false;
  var indexid = false;
  for(i=0; i < memberChk.length; i++){
   if(memberChk[i].checked){
    if(indexid){
      userid = userid + '-';
    }
    userid = userid + memberChk[i].value;
    indexid = true;
   }
  }
  if(!indexid){
   alert("삭제할 사용자를 체크해 주세요");
   return;
  }
  document.userForm.delUserid.value = userid;       // 체크된 사용자 아이디를 '-'로 묶은 userid 를     

                                                                              document.userForm.delUserid 의 value로 저장
  
  var agree=confirm("삭제 하시겠습니까?");
     if (agree){
   document.userForm.execute.value = "userDel";
     document.userForm.submit();
     }
  }﻿

</script> 
</head>
<body>
<table border="1" width="1000" height="100" align="left">
	<tr align="center">
		<td>재직자</td>
		<td>정규직</td>
		<td>계약직</td>
		<td>임시직</td>
		<td>파견직</td>
		<td>위촉직</td>
		<td>일용직</td>
		<td>퇴사자</td>
		<td>전체</td>
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
		<td><input type='checkbox' id="allCheck" onclick="allChk(this);"/></td>
		<td>구분</td>
		<td>입사일</td>
		<td>사원번호</td>
		<td>성명</td>
		<td>부서</td>
		<td>직위</td>
		<td>주민번호</td>
		<td>휴대폰</td>
		<td>이메일</td>
		<td>퇴사일</td>
		<td>상태</td>
	</tr>
</thead>
<tbody>
<c:if test="${employeePage.hasNoEmployees() }">
	<tr>
		<td colspan="4">사원이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="employee" items="${employeePage.employee }">
	<tr>
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
			<td>퇴직</td>
		</c:if>
		<c:if test="${employee.retireddate == null}">
			<td>재직</td>
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
<button type="button" onclick="location.href='list.do'">신규사원 등록</button>
<button type="button">선택 삭제</button>
</body>
</html>