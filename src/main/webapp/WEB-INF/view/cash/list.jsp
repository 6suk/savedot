<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							onclick="location.href='#'"></button>
						<h4>${month}월</h4>
						<button class="btn fa-solid fa-chevron-right"
							onclick="location.href='#'"></button>
					</div>
					<form class="mypage-cash-top-end" action="/mypage/cash/list"
						method="post">
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
							<p class="mypage-card-toptxt" id="card1-top">2월 지출 합계</p>
							<div class="emojitxt">
								<img src="/static/emoji/money-with-wings_1f4b8.png"
									id="card1-emoji" /> <span id="card1-emoji-txt"><h4>${expenseSum}원</h4></span>
							</div>
							<p class="mypage-card-bottomtxt" id="card1-bottom">${startDate}
								~ ${endDate}</p>
						</div>
						<div class="mypage-card-right" id="card1-right">오늘 지출</div>
					</div>
					<!-- /////////// -->
					<div class="col-lg viewcard mypage-card cash" id="card2">
						<div class="mypage-card-left">
							<p class="mypage-card-toptxt" id="card2-top">2월 수입 합계</p>
							<div class="emojitxt">
								<img src="/static/emoji/bellhop-bell_1f6ce-fe0f.png"
									id="card2-emoji" /> <span id="card2-emoji-txt"><h4>${incomeSum}원</h4></span>
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
							<c:forEach var="cash" items="${allCashList}">
							<button class="mypage-cash-item-top" data-bs-toggle="collapse"
								data-bs-target="#date-${cash.regDate}" cash-item>
								<div class="mypage-cash-item-button-group">
									<div class="mypage-cash-item-date">
										<span class="fa-solid fa-sort-down" down></span> <span
											class="fa-solid fa-sort-up hide" up></span> ${cash.regDate}
									</div>
								</div>
								<p class="mypage-cash-item-count">8</p>
							</button>
							<!-- A-2. ID 해당 날짜로 변경 -->
							<div id="date-${cash.regDate}" class="collapse show">
								<!-- A-3. 일자별 리스트 출력 -->
								<!-- // A-4. ver0 : 지출 · ver1 : 수입 -->

								<ul class="cash-item ver${cash.category}">
									<li category></li>
									<li cash-name>${cash.content}</li>
									<li cash-amount>${cash.amount}</li>
									<li cash-memo>${cash.memo}</li>
									<li cash-img
										receipt-src="${cash.filePath }/${cash.fileName}${cash.ext}">
										영수증보기</li>
								</ul>
				</c:forEach>
				<!-- A-4. ver0 : 지출 · ver1 : 수입 끝 // -->

				<!-- // A-5. A-4와 동일(프론트 테스트용) -->
				<ul class="cash-item ver1">
					<li category></li>
					<li cash-name>(주)이마트</li>
					<li cash-amount>5,114,640</li>
					<li cash-memo>메모메모메모메모메모메모메모메모메모메모메모메모메모메모</li>
					<li cash-img receipt-src>영수증보기</li>
				</ul>
				<!-- A-5. A-4와 동일(프론트 테스트용) 끝 // -->
		</div>
	</div>
	<!-- 3-A. CASH ITEM : 수입·지출 일별 출력 끝// -->

	<!-- // 3-B. CASH ITEM : 수입·지출 일별 출력 (프론트 테스트용) -->
	<div class="mypage-cash-item">
		<button class="mypage-cash-item-top" data-bs-toggle="collapse"
			data-bs-target="#date-2023-02-15" cash-item>
			<div class="mypage-cash-item-button-group">
				<div class="mypage-cash-item-date">
					<span class="fa-solid fa-sort-down" down></span> <span
						class="fa-solid fa-sort-up hide" up></span> 2023-02-15 (수)
				</div>
			</div>
			<p class="mypage-cash-item-count">8</p>
		</button>
		<!-- // cash list - content -->
		<div id="date-2023-02-15" class="collapse show">
			<ul class="cash-item ver0">
				<li category></li>
				<li cash-name>(주)이마트</li>
				<li cash-amount>4,640</li>
				<li cash-memo>지출 테스트1</li>
				<li cash-img receipt-src>영수증보기</li>
			</ul>
			<ul class="cash-item ver0">
				<li category></li>
				<li cash-name>(주)이마트</li>
				<li cash-amount>82,000</li>
				<li cash-memo>지출 테스트2</li>
				<li cash-img
					receipt-src="c:/savedot/img/2023-02-10/7f0ec92c-a926-11ed-a94e-376e4f7bc4fa.jpg">
					영수증보기</li>
			</ul>
			<ul class="cash-item ver0">
				<li category></li>
				<li cash-name>(주)이마트</li>
				<li cash-amount>114,640</li>
				<li cash-memo>지출 테스트3 메모메모메모메모메모메모메모메모메모메모메모메모메모메모</li>
				<li cash-img receipt-src>영수증보기</li>
			</ul>
			<ul class="cash-item ver1">
				<li category></li>
				<li cash-name>(주)이마트</li>
				<li cash-amount>114,640</li>
				<li cash-memo>수입 테스트</li>
				<li cash-img receipt-src>영수증보기</li>
			</ul>
		</div>
	</div>
	<!-- 3-B. CASH ITEM : 수입·지출 일별 출력 (프론트 테스트용) 끝 // -->
	</div>
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
