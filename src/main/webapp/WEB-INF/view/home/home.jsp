<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- //// MATE DETAIL PAGE //// -->

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
	<div class="header-wrap">
	<%@ include file="../common/top.jsp"%>
	</div>
	<!-- TOP -->

	<!-- << 슬라이드 시작 -->
    <!-- <div id="demo" class="carousel slide" data-bs-ride="carousel"> -->
    <div id="main" class="carousel slide main-slide carousel-fade" data-bs-ride="carousel" data-bs-interval="3000">
      <!-- Indicators/dots -->
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#main" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#main" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#main" data-bs-slide-to="2"></button>
      </div>

      <div class="carousel-inner">
        <!-- item 시작 -->
        <div
          class="carousel-item main-slide-item active bg1"
          NAV-COLOR="ver1"
          onclick="location.href='/challenge/choice'"
        >
          <div class="top">
            <div class="obj-group">
              <object
                type="image/svg+xml"
                data="/emoji/clock-dynamic-premium.svg"
                class="d-block main-slide-img obj-ver1"
              >
                <img src="/emoji/clock-dynamic-premium.png" />
              </object>
              <object
                type="image/svg+xml"
                data="/emoji/tea-cup-dynamic-premium.svg"
                class="d-block main-slide-img obj-ver2"
              >
                <img src="/emoji/tea-cup-dynamic-premium.png" />
              </object>
            </div>
          </div>
          <div class="desc ver1">
            <div class="desc-top point">Save. Challenge</div>
            <div class="middle">
              <span class="point">챌린지</span>에 참여해<br />
              월 사용액을 <span class="point">감소</span>시켜보세요!
            </div>
            <div class="bottom">
              취미와 여가, 그리고 카페와 간식 부분에서 순간적인 욕구를 억제한다면,<br />
              전월 대비 월 사용액이 17% 감소하는 결과를 보였습니다.
            </div>
          </div>
        </div>
        <!-- item 끝 -->

        <!-- item 시작 -->
        <div class="carousel-item main-slide-item bg2" NAV-COLOR="ver2" onclick="location.href='/mate/list'">
          <div class="top">
            <object
              type="image/svg+xml"
              data="/emoji/chat-iso-color.svg"
              class="d-block main-slide-img"
            >
              <img src="/emoji/chat-iso-color.png" />
            </object>
          </div>
          <div class="desc ver2">
            <div class="desc-top point">Save. PieceMate</div>
            <div class="middle">
              <span class="point">조각메이트</span>를 찾아 전략적이고<br />
              실속있는 소비를 해보세요!
            </div>
            <div class="bottom">
              더 나은 소비 생활을 위해 필요한 만큼만 구매하고,<br />
              함께 나누어 소비하는 전략적 소비자가 되어보세요!
            </div>
          </div>
        </div>
        <!-- item 끝 -->

        <!-- item 시작 -->
        <div class="carousel-item main-slide-item bg0" NAV-COLOR="ver0" onclick="location.href='/mypage/main'">
          <div class="top">
            <object
              type="image/svg+xml"
              data="/emoji/chart-dynamic-color.svg"
              class="d-block main-slide-img"
            >
              <img src="/emoji/chart-dynamic-color.png" />
            </object>
          </div>
          <div class="desc ver0">
            <div class="desc-top point">Save. Statistics</div>
            <div class="middle"><span class="point">지난달의 나</span>와 이번 달의 나를<br />비교해볼 수 있어요!</div>
            <div class="bottom">이번달 얼마나 아꼈을까?<br />지난달의 나와 이번달의 나를 비교해볼 수 있어요!</div>
          </div>
        </div>
        <!-- item 끝 -->
      </div>

      <!-- Left and right controls/icons -->
      <button class="carousel-control-prev" type="button" data-bs-target="#main" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#main" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
      </button>
    </div>
    <!-- 슬라이드 끝 >> -->
    <div class="container" id="container">
      <!-- content -->
      <!-- content 1 -->
      <div class="main-middle-flex">
        <div class="main-middel-left">
          <div class="main-middel-left-content">
            <object
              type="image/svg+xml"
              data="/emoji/fire-dynamic-color.svg"
              class="main-middel-left-img"
            >
              <img src="/emoji/fire-dynamic-color.png" />
            </object>
          </div>
        </div>
        <div class="main-middle-right">
          <div class="main-middle-right-content">
            <div class="middle-top">Save. 챌린지를 시작해보세요!</div>
            <div class="middle-title">
              Save. 유저들은 <span class="bold">챌린지를 통해</span><br />
              <span class="bold">얼마나</span> 아꼈을까?
            </div>
            <div class="middle-content">
              <ul>
                <li>한달 평균 챌린지로 아낀 비용</li>
                <li><span count-top="${home.challengeMonth }" class="counter">0</span><span class="unit">원</span></li>
              </ul>
              <ul>
                <li>하루 평균 챌린지로 아낀 비용</li>
                <li><span count-top="${home.challengeToday }">0</span><span class="unit">원</span></li>
              </ul>
              <ul>
                <li>가장 참여율이 높은 나이대</li>
                <li><span count-top="${home.challengeAge }">0</span><span class="unit">대</span></li>
              </ul>
              <ul>
                <li id="count-top">가장 많이 참여한 챌린지</li>
                <li>${home.challengeType }</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!-- content 1 -->
      <!-- content 2 -->
      <div class="main-middle-flex reverse">
        <div class="main-middel-left">
          <div class="main-middel-left-content">
            <object
              type="image/svg+xml"
              data="/emoji/puzzle-dynamic-color.svg"
              class="main-middel-left-img"
            >
              <img src="/emoji/puzzle-dynamic-color.png" />
            </object>
          </div>
        </div>
        <div class="main-middle-right">
          <div class="main-middle-right-content">
            <div class="middle-top">Save. 조각 메이트를 시작해보세요!</div>
            <div class="middle-title">
              조각 메이트를 찾아 <span class="bold">전략적이고<br />
              실속있는</span> 소비를 해보세요!
            </div>
            <div class="middle-content">
              <ul>
                <li>평균 세이브 금액</li>
                <li><span count-bottom="${home.matePrice }">0</span><span class="unit">원</span></li>
              </ul>
              <ul>
                <li id="count-bottom">구매가 대비 평균 세이브율</li>
                <li><span count-bottom="${home.matePercentage }">0</span><span class="unit">%</span></li>
              </ul>
              <ul>
                <li>거래가 가장 많이 올라오는 요일</li>
                <li>${home.mateTopDayofWeek }</li>
              </ul>
              <ul>
                <li>만남 거래가 가장 활발한 지역</li>
                <li>${home.mateTopArea }</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!-- content 2 -->
      <!-- content -->
    </div>
  </body>
  <script src="/js/savedot_main.js"></script>
</html>

