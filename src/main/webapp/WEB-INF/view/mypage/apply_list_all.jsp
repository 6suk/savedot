<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- //// MYPAGE PAGE - MAIN //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>FinalProject</title>
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
			<section class="col-md-8">
				<!-- << 카드 구좌 1 -->
				<div id="mate-card-1" class="mate_card">
					<div class="mate-card-top">
						<div class="d-flex align-items-center gap-2">
							<h4>조각 메이트 신청</h4>
							<p class="mate-card-top-count">4</p>
						</div>

						<div class="mate-card-arrow">
							<button class="btn fa-solid fa-chevron-left"></button>
							<button class="btn fa-solid fa-chevron-right"></button>
						</div>
					</div>
					<div style="position: relative">
						<div class="mypage-mate-card-box">
							<c:forEach var="apply" items="${apply}">
								<c:if test="${apply.isApply eq '0' }">
									<!-- 카드 시작 -->
									<div class="mate-card-list">
										<div class="mate-card-item">
											<div class="mate-card-top">
												<p class="mate-card-tag">신청완료</p>
												<u><a href="/mate/apply/cancel/${apply.aid}">신청취소</a></u>
											</div>
											<div class="mate-card-middle">
												<h5>${apply.mate.title }</h5>
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>연락방법</li>
														<li>신청일</li>
													</ul>
													<ul>
														<c:set var="tel_url"
															value="/mate/detail/${apply.mate.id }" />
														<c:if test="${apply.mate.telType eq '1' }">
															<c:set var="tel_url"
																value="${apply.mate.telUrl }" />
														</c:if>
														<li><u><a href="${tel_url }" target="_blank">${apply.mate.telName }</a></u></li>
														<li>${fn:replace(apply.modDate,'T',' ')}</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 카드 끝 -->
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 카드 구좌 1 >> -->

				<!-- << 카드 구좌 2 -->
				<div id="mate-card-2" class="mate_card">
					<div class="mate-card-top">
						<div class="d-flex align-items-center gap-2">
							<h4>조각 메이트 거래 중</h4>
							<p class="mate-card-top-count">2</p>
						</div>
						<div class="mate-card-arrow">
							<button class="btn fa-solid fa-chevron-left"></button>
							<button class="btn fa-solid fa-chevron-right"></button>
						</div>
					</div>
					<div style="position: relative">
						<div class="mypage-mate-card-box">
							<c:forEach var="apply" items="${apply}">
								<c:if test="${apply.isApply eq '1' }">
									<!-- 카드 시작 -->
									<div class="mate-card-list">
										<div class="mate-card-item">
											<div class="mate-card-top">
												<p class="mate-card-tag">거래중</p>
												<u><a href="/mate/apply/cancel/${apply.aid}">신청취소</a></u>
											</div>
											<div class="mate-card-middle">
												<h5>${apply.mate.title }</h5>
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>연락방법</li>
														<li>신청일</li>
														<li>연락처</li>
														<li style="position: relative">입금 계좌</li>
													</ul>
													<ul>
														<c:set var="tel_url"
															value="/mate/detail/${apply.mate.id }" />
														<c:if test="${apply.mate.telType eq '1' }">
															<c:set var="tel_url"
																value="${apply.mate.telUrl }" />
														</c:if>
														<li><u><a href="${tel_url }" target="_blank">${apply.mate.telName }</a></u></li>
														<li>${fn:replace(apply.modDate,'T',' ')}</li>
														<li>${apply.mate.user.tel }</li>
														<li>${apply.mate.bank }<br />${apply.mate.accountNumber }
															<i class="fa-solid fa-circle-exclamation"
															style="color: var(- -lgray-color); opacity: 0.5"
															data-bs-toggle="tooltip" data-bs-html="true"
															data-bs-placement="top"
															title="입금 전 더치트에 검색해 본다면<br>더욱 안전한 거래를 할 수 있어요!"></i>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 카드 끝 -->
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 카드 구좌 2 >> -->

				<!-- << 카드 구좌 3 -->
				<div id="mate-card-3" class="mate_card">
					<div class="mate-card-top">
						<div class="d-flex align-items-center gap-2">
							<h4>조각 메이트 완료</h4>
							<p class="mate-card-top-count">4</p>
						</div>
						<div class="mate-card-arrow">
							<button class="btn fa-solid fa-chevron-left"></button>
							<button class="btn fa-solid fa-chevron-right"></button>
						</div>
					</div>
					<div style="position: relative">
						<div class="mypage-mate-card-box">
							<c:forEach var="apply" items="${apply}">
								<c:if test="${apply.isApply eq '2' }">
									<!-- 카드 시작 -->
									<div class="mate-card-list">
										<div class="mate-card-item">
											<div class="mate-card-top">
												<p class="mate-card-tag">신청완료</p>
												<u><a href="/mate/apply/cancel/${apply.aid}">신청취소</a></u>
											</div>
											<div class="mate-card-middle">
												<h5>${apply.mate.title }</h5>
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>연락방법</li>
														<li>신청일</li>
													</ul>
													<ul>
														<c:set var="tel_url"
															value="/mate/detail/${apply.mate.id }" />
														<c:if test="${apply.mate.telType eq '1' }">
															<c:set var="tel_url"
																value="${apply.mate.telUrl }" />
														</c:if>
														<li><u><a href="${tel_url }" target="_blank">${apply.mate.telName }</a></u></li>
														<li>${fn:replace(apply.modDate,'T',' ')}</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<!-- 카드 끝 -->
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 카드 구좌 3 >> -->
			</section>
		</div>
	</div>
	<script src="/js/mypage_mate.js"></script>
	<script src="/js/mypage_nav.js"></script>
	<script>
		var tooltipTriggerList = [].slice.call(document
				.querySelectorAll('[data-bs-toggle="tooltip"]'));
		var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
			return new bootstrap.Tooltip(tooltipTriggerEl);
		});
	</script>
</body>
</html>
