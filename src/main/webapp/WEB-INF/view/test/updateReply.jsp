<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
					<!--  댓글작성부분  -->
					<div class = "mate-detail-reply-insert">
					<span class ="reply_num">
					<h3>댓글수정</h3>
					</span>
					</div>
					<div>
					<form method="post" action="/mate/reply/update">
						<input type="hidden" name="mid"  id="mid" value="${mateReply.mid}">
						<input type="hidden" name="rid"  id="rid" value="${mateReply.rid}">
							<table class="table table-borderless mt-2">
                                <tr class="d-flex">
                                    <td class="col-9">
                                        <textarea class="form-control" id="content" name="content" rows="3" placeholder="댓글을 수정해주세요"></textarea>
                                    </td>
                                    <td class="col-2">
                                        <button type="submit" class="btn btn-primary">수정</button>
                                    </td>
                                </tr>
                            </table>
					</form>
					</div>
</body>
<script type="text/javascript"
	src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849"></script>
<script type="text/javascript"
	src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=dbe219346db7d2ef92284f7144059849&libraries=clusterer"></script>

<script src="/js/map.js"></script>
<script src="/js/apply.js"></script>
<script src="/js/mate_detail.js"></script>
<script src="/js/required.js"></script>
<script src="/js/mate_reply.js"></script>
<script>
	var tooltipTriggerList = [].slice.call(document
			.querySelectorAll('[data-bs-toggle="tooltip"]'));
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl);
	});
</script>
<script>
	function deleteConfirm(rid,mid) {
		if(confirm("삭제하시겠습니까?"))
		 	location.href = "/mate/reply/delete/" + rid + "/" + mid;
	}
</script>   	
</html>

