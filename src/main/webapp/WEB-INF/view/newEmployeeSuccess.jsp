<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>사원 등록 성공</title>
<script type="text/javascript">
	// 팝업 메시지 후에 등록 페이지로 돌아가는 스크립트
	function showPopupAndRedirect() {
		alert("등록이 완료되었습니다.");
		window.location.href = "registerEmployee.do"; // 다시 사원 등록 페이지로 리다이렉트
	}
	// 페이지가 로드되면 팝업을 보여주고 리다이렉트 실행
	window.onload = showPopupAndRedirect;
</script>
</head>
<body>
	<!-- 등록 성공 시 출력할 내용 (팝업 후 리다이렉트 예정) -->
	<h2>사원 등록 성공</h2>
</body>
</html>