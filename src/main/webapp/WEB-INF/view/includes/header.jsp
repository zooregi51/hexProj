<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>급여 관리</title>

<!-- Bootstrap Core CSS -->
<link href="../resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
${ctxPath = pageContext.request.contextPath ; ''}
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-brand">給与管理</a>
			</div>

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="#"> 基本環境設定<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${ctxPath}/registerEmployee.do">社員登録</a></li>
								<li><a href="${ctxPath}/vacationItem.do">休暇項目登録</a></li>
								<li><a href="${ctxPath}/attendanceItem.do">勤怠項目登録</a></li>
							</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"> 人事管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${ctxPath}/humanresource/list.do">社員の現況/管理</a>
								</li>
								<li><a href="${ctxPath}/certificate/list.do">第証明書発行台帳</a>
								</li>
							</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"> 勤怠管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${ctxPath}/attendance/diligenceRecordList.do">勤怠記録/管理</a>
								</li>
								<li><a href="${ctxPath}/attendance/leaveRecordlist.do">休暇照会</a></li>
							</ul> <!-- /.nav-second-level --></li>
						<li><a href="#"> 給与管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${ctxPath}/salary/salaryManage.do">給与入力/管理</a>
								</li>
								<li><a href="${ctxPath}/salary/salLedger.do">給与台帳</a></li>
								<li><a href="${ctxPath}/salary/salarySpec.do">給与明細書</a></li>
								<li><a href="${ctxPath}/salary/salaryTransfer.do">給与振替申請</a></li>
								<li><a href="${ctxPath}/salary/salaryTransferCheck.do">給与振替の結果照会</a></li>
								<li><a href="${ctxPath}/salary/salaryItemizedLedger.do">項目別台帳</a></li>
							</ul> <!-- /.nav-second-level --></li>

						<li><a href="#"> 退職管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">사원 퇴직처리</a></li>
							</ul> <!-- /.nav-second-level --></li>

					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">

			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>