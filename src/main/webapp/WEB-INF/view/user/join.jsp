<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- //// MATE WRITE PAGE //// -->

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

	<div class="container-nonaside-sm">
		<!-- content -->
		<section class="j-wrap" style="margin-bottom: 50rem">
			<form action="/user/join" method="post" id="join-form">
				<div class="j-main-title">
					<h3 class="maintxt">회원가입</h3>
					<p>필수 기입 항목</p>
				</div>
				<div class="j-input-wrap">
					<ul class="j-input-group">
						<li>
							<h6>이름</h6> <input name="uname"
							class="form-control text required" type="text" placeholder="이름" />
						</li>
						<li>
							<h6>아이디</h6> <input name="id"
							class="form-control text j-check required" type="text"
							placeholder="아이디" id="id" />
							<p class="check-ok hide">사용 가능한 아이디입니다.</p>
							<p class="check-none hide">이미 사용 중인 아이디입니다.</p>
						</li>
						<li>
							<h6>패스워드</h6>
							<p>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</p> <input name="pwd"
							class="form-control text j-check required" type="password" id="pwd"
							placeholder="패스워드" /> <input name="pwd2"
							class="form-control text j-check required" type="password"
							placeholder="패스워드 확인" id="pwd2" />
							<p class="check-ok hide">패스워드가 일치합니다.</p>
							<p class="check-none hide">패스워드를 확인해주세요.</p>
						</li>

						<li>
							<h6>닉네임</h6>
							<p>다른 유저와 겹치지 않도록 입력해주세요.</p> <input name="nickname"
							class="form-control text j-check required" type="text"
							placeholder="닉네임" id="nickname" />
							<p class="check-ok hide">사용 가능한 닉네임입니다.</p>
							<p class="check-none hide">이미 사용중인 닉네임입니다.</p>
						</li>
						<li>
							<h6>이메일</h6>
							<div class="j-email" id="email-box">
								<input type="hidden" name="email" id="output-email"
									email-type="0" /> <input class="form-control text required"
									type="text" placeholder="이메일" id="email-input" /> <span>@</span>
								<select class="form-select text required" id="email">
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
								<div class="hide" id="manual">
									<input class="form-control text" type="text"
										placeholder="입력해주세요" id="manual-input" /> <i
										class="fa-solid fa-xmark manual-icon" id="manual-cancel"></i>
								</div>
							</div>
						</li>
						<li>
							<h6>생년월일</h6> <input name="birthDate_"
							class="form-control text required j-date" type="text"
							placeholder="생년월일" onfocus="(this.type='date')" />
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
							<h6>연락처</h6> <input name="tel" class="form-control text"
							type="text" placeholder="연락처" numberOnly />
						</li>
						<li>
							<h6>거주 지역</h6> <select class="form-select text" name="code_">
								<option value disabled selected>선택해주세요</option>
								<option value="11">서울특별시</option>
								<option value="26">부산광역시</option>
								<option value="27">대구광역시</option>
								<option value="28">인천광역시</option>
								<option value="29">광주광역시</option>
								<option value="30">대전광역시</option>
								<option value="31">울산광역시</option>
								<option value="41">경기도</option>
								<option value="42">강원도</option>
								<option value="43">충청북도</option>
								<option value="44">충청남도</option>
								<option value="45">전라북도</option>
								<option value="46">전라남도</option>
								<option value="47">경상북도</option>
								<option value="48">경상남도</option>
								<option value="50">제주특별자치도</option>
						</select>
						</li>
						<li>
							<h6>한달 급여 및 근무시간</h6>
							<p>기입 시 '얼마 벌었지?'를 이용할 수 있어요!</p> <input name="pay_"
							class="form-control text" type="text" placeholder="한달 급여" price />
							<div class="j-email">
								<input name="workIn" class="form-control text j-date in"
									placeholder="출근시간" type="text" name="workIn"
									onfocus="(this.type='time')" /> <span>~</span> <input
									name="workOut" class="form-control text j-date out"
									placeholder="퇴근시간" type="text" name="workOut"
									onfocus="(this.type='time')" />
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
									<option value="0" selected disabled>은행</option>
									<option value="KB국민은행">KB국민은행</option>
									<option value="IBK기업은행">IBK기업은행</option>
									<option value="NH농협은행">NH농협은행</option>
									<option value="신한은행">신한은행</option>
									<option value="씨티은행">씨티은행</option>
									<option value="SC제일은행">SC제일은행</option>
									<option value="우리은행">우리은행</option>
									<option value="카카오뱅크">카카오뱅크</option>
									<option value="케이뱅크">케이뱅크</option>
									<option value="토스뱅크">토스뱅크</option>
									<option value="하나은행">하나은행</option>
									<option value="경남은행">경남은행</option>
									<option value="광주은행">광주은행</option>
									<option value="대구은행">대구은행</option>
									<option value="부산은행">부산은행</option>
									<option value="KDB산업은행">KDB산업은행</option>
									<option value="수협은행">수협은행</option>
									<option value="우체국은행">우체국은행</option>
									<option value="전북은행">전북은행</option>
									<option value="제주은행">제주은행</option>
									<option value="새마을금고">새마을금고</option>
									<option value="신협은행">신협은행</option>
									<option value="SBI저축은행">SBI저축은행</option>
									<option value="저축은행">저축은행</option>
									<option value="0" disabled>증권</option>
									<option value="NH투자증권">NH투자증권</option>
									<option value="미래에셋증권">미래에셋증권</option>
									<option value="삼성증권">삼성증권</option>
									<option value="신한투자증권">신한투자증권</option>
									<option value="SK증권">SK증권</option>
									<option value="유안타증권">유안타증권</option>
									<option value="유진투자증권">유진투자증권</option>
									<option value="한국투자증권">한국투자증권</option>
								</select> <input class="form-control text" type="text" placeholder="계좌번호"
									name="accountNumber" numberOnly style="flex: 0.8 0 0" />
							</div>
						</li>
						<li><input type="hidden" name="oauth" /> <input
							type="button" class="btn btn-main join-btn" id="join-btn" value="회원가입" /></li>
					</ul>
				</div>
			</form>
		</section>
	</div>
</body>
<script src="/js/savedot_join.js"></script>
<script src="/js/required.js"></script>
<script src="/js/saveDot_numberOnly.js"></script>
</html>

