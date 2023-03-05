<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- //// MATE DETAIL PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>Save. - 알뜰정보</title>
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->

	<div class="container-lg">
		<!-- content -->
		<section class="n-wrap">
			<!-- 간편경제뉴스 시작 -->
			<div class="n-content-wrap">
				<div class="n-title">
					<h3>간편 경제 뉴스</h3>
					<p>↻ ${now }</p>
				</div>
				<div class="n-card-group">
					<c:forEach items="${newsList}" var="list">
						<div class="n-card-item" onclick="location.href='${list.link}'">
							<div class="n-item-top">
								<p class="n-item-date">${list.pubDate}</p>
								<p class="n-item-tag">${list.category}</p>
							</div>
							<h5 class="news-title">${list.title}</h5>
							<p class="news-desc">${list.description}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- 간편경제뉴스 끝 -->

			<!-- 환율 시작 -->
			<div class="n-content-wrap">
				<div class="n-title">
					<h3>국가별 환율</h3>
					<p>↻ ${elist.get(0).today }(${elist.get(0).todayOfWeek })</p>
				</div>
				<div class="n-card-group ex">
					<c:forEach var="ex" items="${elist }">
						<div class="n-card-item">
							<div class="n-item-top">
								<p class="n-item-date">${ex.today }</p>
								<p class="n-item-tag">${ex.todayCurUnit }</p>
							</div>
							<div class="ex-title" city="${ex.todayCurUnit }">
								<img src="/emoji/" />
								<h3></h3>
							</div>
							<div class="ex-desc">
								<ul class="ex-desc-left">
									<li>매매기준율</li>
									<li>전일대비</li>
									<li>등락률</li>
								</ul>
								<ul class="ex-desc-right">
									<li class="bold">${ex.todayDealBasR }</li>
									<li data-val="${ex.subDeal }">
										<p class="sub-comment">(${ex.yesterday } 기준)</p>
									</li>
									<li data-val="${ex.fluctuationRate }">%</li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- 환율 끝 -->
		</section>
	</div>
</body>
<script src="/js/savedot_news.js"></script>
</html>
