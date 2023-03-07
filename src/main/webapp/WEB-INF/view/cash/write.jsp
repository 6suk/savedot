<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <!-- // Heading -->
    <%@ include file="../common/heading.jsp"%>
    <!-- // Heading -->

    <title>SAVE. : 수입/지출 등록</title>
  </head>
  <body>
    <!-- //TOP -->
    <%@ include file="../common/top.jsp"%>
    <!-- TOP -->

    <div class="container-nonaside">
      <!-- content -->
      <section>
        <div class="content-title">
          <div class="form-tag" id="cashcategory">
            <input type="radio" name="category" id="cate_0" value="0" checked />
            <label for="cate_0" id="category">지출 영수증 등록</label>
            <input type="radio" name="category" id="cate_1" value="1" />
            <label for="cate_1" id="category">지출 직접 등록</label>
            <input type="radio" name="category" id="cate_2" value="2" />
            <label for="cate_2" id="category">수입 직접 등록</label>
          </div>
          <input type="button" id="sendbtn" class="btn btn-main main" value="등록하기" />
        </div>

        <div class="writebox flex-lg-row">
          <div class="imgbox-only">
            <label class="imgbox-img" for="input" id="preview" hidden></label>
            <label class="imgbox-img label" id="label" for="input">
              <div class="cash inner" id="inner">
                <i class="fa-solid fa-receipt" style="color: #9999997d"></i>
                <span class="pt-2 pb-1 font-600 l-graytxt">영수증 지출등록</span>
                <span class="l-graytxt font-14 font-300">영수증 전체가 잘 나오도록 정면에서 촬영해 주세요.</span>
                <span class="l-graytxt font-14 font-300">배경에 다른 물체나 글씨가 없도록 촬영해 주세요.</span>
                <span class="l-graytxt font-14 font-300">결제 일시, 품명, 금액가 잘 나오도록 촬영해 주세요.</span>
              </div>
            </label>
            <input
              id="input"
              class="input required"
              accept="image/*"
              type="file"
              multiple="false"
              hidden="true"
              name="img"
            />
          </div>
          <form id="cashsaveform" class="inputbox">
            <div class="inputbox">
              <input class="form-control text input-text" type="date" name="regDate" id="date" disabled />
              <input
                class="form-control text input-text"
                type="text"
                name="amount"
                id="amount"
                placeholder="금액*"
                disabled
              />
              <input class="form-control text input-text" type="text" name="content" id="shopName" placeholder="내역*" disabled />
              <textarea id="memo" class="form-control" name="memo" cols="30" rows="10" placeholder="메모"></textarea>
            </div>
          </form>
        </div>
      </section>
    </div>
  </body>
  <script src="/js/cash.js"></script>
  <script src="/js/required.js"></script>
</html>
