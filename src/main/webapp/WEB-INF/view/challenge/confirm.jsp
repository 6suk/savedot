<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- //// MATE DETAIL PAGE //// -->

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
	<title>챌린지 확인 페이지</title>
    <link rel="stylesheet" href="/css2/common.css">
    <link rel="stylesheet" href="/css2/questions.css">
</head>
<body>
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->
   <div class="question-box">
       	<div class="question">
        	<h3>${c.cname}<br>
        		챌린지를 성공하셨나요?</h3>
       	</div>
		<br>
		<button class="btn btn-gray choice" type="submit"
			onclick="location.href='/challenge/save/${c.cid}'">네, 성공했어요!︎</button>
		<button class="btn btn-gray choice"
			onclick="location.href='/challenge/choice'">챌린지 다시 선택하기</button>
	</div>
	<!-- BOTTOM -->
    <%@ include file="../common/bottom.jsp" %>
	<!-- BOTTOM -->
</body>
</html>