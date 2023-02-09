<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	<div style="position: relative">
		<div class="container-nonaside-sm" id="container">
			<!-- content -->
			<section id="section-content">
				<!-- << 슬라이드 시작 -->
				<!-- <div id="demo" class="carousel slide" data-bs-ride="carousel"> -->
				<div id="demo" class="carousel slide">
					<!-- Indicators/dots -->
					<div class="carousel-indicators">
						<c:forEach items="${mate.imgInfo }" varStatus="i">
							<button type="button" data-bs-target="#demo"
								data-bs-slide-to="${i.index }"
								class="${i.index eq 0 ? 'active' : '' }"></button>
						</c:forEach>
					</div>

					<div class="carousel-inner">
						<c:forEach items="${mate.imgInfo }" var="img" varStatus="i">
							<div class="carousel-item ${i.index eq 0 ? 'active' : '' }">
								<img src="/upload/${img.saveDate }/${img.id }${img.ext }"
									class="d-block slide-img" />
							</div>
						</c:forEach>
					</div>

					<!-- Left and right controls/icons -->
					<button class="carousel-control-prev" type="button"
						data-bs-target="#demo" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#demo" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
					</button>
				</div>
				<!-- 슬라이드 끝 >> -->

				<div class="mate-detail-all">
					<div class="mate-detail-top">
						<!-- Top 01 -->
						<div class="mate-detail-sub-info">
							<div class="dot d-flex">
								<p>${mate.categoryName }</p>
								<p>매칭 전</p>
							</div>
							<div class="line d-flex">
								<p>
									<span>조회</span><span>33</span>
								</p>
								<p>
									<!-- <span><i class="fa-solid fa-heart"></i></span><span>13</span> -->
									<span><i class="fa-regular fa-heart"></i></span><span>13</span>
								</p>
							</div>
						</div>
						<!-- Top 02 -->
						<h4>${mate.title }</h4>
						<!-- Top 03 -->
						<div class="mate-detail-price">
							<h5>
								<fmt:formatNumber value="${mate.price2}" pattern="#,###" />
							</h5>
							<p class="mate-detail-price-tag">
								SAVE
								<fmt:formatNumber value="${mate.savePrice}" pattern="#,###" />
							</p>
						</div>
					</div>

					<div class="mate-detail-middle">
						<div>
							<p class="font-15 font-600">${mate.user.id }</p>
							<p class="font-14 l-graytxt">${mate.area }</p>
						</div>
						<button class="btn btn-main sm font-14" id="applybtn">
							매칭신청<i class="fa-solid fa-plus" id="applybtn-icon"></i>
						</button>
						<button class="btn btn-gray sm font-14" id="applybtn-o"
							style="display: none">
							작성취소<i class="fa-solid fa-minus" id="applybtn-icon"
								style="color: #666"></i>
						</button>
					</div>

					<div class="mate-detail-content">${mate.content }</div>
					<div class="mate-detail-middle">
						<p class="font-15 font-600">여기에서 만나요!</p>
						<a class="font-15" href=""><i class="fa-solid fa-location-dot"></i>
							<span>${mate.placeName }</span></a>
					</div>
				</div>
			</section>
			<section class="seletion-side" id="section-apply">
          <div class="content-title">
            <div>
              <h5>매칭 신청</h5>
              <p class="font-14 l-graytxt pt-1">최종 매칭은 글 작성자의 승낙 시 진행됩니다!</p>
            </div>
            <input type="button" id="sendbtn" class="btn btn-main mdi" value="매칭신청" />
          </div>
          <form id="applyform" class="inputbox" method="post" action="/apply/${mate.id}">
            <div class="input-btn">
            
              <select name="" id="area1" class="form-select empty-select required" disabled>
                <option value="" selected disabled id="area1_title">시</option>
              </select>
              <select name="" id="area2" class="form-select empty-select required" disabled>
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
            <div class="d-flex gap-2">
              <input class="form-control text required" type="text" name="nickname" value="${user.nickname }" readonly />
              <div style="position: relative; width: 100%">
                <input type="hidden" name="id" value = ${user.id }>
                <input class="form-control text required" type="text" name="tel" value="${user.telFormat }" readonly />
                <i
                  class="fa-solid fa-circle-exclamation inner-btn font-14"
                  style="color: var(--lgray-color); opacity: 0.5"
                  data-bs-toggle="tooltip"
                  data-bs-html="true"
                  data-bs-placement="top"
                  title="연락처는 매칭 성공 시 작성자에게만 공개됩니다.<br>
                  연락처 수정은 마이페이지에서 가능합니다."
                ></i>
              </div>
            </div>
            <input class="form-control text required" type="text" name="title" placeholder="타이틀*" />
            <textarea
              class="form-control"
              name="content"
              cols="30"
              rows="10"
              placeholder="남기고 싶은 말을 적어주세요 :)"
            ></textarea>
          </form>
        </section>
		</div>
	</div>
</body>
  <script src="/js/apply.js"></script>
  <script>
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl);
    });
  </script>
  <script src="/js/geolocation.js"></script>
</html>

