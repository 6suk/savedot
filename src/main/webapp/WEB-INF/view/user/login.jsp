<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>SAVE. : 로그인</title>
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->

	<div class="container-nonaside-sm">
		<section class="j-wrap login">
			<h1 class="maintxt login-title">SAVE.</h1>
			<form class="login-wrap" action="/user/login" method="post">
				<input name="id" type="text" class="form-control text login" placeholder="아이디" />
				<input name="pwd" type="password" class="form-control text login"
					placeholder="패스워드" /> <input type="submit"
					class="btn btn-main full login-btn" value="로그인" />
			</form>
			<div class="separator">
				<p>OR</p>
			</div>
			<c:if test="${userId eq null}">
				<button class="btn btn-kakao full login-btn"
					onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=1dd88828b878b6857e1d2f8e5cf50324&redirect_uri=http://localhost:8080/user/loginKakao&response_type=code'">
					<i class="fa fa-kakao"></i> 카카오계정으로 시작하기
				</button>
			</c:if>
		</section>
	</div>
</body>
</html>