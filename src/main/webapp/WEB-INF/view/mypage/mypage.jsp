<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- //// MYPAGE PAGE - MAIN //// -->

<!DOCTYPE html>
<html lang="ko">
  <head>
    <!-- // Heading -->
    <%@ include file="../common/heading.jsp"%>
    <!-- // Heading -->
    <link rel="stylesheet" href="/css/calendar.css" />
    <title>Save. - Mypage</title>
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
          <!-- // mypage - calendar -->
          <div class="mypage-calendar-flex">
            <input type="hidden" value="${calendar.year }" name="Y" id="Y" />
            <input type="hidden" value="${calendar.month }" name="M" id="N" />
            <div class="mate-top-title">
              <div class="mypage-calendar-top-start">
                <button class="btn fa-solid fa-solid fa-angles-left" id="year-left-btn"></button>
                <button class="btn fa-solid fa-angle-left" id="left-btn"></button>
                <h4>${calendar.year }년 ${calendar.month }월</h4>
                <button class="btn fa-solid fa-angle-right" id="right-btn"></button>
                <button class="btn fa-solid fa-solid fa-angles-right" id="year-right-btn"></button>
              </div>
              <i class="fa-solid fa-list-ul calendar-list-icon" onclick="location.href='/mypage/cash'"></i>
            </div>
            <div>
              <ul class="weekdays">
                <li SUNDAY>일</li>
                <li>월</li>
                <li>화</li>
                <li>수</li>
                <li>목</li>
                <li>금</li>
                <li SATURDAY>토</li>
              </ul>
              <ol class="day-grid">
                <!-- // 시작 -->
                <c:forEach var="left" items="${calendar.leftDate }">
                  <li NOTE>${left }</li>
                </c:forEach>
                <c:forEach var="info" items="${calendar.infoList }">
                  <li ${info.value.type}>
                    <div class="date">${info.key}</div>
                    <div class="stamp" SaveSuccess="${info.value.stamp}"></div>
                    <div class="amount">
                      <c:if test="${info.value.amountPlus ne 0}">
                        <p class="plus">${info.value.plusFormat}</p>
                      </c:if>
                      <c:if test="${info.value.amountMinus ne 0}">
                        <p class="minus">${info.value.minusFormat}</p>
                      </c:if>
                    </div>
                  </li>
                </c:forEach>

                <c:forEach var="right" items="${calendar.rightDate }">
                  <li NOTE>${right }</li>
                </c:forEach>
                <!-- // 끝 -->
              </ol>
            </div>
          </div>
          <!-- // mypage - calendar -->
        </section>
      </div>
    </div>
    <script src="/js/mypage_calendar.js"></script>
    <script src="/js/mypage_nav.js"></script>
    <script src="/js/mypage_card.js"></script>
  </body>
</html>
