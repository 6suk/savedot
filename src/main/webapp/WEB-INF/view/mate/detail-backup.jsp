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

	<div class="container-nonaside-sm">
		<!-- content -->
		<section>
			<!-- << 슬라이드 시작 -->
			<!-- <div id="demo" class="carousel slide" data-bs-ride="carousel"> -->
			<div id="demo" class="carousel slide">
				<!-- Indicators/dots -->
				<div class="carousel-indicators">
					<c:forEach items="${mate.imgInfo }" varStatus="i">
						<button type="button" data-bs-target="#demo"
							data-bs-slide-to="${i.index }" class="${i eq 0 ? 'active' : '' }"></button>
					</c:forEach>
				</div>

				<div class="carousel-inner">
					<c:forEach items="${mate.imgInfo }" var="img" varStatus="i">
						<div class="carousel-item ${i eq 0 ? 'active' : '' }">
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
					<button class="btn btn-main sm font-14 font-200">
						매칭신청<i class="fa-solid fa-circle-plus"></i>
					</button>
				</div>

				<div class="mate-detail-content">${mate.content }</div>
			</div>
		</section>
	</div>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
<script src="/js/upload.js"></script>
<script src="/js/location.js"></script>
</html>
