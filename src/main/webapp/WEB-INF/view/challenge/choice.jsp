<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- //// MATE DETAIL PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>SAVE. : 챌린지 선택</title>
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
					<div class="challenge-choice-step active">
						<p class="step_num">1</p>
						<p>챌린지 선택</p>
					</div>
					<div class="challenge-choice-step-border"></div>
					<div class="challenge-choice-step">
						<p class="step_num">2</p>
						<p>챌린지 확인</p>
					</div>
				</div>
				<div class="challenge-choice-title">
					${user eq null ? '알뜰러' : user.nickname}님,<br /> 오늘 참여한 <b>챌린지</b>를<br /> <b>선택</b>해주세요!
				</div>
				<!-- // BUTTON GROUP -->
				<div class="challenge-choice-btn-group">
					<c:forEach items="${challenge}" var="c">
						<!-- // BUTTON -->
						<button class="challenge-choice-btn-item active"
							onclick="location.href='/challenge/choice/${c.cid}'">
							<c:set var="src" value="oncoming-bus_1f68d.png"></c:set>
							<c:if test="${c.cid ne 1}">
								<c:set var="src"
									value="${c.cid eq 2 ? 'fork-and-knife_1f374.png' : 'hot-beverage_2615.png'}"></c:set>
							</c:if>
							<div class="inner-img">
								<img src="/emoji/${src }" />
							</div>
							<div class="inner-text-group">
								<p class="inner-title">${c.cname}</p>
								<p class="inner-price">
									약
									<fmt:formatNumber value="${c.camount}" pattern="#,###" />
									원 절약
								</p>
							</div>
						</button>
						<!-- BUTTON // -->
					</c:forEach>
				</div>
				<!-- BUTTON GROUP // -->
			</div>
		</section>
		<!-- content -->
		<c:if test="${user eq null }">
			<!-- // Login Modal -->
			<div class="modalBg on" id="modal_login">
				<div class="requestModal savedot-modal question">
					<div class="question-title">
						<h5>로그인 후 챌린지에 참여해보세요!</h5>
						<div class="question-btn">
							<input type="button" class="btn btn-main full font-14"
								onclick="location.href='/user/login'" value="로그인하러가기" /> <input
								type="button" class="closebtn btn btn-sub full font-14"
								onClick="history.back()" value="돌아가기" />
						</div>
					</div>
				</div>
			</div>
			<!-- Login Modal // -->
		</c:if>
	</div>
</body>
</html>