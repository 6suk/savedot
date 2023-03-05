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
<title>회원정보 수정</title>
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
			<section class="j-wrap update" style="margin-bottom: 50rem">
				<form action="/user/update" method="post" id="update-form">
					<input type="hidden" name="uid_" value="${user.uid}">
					<div class="j-main-title update">
						<h3 class="maintxt">정보수정</h3>
						<p>필수 기입 항목</p>
					</div>
					<div class="j-input-wrap">
						<ul class="j-input-group">
							<li>
								<h6>이름</h6> <input value="${user.uname}" disabled name="uname"
								class="form-control text" type="text" placeholder="이름" />
							</li>
							<li>
								<h6>아이디</h6> <input name="id" class="form-control text j-check"
								type="text" placeholder="아이디" id="id" value="${user.id}"
								disabled />
								<p class="check-ok hide">사용 가능한 아이디입니다.</p>
								<p class="check-none hide">이미 사용 중인 아이디입니다.</p>
							</li>
							<c:choose>
								<c:when test="${empty user.oauth}">
									<li>
										<h6>패스워드</h6>
										<p>패스워드 변경을 원하신다면 영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</p> <input
										name="pwd" class="form-control text j-check" type="password" id="pwd"
										placeholder="패스워드" /> <input name="pwd2"
										class="form-control text j-check" type="password"
										placeholder="패스워드 확인" id="pwd2" />
										<p class="check-ok hide">패스워드가 일치합니다.</p>
										<p class="check-none hide">패스워드를 확인해주세요.</p>
									</li>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>

							<li>
								<h6>닉네임</h6>
								<p>다른 유저와 겹치지 않도록 입력해주세요.</p> <input value="${user.nickname}"
								name="nickname" class="form-control text j-check" type="text"
								placeholder="닉네임" id="nickname" />
								<p class="check-ok hide">사용 가능한 닉네임입니다.</p>
								<p class="check-none hide">이미 사용중인 닉네임입니다.</p>
							</li>
							<c:set var="email" value="${fn:split(user.email,'@')}" />
							<li>
								<h6>이메일</h6>
								<div class="j-email" id="email-box">
									<input type="hidden" name="email" id="output-email"
										email-type="1" /> <input value="${email[0]}"
										class="form-control text required" type="text"
										placeholder="이메일" id="email-input" /> <span>@</span> <select
										class="form-select text hide" id="email">
										<option value="" disabled="" selected>선택해주세요</option>
										<option value="naver.com">naver.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="daum.net">daum.net</option>
										<option value="gmail.com">gmail.com</option>
										<option value="nate.com">nate.com</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="outlook.com">outlook.com</option>
										<option value="icloud.com">icloud.com</option>
										<option value="_manual">직접입력</option>
									</select>
									<div class="" id="manual">
										<input value="${email[1]}" class="form-control text required"
											type="text" placeholder="입력해주세요" id="manual-input" /> <i
											class="fa-solid fa-xmark manual-icon" id="manual-cancel"></i>
									</div>
								</div>
							</li>
							<li>
								<h6>생년월일</h6> <input name="birthDate_" value="${user.birthDate}"
								class="form-control text required j-date" type="date"
								placeholder="생년월일" />
							</li>
						</ul>
					</div>
					<div class="j-main-title">
						<h4 class="">선택기입</h4>
						<p>선택 기입 항목</p>
					</div>
					<div class="j-input-wrap">
						<ul class="j-input-group">
							<li>
								<h6>연락처</h6> <input value="${user.tel}" name="tel"
								class="form-control text" type="text" placeholder="연락처"
								numberOnly />
							</li>
							<li>
								<h6>거주 지역</h6> <select class="form-select text" name="code_">
									<option value="0" ${user.code eq 0 ? selected : '' } disabled >선택해주세요</option>
									<option value="11" ${user.code eq 11 ? selected : '' }>서울특별시</option>
									<option value="26" ${user.code eq 26 ? selected : '' }>부산광역시</option>
									<option value="27" ${user.code eq 27 ? selected : '' }>대구광역시</option>
									<option value="28" ${user.code eq 28 ? selected : '' }>인천광역시</option>
									<option value="29" ${user.code eq 29 ? selected : '' }>광주광역시</option>
									<option value="30" ${user.code eq 30 ? selected : '' }>대전광역시</option>
									<option value="31" ${user.code eq 31 ? selected : '' }>울산광역시</option>
									<option value="41" ${user.code eq 41 ? selected : '' }>경기도</option>
									<option value="42" ${user.code eq 42 ? selected : '' }>강원도</option>
									<option value="43" ${user.code eq 43 ? selected : '' }>충청북도</option>
									<option value="44" ${user.code eq 44 ? selected : '' }>충청남도</option>
									<option value="45" ${user.code eq 45 ? selected : '' }>전라북도</option>
									<option value="46" ${user.code eq 46 ? selected : '' }>전라남도</option>
									<option value="47" ${user.code eq 47 ? selected : '' }>경상북도</option>
									<option value="48" ${user.code eq 48 ? selected : '' }>경상남도</option>
									<option value="50" ${user.code eq 50 ? selected : '' }>제주특별자치도</option>
							</select>
							</li>
							<li>
								<h6>한달 급여 및 근무시간</h6>
								<p>기입 시 '얼마 벌었지?'를 이용할 수 있어요!</p> <input value="${user.pay}"
								name="pay_" class="form-control text" type="text"
								placeholder="한달 급여" price />
								<div class="j-email">
									<input name="workIn" class="form-control text j-date in"
										placeholder="출근시간" type="text" name="workIn"
										value="${user.workIn }" onfocus="(this.type='time')" /> <span>~</span>
									<input value="${user.workOut }" name="workOut"
										class="form-control text j-date out" placeholder="퇴근시간"
										type="text" name="workOut" onfocus="(this.type='time')" />
								</div>
							</li>
							<!-- <li>
							<h6>자주 가는 경로</h6>
							<p>기입 시 챌린지 '대중교통 이용하기' 금액을 계산해드릴게요!</p>
							<div class="mypage-cash-top-end">
								<input name="departures" class="form-control text" type="text"
									placeholder="경로1 주소검색을 클릭해주세요!" disabled />
								<button class="query-search addr" style="border-left: 0">
									<p>주소검색</p>
								</button>
							</div>
							<div class="mypage-cash-top-end">
								<input name="arrivals" class="form-control text" type="text"
									placeholder="경로2 주소검색을 클릭해주세요!" disabled />
								<button class="query-search addr" style="border-left: 0">
									<p>주소검색</p>
								</button>
							</div>
							<p>자차를 이용한다면 유류비를 계산해드릴게요!</p>
							<div class="form-tag j-tag" id="matecategory">
								<input type="radio" name="vehicles" id="taxi" value="taxi"
									checked /> <label for="taxi">택시이용</label> <input type="radio"
									name="vehicles" id="car" value="car" /> <label for="car">자차이용</label>
							</div>
						</li> -->
							<li>
								<h6>자주 사용하는 계좌</h6>
								<p>'조각메이트' 이용 시 편리하게 사용할 수 있어요!</p>
								<div class="j-email">
									<select name="bank" class="form-select text"
										style="flex: 0.2 0 0">
										<option value ${user.bank eq null or empty
											user.bank ? 'selected' : '' } disabled>은행</option>
										<option value="KB국민은행"
											${user.bank eq 'KB국민은행' ? 'selected' : '' }>KB국민은행</option>
										<option value="IBK기업은행"
											${user.bank eq 'IBK기업은행' ? 'selected' : '' }>IBK기업은행</option>
										<option value="NH농협은행"
											${user.bank eq 'NH농협은행' ? 'selected' : '' }>NH농협은행</option>
										<option value="신한은행" ${user.bank eq '신한은행' ? 'selected' : '' }>신한은행</option>
										<option value="씨티은행" ${user.bank eq '씨티은행' ? 'selected' : '' }>씨티은행</option>
										<option value="SC제일은행"
											${user.bank eq 'SC제일은행' ? 'selected' : '' }>SC제일은행</option>
										<option value="우리은행" ${user.bank eq '우리은행' ? 'selected' : '' }>우리은행</option>
										<option value="카카오뱅크"
											${user.bank eq '카카오뱅크' ? 'selected' : '' }>카카오뱅크</option>
										<option value="케이뱅크" ${user.bank eq '케이뱅크' ? 'selected' : '' }>케이뱅크</option>
										<option value="토스뱅크" ${user.bank eq '토스뱅크' ? 'selected' : '' }>토스뱅크</option>
										<option value="하나은행" ${user.bank eq '하나은행' ? 'selected' : '' }>하나은행</option>
										<option value="경남은행" ${user.bank eq '경남은행' ? 'selected' : '' }>경남은행</option>
										<option value="광주은행" ${user.bank eq '광주은행' ? 'selected' : '' }>광주은행</option>
										<option value="대구은행" ${user.bank eq '대구은행' ? 'selected' : '' }>대구은행</option>
										<option value="부산은행" ${user.bank eq '부산은행' ? 'selected' : '' }>부산은행</option>
										<option value="KDB산업은행"
											${user.bank eq 'KDB산업은행' ? 'selected' : '' }>KDB산업은행</option>
										<option value="수협은행" ${user.bank eq '수협은행' ? 'selected' : '' }>수협은행</option>
										<option value="우체국은행"
											${user.bank eq '우체국은행' ? 'selected' : '' }>우체국은행</option>
										<option value="전북은행" ${user.bank eq '전북은행' ? 'selected' : '' }>전북은행</option>
										<option value="제주은행" ${user.bank eq '제주은행' ? 'selected' : '' }>제주은행</option>
										<option value="새마을금고"
											${user.bank eq '새마을금고' ? 'selected' : '' }>새마을금고</option>
										<option value="신협은행" ${user.bank eq '신협은행' ? 'selected' : '' }>신협은행</option>
										<option value="SBI저축은행"
											${user.bank eq 'SBI저축은행' ? 'selected' : '' }>SBI저축은행</option>
										<option value="저축은행" ${user.bank eq '저축은행' ? 'selected' : '' }>저축은행</option>
										<option value="0" disabled>증권</option>
										<option value="NH투자증권"
											${user.bank eq 'NH투자증권' ? 'selected' : '' }>NH투자증권</option>
										<option value="미래에셋증권"
											${user.bank eq '미래에셋증권' ? 'selected' : '' }>미래에셋증권</option>
										<option value="삼성증권" ${user.bank eq '삼성증권' ? 'selected' : '' }>삼성증권</option>
										<option value="신한투자증권"
											${user.bank eq '신한투자증권' ? 'selected' : '' }>신한투자증권</option>
										<option value="SK증권" ${user.bank eq 'SK증권' ? 'selected' : '' }>SK증권</option>
										<option value="유안타증권"
											${user.bank eq '유안타증권' ? 'selected' : '' }>유안타증권</option>
										<option value="유진투자증권"
											${user.bank eq '유진투자증권' ? 'selected' : '' }>유진투자증권</option>
										<option value="한국투자증권"
											${user.bank eq '한국투자증권' ? 'selected' : '' }>한국투자증권</option>
									</select> <input value="${user.accountNumber }"
										class="form-control text" type="text" placeholder="계좌번호"
										name="accountNumber" numberOnly style="flex: 0.8 0 0" />
								</div>
							</li>
							<li><a href="/user/delete/${uid}" style="float: left;">회원탈퇴</a></li>
							<li><input type="hidden" name="oauth" /> <input
								type="button" class="btn btn-main join-btn" id="update-btn"
								value="수정하기" /></li>
						</ul>
					</div>
				</form>
			</section>
		</div>
	</div>
</body>
<script src="/js/savedot_join.js"></script>
<script src="/js/required.js"></script>
<script src="/js/saveDot_numberOnly.js"></script>
<script src="/js/profile.js"></script>
</html>