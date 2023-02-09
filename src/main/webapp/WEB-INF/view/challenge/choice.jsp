<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- //// MATE DETAIL PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
	<title>챌린지 선택</title>
    <link rel="stylesheet" href="/css2/common.css">
    <link rel="stylesheet" href="/css2/questions.css">
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->
    <div class="question-box">
        <div class="question">
            <h3>${uname}님,<br>
                오늘 참여한 챌린지를<br>
                선택해주세요!</h3>
        </div>
        <c:forEach items="${challenge}" var="c">
        <button class="btn btn-gray choice" type="button" name="op" value="cha1" onclick="location.href='/challenge/choice/${c.cid}'">
	        <c:if test="${c.cid eq 1}">&#128652;</c:if>
	        <c:if test="${c.cid eq 2}">&#127869;</c:if>
	        <c:if test="${c.cid eq 3}">☕</c:if>				<!-- 커피이모지는 따로 코드 없어서 이미지로 첨부 -->
        	${c.cname}<br>
        	<strong style="color:CornflowerBlue; font-size: smaller; font-weight: 400;">${c.camount}원 절약</strong>
        </button>
        </c:forEach>		
    </div>
   	<!-- BOTTOM -->
    <%@ include file="../common/bottom.jsp" %>
	<!-- BOTTOM -->
</body>
</html>