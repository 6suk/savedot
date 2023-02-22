<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/css/bootstrap.css" />
</head>
<body>
    <div class="container" style="max-width: 400px">
        <div class="py-5">
    
            <!-- 간편 회원가입 -->
            <div class="text-center">
                <h3>회원가입</h3>
                <div>
                    <div>카카오 계정으로 간편 로그인/회원가입</div>
                    <a dataype="kakao" href="#">
                        <svg width="48" height="48" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet"><g fill="none" fill-rule="evenodd"><path fill="#FFEB00" d="M0 24C0 10.745 10.745 0 24 0s24 10.745 24 24-10.745 24-24 24S0 37.255 0 24z"></path><path fill="#3C2929" d="M24 11.277c8.284 0 15 5.306 15 11.85 0 6.545-6.716 11.85-15 11.85-.92 0-1.822-.066-2.697-.191l-6.081 4.105a.43.43 0 0 1-.674-.476l1.414-5.282C11.777 31.031 9 27.335 9 23.127c0-6.544 6.716-11.85 15-11.85zm6.22 8.407c-.416 0-.718.297-.718.707v5.939c0 .41.289.686.718.686.41 0 .718-.295.718-.686v-1.932l.515-.526 1.885 2.57c.277.374.426.54.691.568.037.003.075.005.112.005.154 0 .66-.04.716-.563.038-.293-.137-.52-.348-.796l-2.043-2.675 1.727-1.743.176-.196c.234-.26.353-.39.353-.587.009-.422-.34-.652-.687-.661-.274 0-.457.15-.57.262l-2.528 2.634v-2.3c0-.422-.288-.706-.717-.706zm-9.364 0c-.56 0-.918.432-1.067.837l-2.083 5.517a.84.84 0 0 0-.065.315c0 .372.31.663.706.663.359 0 .578-.162.69-.51l.321-.97h2.999l.313.982c.111.335.34.498.7.498.367 0 .655-.273.655-.62 0-.056-.017-.196-.081-.369l-2.019-5.508c-.187-.53-.577-.835-1.069-.835zm-2.92.064h-4.452c-.417 0-.642.337-.642.654 0 .3.168.652.642.652h1.51v5.21c0 .464.274.752.716.752.443 0 .718-.288.718-.751v-5.21h1.508c.474 0 .643-.352.643-.653 0-.317-.225-.654-.643-.654zm7.554-.064c-.442 0-.717.287-.717.75v5.707c0 .497.28.794.75.794h2.674c.434 0 .677-.321.686-.627a.642.642 0 0 0-.17-.479c-.122-.13-.3-.2-.516-.2h-1.99v-5.195c0-.463-.274-.75-.717-.75zm-4.628 1.306l.008.01 1.083 3.265h-2.192L20.842 21a.015.015 0 0 1 .028 0z"></path></g></svg>
                    </a>
                </div>
            </div>
            <hr class="my-4">
               
               <!-- 일반 회원가입 폼 -->
            <form action="/user/join" method="post">
                <legend>필수입력사항</legend>
                
                <!-- 이름 -->
                <div>
                    <label>* 이름</label>
                    <br>
                    <input class="form-control" value="" name="uname" placeholder="ex) 홍길동" required>
                </div>
                
                <!-- 아이디 -->
                <div>
                    <label>* 아이디</label>
                    <div>
                        <label><input class="form-control" placeholder="아이디" name="id" value="" id="id" required></label>
                        <input name="CheckId" type="button" value="아이디 중복확인">
                        <input type="hidden" name="idDuplication" value="idUncheck">
                    </div>
                </div>
                
                <!-- 비밀번호 -->
                <div>
                    <label>* 비밀번호</label>
                    <p>영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.</p>
                    <input type="password" name="pwd" class="form-control" placeholder="비밀번호" value="" required>
                   </div>
                <div>
                    <label>* 비밀번호 확인</label>
                    <br>
                    <input type="password" class="form-control" value="" name="pwd2" placeholder="비밀번호 확인" required>
                </div>
    
                <!-- 닉네임 -->
                <div>
                    <label>* 닉네임</label>
                    <p>다른 유저와 겹치지 않도록 입력해주세요. (2~15자)</p>
                    <input class="form-control" value="" name="nickname" placeholder="별명 (2~15자)" required>
                </div>
    
                <!-- 이메일 -->
                <div>
                    <label>* 이메일</label>
                    <div>
                        <span class="email-input__local">
                            <input type="email" class="form-control" placeholder="ex) savedot@mulcam.com (@ 포함기입)" required name="email" value="">
                        </span>
                    </div>
                </div>

                <!-- 선택 항목 -->
                <legend>선택입력사항</legend>
                <!-- 연락처 -->
                <div>
                    <label>연락처</label>
                    <br>
                    <input class="form-control" value="" name="tel" placeholder="ex) 01012345678">
                </div>
                
                <!-- 생년월일 -->
                <div>
                    <label>생년월일</label>
                    <p>생년월일 8자리 숫자를 입력해주세요.</p>
                    <input class="form-control" value="" name="birthDate" placeholder="ex) 19990101">
                </div>

                <!-- 주소 -->
                <div>
                    <label>주소</label>
                    <br>
                    <label><input class="form-control" name="addr" type="text" id="sample4_postcode" placeholder="우편번호"></label>
                    <input type="button" onclick="" value="우편번호 찾기"><br>
                </div>
                <div>
                    <label>도로명주소(자동입력)</label>
                    <br>
                    
                    <input class="form-control" type="text" name="addr" id="sample4_roadAddress" placeholder="우편번호찾기를 이용해주세요" readonly><br>
                    <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
                    <span id="guide" style="color:#999;display:none"></span>
                    <input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
                </div>
                
                <!-- 급여 -->
                <div>
                    <label>월 급여 또는 용돈</label>
                    <br>
                    <input class="form-control" value="" name="pay" placeholder="급여나 용돈을 입력해주세요">
                </div>
    
                <!-- 출발지 -->
                <div>
                    <label>출발지</label>
                    <br>
                    <input class="form-control" value="" name="departures" placeholder="">
                </div>
    
                <!-- 목적지 -->
                <div>
                    <label>목적지</label>
                    <br>
                    <input class="form-control" value="" name="arrivals" placeholder="">
                </div>
    
                <!-- 교통수단 -->
                <div>
                    <label>교통수단</label>
                    <br>
                    <label><select class="form-control empty" name="vehicles">
                        <option value="" selected>선택해주세요</option>
                        <option value="taxi">택시</option>
                        <option value="car">자차</option>
                    </select></label>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">회원가입하기</button>
                </div> 
            </form>
        </div>
    </div> <!-- /container -->
    <script src="/js/idCheck.js"></script>      
    </body>
    </html>