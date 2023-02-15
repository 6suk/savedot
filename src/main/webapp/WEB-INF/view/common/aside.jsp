<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- //// COMMON - ASIDE //// -->

<aside class="col-md-2">
  <div class="aside-img">
    <!-- <img
          width="80%"
          src="https://emojipedia-us.s3.amazonaws.com/source/microsoft-teams/337/blue-heart_1f499.png"
        /> -->
  </div>
  <div class="aside-info border-bottom">
    <h4>${user.nickname }</h4>
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
