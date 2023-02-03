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

   <div style="position: relative">
      <div class="container-nonaside">
        <!-- content -->
        <section>
          <div class="content-title">
            <div class="form-tag" id="matecategory">
              <input type="radio" name="category" id="mate" value="0" class="required" />
              <label for="mate" id="category">조각 메이트</label>
              <input type="radio" name="category" id="ott" value="1" class="required" />
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
              <input
                id="input"
                class="input required"
                accept="image/*"
                type="file"
                multiple="true"
                hidden="true"
                name="img"
              />
              <div class="preview" id="preview" hidden></div>
            </div>
            <div class="inputbox">
              <div class="input-btn">
                <select name="" id="area1" class="form-select empty-select required" disabled>
                  <option value="" selected disabled id="area1_title">시</option>
                </select>
                <select name="area" id="area2" class="form-select empty-select required" disabled>
                  <option value="" disabled selected id="area2_title">군/구</option>
                  <option value="error" disabled>시를 먼저 선택해주세요!</option>
                </select>
                <a class="input-btn-ele" id="getLocation"
                  ><span>위치찾기</span> <i class="fa-solid fa-location-crosshairs"></i
                ></a>
                <a class="input-btn-ele" id="self_write" style="display: none"
                  ><span>직접선택</span> <i class="fa-solid fa-chevron-down"></i
                ></a>
              </div>
              <form id="mateform" class="inputbox">
                <div style="position: relative">
                  <input type="hidden" name="uid" value="${user.id}" />
                  <input class="form-control text required" type="text" name="tel" value="${user.telFormat }" readonly />
                  <i
                    class="fa-solid fa-circle-exclamation inner-btn font-14"
                    style="color: var(--lgray-color); opacity: 0.5"
                    data-bs-toggle="tooltip"
                    data-bs-html="true"
                    data-bs-placement="top"
                    title="연락처는 매칭 성공 시 메이트에게만 공개됩니다.<br>
                    연락처 수정은 마이페이지에서 가능합니다."
                  ></i>
                </div>
                <input class="form-control text required" type="text" name="title" placeholder="타이틀*" />

                <div class="d-flex gap-2">
                  <input class="form-control text required" type="number" name="price1" placeholder="구매가*" />
                  <input class="form-control text required" type="number" name="price2" placeholder="판매가*" />
                </div>
                <textarea
                  class="form-control"
                  name="content"
                  cols="30"
                  rows="10"
                  placeholder="제품의 정보를 자세하게 기재해주세요!"
                ></textarea>
                <div style="position: relative">
                  <input type="hidden" name="placeCoords" id="place_coords" />
                  <input type="hidden" name="placeAddr" id="place_addr" />
                  <input
                    type="text"
                    class="form-control text"
                    id="place_name"
                    name="placeName"
                    placeholder="만남 장소가 정해진 경우 검색 버튼을 눌러 기입해주세요 :)"
                    readonly
                  />
                  <a class="input-inner-btn" onclick="sample3_execDaumPostcode()" id="addr-find"
                    ><span class="font-14 font-600">검색</span><i class="fa-solid fa-magnifying-glass font-14"></i
                  ></a>
                  <a class="input-inner-btn" onclick="foldDaumPostcode()" id="addr-close" style="display: none"
                    ><span class="font-14 font-600">닫기</span><i class="fa-solid fa-chevron-down font-14"></i
                  ></a>
                  <a class="input-inner-btn" onclick="addr_cancle()" id="addr-cancle" style="display: none"
                    ><span class="font-14 font-600">삭제</span><i class="fa-solid fa-xmark font-14"></i
                  ></a>
                </div>
              </form>
            </div>
          </div>
        </section>
        <section class="seletion-side-addr" id="section-wrap">
          <div id="wrap"></div>
        </section>
      </div>
    </div>
  </body>
  <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849&libraries=services"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
  <script src="/js/geolocation.js"></script>
  <script src="/js/kakao_address.js"></script>
  <script src="/js/upload.js"></script>
  <script>
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  </script>
</html>

