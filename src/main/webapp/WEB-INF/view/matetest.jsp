<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/bootstrap.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/354b0809a2.js" crossorigin="anonymous"></script>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <link rel="stylesheet" href="/css/mainStyle.css" />
    <link rel="stylesheet" href="/css/card.css" />
    <link rel="stylesheet" href="/css/mate.css" />
    <script src="/js/card.js"></script>
    <script src="/js/mate.js"></script>
    <title>FinalProject</title>
  </head>
  <body>
    <!-- TOP -->
    <nav class="navbar navbar-expand navbar-light fixed-top">
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

    <div class="container-nonaside" onload="getUserLocation()">
      <!-- content -->
      <section>
        <div class="content-title">
          <div class="form-tag" id="matecategory">
            <input type="radio" name="category" id="mate" value="0" />
            <label for="mate" id="category">조각 메이트</label>
            <input type="radio" name="category" id="ott" value="1" />
            <label for="ott" id="category">OTT 메이트</label>
          </div>
          <input type="button" id="sendbtn" class="btn btn-main main" value="등록하기" />
        </div>

        <div class="writebox flex-lg-row">
          <div class="imgbox">
            <label class="imgbox-img label" id="label" for="input">
              <div class="inner" id="inner">
                <i class="fa-solid fa-camera"></i>
                <span class="graytxt pt-2">사진 올리기</span>
                <span class="l-graytxt font-12">(최대 5장)</span>
              </div>
            </label>
            <input id="input" class="input" accept="image/*" type="file" multiple="true" hidden="true" name="img" />
            <div class="preview" id="preview" hidden></div>
          </div>
          <form id="mateform" class="inputbox">
          <div class="inputbox">
            <input type="text" class="form-control text" placeholder="지역*" name="area" />
            <input class="form-control text" type="text" name="title" placeholder="제목*" />
            <input class="form-control text" type="number" name="price1" placeholder="구매가격*" />
            <input class="form-control text" type="number" name="price2" placeholder="판매가격*" />
            <textarea class="form-control" name="content" cols="30" rows="10" placeholder="본문"></textarea>
          </div>
        </div>
      </form>
      </section>
    </div>
  </body>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
  <script src="/js/upload.js"></script>
</html>



