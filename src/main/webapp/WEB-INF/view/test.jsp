<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/bootstrap.css" />
    <!-- <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    /> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script
      src="https://kit.fontawesome.com/354b0809a2.js"
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <link rel="stylesheet" href="/css/mainStyle.css" />
    <link rel="stylesheet" href="/css/card.css" />
    <script src="/js/card.js"></script>
    <title>FinalProject</title>
  </head>
  <body>
    <!-- TOP -->
    <nav class="navbar navbar-expand navbar-light fixed-top">
      <!-- <div class="container-fluid"> -->
      <div class="container-lg justify-content-lg-between">
        <ul class="navbar-nav">
          <a class="navbar-brand" href="#">
            <img src="/img/logo.png" class="logo" />
          </a>
          <li class="nav-item">
            <a class="nav-link active" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">조각메이트</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">챌린지</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">알뜰정보</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">얼마벌었지</a>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link d-none" href="#">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link d-none" href="#">회원가입</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">지출등록</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">마이페이지</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">로그아웃</a>
          </li>
        </ul>
      </div>
    </nav>
    <!-- TOP -->

    <div class="container-lg">
      <div class="row">
        <!-- // ASIDE -->
        <aside class="col-md-2">
          <div class="aside-img">
            <!-- <img
              width="80%"
              src="https://emojipedia-us.s3.amazonaws.com/source/microsoft-teams/337/blue-heart_1f499.png"
            /> -->
          </div>
          <div class="aside-info border-bottom">
            <h4>짠돌쓰</h4>
            <dl>
              <dt>챌린지</dt>
              <dd>
                <a href="#">12</a>
              </dd>
              <dt>조각메이트</dt>
              <dd><a href="#">2</a></dd>
            </dl>
            <div class="btn btn-border font-12">정보수정</div>
          </div>
          <div class="multiple-btn pt-3 pb-5">
            <a class="icon-btn" href="#">
              <i class="fa-solid fa-certificate mb-2"></i>
              <p>챌린지</p>
            </a>
            <a class="icon-btn" href="#">
              <i class="fa-solid fa-circle-plus mb-2"></i>
              <p>지출등록</p>
            </a>
            <a class="icon-btn" href="#">
              <i class="fa-solid fa-share-nodes mb-2"></i>
              <p>공유하기</p>
            </a>
          </div>
        </aside>
        <!-- ASIDE// -->

        <!-- content -->
        <section class="col">
          <!-- // mypage - card -->
          <div class="row viewcard-group">
            <div class="col-lg viewcard mypage-card" id="card1">
              <div class="mypage-card-left">
                <p class="mypage-card-toptxt" id="card1-top"></p>
                <div class="emojitxt">
                  <img src="" id="card1-emoji" />
                  <span id="card1-emoji-txt"></span>
                </div>
                <p class="mypage-card-bottomtxt" id="card1-bottom"></p>
              </div>
              <div class="mypage-card-right" id="card1-right">주간</div>
            </div>
            <!-- /////////// -->
            <div class="col-lg viewcard mypage-card">
              <div class="mypage-card-left">
                <p class="mypage-card-toptxt">조각메이트로 오늘 아낀 비용</p>
                <div class="emojitxt">
                  <img
                    src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/apple/325/cooking_1f373.png"
                  />
                  <span>0.5 계란 한 판</span>
                </div>
                <p class="mypage-card-bottomtxt">약 0,000원을 아꼈어요!</p>
              </div>
              <div class="mypage-card-right">주간</div>
            </div>
          </div>
          <!-- mypage - card // -->
        </section>
      </div>
    </div>
  </body>
</html>


