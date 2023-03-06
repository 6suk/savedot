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
          <div class="mate-top-title">
            <div class="form-tag" id="matecategory">
              <input type="radio" name="state" id="all" value="A" checked /> <label for="all">전체보기</label>
              <input type="radio" name="state" id="ing" value="0" /> <label for="ing">거래중</label>
              <input type="radio" name="state" id="end" value="1" /> <label for="end">거래완료</label>
            </div>
            <div class="mypage-cash-top-end">
              <input id="query-search" class="form-control mypage text" type="text" placeholder="검색하기" />
              <button class="query-search" id="query-search-btn" style="border-left: 0">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <div class="mate-flex-view">
            <c:forEach var="mate" items="${mate }">
              <!-- item -->
              <div class="mate-flex-item mypage" mid="${mate.mid }">
                <div class="mate-top-info">
                  <div class="top">
                    <p class="mate-card-tag position" ${mate.stateName }>
                      ${mate.positonApplyNum } / ${mate.positionNum }
                    </p>
                    <!--  좋아요 기능 (비로그인시 하트모양 안보임) -->
                    <c:if test="${user.uid ne null}">
                      <c:set var="likeType" value="0"></c:set>
                      <c:set var="likeUrl" value="likePress(${mate.mid})"></c:set>
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
  </body>
  <script src="/js/mypage_mate_list.js"></script>
  <script src="/js/mate_like.js"></script>
</html>
