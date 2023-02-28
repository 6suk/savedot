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
								<p>${mate.state eq 0 ? '판매중' : '판매완료' }</p>
							</div>
							<div class="line d-flex">
								<p>
									<span>조회</span><span>${mate.viewCnt }</span>
								</p>
								<p>
									<span><i class="fa-regular fa-heart"></i></span><span>${mate.likeCnt }</span>
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
									<span>${mate.state eq 0 ? '모집중' : '모집완료' }</span> <span
										class="mate-url-tag ver${mate.state}">${mate.positonApplyNum }
										/ ${mate.positionNum }</span>
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
						<c:choose>
							<c:when test="${mate.user.uid eq user.uid }">
								<div>
									<button class="btn btn-sub sm font-14"
										onclick="location.href='/mate/update/${mate.mid}'">게시물
										수정</button>
									<button class="btn btn-gray sm font-14" id="question-openbtn">게시물
										삭제</button>
								</div>
							</c:when>
							<c:when test="${empty user }">
								<button class="btn btn-main sm font-14" id="loginbtn">
									메이트 신청 <i class="fa-solid fa-plus" id="applybtn-icon"></i>
								</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-main sm font-14" id="applybtn">
									메이트 신청 <i class="fa-solid fa-plus" id="applybtn-icon"></i>
								</button>
							</c:otherwise>
						</c:choose>
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
					<!--  댓글작성부분  -->
					<div class = "mate-detail-reply-insert">
					<span class ="reply_num">
					댓글
					</span>
					</div>
					<div>
					<form method="post" action="/mate/reply/insert">
						<input type="hidden" name="mid"  id="mid" value="<c:out value="${mate.mid}"/>">
						<input type="hidden" name="uid"  id="rid"  value="${user.uid}">
							<table class="table table-borderless mt-2">
                                <tr class="d-flex">
                                    <td class="col-1 text-end">
                                        <label for="content"><p class="font-15 font-600" >${user.nickname }</p></label>
                                    </td>
                                    <td class="col-9">
                                        <textarea class="form-control" id="content" name="content" rows="3" placeholder="댓글을 입력해주세요."></textarea>
                                    </td>
                                    <td class="col-2">
                                        <button type="submit" class="btn btn-primary">등록</button>
                                    </td>
                                </tr>
                            </table>
					</form>
					</div>
					<!--  댓글작성부분  -->
					<!-- 댓글리스트부분 -->
					   <div class="col-12"><hr></div>
                    <div class="col-12">
                    <c:forEach var="reply" items="${replyList}">
                      <c:if test="${reply.grps eq 0}">
                        <div class="d-flex flex-row mt-1">
                            <div class="card bg-light text-dark w-75">
                                <div class="card-body" id="replyList">   
                                   <strong>${reply.nickname}</strong>&nbsp;&nbsp;${fn:replace(reply.regDate, 'T', ' ')}<br>  
                                    ${fn:replace(reply.content, newline, '<br>')}<br>   <!-- content -->
                                	<c:if test="${mate.uid == user.uid}">
                                	<a href="/mate/rereply/${reply.rid}/${reply.grp}">댓글달기</a>
                                	</c:if>
                                	<span>
                                	<c:if test="${user.uid == reply.uid}">
                            				<a href="/mate/reply/update/${reply.rid}">수정</a>
 			                 				<a onclick="deleteConfirm(${reply.rid},${reply.mid})" href="#" >삭제</a>
 			                   		</c:if>
 			                   		</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                     <c:if test="${reply.grps eq 1}">
                        <div class="d-flex flex-row-reverse mt-1">
                            <div class="card w-75">
                                <div class="card-body text-end">
                                    RE : <strong>${reply.nickname}</strong>&nbsp;&nbsp;${fn:replace(reply.regDate, 'T', ' ')}<br>  
                                    ${fn:replace(reply.content, newline, '<br>')}  <!-- content -->
                                </div>
                            </div>
                        </div>
                    </c:if>
                    </c:forEach>
                    </div>
					<!-- 댓글리스트부분 -->
				</div>
			</section>
		</div>
	</div>
	<!-- // Login Modal -->
	<div class="modalBg" id="modal_login">
		<div class="requestModal savedot-modal question" style="opacity: 1">
			<div class="question-title">
				<h5>로그인 후 메이트를 신청해주세요!</h5>
				<div class="question-btn">
					<input type="button" class="btn btn-main full font-14"
						onclick="location.href='/user/login'" value="로그인하러가기" />
					<input type="button" class="closebtn btn btn-sub full font-14"
						value="취소" />
				</div>
			</div>
		</div>
	</div>
	<!-- Login Modal // -->

	<!-- // question Modal -->
	<div class="modalBg" id="modal_question">
		<div class="requestModal savedot-modal question" style="opacity: 1">
			<div class="question-title">
				<h5>게시물을 삭제하시겠습니까?</h5>
				<div class="question-btn">
					<input type="button" class="btn btn-main full font-14"
						onclick="location.href='/mate/delete/${mate.mid}'" value="삭제" />
					<input type="button"
						class="closebtn btn btn-sub full font-14" value="취소" />
				</div>
			</div>
		</div>
	</div>
	<!-- question Modal // -->

	<!-- // Apply Form -->
	<div class="modalBg" id="modal_apply">
		<div class="requestModal savedot-modal" style="opacity: 1">
			<div class="content-title">
				<h5>매칭 신청</h5>
				<div>
					<input type="button" id="sendbtn" class="btn btn-main mdi"
						value="메이트 신청" /> <input type="button"
						class="closebtn btn btn-sub mdi" value="닫기" />
				</div>
			</div>
			<input type="hidden" value="${mate.placeAddr}" id="apply_place_addr" />
			<input type="hidden" value="${mate.parcelPrice}"
				id="apply_parcel_price" /> <input type="hidden"
				value="${mate.parcelType}" id="apply_parcel_type" />
			<form id="applyform" class="inputbox"
				action="/mate/apply/${mate.mid}" method="post">
				<input type="hidden" name="uid" value="${user.uid}" /> <input
					type="hidden" value="${mate.placeAddr}" id="apply_place_addr" /> <input
					type="hidden" value="${mate.parcelPrice}" id="apply_parcel_price" />
				<input type="hidden" value="${mate.parcelType}"
					id="apply_parcel_type" /> <input
					class="form-control text required" type="text" name="nickname"
					value="${user.nickname }" readonly />
				<!-- <textarea
            class="form-control"
            name="content"
            cols="30"
            rows="10"
            placeholder="남기고 싶은 말을 적어주세요 :)"
          ></textarea> -->
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
					<ul class="apply_notice">
						<li>제품에 대한 질문은 게시물의 댓글 또는 기재된 연락 수단을 이용해주세요.</li>
						<li>거래 진행 중으로 변경 시 판매자의 입금 계좌번호를 확인할 수 있습니다.</li>
						<li>모집인원이 모두 차면 자동으로 취소됩니다.</li>
					</ul>
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
<script src="/js/mate_reply.js"></script>
<script>
	var tooltipTriggerList = [].slice.call(document
			.querySelectorAll('[data-bs-toggle="tooltip"]'));
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});
</script>
<script>
	function deleteConfirm(rid,mid) {
		if(confirm("삭제하시겠습니까?"))
		 	location.href = "/mate/reply/delete/" + rid + "/" + mid;
	}
</script>   	
</html>

