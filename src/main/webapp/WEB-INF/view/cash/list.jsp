<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>FinalProject</title>
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->
	<!-- MYPAGE - TOP -->
	<%@ include file="../common/mypage_top.jsp"%>
	<!-- MYPAGE - TOP -->

	<div class="container-lg">
		<div class="row">
			<!-- // ASIDE -->
			<%@ include file="../common/aside.jsp"%>
			<!-- ASIDE// -->

			<!-- content -->
			<section class="col-md-8 mypage-cash">
				<!-- // 1. TOP : 0월 출력 및 검색 -->
				<div class="mypage-cash-top mobile">
					<div class="mypage-cash-top-start">
						<button class="btn fa-solid fa-chevron-left"
							onclick="location.href='/mypage/cash/list/left'"></button>
						<h4>${year}년 ${month}월</h4>
						<button class="btn fa-solid fa-chevron-right"
							onclick="location.href='/mypage/cash/list/right'"></button>
					</div>
					<form class="mypage-cash-top-end" action="/mypage/cash/list"
						method="get">
						<input required class="form-control text input-text required"
							type="date" name="startDate" id="startDate" value="${startDate}"
							data-placeholder="시작일" /> <input required
							class="form-control text input-text required" type="date"
							name="endDate" id="endDate" value="${endDate}"
							data-placeholder="종료일" />
						<!-- submit -->
						<button class="date-search" id="date-search">
							<i class="fa-solid fa-magnifying-glass"></i>
						</button>
					</form>
				</div>
				<!-- 1. TOP : 0월 출력 및 검색 끝 // -->

				<!-- // 2. CARD : 월 총 수입·지출 + 검색 총 수입·지출 출력 -->
				<div class="row viewcard-group">
					<!-- /////////// -->
					<div class="col-lg viewcard mypage-card cash" id="card1">
						<div class="mypage-card-left">
							<p class="mypage-card-toptxt" id="card1-top">${month}월 지출 합계</p>
							<div class="emojitxt">
								<img src="/emoji/money-with-wings_1f4b8.png" id="card1-emoji" />
								<span id="card1-emoji-txt"><h4>
										<fmt:formatNumber value="${expenseSum}" pattern="#,###">
										</fmt:formatNumber>원</h4>
								</span>
							</div>
							<p class="mypage-card-bottomtxt" id="card1-bottom">${startDate}
								~ ${endDate}</p>
						</div>
						<div class="mypage-card-right" id="card1-right">오늘 지출</div>
					</div>
					<!-- /////////// -->
					<div class="col-lg viewcard mypage-card cash" id="card2">
						<div class="mypage-card-left">
							<p class="mypage-card-toptxt" id="card2-top">${month}월 수입 합계</p>
							<div class="emojitxt">
								<img src="/emoji/bellhop-bell_1f6ce-fe0f.png" id="card2-emoji" />
								<span id="card2-emoji-txt"><h4>
										<fmt:formatNumber value="${incomeSum}" pattern="#,###"></fmt:formatNumber>
										원</h4>
								</span>
							</div>
							<p class="mypage-card-bottomtxt" id="card2-bottom">${startDate}
								~ ${endDate}</p>
						</div>
						<div class="mypage-card-right" id="card2-right">오늘 수입</div>
					</div>
					<!-- /////////// -->
				</div>
				<!-- 2. CARD : 월 총 수입·지출 + 검색 총 수입·지출 출력 끝 // -->

				<!-- // 3. CASH LIST : 수입·지출 내역 모두 출력 -->
				<div class="mypage-cash-item-list">
					<!-- // 3-A. CASH ITEM : 수입·지출 일별 출력 -->
					<div class="mypage-cash-item">
						<!-- A-1. data-bs-target 해당 날짜로 변경 -->
						<c:forEach var="entry" items="${map}">
							<button class="mypage-cash-item-top" data-bs-toggle="collapse"
								data-bs-target="#date-${entry.key}" cash-item>
								<div class="mypage-cash-item-button-group">
									<div class="mypage-cash-item-date">
										<span class="fa-solid fa-sort-down" down></span> <span
											class="fa-solid fa-sort-up hide" up></span>${entry.key}
									</div>
								</div>
								<p class="mypage-cash-item-count">${entry.value.size()}</p>
							</button>
							<!-- A-2. ID 해당 날짜로 변경 -->
							<div id="date-${entry.key}" class="collapse show">
								<!-- A-3. 일자별 리스트 출력 -->
								<!-- // A-4. ver0 : 지출 · ver1 : 수입 -->
								<c:forEach var="cashList" items="${entry.value}">
									<ul class="cash-item ver${cashList.category}">
										<li category></li>
										<li cash-name>${cashList.content}</li>
										<li cash-amount><fmt:formatNumber
												value="${cashList.amount}" pattern="#,###"></fmt:formatNumber></li>
										<li cash-memo>${cashList.memo}</li>
										<c:if test="${not empty cashList.fileName}">
											<li cash-img
												receipt-src="/savedot/upload/${cashList.saveDate}/${cashList.fileName}${cashList.ext}">
												영수증보기</li>
										</c:if>
										<c:if test="${empty cashList.fileName}">
											<li cash-img
												receipt-src="">
												영수증보기</li>
									</c:if>
									</ul>
							</c:forEach>
				
						<!-- A-4. ver0 : 지출 · ver1 : 수입 끝 // -->
					</div>
					</c:forEach>
				</div>
				<!-- 3-A. CASH ITEM : 수입·지출 일별 출력 끝// -->
				<!-- 3. CASH LIST : 수입·지출 내역 모두 출력 끝 // -->
			</section>
			<!-- content -->
		</div>
	</div>

	<!-- // MODAL : 영수증 이미지 -->
	<div class="modalBg">
		<div class="requestModal savedot-modal cash" style="opacity: 1">
			<div class="close-button">
				<input type="button" id="closebtn" class="btn btn-sub mdi"
					value="닫기" />
			</div>
			<div class="receipt-img">
				<img src="" id="receipt-img" />
			</div>
		</div>
	</div>
	<!-- // Apply Form -->
	<script src="/js/mypage_cash.js"></script>
	<script src="/js/mypage_nav.js"></script>
	<script src="/js/required.js"></script>
</body>
</html>
