<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- //// COMMON - ASIDE //// -->

<aside class="col-md-3">
	<!-- <div class="aside-img"> -->

		<c:if test="${empty sessionFilename}">
			<img class="rounded-circle" width="90%" src="/img/bros_blank.jpg">
		</c:if>
		<c:if test="${not empty sessionFilename}">
			<img class="rounded-circle" width="90%" src="/aside/blob/${user.id}">
		</c:if>
<!-- 	</div> -->

	<div class="aside-info border-bottom">
		<h4>${user.nickname }</h4>
		<dl>
			<dt>챌린지</dt>
			<dd>
				<a href="#">12</a>
			</dd>
			<dt>조각메이트</dt>
			<dd>
				<a href="#">2</a>
			</dd>
		</dl>
		<div class="mt-5" id="profile">
		<div class="btn btn-border font-12">
			<p onclick="editProfile('${user.id}')">프로필 수정</p>
		</div>
	</div>
	<div class="mt-5 mb-5" id="hiddenProfile" style="display: none;">
		<form action="/aside/profile" method="post" enctype="multipart/form-data">
			<table class="table table-borderless table-sm">
				<tr class="d-flex">
					<td class="col-2" style="text-align: center;"><label
						class="mt-1" for="filename"><img src="/img/fileBlue.png"
							height="30"></label></td>
					<td class="col-10"><input class="form-control" type="text"
						name="filename" id="filename" placeholder="아래 파일선택에서 입력">
					</td>
				</tr>
				<tr class="d-flex">
					<td class="col-2" style="text-align: center;"><label
						class="mt-1" for="image"><img src="/img/fileChoice.png"
							height="30"></label></td>
					<td class="col-10"><input class="form-control" type="file"
						name="image" id="image"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="button" class="btn btn-primary me-2"
							onclick="editExecute('${user.id}')">제출</button>
						<button type="button" class="btn btn-secondary"
							onclick="editCancel()">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
		<div class="btn btn-border font-12">
			<a href="/user/update/${user.uid}">정보 수정</a>
		</div>
	</div>
	<div class="multiple-btn pt-3 pb-5">
		<a class="icon-btn" href="#"> <i
			class="fa-solid fa-certificate mb-2"></i>
			<p>챌린지</p>
		</a> <a class="icon-btn" href="#"> <i
			class="fa-solid fa-circle-plus mb-2"></i>
			<p>지출등록</p>
		</a> <a class="icon-btn" href="#"> <i
			class="fa-solid fa-share-nodes mb-2"></i>
			<p>공유하기</p>
		</a>
	</div>
</aside>
