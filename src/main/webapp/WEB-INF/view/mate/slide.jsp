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
								<img src="/savedot/upload/${img.saveDate }/${img.id }${img.ext }"
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