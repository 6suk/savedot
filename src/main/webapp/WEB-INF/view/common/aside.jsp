<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- //// COMMON - ASIDE //// -->

     <aside class="col-md-3">
        <div class="aside-img" >
            <c:if test="${empty sessionFilename}">
                <img src="/img/bros_blank.jpg" width="100%">
            </c:if>
            <c:if test="${not empty sessionFilename}">
                <img src="/aside/blob/${user.id}" width="100%">
            </c:if>
        </div>
    
        <div class="aside-info border-bottom">
            <h4>${user.nickname }</h4>
            <dl>
                <dt>알림</dt>
                <dd>
                    <a href="/mypage/alarm">${alarmCnt }</a>
                </dd>
                <dt>좋아요</dt>
                <dd>
                    <a href="/mypage/mate/like/list">${user.likeCount }</a>
                </dd>
            </dl>

            <!-- 정보 수정 -->
            <div class="btn btn-border font-12">
                <a href="/user/update">정보 수정</a>
            </div>
            <!-- 정보 수정 버튼 클릭 시 프로필 변경 오픈 -->
            <div class="btn btn-border font-12">
				<p onclick="editProfile('${user.id}')">프로필 변경</p>
			</div>

        </div>
        
        <div class="mt-3 mb-5" id="hiddenProfile" style="display: none;">
            <form action="/aside/profile" method="post" enctype="multipart/form-data">
                <table class="table table-borderless table-sm">
                    <tr class="d-flex">
						<input class="form-control" type="file" name="image" id="image">
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="button" class="btn btn-main mdi me-1"
                                onclick="editExecute('${user.id}')">등록</button>
                            <button type="button" class="btn btn-gray mdi mt-1"
                                onclick="editCancel()">취소</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="multiple-btn pt-3 pb-5">
            <a class="icon-btn" href="/challenge/choice">
                <i class="fa-solid fa-certificate mb-2"></i>
                <p>챌린지</p>
            </a>
            <a class="icon-btn" href="/cash/write">
                <i class="fa-solid fa-circle-plus mb-2"></i>
                <p>지출등록</p>
            </a>
            <a class="icon-btn" href="/pay">
                <i class="fa-solid fa-dollar-sign mb-2"></i>
                <p>얼마벌었지</p>
            </a>
        </div>
    </aside>