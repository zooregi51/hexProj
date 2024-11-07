<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>

<script type="text/javascript">
	// 팝업 메시지 후에 등록 페이지로 돌아가는 스크립트
	// 登録完了メッセージを表示した後、登録ページに戻るスクリプト
	function showPopupAndRedirect() {
		alert("登録が完了しました。");
		window.location.href = "registerEmployee.do"; // 사원 등록 페이지로 리다이렉트
		// 社員登録ページへリダイレクト
	}
	// 페이지가 로드되면 팝업을 보여주고 리다이렉트 실행
	// ページがロードされたらポップアップを表示してリダイレクトを実行
	window.onload = showPopupAndRedirect;
</script>

<!-- 등록 성공 시 출력할 내용 (팝업 후 리다이렉트 예정) -->
<!-- 登録成功時に表示する内容 (ポップアップ後リダイレクト予定) -->
<h2>社員登録成功</h2>
</script>
<%@include file="../includes/footer.jsp"%>