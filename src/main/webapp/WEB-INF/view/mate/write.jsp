<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- //// MATE WRITE PAGE //// -->

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
          <div class="inputbox">
            <div class="input-btn">
              <select name="" id="area1" class="form-select empty-select" disabled>
                <option value="" selected disabled id="area1_title">시</option>
              </select>
              <select name="area" id="area2" class="form-select empty-select" disabled>
                <option value="" disabled selected id="area2_title">군/구</option>
                <option value="error" disabled>시를 먼저 선택해주세요!</option>
              </select>
              <div type="button" class="input-btn-ele" id="getLocation">
                <i class="fa-sharp fa-solid fa-location-pin"></i>
                <p>위치찾기</p>
              </div>
              <div type="button" class="input-btn-ele" id="self_write">
                <i class="fa-solid fa-pen"></i>
                <p>직접기입</p>
              </div>
            </div>
            <form id="mateform" class="inputbox">
              <input class="form-control text" type="text" name="title" placeholder="제목*" />
              <input class="form-control text" type="number" name="price1" placeholder="구매가격*" />
              <input class="form-control text" type="number" name="price2" placeholder="판매가격*" />
              <textarea class="form-control" name="content" cols="30" rows="10" placeholder="본문"></textarea>
            </form>
          </div>
        </div>
      </section>
    </div>
  </body>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
  <script src="/js/upload.js"></script>
  <script src="/js/location.js"></script>
</html>

