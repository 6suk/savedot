<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- //// MYPAGE PAGE - MAIN //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->

<title>회원정보 수정</title>
<style>
.nickname_ok {
	color: #008000;
	display: none;
}

.nickname_already {
	color: #6A82FB;
	display: none;
}
</style>
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
			<section class="col">
				<h3>
					<strong>회원정보 수정</strong>
				</h3>
				<hr>
				<!-- 수정 폼 -->
				<form action="/user/update" method="post">
					<input type="hidden" name="uid" value="${user.uid}">
					<legend>필수입력사항</legend>



					<!-- 이름 -->
					<div>
						<label>* 이름</label> <br> <input class="form-control"
							value="${user.uname}" name="uname" placeholder="이름" disabled>
					</div>

					<!-- 아이디 -->
					<div>
						<label>* 아이디</label>
						<p>다른 유저와 겹치지 않도록 입력해주세요. (2~15자)</p>
						<input type="text" id="id" name="id" class="form-control"
							oninput="checkId()" placeholder="아이디" value="${user.id}" disabled>
					</div>

					<!-- 비밀번호 -->
					<div>
						<label>* 비밀번호</label>
						<p>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</p>
						<input type="password" name="pwd" class="form-control"
							placeholder="비밀번호" value="">
					</div>
					<div>
						<label>* 비밀번호 확인</label> <br> <input type="password"
							class="form-control" value="" name="pwd2" placeholder="비밀번호 확인">
					</div>

					<!-- 닉네임 -->
					<div>
						<label>* 닉네임</label>
						<p>다른 유저와 겹치지 않도록 입력해주세요. (2~15자)</p>
						<input type="text" id="nickname" name="nickname"
							class="form-control" oninput="checkNickname()" placeholder="별명"
							value="${user.nickname}" required>
						<!-- nickname ajax 중복체크 -->
						<span class="nickname_ok">사용 가능한 닉네임입니다.</span> <span
							class="nickname_already">누군가 이 닉네임을 사용하고 있어요.</span>
					</div>

					<!-- 이메일 -->
					<div>
						<label>* 이메일</label> <input type="email" class="form-control"
							placeholder="ex) savedot@mulcam.com (@ 포함기입)" required
							name="email" value="${user.email}">
					</div>

					<!-- 생년월일 -->
					<div>
						<label>생년월일</label>
						<p>생년월일 8자리 숫자를 입력해주세요.</p>
						<input type="number" class="form-control"
							value="${user.birthDate}" name="birthDate"
							placeholder="ex) 19990101" required>
					</div>

					<!-- 선택 항목 -->
					<legend>선택입력사항</legend>
					<!-- 연락처 -->
					<div>
						<label>연락처</label> <br> <input type="number"
							class="form-control" value="${user.tel}" name="tel"
							placeholder="ex) 01012345678">
					</div>

					<!-- 주소 -->
					<div>
						<label>주소</label>
						<p>우편번호를 먼저 찾아주세요.</p>
						<label><input type="text" class="form-control"
							id="postcode" name="postcode" placeholder="우편번호"></label> <input
							type="button" onclick="daumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" name="addr" id="addr"
							placeholder="주소" value="${user.addr}"><br> <input
							type="text" class="form-control" name="detailAddr"
							id="detailAddr" placeholder="상세주소" value="${user.detailAddr}">
						<input type="hidden" id="extraAddr" placeholder="참고항목"> <input
							type="hidden" name="code" value="0">
					</div>

					<!-- 급여 -->
					<div>
						<label>월 급여 또는 용돈</label> <br> <input type="number"
							class="form-control" value="${user.pay}" name="pay"
							placeholder="급여나 용돈을 입력해주세요">
					</div>

					<!-- 출퇴근 시간 -->
					<div class="input__block">
						<input type="time" class="input" value="" name="workIn"
							placeholder="출근 시간"> <input type="time" class="input"
							value="" name="workOut" placeholder="퇴근 시간">
					</div>

					<!-- 출발지 -->
					<div>
						<label>출발지</label> <br> <input class="form-control"
							value="${user.departures}" name="departures" placeholder="">
					</div>

					<!-- 목적지 -->
					<div>
						<label>목적지</label> <br> <input class="form-control"
							value="${user.arrivals}" name="arrivals" placeholder="">
					</div>

					<!-- 교통수단 -->
					<div>
						<label>교통수단</label> <br> <select name="vehicles"
							id="vehicles" class="form-select required font-14"
							style="flex: 0.2 0 0">
							<option value="" selected>선택해주세요</option>
							<option value="taxi">택시</option>
							<option value="car">자차</option>
						</select>
					</div>

					<!-- 자주 사용하는 계좌 -->
					<div>
						<label>자주 사용하는 계좌</label> <br> <label> <select
							name="bank" id="bank" class="form-select required font-14"
							style="flex: 0.2 0 0">
								<option value="${user.bank}" selected>${user.bank}</option>
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
								<option value="" disabled>증권</option>
								<option value="NH투자증권">NH투자증권</option>
								<option value="미래에셋증권">미래에셋증권</option>
								<option value="삼성증권">삼성증권</option>
								<option value="신한투자증권">신한투자증권</option>
								<option value="SK증권">SK증권</option>
								<option value="유안타증권">유안타증권</option>
								<option value="유진투자증권">유진투자증권</option>
								<option value="한국투자증권">한국투자증권</option>
						</select>
						</label> <label><input type="number" name="accountNumber"
							class="form-control" placeholder="계좌번호 (-)제외"
							value="${user.accountNumber }"></label>
					</div>
					<br>
					<div class="text-center">
						<a href="/user/delete/${uid}" style="float: left;">회원탈퇴</a>
						<button type="submit" class="btn btn-main">수정하기</button>
					</div>
				</form>
			</section>
		</div>
	</div>
	<!-- /container -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/js/nicknameCheck.js"></script>
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/js/findAddr.js"></script>
	<script src="/js/profile.js"></script>
</body>
</html>