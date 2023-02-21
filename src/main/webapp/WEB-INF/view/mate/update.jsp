<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- //// MATE UPDATE PAGE //// -->

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
		<div class="container-nonaside">
			<c:if test="${not empty mate.imgInfo}">
				<c:forEach var="img" items="${mate.imgInfo }">
					<input type="hidden"
						MATEIMG="${img.saveDate }/${img.id }${img.ext }" />
				</c:forEach>
			</c:if>
			<!-- content -->
			<section>
				<div class="content-title">
					<c:set var="cate_mate" value=""></c:set>
					<c:set var="cate_ott" value=""></c:set>
					<c:if test="${mate.category eq 0 }">
						<c:set var="cate_mate" value="checked"></c:set>
					</c:if>
					<c:if test="${mate.category eq 1 }">
						<c:set var="cate_ott" value="checked"></c:set>
					</c:if>

					<div class="form-tag" id="matecategory">
						<input type="radio" name="category" id="mate" value="0"
							class="required" ${cate_mate } /> <label for="mate" id="category">조각
							메이트</label> <input type="radio" name="category" id="ott" value="1"
							class="required" ${cate_ott } /> <label for="ott" id="category">OTT
							메이트</label>
					</div>
					<input type="button" id="updatebtn" class="btn btn-main main"
						value="등록하기" />
				</div>

				<div class="writebox flex-lg-row">
					<div class="imgbox">
						<label class="imgbox-img label" id="label" for="input">
							<div class="inner" id="inner">
								<i class="fa-solid fa-camera"></i> <span class="graytxt pt-2">사진
									올리기</span> <span class="l-graytxt font-12">(최대 5장)</span>
							</div>
						</label> <input id="input" class="input" accept="image/*" type="file"
							multiple="true" hidden="true" name="img" />
						<div class="preview" id="preview" hidden></div>
					</div>
					<div class="inputbox">
						<form id="mate-update-form" class="inputbox">
							<div style="position: relative; width: 100%">
								<input class="form-control text required" type="text"
									value="${user.telFormat }" readonly /> <i
									class="fa-solid fa-circle-exclamation inner-btn font-14"
									style="color: var(- -lgray-color); opacity: 0.5"
									data-bs-toggle="tooltip" data-bs-html="true"
									data-bs-placement="top"
									title="연락처는 거래 진행 중일 시 메이트에게만 공개됩니다.<br>
                    연락처 수정은 마이페이지에서 가능합니다."></i>
							</div>
							<div style="position: relative; width: 100%">
								<div class="input-btn">
									<select name="bank" id="bank"
										class="form-select required font-14" style="flex: 0.2 0 0">
										<option value="${mate.bank }" selected>${mate.bank }</option>
										<option value="카카오뱅크">카카오뱅크</option>
										<option value="농협">농협</option>
										<option value="국민">국민</option>
									</select> <input type="text" class="form-control text"
										name="accountNumber" id="accountNumber" style="flex: 1 0 0"
										placeholder="*계좌번호" value="${mate.accountNumber }" /> <i
										class="fa-solid fa-circle-exclamation inner-btn font-14"
										style="color: var(- -lgray-color); opacity: 0.5"
										data-bs-toggle="tooltip" data-bs-html="true"
										data-bs-placement="top" title="계좌번호는 거래 진행 중일 시 메이트에게만 공개됩니다."></i>
								</div>
							</div>
							<div class="input-btn">
								<select name="telType" id="tel_type"
									class="form-select required font-14" style="flex: 0.2 0 0">
									<option value="0" selected disabled>연락 방법</option>
									<c:if test="${mate.telType eq 1 }">
										<option value="1" selected>오픈채팅</option>
										<option value="2">댓글</option>
									</c:if>
									<c:if test="${mate.telType eq 2 }">
										<option value="1">오픈채팅</option>
										<option value="2" selected>댓글</option>
									</c:if>
								</select>
								<c:if test="${mate.telType eq 1 }">
									<input type="text" class="form-control text" name="telUrl"
										id="tel_url" style="flex: 1 0 0" value="${mate.telUrl }" />
								</c:if>
								<c:if test="${mate.telType eq 2 }">
									<input type="text" class="form-control text" name="telUrl"
										id="tel_url" style="flex: 1 0 0" placeholder="해당 게시물 댓글 소통"
										disabled />
								</c:if>
							</div>
							<input class="form-control text required" type="text"
								name="title" placeholder="타이틀*" value="${mate.title }" />
							<div class="d-flex gap-2">
								<input class="form-control text required" type="text"
									name="price1" placeholder="구매가*" style="flex: 1 0 0"
									id="price1" price
									value="<fmt:formatNumber value="${mate.price1}" pattern="#,###" />" />
								<input class="form-control text required" type="text"
									name="positionNum" placeholder="모집인원*" style="flex: 0.3 0 0"
									id="position_num" numberOnly value="${mate.positionNum }" /> <input
									class="btn btn-gray font-14" type="button" value="자동계산"
									data-bs-toggle="tooltip" data-bs-html="true"
									data-bs-placement="top"
									title="구매가와 모집인원 기입 시,<br>판매가는 자동으로 계산됩니다.<br>모집인원 미기입 시 기본 1명으로 계산됩니다.<br>자동 계산된 판매가는 수정이 가능합니다."
									id="calc_price" />
							</div>
							<input class="form-control text required" type="text"
								name="price2" id="price2" placeholder="판매가*" price
								value="<fmt:formatNumber value="${mate.price2}" pattern="#,###" />" />
							<textarea class="form-control" name="content" cols="30" rows="10"
								placeholder="제품의 정보를 자세하게 기재해주세요!">${mate.contentUpdate }</textarea>
							<div class="form-tag tag-border" id="tradetype">
								<input type="radio" name="tradeType" id="direct" value="1"
									class="required" ${mate.tradeType eq 1 ? 'checked' : ''} /> <label
									for="direct" id="tradetype">직접거래</label> <input type="radio"
									name="tradeType" id="parcel" value="2" class="required"
									${mate.tradeType eq 2 ? 'checked' : ''} /> <label for="parcel"
									id="tradetype">택배거래</label> <input type="radio"
									name="tradeType" id="all" value="3" class="required"
									${mate.tradeType eq 3 ? 'checked' : ''} /> <label for="all"
									id="tradetype">직접거래 / 택배거래 모두 가능</label>
							</div>

							<!-- << 장소검색 -->
							<c:set var="hide" value="style='display: none'"></c:set>
							<div class="${mate.tradeType ne 2 ? '' : 'hide'}"
								id="direct_show">
								<div class="input-btn">
									<a class="input-btn-ele-reverse"
										${mate.tradeType ne 2 ? hide : '' }
										onclick="sample3_execDaumPostcode()" id="addr-find"><span>장소검색</span>
										<i class="fa-solid fa-magnifying-glass font-14"></i></a> <a
										class="input-btn-ele-reverse" onclick="addr_cancle()"
										id="addr-cancle" ${mate.tradeType ne 2 ? '' : hide }><span>삭제</span></a>
									<input type="hidden" name="placeCoords" id="place_coords" 
									value="${mate.tradeType ne 2 ? mate.placeCoords : '' }"
										${mate.tradeType ne 2 ? '' : 'disabled' } />
									<input type="hidden" name="placeCode" id="place_code"
										value="${mate.tradeType ne 2 ? mate.placeCode : '' }"
										${mate.tradeType ne 2 ? '' : 'disabled' }  />
									<input type="hidden" name="placeAddr" id="place_addr"
										value="${mate.tradeType ne 2 ? mate.placeAddr : '' }"
										${mate.tradeType ne 2 ? '' : 'disabled' } />
									<input type="text" class="form-control text" id="place_name"
										name="placeName" placeholder="*만남 장소를 기입해주세요."
										style="flex: 1 0 0"
										${mate.tradeType ne 2 ? 'readOnly' : 'disabled' }
										value="${mate.tradeType ne 2 ? mate.placeName : '' }" />
								</div>
							</div>
							<!-- 장소검색 >> -->
							<!-- << 선불/착불 선택 -->
							<div class="${mate.tradeType ne 1 ? '' : 'hide'}"
								id="parcel_show">
								<div class="input-btn">
									<select name="parcelType" id="parcel_type"
										class="form-select input-btn-ele-reverse"
										style="flex: 0.2 0 0">
										<option value="0" ${mate.parcelType eq 0 ? 'selected' : ''}
											disabled>선불/착불</option>
										<option value="1" ${mate.parcelType eq 1 ? 'selected' : ''}>선불</option>
										<option value="2" ${mate.parcelType eq 2 ? 'selected' : ''}>착불</option>
									</select> <input type="number" class="form-control text"
										name="parcelPrice" id="parcel_price" style="flex: 1 0 0"
										placeholder="*택배비를 기입해주세요."
										value="${mate.parcelType eq 1 ? mate.parcelPrice : '0'}" />
								</div>
							</div>
							<!-- 선불/착불 선택 >> -->

						</form>
					</div>
				</div>
			</section>
		</div>
	</div>

	<div class="modalBg" id="modal-bg">
		<div class="requestModal savedot-modal-addr" id="section-wrap">
			<div id="closebtn" class="modal-close">
				<i class="fa-solid fa-x"></i>
			</div>
			<div id="wrap"></div>
		</div>
	</div>
</body>

<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849&libraries=services"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
<script src="/js/kakao_address.js"></script>
<script src="/js/upload.js"></script>
<script src="/js/mate.js"></script>
<script src="/js/required.js"></script>
<script src="/js/mate_update.js"></script>
<script>
	var tooltipTriggerList = [].slice.call(document
			.querySelectorAll('[data-bs-toggle="tooltip"]'));
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});
</script>
</html>
