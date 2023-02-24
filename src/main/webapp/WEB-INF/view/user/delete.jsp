<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- //// MYPAGE PAGE - MAIN //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->

<title>Save. - Mypage</title>
</head>
<body>
	<!-- //TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP// -->
	<!-- MYPAGE - TOP -->
	<%@ include file="../common/mypage_top.jsp"%>
	<!-- MYPAGE - TOP -->

	<div class="container-lg">
		<div class="row">
			<!-- // ASIDE -->
			<%@ include file="../common/aside.jsp"%>
			<!-- ASIDE// -->

			<!-- content -->
			<section class="col">
				<h3>
					<strong>회원 탈퇴</strong>
				</h3>
				<hr>
				<div class="row">
					<div class="col-3"></div>
					<div class="col-6">
						<div class="card mt-3">
							<div class="card-body">
								<strong class="card-title">${id}님, 정말 탈퇴하시겠습니까?</strong>
								<p>Save dot과의 추억을 간직해주세요&#128546;</p>
								<p class="card-text text-center">
									<br>
									<button class="btn btn-secondary"
										onclick="location.href='/user/deleteConfirm/${id}'">그래도 탈퇴하기</button>
									<button class="btn btn-primary"
										onclick="location.href='/mypage/main'">안할래요!</button>
								</p>
							</div>
						</div>
					</div>
					<div class="col-3"></div>
				</div>
			</section>
		</div>
		<!-- =================== main =================== -->

	</div>
</body>
</html>