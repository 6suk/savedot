<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- //// MATE DETAIL PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>SAVE. : 챌린지 확인</title>
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->

	<div class="container-nonaside-sm">
		<!-- content -->
		<section class="pt-md-4">
			<div class="challenge-choice">
				<div class="challenge-choice-step-group">
					<div class="challenge-choice-step">
						<p class="step_num">1</p>
						<p>챌린지 선택</p>
					</div>
					<div class="challenge-choice-step-border"></div>
					<div class="challenge-choice-step active">
						<p class="step_num">2</p>
						<p>챌린지 확인</p>
					</div>
				</div>
				<c:set var="src" value="oncoming-bus_1f68d.png"></c:set>
				<c:if test="${c.cid ne 1}">
					<c:set var="src"
						value="${c.cid eq 2 ? 'fork-and-knife_1f374.png' : 'hot-beverage_2615.png'}"></c:set>
				</c:if>
				<div class="challenge-choice-title">
					${user.nickname}님,<br /> <img
						src="/emoji/${src }" /><b>${c.cname}</b>를<br><b>성공</b>하셨나요?
					
				</div>
				<div class="challenge-choice-btn-group">
					<button class="challenge-choice-btn-item active"
						onclick="location.href='/challenge/save/${c.cid}'">
						<div class="inner-img">
							<img src="/emoji/ok-hand_light-skin-tone_1f44c-1f3fb_1f3fb.png" />
						</div>
						<div class="inner-text-group">
							<p class="inner-title">그럼요, 성공했어요!</p>
							<p class="inner-price">정말 대단해요! 기록해드릴게요!</p>
						</div>
					</button>
					<button class="challenge-choice-btn-item active"
						onclick="location.href='/challenge/choice'">
						<div class="inner-img">
							<img
								src="/emoji/backhand-index-pointing-left_light-skin-tone_1f448-1f3fb_1f3fb.png" />
						</div>
						<div class="inner-text-group">
							<p class="inner-title">다시 선택할게요!</p>
							<p class="inner-price">선택 페이지로 다시 돌아갈게요!</p>
						</div>
					</button>
				</div>
			</div>
		</section>
		<!-- content -->
	</div>
</body>
</html>
