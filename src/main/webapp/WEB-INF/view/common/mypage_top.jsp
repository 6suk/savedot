<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav id="mypage_category">
	<ul class="nav justify-content-center mypage-navbar">
		<li class="nav-item"><a class="nav-link mypage" id="nav_main"
			href="/mypage/main">나의 절약캘린더</a></li>
		<li class="nav-item"><a class="nav-link mypage" id="nav_mate"
			href="/mypage/mate/write/list">나의 조각메이트</a></li>
		<li class="nav-item"><a class="nav-link mypage" id="nav_cash"
			href="/mypage/cash/list">나의 수입/지출</a></li>
		<li class="nav-item"><a class="nav-link mypage"
			href="/mypage/chart">통계</a></li>
		<li class="nav-item"><a class="nav-link mypage" href="/mypage/alarm">알림</a></li>
	</ul>
</nav>
<nav id="mate_sub_category" class="hide">
	<ul class="nav justify-content-center mypage-navbar sub">
		<li class="nav-item"><a class="nav-link mypage-sub" href="/mypage/mate/write/list">내가
				작성한 게시물</a></li>
		<li class="nav-item"><a class="nav-link mypage-sub" href="/mypage/mate/like/list">내가
				좋아요한 게시물</a></li>
		<li class="nav-item"><a class="nav-link mypage-sub"
			href="/mypage/mate/apply/all">매칭 신청 모아보기</a></li>
		<li class="nav-item"><a class="nav-link mypage-sub" href="#">매칭 신청 전체보기</a></li>
	</ul>
</nav>

<nav id="chart_sub_category" class="hide">
	<ul class="nav justify-content-center mypage-navbar sub">
		<li class="nav-item"><a class="nav-link mypage-sub"
			href="/mypage/chart/cash">수입/지출 통계</a></li>
		<li class="nav-item"><a class="nav-link mypage-sub" href="/mypage/chart/challenge">챌린지
				통계</a></li>
	</ul>
</nav>