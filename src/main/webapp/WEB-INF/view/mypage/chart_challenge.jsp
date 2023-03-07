<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- //// MYPAGE PAGE - MAIN //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<link rel="stylesheet" href="/css/calendar.css" />
<title>SAVE. : 마이페이지 - 통계</title>
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
        <section class="col-md-8 mypage-cash">
          <!-- // mypage - card -->
          <div class="row viewcard-group">
            <!-- /////////// -->
            <div class="col-lg viewcard mypage-card-chart">
              <div class="mypage-card-chart-top">
                <p class="mypage-card-chart-toptxt">한달전의 나보다</p>
                <p class="mypage-card-chart-toptxt" id="user-date">2022-01-25 기준</p>
              </div>
              <div class="emojitxt">
                <img src="/emoji/pleading-face_1f97a.png" class="hide" id="user-ver1" />
                <img
                  src="/emoji/smiling-face-with-sunglasses_1f60e.png"
                  class="hide"
                  id="user-ver2"
                />
                <span id="user-price">112,000원 더 아꼈어요!</span>
              </div>
              <p class="mypage-card-chart-bottomtxt" id="user-bottom">남은 시간동안 조금만 더 아껴보아요!</p>
            </div>
            <!-- /////////// -->
            <!-- /////////// -->
            <div class="col-lg viewcard mypage-card-chart">
              <div class="mypage-card-chart-top">
                <p class="mypage-card-chart-toptxt" id="all-top">또래 유저들보다</p>
                <p class="mypage-card-chart-toptxt" id="all-date">2022-02-25 기준</p>
              </div>
              <div class="emojitxt">
                <img src="/emoji/cloud_2601-fe0f.png" class="hide" id="all-ver1" />
                <img src="/emoji/sun_2600-fe0f.png" class="hide" id="all-ver2" />
                <span id="all-price">112,000원 더 썼어요!</span>
              </div>
              <p class="mypage-card-chart-bottomtxt" id="all-bottom">남은 시간동안 조금만 더 아껴보아요!</p>
            </div>
            <!-- /////////// -->
          </div>
          <!-- mypage - card // -->
          <div>
            <div class="content-title pb-3 pt-3">
              <div>
                <h4 id="challenge_title">6개월간 챌린지로 아낀 비용</h4>
                <p class="font-15 l-graytxt pt-1" id="date_term"></p>
              </div>
              <div>
                <input
                  type="button"
                  class="btn btn-sub main sm font-14"
                  value="내 또래 다른 유저들은?"
                  data-bs-toggle="tooltip"
							data-bs-html="true" data-bs-placement="top"
							title="회원정보에 등록된 생년월일을 기준으로<br>
                같은 나이대의 회원들은 오늘까지 얼마나 절약했는지 확인해보세요!" 
                  id="addAllUser"
                />
                <input type="button" class="btn btn-sub main sm font-14 hide" value="n개월 전 나는?" id="addOneUser" />
                <input type="button" class="btn btn-main main sm font-14" value="6개월간 진행한 챌린지" id="addType" data-bs-toggle="tooltip"
							data-bs-html="true" data-bs-placement="top"
							title="6개월간 어떤 챌린지를 많이 진행했는지<br>한 눈에 볼 수 있어요!" />
              </div>
            </div>
            <canvas id="CashChart"></canvas>
          </div>
        </section>
        <!-- content -->
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.0/dist/chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
    <script src="/js/mypage_challenge_chart.js"></script>
    <script src="/js/mypage_nav.js"></script>
    <script>
      var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
      var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
      });
    </script>
  </body>
</html>

