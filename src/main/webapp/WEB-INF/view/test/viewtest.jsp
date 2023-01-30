<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- // Heading -->
<%@ include file="../common/heading.jsp"%>
<!-- // Heading -->
<title>Insert title here</title>
</head>
<body style="margin: 50px">
	<!-- TOP -->
	<%@ include file="../common/top.jsp"%>
	<!-- TOP -->
	<div class="container-nonaside">

		<table>
			<tr>
				<td>작성자 아이디</td>
				<td>${mate.user.id }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${mate.modDate }</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>${mate.categoryName }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${mate.title }</td>
			</tr>
			<tr>
				<td>지역</td>
				<td>${mate.area }</td>
			</tr>
			<tr>
				<td>원가</td>
				<td>${mate.price1 }</td>
			</tr>
			<tr>
				<td>판매가</td>
				<td>${mate.price2 }</td>
			</tr>
			<tr>
				<td>본문</td>
				<td>${mate.content }</td>
			</tr>
			<c:forEach items="${mate.imgInfo }" var="img" varStatus="i">
				<tr>
					<td>이미지 ${i.count }</td>
					<td><img src="/upload/${img.saveDate }/${img.id }${img.ext }"
						style="width: 200px;" /></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>