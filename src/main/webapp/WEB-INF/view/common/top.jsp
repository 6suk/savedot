<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- //// COMMON - TOP //// -->

<nav class="navbar navbar-expand navbar-light fixed-top" id="main-nav">
	<div class="container-lg justify-content-lg-between">
		<ul class="navbar-nav">
			<a class="navbar-brand" href="/home"> <!-- <svg class="logo"
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
					<path
						d="M146 0c17.7 0 32 14.3 32 32V67.7c1.6 .2 3.1 .4 4.7 .7c.4 .1 .7 .1 1.1 .2l48 8.8c17.4 3.2 28.9 19.9 25.7 37.2s-19.9 28.9-37.2 25.7l-47.5-8.7c-31.3-4.6-58.9-1.5-78.3 6.2s-27.2 18.3-29 28.1c-2 10.7-.5 16.7 1.2 20.4c1.8 3.9 5.5 8.3 12.8 13.2c16.3 10.7 41.3 17.7 73.7 26.3l2.9 .8c28.6 7.6 63.6 16.8 89.6 33.8c14.2 9.3 27.6 21.9 35.9 39.5c8.5 17.9 10.3 37.9 6.4 59.2c-6.9 38-33.1 63.4-65.6 76.7c-13.7 5.6-28.6 9.2-44.4 11V480c0 17.7-14.3 32-32 32s-32-14.3-32-32V445.1c-.4-.1-.9-.1-1.3-.2l-.2 0 0 0c-24.4-3.8-64.5-14.3-91.5-26.3C4.9 411.4-2.4 392.5 4.8 376.3s26.1-23.4 42.2-16.2c20.9 9.3 55.3 18.5 75.2 21.6c31.9 4.7 58.2 2 76-5.3c16.9-6.9 24.6-16.9 26.8-28.9c1.9-10.6 .4-16.7-1.3-20.4c-1.9-4-5.6-8.4-13-13.3c-16.4-10.7-41.5-17.7-74-26.3l-2.8-.7 0 0C105.4 279.3 70.4 270 44.4 253c-14.2-9.3-27.5-22-35.8-39.6C.3 195.4-1.4 175.4 2.5 154.1C9.7 116 38.3 91.2 70.8 78.3c13.3-5.3 27.9-8.9 43.2-11V32c0-17.7 14.3-32 32-32z"></path></svg> -->
			<svg version="1.1" class="logo"
				xmlns="http://www.w3.org/2000/svg"
				xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
				viewBox="0 0 437.9 604.6"
				xml:space="preserve">
			<g>
				<path d="M170.2,2.6c20.7,0,37.5,16.7,37.5,37.5v41.8c1.9,0.2,3.6,0.5,5.5,0.8c0.5,0.1,0.8,0.1,1.3,0.2l56.2,10.3
					c20.4,3.7,33.8,23.3,30.1,43.5s-23.3,33.8-43.5,30.1l-55.6-10.2c-36.6-5.4-68.9-1.8-91.7,7.3s-31.8,21.4-33.9,32.9
					c-2.3,12.5-0.6,19.5,1.4,23.9c2.1,4.6,6.4,9.7,15,15.5c19.1,12.5,48.3,20.7,86.3,30.8l3.4,0.9c33.5,8.9,74.4,19.7,104.9,39.6
					c16.6,10.9,32.3,25.6,42,46.2c9.9,21,12.1,44.4,7.5,69.3c-8.1,44.5-38.7,74.2-76.8,89.8c-16,6.6-33.5,10.8-52,12.9v38.9
					c0,20.7-16.7,37.5-37.5,37.5c-20.7,0-37.5-16.7-37.5-37.5v-40.9c-0.5-0.1-1.1-0.1-1.5-0.2h-0.2l0,0C102.5,519,55.6,506.7,24,492.6
					C5.2,484.2-3.4,462,5,443.1c8.4-19,30.6-27.4,49.4-19c24.5,10.9,64.7,21.7,88,25.3c37.3,5.5,68.1,2.3,89-6.2
					c19.8-8.1,28.8-19.8,31.4-33.8c2.2-12.4,0.5-19.5-1.5-23.9c-2.2-4.7-6.6-9.8-15.2-15.6c-19.2-12.5-48.6-20.7-86.6-30.8l-3.3-0.8
					l0,0c-33.4-8.8-74.3-19.7-104.8-39.6C34.8,287.9,19.2,273,9.5,252.4c-9.7-21.1-11.7-44.5-7.1-69.4c8.4-44.6,41.9-73.6,80-88.7
					C98,88.1,115.1,83.9,133,81.4V40.1C132.7,19.4,149.4,2.6,170.2,2.6z"></path>
				<circle cx="389" cy="553" r="48.9"></circle>
			</g>
			</svg>
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
