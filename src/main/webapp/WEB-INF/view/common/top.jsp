<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- //// COMMON - TOP //// -->

<nav class="navbar navbar-expand navbar-light fixed-top" id="main-nav">
	<div class="container-lg justify-content-lg-between">
		<ul class="navbar-nav">
			<a class="navbar-brand" href="/home"> <img src="/img/logo2.png"
				class="logo" />
			</a>
			<li class="nav-item"><a class="nav-link main-nav" href="/home">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link main-nav"
				href="/mate/list">조각메이트</a></li>
			<li class="nav-item"><a class="nav-link main-nav"
				href="/challenge/choice">챌린지</a></li>
			<li class="nav-item"><a class="nav-link main-nav"
				href="/info/news">알뜰정보</a></li>
			<li class="nav-item"><a class="nav-link main-nav" href="/pay">얼마벌었지</a>
			</li>
		</ul>
		<c:set var="login" value=""></c:set>
		<c:set var="logout" value="d-none"></c:set>
		<!-- 로그인 유저가 있을 때  -->
		<c:if test="${not empty user and user ne null }">
			<c:set var="login" value="d-none"></c:set>
			<c:set var="logout" value=""></c:set>
		</c:if>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link main-nav ${login }"
				href="/user/login">로그인</a></li>
			<li class="nav-item"><a class="nav-link main-nav ${login }"
				href="/user/join">회원가입</a></li>
			<li class="nav-item"><a class="nav-link main-nav ${logout }"
				href="/cash/write">수입 / 지출 등록</a></li>
			<li class="nav-item"><a class="nav-link main-nav ${logout }"
				href="/mypage/main">마이페이지</a></li>
			<li class="nav-item"><a class="nav-link main-nav ${logout }"
				href="/mypage/alarm" style="display: flex; align-items: center;">알림<c:if
						test="${alarmCnt > 0}">
						<p class="alarmCnt">${alarmCnt }</p>
					</c:if></a></li>
			<li class="nav-item"><a class="nav-link main-nav ${logout }"
				href="/user/logout">로그아웃</a></li>
		</ul>
	</div>
</nav>
