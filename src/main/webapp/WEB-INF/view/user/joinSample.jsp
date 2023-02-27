<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- // Heading -->
	<%@ include file="../common/heading.jsp"%>
	<!-- // Heading -->
    <title></title>
	<link rel="stylesheet" href="/css/loginForm.css">
</head>
<body>
 	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->
	 
    <div class="container">
        <!-- Heading -->
        <h1>Save dot</h1>
        
        <!-- Links -->
        <ul class="links">
          <li>
            <a href="/user/joinSample" id="signup">회원가입</a>
          </li>
        </ul>
        <!-- 회원가입 -->
        <form action="/user/join" method="post">
        
       		<!-- 이름 -->
			<div>
				<input class="input repeat__password" value="" name="uname" placeholder="이름" required>
			</div>
			
            <!-- id -->
            <div class="first-input input__block first-input__block">
	            <input type="text" id="id" name="id" class="input" oninput="checkId()" placeholder="아이디" value="" required>
				
				<!-- id ajax 중복체크 -->
				<span class="id_ok">사용 가능한 아이디입니다.</span>
				<span class="id_already">이미 사용중인 아이디입니다.</span>
            </div>
            
            <!-- password -->
            <div class="input__block">
                <input type="password" name="pwd" placeholder="비밀번호" class="input" id="password" required />
            </div>
            
            <!-- repeat password -->
            <div class="input__block">
                <input type="password" name="pwd2" placeholder="비밀번호 확인" class="input repeat__password" id="repeat__password" required   />
            </div>
            
            <!-- 닉네임 -->
			<div>
				<input type="text" id="nickname" name="nickname" class="input repeat__password" oninput="checkNickname()" placeholder="닉네임" value="" required>
				<!-- nickname ajax 중복체크 -->
				<span class="nickname_ok">사용 가능한 닉네임입니다.</span>
				<span class="nickname_already">누군가 이 닉네임을 사용하고 있어요.</span>
			</div>
			
			<!-- 이메일 -->
			<div>
				<input type="email" class="input repeat__password"
				placeholder="이메일  ex) savedot@mulcam.com (@ 포함기입)" required
				name="email" value="">
			</div>
			
			<!-- 선택 항목 -->
			<legend>선택입력사항</legend>
			<!-- 연락처 -->
			<div>
				<label>연락처</label> <br> <input type="number"
					class="input repeat__password" value="" name="tel"
					placeholder="ex) 01012345678">
			</div>

			<!-- 생년월일 -->
			<div>
				<label>생년월일</label>
				<p>생년월일 8자리 숫자를 입력해주세요.</p>
				<input type="number" class="input repeat__password" value="" name="birthDate"
					placeholder="ex) 19990101">
			</div>

			<!-- 주소 -->
			<div>
				<label>주소</label>
				<p>우편번호를 먼저 찾아주세요.</p>
				<label><input type="text" class="input repeat__password" id="postcode" placeholder="우편번호"></label>
				<input type="button" onclick="daumPostcode()" value="우편번호 찾기"><br>
				<input type="text" class="input repeat__password" name="addr" id="addr" placeholder="주소" value=""><br>
				<input type="text" class="input repeat__password" name="detailAddr" id="detailAddr" placeholder="상세주소" value="">
				<input type="text" class="input repeat__password" id="extraAddr" placeholder="참고항목">
			</div>

			<!-- 급여 -->
			<div>
				<label>월 급여 또는 용돈</label> <br> <input type="number"
					class="input repeat__password" value="" name="pay"
					placeholder="급여나 용돈을 입력해주세요">
			</div>

			<!-- 출발지 -->
			<div>
				<label>출발지</label> <br> <input class="input repeat__password" value=""
					name="departures" placeholder="">
			</div>

			<!-- 목적지 -->
			<div>
				<label>목적지</label> <br> <input class="input repeat__password" value=""
					name="arrivals" placeholder="">
			</div>

			<!-- 교통수단 -->
			<div>
				<label>교통수단</label> <br> <select name="vehicles" id="vehicles"
					class="form-select required font-14" style="flex: 0.2 0 0">
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
					class="input repeat__password" placeholder="계좌번호 (-)제외" value=""></label>
			</div>

			<!-- 내 지역 -->
			<div>
				<label>지역</label> <br> <select name="code" id="code"
					class="form-select required font-14" style="flex: 0.2 0 0">
					<option value="" selected>지역</option>
					<option value="서울특별시">서울특별시</option>
					<option value="부산광역시">부산광역시</option>
					<option value="대구광역시">대구광역시</option>
					<option value="인천광역시">인천광역시</option>
					<option value="광주광역시">광주광역시</option>
					<option value="대전광역시">대전광역시</option>
					<option value="울산광역시">울산광역시</option>
					<option value="경기도">경기도</option>
					<option value="강원도">강원도</option>
					<option value="충청북도">충청북도</option>
					<option value="충청남도">충청남도</option>
					<option value="전라북도">전라북도</option>
					<option value="전라남도">전라남도</option>
					<option value="경상북도">경상북도</option>
					<option value="경상남도">경상남도</option>
					<option value="제주특별자치도">제주특별자치도</option>
				</select>
			</div>
            
            <!-- sign in button -->
            <button type="submit" class="signin__btn">
                가입하기
            </button>
        </form>
        
        <!-- separator -->
        <div class="separator">
          <p>OR</p>
        </div>
        
        <!-- 카카오 로그인 -->
        <button class="kakao__btn">
          <i class="fa fa-kakao"></i>
          카카오계정으로 로그인
        </button>
      </div>
      
      <footer>
        <p>
          <i class="fa fa-heart"></i> 
          <i class="fa fa-heart"></i> 
          <i class="fa fa-heart"></i> 
        </p>
      </footer>
      <!-- /container -->
    <script src="/js/loginForm.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="/js/idCheck.js"></script>
	<script src="/js/nicknameCheck.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function daumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("extraAddr").value = extraAddr;
	                
	                } else {
	                    document.getElementById("extraAddr").value = '';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("addr").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("detailAddr").focus();
	            }
	        }).open();
	    }
	</script>
</body>
</html>
