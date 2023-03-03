<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- // Heading -->
	<%@ include file="../common/heading.jsp"%>
	<!-- // Heading -->
    <title></title>
    <style>
        .id_ok {
            color: #008000;
            display: none;
        }
        
        .id_already {
            color: #6A82FB;
            display: none;
        }
        
        .nickname_ok {
            color: #008000;
            display: none;
        }
        
        .nickname_already {
            color: #6A82FB;
            display: none;
        }
        </style>
	<link rel="stylesheet" href="/css/loginForm.css">
</head>
<body>
 	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->

	<div class="container">
		<!-- Heading -->
		<h1>Save.</h1>

		<!-- Links -->
		<ul class="links">
			<li><a href="/user/join" id="signup">회원가입</a></li>
		</ul>
		<!-- 회원가입 -->
		<form action="/user/join" method="post">

			<!-- 이름 -->
			<div class="first-input input__block first-input__block">
				<input value="" name="uname" placeholder="이름*" required>
			</div>

			<!-- id -->
			<div class="input__block">
				<input type="text" id="id" name="id" oninput="checkId()"
					placeholder="아이디*" value="" required>
				<!-- id ajax 중복체크 -->
				<span class="id_ok">사용 가능한 아이디입니다.</span> <span class="id_already">이미
					사용중인 아이디입니다.</span>
			</div>

			<!-- password -->
			<div class="input__block">
				<input type="password" name="pwd" placeholder="비밀번호*" id="pwd"
					required />
			</div>

			<!-- repeat password -->
			<div class="input__block">
				<input type="password" name="pwd2" placeholder="비밀번호 확인*" id="pwd2"
					required />
			</div>

			<!-- 닉네임 -->
			<div class="input__block">
				<input type="text" id="nickname" name="nickname"
					oninput="checkNickname()" placeholder="닉네임*" value="" required>
				<!-- nickname ajax 중복체크 -->
				<span class="nickname_ok">사용 가능한 닉네임입니다.</span> <span
					class="nickname_already">이미 사용중인 닉네임입니다.</span>
			</div>

			<!-- 이메일 -->
			<div class="input__block">
				<input type="email" placeholder="이메일*  ex) savedot@mulcam.com"
					required name="email" value="">
			</div>

			<!-- 생년월일 -->
			<div class="input__block">
				<input type="number" value="" name="birthDate"
					placeholder="생년월일*  ex) 19990101" required>
			</div>

			<!-- 선택 항목 -->
			<legend>선택입력사항</legend>
			<!-- 연락처 -->
			<div class="input__block">
				<input type="number" value="" name="tel" placeholder="연락처 (-)제외">
			</div>

			<!-- 주소 -->
			<div class="input__block">
				<p>'우편번호 찾기'를 클릭해 주세요.</p>
				<input type="text" id="postcode" name="postcode" placeholder="우편번호"
					readonly>
				<button type="button" onclick="daumPostcode()" value="">우편번호
					찾기</button>
				<input type="text" name="addr" id="addr" placeholder="주소" value=""
					readonly><br> <input type="text" name="detailAddr"
					id="detailAddr" placeholder="상세주소" value=""><br> <input
					type="hidden" id="extraAddr" placeholder="참고항목">
			</div>

			<!-- 급여 -->
			<div class="input__block">
				<input type="number" value="" name="pay" placeholder="월 급여 or 용돈">
			</div>

			<!-- 출퇴근 시간 -->
			<div class="input__block">
				<input type="time" class="input" value="" name="workIn"
					placeholder="출근 시간"> <input type="time" class="input"
					value="" name="workOut" placeholder="퇴근 시간">
			</div>

			<!-- 출발지 -->
			<div class="input__block">
				<input value="" name="departures" placeholder="출발지">
			</div>

			<!-- 목적지 -->
			<div class="input__block">
				<input value="" name="arrivals" placeholder="목적지">
			</div>

			<!-- 교통수단 -->
			<div class="input__block">
				<label>교통수단</label><br> <select name="vehicles" id="vehicles"
					class="form-select required font-14" style="flex: 0.2 0 0">
					<option value="" selected>선택해주세요</option>
					<option value="taxi">택시</option>
					<option value="car">자차</option>
				</select>
			</div>

			<!-- 자주 사용하는 계좌 -->
			<div class="input__block">
				<label>자주 사용하는 계좌</label> <br> <label> <select
					name="bank" id="bank" class="form-select required font-14"
					style="flex: 0.2 0 0">
						<option value="" selected>은행</option>
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
					placeholder="계좌번호 (-)제외" value=""></label>
				
				<!-- 지역코드, oauth 임시 -->
				<div class="input__block">
					<input type="hidden" value="" name="code" placeholder="">
					<input type="hidden" value="" name="oauth" placeholder="">
				</div>
				
			</div>
			<br>
			<!-- sign in button -->
			<button type="submit" class="signin__btn">가입하기</button>
		</form>
	</div>
	<!-- /container -->
    <script src="/js/loginForm.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/js/idCheck.js"></script>
	<script src="/js/nicknameCheck.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/js/findAddr.js"></script>
</body>
</html>