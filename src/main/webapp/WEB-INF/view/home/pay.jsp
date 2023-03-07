<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- //// MATE WRITE PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
  <head>
    <!-- // Heading -->
    <%@ include file="../common/heading.jsp"%>
    <!-- // Heading -->
    <title>SAVE. : 얼마나 벌었지?</title>
  </head>
  <body>
    <!-- TOP -->
    <%@ include file="../common/top.jsp"%>
    <!-- TOP -->

    <div class="container-nonaside">
      <!-- content -->
      <section class="pay-check-container">
        <div class="pay-check-title-flex">
          <div class="challenge-choice-title">
            오늘 출근해서 <br /><b><span id="nowPay">0</span>원</b> 벌고<br />지금까지
            <b><span id="sumNowExpense">0</span>원</b> 썼어요.<br />
            순이익은 <b class="maintxt"><span class="maintxt" id="total">0</span>원</b>이에요!
          </div>
          <div class="pay-check-btn-group">
            <button class="btn btn-main" onclick="location.href='/cash/write'">지출 등록</button>
            <button class="btn btn-sub" onclick="location.href='/user/update/${user.uid}'">급여 변경</button>
          </div>
        </div>
        <div class="pay-check-progress">
          <div class="pay-check-label-group" id="label-group">
            <div class="pay-check-label" id="label" num="0"></div>
          </div>
          <div id="myProgress">
            <div id="myBar"></div>
          </div>
          <div class="pay-time">
            <p>${user.workIn}</p>
            <p>${user.workOut}</p>
          </div>
        </div>
      </section>
      <!-- // update Modal -->
      <div class="modalBg" id="modal_update">
        <div class="requestModal savedot-modal question">
          <div class="question-title" style="text-align: center">
            <h5>한달 급여와 출퇴근 시간을 기입하고<br />얼마벌었지를 이용해보세요!</h5>
            <div class="question-btn">
              <input
                type="button"
                class="btn btn-main full font-14"
                onclick="location.href='/user/update'"
                value="정보수정하러가기"
              />
              <input
                type="button"
                class="closebtn btn btn-sub full font-14"
                onClick="history.back()"
                value="돌아가기"
              />
            </div>
          </div>
        </div>
      </div>
      <!-- update Modal // -->

      <!-- // Login Modal -->
      <div class="modalBg" id="modal_login">
        <div class="requestModal savedot-modal question">
          <div class="question-title">
            <h5>로그인 후 얼마벌었지를 이용해보세요!</h5>
            <div class="question-btn">
              <input
                type="button"
                class="btn btn-main full font-14"
                onclick="location.href='/user/login'"
                value="로그인하러가기"
              />
              <input
                type="button"
                class="closebtn btn btn-sub full font-14"
                onClick="history.back()"
                value="돌아가기"
              />
            </div>
          </div>
        </div>
      </div>
      <!-- Login Modal // -->
    </div>
  </body>
  <script src="/js/pay_check.js"></script>
</html>
