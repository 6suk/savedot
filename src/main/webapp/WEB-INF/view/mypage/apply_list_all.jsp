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
<title>SAVE. : 마이페이지 - 조각메이트</title>
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
				<div id="mate-card-1" class="mate_card">
					<!-- 검색창 -->
					<div class="mate-top-title">
						<div class="form-tag" id="matecategory">
							<input type="radio" name="sendBy" id="sendAll" value="A" checked />
							<label for="sendAll">전체보기</label>
							<input type="radio" name="sendBy" id="sendMe" value="0" />
							<label for="sendMe">보낸 조각메이트</label>
							<input type="radio" name="sendBy" id="sendYou" value="1" />
							<label for="sendYou">받은 조각메이트</label>
						</div>
						<div class="">
							<select name="state" id="" class="form-select text mypage">
								<option value="A" id="all">전체보기</option>
								<option value="0" id="standby">대기중</option>
								<option value="1" id="ing">거래중</option>
								<option value="2" id="end">거래완료</option>
							</select>
						</div>
					</div>
					<!-- 검색창 -->
					<div class="mypage-mate-card-box all">
						<c:forEach var="apply" items="${applyList }">
							<div class="mate-card-list all"
								onclick="/mate/detail/${apply.mate.mid}'">
								<div class="mate-card-item all">
									<!-- ////////// 보낸 조각 메이트 시작 ////////// -->
									<c:if test="${apply.getUid ne user.uid}">
										<div class="mate-card-top">
											<p class="mate-card-tag ver${apply.isApply }"></p>
											<c:if test="${apply.isApply ne '2'}">
												<u><a
													href="/mate/apply/cancel/${user.uid }/${apply.aid}">신청취소</a></u>
											</c:if>

										</div>
										<div class="mate-card-middle">
											<h5>${apply.mate.title }</h5>
											<c:if test="${apply.isApply eq '0'}">
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>판매자</li>
														<li>연락수단</li>
														<li>거래방법</li>
														<li>신청일</li>
													</ul>
													<ul>
														<li>${apply.mate.user.nickname }</li>
														<c:choose>
															<c:when test="${apply.mate.telType eq '1' }">
																<li><u><a href="${apply.mate.telUrl }"
																		target="_blank">${apply.mate.telName }</a></u></li>
															</c:when>
															<c:otherwise>
																<li>${apply.mate.telName }</li>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${apply.applyTradelType ne 0 }">
																<li>${apply.applyTradelType }</li>
															</c:when>
															<c:otherwise>
																<li>${apply.mate.tradeName }</li>
															</c:otherwise>
														</c:choose>
														<li>${apply.regDate }</li>
													</ul>
												</div>
											</c:if>
											<c:if test="${apply.isApply eq '1'}">
												<!-- //진행중 ul -->
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>판매자</li>
														<li>연락수단</li>
														<li>거래방법</li>
														<li>신청일</li>
														<li>${apply.mate.bank }</li>
													</ul>
													<ul>
														<li>${apply.mate.user.nickname }</li>
														<c:choose>
															<c:when test="${apply.mate.telType eq '1' }">
																<li><u><a href="${apply.mate.telUrl }"
																		target="_blank">${apply.mate.telName }</a></u></li>
															</c:when>
															<c:otherwise>
																<li>${apply.mate.telName }</li>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${apply.applyTradelType ne 0 }">
																<li>${apply.tradeName }</li>
															</c:when>
															<c:otherwise>
																<li>${apply.mate.tradeName }</li>
															</c:otherwise>
														</c:choose>
														<li>${apply.regDate }</li>
														<li>${apply.mate.accountNumber }<i
															class="fa-solid fa-circle-exclamation"
															style="color: var(- -lgray-color); opacity: 0.5"
															data-bs-toggle="tooltip" data-bs-html="true"
															data-bs-placement="top"
															title="입금 전 더치트에 검색해 본다면<br>더욱 안전한 거래를 할 수 있어요!"></i>
														</li>
													</ul>
												</div>
												<!-- 진행중 ul// -->
											</c:if>
											<c:if test="${apply.isApply eq '2'}">
												<!-- //거래완료 ul -->
												<div class="mate-card-middle-tb">
													<ul class="tb-title">
														<li>판매자</li>
														<li>신청일</li>
														<li>거래완료</li>
													</ul>
													<ul>
														<li>${apply.mate.user.nickname }</li>
														<li>${apply.regDate }</li>
														<li>${fn:replace(apply.modDate,'T',' ')}</li>
													</ul>
												</div>
												<!-- 거래완료 ul// -->
											</c:if>
										</div>
									</c:if>
									<!-- ////////// 보낸 조각 메이트 끝 ////////// -->
									<!-- ////////// 받은 조각 메이트 시작 ////////// -->
									<c:if test="${apply.getUid eq user.uid}">
										<form id="apply_edit_form">
											<div class="mate-card-top">
												<p class="is_apply_tag mate-card-tag ver${apply.isApply }"></p>
												<input type="hidden" name="aid" value="${apply.aid }" /> <input
													type="hidden" name="mid" value="${apply.mate.mid }" /> <input
													type="hidden" name="beforeIsApply"
													value="${apply.isApply }" /> <select name="isApply"
													class="is_apply_select_tag mate-card-tag ver${apply.isApply } hide">
													<option value="0" class="tag_0"
														${apply.isApply eq '0' ? 'selected' : ''}>대기 중</option>
													<option value="1" class="tag_1"
														${apply.isApply eq '1' ? 'selected' : ''}>거래 중</option>
													<option value="2" class="tag_2"
														${apply.isApply eq '2' ? 'selected' : ''}>거래완료</option>
												</select> <a class="is_apply_mod"><u>상태변경</u></a>
												<div class="is_apply_edit hide">
													<div class="d-flex gap-1">
														<button class="mate-card-tag ver0 is-apply-save-btn">변경</button>
														<button class="mate-card-tag ver2 is-apply-edit-btn">취소</button>
													</div>
												</div>
											</div>
										</form>
										<div class="mate-card-middle">
											<h5>${apply.mate.title }</h5>
											<div class="mate-card-middle-tb">
												<ul class="tb-title" id="ul_title_aid_${apply.aid }">
													<li>신청자</li>
													<li>거래방법</li>
													<li>신청일</li>
													<c:if test="${apply.isApply eq '2' }">
														<li>거래완료</li>
													</c:if>

												</ul>
												<ul id="ul_desc_aid_${apply.aid }">
													<li>${apply.user.nickname }</li>
													<c:choose>
														<c:when test="${apply.applyTradelType ne 0 }">
															<li>${apply.tradeName }</li>
														</c:when>
														<c:otherwise>
															<li>${apply.mate.tradeName }</li>
														</c:otherwise>
													</c:choose>
													<li>${apply.regDate }</li>
													<c:if test="${apply.isApply eq '2' }">
														<li>${fn:replace(apply.modDate,'T',' ')}</li>
													</c:if>
												</ul>
											</div>
										</div>
									</c:if>
									<!-- ////////// 받은 조각 메이트 끝 ////////// -->
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</div>
	</div>

	<script src="/js/mypage_mate_apply.js"></script>
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