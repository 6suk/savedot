<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>SAVE. : 마이페이지 - 알림</title>
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
			<section class="col-md-8">
				<div class="d-flex align-items-center gap-2 alarm-top">
					<h5>읽지 않은 알림</h5>
					<p class="alarm-count" data-count="${alarmBeforeCnt }"></p>
				</div>
				
				<c:if test="${not noneread }">
					<div class="mypage-mate-none-card alarm">
						<p>아직 도착한 알림이 없어요!</p>
					</div>
				</c:if>
				<!-- 알람 시작 -->
				<div class="alarm-wrap">
					<ul class="alarm-list">
						<c:forEach var="alarm" items="${alarmList }">
							<c:if test="${alarm.read eq 0 and alarm.type ne 3 }">
								<c:set var="url"
									value="/mate/detail/${alarm.mid }/#${alarm.rid }"></c:set>
								<!-- TYPE 0, 1, 2 ALARM -->
								<li class="alarm-item"
									onclick="location.href='${alarm.type eq 2 ? '/mypage/mate/apply/view' : url}'">
									<div class="alarm-item-top">
										<div class="flex">
											<p alarm-read="0"></p>
											<div class="flex ps-3 gap-1">
												<p alarm-type="${alarm.type }"></p>
												<div class="alarm-content">
													<p class="alarm-mate-title">${alarm.mate.title }</p>
													<div class="alarm-content-bottom">
														<div class="re-comment-item nickname">
															<div class="m-re-profile">
																<div class="m-re-thum">
																	<img src="/aside/blob/${alarm.user.id}"
																		onError="this.style.visibility='hidden'" />
																</div>
																<div class="m-re-name">${alarm.user.nickname}</div>
															</div>
														</div>
														<p class="alarm-comment"></p>
													</div>
												</div>
											</div>
										</div>
										<p class="re-date">${fn:replace(alarm.alarmDate, 'T', ' ')}</p>
									</div> <c:if test="${alarm.type ne 2 }">
										<div class="re-comment-item desc">${alarm.reply.content}</div>
									</c:if>
								</li>
								<!-- TYPE 0, 1, 2 ALARM -->
							</c:if>
							<c:if test="${alarm.read eq 0 and alarm.type eq 3 }">
								<!-- TYPE 3 ALARM -->
								<li class="alarm-item"
									onclick="location.href='/mypage/mate/apply/view'">
									<div class="alarm-item-top">
										<div class="flex">
											<p alarm-read="0"></p>
											<div class="flex ps-3 gap-1">
												<p alarm-type="${alarm.type }"></p>
												<div class="alarm-content">
													<p class="alarm-mate-title">${alarm.mate.title }</p>
													<div class="alarm-content-bottom">
														<p class="alarm-comment">보낸 메이트 요청이</p>
														<div class="re-comment-item nickname">
															<div class="m-re-profile">
																<div class="m-re-name">${alarm.apply.isApplyName }</div>
															</div>
														</div>
														<p>상태로 변경되었어요!</p>
													</div>
												</div>
											</div>
										</div>
										<p class="re-date">${fn:replace(alarm.alarmDate, 'T', ' ')}</p>
									</div>
								</li>
								<!-- TYPE 3 ALARM -->
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<!-- 알람 끝 -->


				<form action="/mypage/alarm/delete" method="post">
					<div class="d-flex align-items-center gap-2 alarm-top">
						<h5>지난 알림</h5>
						<input type="submit" value="지난 알림 삭제">
					</div>

					<c:if test="${not read }">
						<div class="mypage-mate-none-card alarm">
							<p>아직 도착한 알림이 없어요!</p>
						</div>
					</c:if>
					<!-- 알람 시작 -->
					<div class="alarm-wrap">
						<ul class="alarm-list">
							<c:forEach var="alarm" items="${alarmList }">
								<input type="hidden" name="id" value="${alarm.id}">
								<c:if test="${alarm.read eq 1 and alarm.type ne 3 }">
									<c:set var="url"
										value="/mate/detail/${alarm.mid }/#${alarm.rid }"></c:set>
									<!-- TYPE 0, 1, 2 ALARM -->
									<li class="alarm-item"
										onclick="location.href='${alarm.type eq 2 ? '/mypage/mate/apply/view' : url}'">
										<div class="alarm-item-top">
											<div class="flex">
												<p alarm-read="1"></p>
												<div class="flex ps-3 gap-1">
													<p alarm-type="${alarm.type }"></p>
													<div class="alarm-content">
														<p class="alarm-mate-title">${alarm.mate.title }</p>
														<div class="alarm-content-bottom">
															<div class="re-comment-item nickname">
																<div class="m-re-profile">
																	<div class="m-re-thum">
																		<img src="/aside/blob/${alarm.user.id}"
																			onError="this.style.visibility='hidden'" />
																	</div>
																	<div class="m-re-name">${alarm.user.nickname}</div>
																</div>
															</div>
															<p class="alarm-comment"></p>
														</div>
													</div>
												</div>
											</div>
											<p class="re-date">${fn:replace(alarm.alarmDate, 'T', ' ')}</p>
										</div> <c:if test="${alarm.type ne 2 }">
											<div class="re-comment-item desc">${alarm.reply.content}</div>
										</c:if>
									</li>
									<!-- TYPE 0, 1, 2 ALARM -->
								</c:if>
								<c:if test="${alarm.read eq 1 and alarm.type eq 3 }">
									<!-- TYPE 3 ALARM -->
									<li class="alarm-item"
										onclick="location.href='/mypage/mate/apply/view'">
										<div class="alarm-item-top">
											<div class="flex">
												<p alarm-read="1"></p>
												<div class="flex ps-3 gap-1">
													<p alarm-type="${alarm.type }"></p>
													<div class="alarm-content">
														<p class="alarm-mate-title">${alarm.mate.title }</p>
														<div class="alarm-content-bottom">
															<p class="alarm-comment">보낸 메이트 요청이</p>
															<div class="re-comment-item nickname">
																<div class="m-re-profile">
																	<div class="m-re-name">${alarm.apply.isApplyName }</div>
																</div>
															</div>
															<p>상태로 변경되었어요!</p>
														</div>
													</div>
												</div>
											</div>
											<p class="re-date">${fn:replace(alarm.alarmDate, 'T', ' ')}</p>
										</div>
									</li>
									<!-- TYPE 3 ALARM -->
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</form>
				<!-- 알람 끝 -->
			</section>
			<!-- content -->
		</div>
	</div>
</body>
</html>


