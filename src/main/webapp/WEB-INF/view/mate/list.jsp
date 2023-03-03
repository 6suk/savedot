<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
    <%@ include file="../common/top.jsp"%>
    <!-- TOP -->

    <div class="container-lg">
      <div class="row">
        <!-- // ASIDE -->
        <aside class="search-bar">
          <div class="mypage-cash-top-end">
            <input id="query-search" class="form-control mate-search text" type="text" placeholder="검색하기" />
            <button class="query-search" id="query-search-btn" style="border-left: 0">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
          <form action="#" method="GET" id="mate-search-form">
            <div class="mate-search-select">
              <!-- // 카테고리 1 -->
              <button class="search-top-button" data-bs-toggle="collapse" data-bs-target="#category-search" search-item>
                <div class="search-top-title-group">
                  <span class="fa-solid fa-sort-down" down></span>
                  <span class="fa-solid fa-sort-up hide" up></span> 카테고리
                </div>
              </button>
              <div id="category-search" class="collapse show mate-search">
                <ul>
                  <li>
                    <input
                      checked
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="category"
                      id="mate"
                      value="0"
                    />
                    <label for="mate">조각 메이트</label>
                  </li>
                  <li>
                    <input
                      checked
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="category"
                      id="ott"
                      value="1"
                    />
                    <label for="ott">OTT 메이트</label>
                  </li>
                </ul>
              </div>
              <!-- 카테고리 1 // -->
              <!-- // 카테고리 2 -->
              <button class="search-top-button" data-bs-toggle="collapse" data-bs-target="#state-search" search-item>
                <div class="search-top-title-group">
                  <span class="fa-solid fa-sort-down" down></span>
                  <span class="fa-solid fa-sort-up hide" up></span> 상태
                </div>
              </button>
              <div id="state-search" class="collapse show mate-search">
                <ul>
                  <li>
                    <input checked type="radio" class="form-check-input mate-search" name="state" id="all" value="A" />
                    <label for="all">전체</label>
                  </li>
                  <li>
                    <input type="radio" class="form-check-input mate-search" name="state" id="progress" value="0" />
                    <label for="progress">진행 중</label>
                  </li>
                  <li>
                    <input type="radio" class="form-check-input mate-search" name="state" id="end" value="1" />
                    <label for="end">판매 완료</label>
                  </li>
                </ul>
              </div>
              <!-- // 카테고리 2 -->
              <!-- // 카테고리 3 -->
              <button class="search-top-button" data-bs-toggle="collapse" data-bs-target="#area--search" search-item>
                <div class="search-top-title-group">
                  <span class="fa-solid fa-sort-down" down></span>
                  <span class="fa-solid fa-sort-up hide" up></span> 지역 검색
                </div>
              </button>
              <div id="area--search" class="mate-search collapse">
                <ul id="area-search">
                  <li>
                    <input
                      checked
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="00"
                      value="00"
                      area-all
                    />
                    <label for="00">전체</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="11"
                      value="11"
                    />
                    <label for="11">서울특별시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="26"
                      value="26"
                    />
                    <label for="26">부산광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="27"
                      value="27"
                    />
                    <label for="27">대구광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="28"
                      value="28"
                    />
                    <label for="28">인천광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="29"
                      value="29"
                    />
                    <label for="29">광주광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="30"
                      value="30"
                    />
                    <label for="30">대전광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="31"
                      value="31"
                    />
                    <label for="31">울산광역시</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="41"
                      value="41"
                    />
                    <label for="41">경기도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="42"
                      value="42"
                    />
                    <label for="42">강원도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="43"
                      value="43"
                    />
                    <label for="43">충청북도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="44"
                      value="44"
                    />
                    <label for="44">충청남도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="45"
                      value="45"
                    />
                    <label for="45">전라북도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="46"
                      value="46"
                    />
                    <label for="46">전라남도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="47"
                      value="47"
                    />
                    <label for="47">경상북도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="48"
                      value="48"
                    />
                    <label for="48">경상남도</label>
                  </li>
                  <li>
                    <input
                      area-ele
                      type="checkbox"
                      class="form-check-input mate-search"
                      name="area"
                      id="50"
                      value="50"
                    />
                    <label for="50">제주특별자치도</label>
                  </li>
                </ul>
              </div>
              <div id="area--search" class="collapse">
                <ul id="area-search"></ul>
              </div>
              <!-- // 카테고리 3 -->
            </div>
          </form>
        </aside>
        <!-- ASIDE// -->

        <!-- content -->
        <section class="col-md">
          <div class="mate-top-title">
            <h4 id="search-title"></h4>
            <button class="btn btn-main mdi" onclick="location.href='/mate/write'">글작성하기</button>
          </div>
          <div class="mate-flex-view">
            <c:forEach var="mate" items="${mate }">
              <!-- item -->
              <div class="mate-flex-item" mid="${mate.mid }">
                <div class="mate-top-info">
                  <div class="top">
                    <p class="mate-card-tag position" ${mate.stateName }>
                      ${mate.positonApplyNum } / ${mate.positionNum }
                    </p>
                    <!--  좋아요 기능 (비로그인시 하트모양 안보임) -->
                    <c:if test="${user.uid ne null}">
                    <c:set var="likeType" value="0"></c:set>
                    <c:set var="likeUrl" value="likePress(${mate.mid})" ></c:set>
                    <c:forEach var="like" items="${likelist }">
                      <c:if test="${like.mid eq mate.mid}">
                        <c:set var="likeType" value="1"></c:set>
                        <c:set var="likeUrl" value="likeDel(${mate.mid})"></c:set>
                      </c:if>
                    </c:forEach>
                    <span class="" LIKE="${likeType}" id="${mate.mid}"></span>
                    </c:if>
                    <!--  좋아요 기능 끝 -->
                  </div>
              
                  <c:set
                    var="thumpath"
                    value="/savedot/upload/${mate.thum.saveDate }/${mate.thum.id }${mate.thum.ext }"
                  ></c:set>
                  <div class="thum">
                    <img src="${mate.thum eq null ? '' : thumpath }" />
                  </div>
                </div>
                <div class="mate-info-group">
                  <div class="mate-middle-info">
                    <div class="top">
                      <p>${mate.user.nickname }</p>
                      <div class="line d-flex" style="font-size: 12px">
                        <p><span class="fa-solid fa-heart" id="like${mate.mid}"></span><span>${mate.likeCnt }</span></p>
                        <p><span class="fa-regular fa-comment"></span><span>${mate.replyCnt }</span></p>
                      </div>
                    </div>
                    <div class="title">${mate.title }</div>
                    <div class="price">
                      <fmt:formatNumber value="${mate.price2}" pattern="#,###" />
                    </div>
                  </div>
                  <div class="mate-bottom-info">
                    <div class="left" TradeType="${mate.tradeType }">
                      <p Trade ver1 class="hide">만남 거래</p>
                      <p Trade ver2 class="hide">택배 거래</p>
                    </div>
                    <div class="right">
                      <p class="category category${mate.category }"></p>
                    </div>
                  </div>
                </div>
              </div>
              <!-- item -->
            </c:forEach>
          </div>
        </section>
      </div>
    </div>
    <script src="/js/mate_list.js"></script>
  </body>
</html>
