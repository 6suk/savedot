<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!-- //// MYPAGE TEST PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
  <head>
    <!-- // Heading -->
    <%@ include file="../common/heading.jsp"%>
    <!-- // Heading -->
    <title>FinalProject</title>
  </head>
  <body>
    <!-- //TOP -->
    <%@ include file="../common/top.jsp"%>
    <!-- TOP// -->

    <div class="container-lg">
      <div class="row">
        <!-- // ASIDE -->
        <%@ include file="../common/aside.jsp"%>
        <!-- ASIDE// -->

        <!-- content -->
        <section class="col">
          <!-- // mypage - card -->
          <div class="row viewcard-group">
            <!-- /////////// -->
            <div class="col-lg viewcard mypage-card" id="card1">
              <div class="mypage-card-left">
                <p class="mypage-card-toptxt" id="card1-top"></p>
                <div class="emojitxt"><img src="" id="card1-emoji" /> <span id="card1-emoji-txt"></span></div>
                <p class="mypage-card-bottomtxt" id="card1-bottom"></p>
              </div>
              <div class="mypage-card-right" id="card1-right"></div>
            </div>
            <!-- /////////// -->
            <div class="col-lg viewcard mypage-card" id="card2">
              <div class="mypage-card-left">
                <p class="mypage-card-toptxt" id="card2-top"></p>
                <div class="emojitxt"><img src="" id="card2-emoji" /> <span id="card2-emoji-txt"></span></div>
                <p class="mypage-card-bottomtxt" id="card2-bottom"></p>
              </div>
              <div class="mypage-card-right" id="card2-right"></div>
            </div>
            <!-- /////////// -->
          </div>
          <!-- mypage - card // -->
        </section>
      </div>
    </div>
    <script src="/js/mypage_card.js"></script>
  </body>
</html>
