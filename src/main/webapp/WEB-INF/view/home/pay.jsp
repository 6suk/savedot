<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    </div>
  </body>
  <script src="/js/pay_check.js"></script>
</html>
