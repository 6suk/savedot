<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- //// MATE DETAIL PAGE //// -->

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

	<div style="position: relative">
		<div class="container-nonaside-sm" id="container">
			<!-- content -->
			<section id="section-content">
				<!-- << 슬라이드 시작 -->
				<c:if test="${fn:length(mate.imgInfo) ne 0 }">
					<%@ include file="./slide.jsp"%>
				</c:if>
				<!-- 슬라이드 끝 >> -->

				<div class="mate-detail-all">
					<div class="mate-detail-top">
						<!-- Top 01 -->
						<div class="mate-detail-sub-info">
							<div class="dot d-flex">
								<p>${mate.categoryName }</p>
								<p>매칭 전</p>
							</div>
							<div class="line d-flex">
								<p>
									<span>조회</span><span>33</span>
								</p>
								<p>
									<span><i class="fa-regular fa-heart"></i></span><span>13</span>
								</p>
							</div>
						</div>
						<!-- Top 02 -->
						<h4>${mate.title }</h4>
						<!-- Top 03 -->
						<div class="mate-detail-price">
							<h5>
								<fmt:formatNumber value="${mate.price2}" pattern="#,###" />
							</h5>
							<div class="mate-detail-price-tag-box">
								<input type="hidden" name="tradeType" value="${mate.tradeType}"
									id="trade_type" />
								<p class="mate-detail-price-tag hide" id="trade_type_1">직접거래</p>
								<p class="mate-detail-price-tag hide" id="trade_type_2">택배거래</p>
							</div>
						</div>
					</div>

					<div class="mate-detail-middle">
						<div class="d-flex flex-column gap-2">
							<p class="font-15 font-600">${mate.user.nickname }</p>
							<div class="font-14 d-flex detail line">
								<p>
									<span>모집인원</span><span class="mate-url-tag">${mate.positionNum }명</span>
								</p>
								<p>
									<input type="hidden" name="telType" value="${mate.telType }"
										id="tel_type" /> <span>연락방식</span><a
										class="mate-url-tag hover hide"
										href="https://open.kakao.com/o/saWWrd4e" target="_blank"
										id="tel_type_1">오픈채팅 <i
										class="fa-solid fa-arrow-up-right-from-square"></i></a><span
										class="mate-url-tag hide" id="tel_type_2">댓글</span>
								</p>
							</div>
						</div>
						<button class="btn btn-main sm font-14" id="applybtn">
							메이트 신청 <i class="fa-solid fa-plus" id="applybtn-icon"></i>
						</button>
					</div>
					<div class="mate-detail-content">${mate.content }</div>
					<c:if test="${not empty mate.placeCoords }">
						<div class="mate-detail-map">
							<div>
								<input type="hidden" id="coords" name="placeCoords"
									value="${mate.placeCoords}" />
								<div id="map" class="map"></div>
							</div>
							<div class="mate-detail-map-info">
								<p class="font-14">
									직접거래 시 <u>만남 장소</u>를 미리 확인하세요!
								</p>
								<p class="mate-url-tag">
									<i class="fa-solid fa-location-arrow graytxt"></i>
									${mate.placeAddr}
									<c:if test="${mate.placeAddr ne mate.placeName}"> ${mate.placeName}</c:if>
								</p>
							</div>
						</div>
					</c:if>
				</div>
			</section>
		</div>
	</div>

	<!-- // Apply Form -->
	<div class="modalBg">
		<div class="requestModal savedot-modal">
			<div class="content-title">
				<div>
					<h5>메이트 신청</h5>
					<p class="font-14 l-graytxt pt-1">최종 매칭은 글 작성자의 승낙 시 진행됩니다!</p>
				</div>
				<div>
					<input type="button" id="sendbtn" class="btn btn-main mdi"
						value="메이트 신청" /> <input type="button" id="closebtn"
						class="btn btn-sub mdi" value="닫기" />
				</div>
			</div>
			<form id="applyform" class="inputbox" method="post"
				action="/mate/apply/${mate.id}">
				<input type="hidden" name="userid" value="${user.id}" />
				<input type="hidden" value="${mate.placeAddr}" id="apply_place_addr" />
				<input type="hidden" value="${mate.parcelPrice}"
					id="apply_parcel_price" /> <input type="hidden"
					value="${mate.parcelType}" id="apply_parcel_type" />
				<div class="d-flex gap-2">
					<input class="form-control text required" type="text"
						name="nickname" value="닉네임" readonly />
					<div style="position: relative; width: 100%">
						<input type="hidden" name="id" /> <input
							class="form-control text required" type="text" name="tel"
							value="010-0000-0000" readonly /> <i
							class="fa-solid fa-circle-exclamation inner-btn font-14"
							style="color: var(- -lgray-color); opacity: 0.5"
							data-bs-toggle="tooltip" data-bs-html="true"
							data-bs-placement="top"
							title="연락처는 매칭 성공 시 작성자에게만 공개됩니다.<br>
                연락처 수정은 마이페이지에서 가능합니다."></i>
					</div>
				</div>
				<textarea class="form-control" name="content" cols="30" rows="10"
					placeholder="남기고 싶은 말을 적어주세요 :)"></textarea>
				<div class="" id="trade_show">
					<div class="input-btn">
						<select name="applyTradelType" id="apply_trade_type"
							class="form-select input-btn-ele-reverse" style="flex: 0.2 0 0">
							<option selected disabled>거래방법</option>
							<option value="1" id="apply_trade_type_1">직접거래</option>
							<option value="2" id="apply_trade_type_2">택배거래</option>
						</select> <input type="text" class="form-control text"
							id="apply_parcel_notice" style="flex: 1 0 0" value="" readonly
							placeholder="" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- // Apply Form -->
</body>
<script type="text/javascript"
	src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849"></script>
<script type="text/javascript"
	src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849&libraries=clusterer"></script>

<script src="/js/map.js"></script>
<script src="/js/apply.js"></script>
<script src="/js/mate_detail.js"></script>
<script src="/js/required.js"></script>
<script>
	var tooltipTriggerList = [].slice.call(document
			.querySelectorAll('[data-bs-toggle="tooltip"]'));
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});
</script>
</html>

