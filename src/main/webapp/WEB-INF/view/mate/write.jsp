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
              <input id="input" class="input" accept="image/*" type="file" multiple="true" hidden="true" name="img" />
              <div class="preview" id="preview" hidden></div>
            </div>
            <div class="inputbox">
              <form id="mateform" class="inputbox">
                <div style="position: relative; width: 100%">
                  <input type="hidden" name="uid" value="${user.id}" />
                  <input
                    class="form-control text required"
                    type="text"
                    name="tel"
                    value="${user.telFormat }"
                    readonly
                  />
                  <i
                    class="fa-solid fa-circle-exclamation inner-btn font-14"
                    style="color: var(--lgray-color); opacity: 0.5"
                    data-bs-toggle="tooltip"
                    data-bs-html="true"
                    data-bs-placement="top"
                    title="연락처는 거래 진행 중일 시 메이트에게만 공개됩니다.<br>
                    연락처 수정은 마이페이지에서 가능합니다."
                  ></i>
                </div>
                <div style="position: relative; width: 100%">
                  <input
                    class="form-control text required"
                    type="text"
                    name="accountNumber"
                    placeholder="계좌번호*"
                    numberOnly
                  />
                  <i
                    class="fa-solid fa-circle-exclamation inner-btn font-14"
                    style="color: var(--lgray-color); opacity: 0.5"
                    data-bs-toggle="tooltip"
                    data-bs-html="true"
                    data-bs-placement="top"
                    title="계좌번호는 거래 진행 중일 시 메이트에게만 공개됩니다."
                  ></i>
                </div>
                <input class="form-control text required" type="text" name="title" placeholder="타이틀*" />
                <div class="d-flex gap-2">
                  <input
                    class="form-control text required"
                    type="text"
                    name="price1"
                    placeholder="구매가*"
                    style="flex: 1 0 0"
                    id="price1"
                    price
                  />
                  <input
                    class="form-control text required"
                    type="text"
                    name="positionNum"
                    placeholder="모집인원*"
                    style="flex: 0.3 0 0"
                    id="position_num"
                    numberOnly
                  />
                  <input
                    class="btn btn-gray font-14"
                    type="button"
                    name="price2"
                    value="자동계산"
                    data-bs-toggle="tooltip"
                    data-bs-html="true"
                    data-bs-placement="top"
                    title="구매가와 모집인원을 기입하면,<br> 판매가가 자동으로 계산됩니다.<br>판매가는 수정이 가능합니다."
                    id="calc_price"
                  />
                </div>
                <input
                  class="form-control text required"
                  type="text"
                  name="price2"
                  id="price2"
                  placeholder="판매가*"
                  price
                />
                <textarea
                  class="form-control"
                  name="content"
                  cols="30"
                  rows="10"
                  placeholder="제품의 정보를 자세하게 기재해주세요!"
                ></textarea>
                <input class="form-control text" type="text" name="openChat" placeholder="오픈채팅 URL" />
                <div class="form-tag tag-border" id="tradetype">
                  <input type="radio" name="tradeType" id="direct" value="1" class="required" />
                  <label for="direct" id="tradetype">직접거래</label>
                  <input type="radio" name="tradeType" id="parcel" value="2" class="required" />
                  <label for="parcel" id="tradetype">택배거래</label>
                  <input type="radio" name="tradeType" id="all" value="3" class="required" />
                  <label for="all" id="tradetype">직접거래 / 택배거래 모두 가능</label>
                </div>
                <!-- << 장소검색 -->
                <div class="hide" id="direct_show">
                  <div class="input-btn">
                    <a class="input-btn-ele-reverse" onclick="sample3_execDaumPostcode()" id="addr-find"
                      ><span>장소검색</span> <i class="fa-solid fa-magnifying-glass font-14"></i
                    ></a>
                    <a class="input-btn-ele-reverse" onclick="foldDaumPostcode()" id="addr-close" style="display: none"
                      ><span>닫기</span></a
                    >
                    <a class="input-btn-ele-reverse" onclick="addr_cancle()" id="addr-cancle" style="display: none"
                      ><span>삭제</span></a
                    >
                    <input type="hidden" name="placeCoords" id="place_coords" />
                    <input type="hidden" name="placeAddr" id="place_addr" />
                    <input
                      type="text"
                      class="form-control text"
                      id="place_name"
                      name="placeName"
                      placeholder="*만남 장소를 기입해주세요."
                      style="flex: 1 0 0"
                      readonly
                    />
                  </div>
                </div>
                <!-- 장소검색 >> -->
                <!-- << 선불/착불 선택 -->
                <div class="hide" id="parcel_show">
                  <div class="input-btn">
                    <select
                      name="parcelType"
                      id="parcel_type"
                      class="form-select input-btn-ele-reverse"
                      style="flex: 0.2 0 0"
                    >
                      <option value="1">선불</option>
                      <option value="2">착불</option>
                    </select>
                    <input
                      type="number"
                      class="form-control text"
                      name="parcelPrice"
                      id="parcel_price"
                      style="flex: 1 0 0"
                      placeholder="*택배비를 기입해주세요."
                    />
                  </div>
                </div>
                <!-- 선불/착불 선택 >> -->
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

  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
  <script src="/js/kakao_address.js"></script>
  <script src="/js/upload.js"></script>
  <script src="/js/mate.js"></script>
  <script src="/js/required.js"></script>
  <script>
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  </script>
</html>
