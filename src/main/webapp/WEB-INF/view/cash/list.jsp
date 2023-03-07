<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>SAVE. : 마이페이지 - 나의 수입/지출</title>
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
						<h4>${year}년${month}월</h4>
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
					<div class="col-lg viewcard mypage-card-chart">
						<div class="mypage-card-chart-top">
							<p class="mypage-card-toptxt">${month}월지출합계</p>
						</div>
						<div class="emojitxt">
							<img src="/emoji/money-with-wings_1f4b8.png" /><span id="user-price"> <fmt:formatNumber
									value="${expenseSum}" pattern="#,###">
								</fmt:formatNumber>원
							</span>
						</div>
						<p class="mypage-card-chart-bottomtxt" id="user-bottom">${startDate}
							~ ${endDate}</p>
					</div>
					<!-- /////////// -->
					<!-- /////////// -->
					<div class="col-lg viewcard mypage-card-chart">
						<div class="mypage-card-chart-top">
							<p class="mypage-card-toptxt">${month}월수입합계</p>
						</div>
						<div class="emojitxt">
							<img src="/emoji/bellhop-bell_1f6ce-fe0f.png"/><span id="user-price"> <fmt:formatNumber
									value="${incomeSum}" pattern="#,###">
								</fmt:formatNumber>원
							</span>
						</div>
						<p class="mypage-card-chart-bottomtxt" id="user-bottom">${startDate}
							~ ${endDate}</p>
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
									<ul class="cash-item ver${cashList.category}"
										id="${cashList.cid }">
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
											<li cash-img receipt-src="">영수증보기</li>
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
	<div class="modalBg receipt">
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
	<!-- // MODAL : 영수증 이미지 -->

	<!-- // MODAL : 수정 페이지 -->
	<div class="modalBg edit">
		<div class="requestModal savedot-modal cashEdit">
			<div class="close-button gap-1 pb-3">
				<input type="button" id="editbtn_edit" class="btn btn-sub mdi"
					value="수정" /> <input type="button" id="submitbtn_edit"
					class="btn btn-main mdi hide" value="등록" /> <input type="button"
					id="deletebtn_edit" class="btn btn-sub mdi" value="삭제" /> <input
					type="button" id="closebtn_edit" class="btn btn-main mdi"
					value="닫기" />
			</div>
			<div class="cash-edit-item">
				<form class="inputbox" id="cash-edit-form" method="post"
					action="/cash/edit">
					<input type="hidden" name="cid" id="edit-cid" /> <select
						name="category" id="e-category"
						class="form-select text requiredModal" disabled>
						<option value="0" id="e-category-ver0">지출</option>
						<option value="1" id="e-category-ver1">수입</option>
					</select> <input class="requiredModal form-control text input-text"
						type="date" name="regDate" id="date" disabled /> <input
						class="form-control text input-text requiredModal" type="text"
						name="amount" id="amount" placeholder="금액*" disabled price /> <input
						class="form-control text input-text requiredModal" type="text"
						name="content" id="shopName" placeholder="내역*" disabled />
					<textarea id="memo" class="form-control" name="memo" cols="30"
						rows="10" placeholder="메모" disabled></textarea>
				</form>
			</div>
		</div>
	</div>
	<!-- // MODAL : 수정 페이지 -->
	<script src="/js/mypage_cash.js"></script>
	<script src="/js/mypage_nav.js"></script>
	<script src="/js/required.js"></script>
	<script src="/js/saveDot_numberOnly.js"></script>
</body>
</html>
