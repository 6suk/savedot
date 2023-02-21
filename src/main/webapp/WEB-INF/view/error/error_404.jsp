<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>

<!DOCTYPE html>
<html>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>FinalProject</title>
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->

	<div class="container" style="margin-top: 300px;">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4" style="text-align: center">
				<h4 style="color: #666" class="pb-1">404 Error</h4>
				<h1 class="txtmain pb-4">Page Not Found</h1>
				<p>죄송합니다. 페이지를 찾을 수 없습니다.</p>
				<p>존재하지 않는 주소를 입력하셨거나,</p>
				<p>요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.</p>
				<button class="btn btn-sub mdi font-14 mt-4"
					style="padding-block: 0.6rem; padding-inline: 3rem;"
					onclick="location.href='/mate/list'">HOME</button>
			</div>
			<div class="col-4"></div>
		</div>
	</div>
</body>