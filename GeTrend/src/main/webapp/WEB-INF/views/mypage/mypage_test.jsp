<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mypage test ]</title>
<link rel= "stylesheet" type="text/css" href="../resources/css/reply.css">
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function deleteReply(no){
	$.ajax({
		url: "/getrend/reply/replyRemove",
		type: "get",
		data: {"reply_no": no},
		success: printList,
		error: function() { alert("에러 발생"); }
	});
}

function printList() {
	$.ajax({
		url: "/getrend/reply/replyList",
		type: "get",
		data: {"store_no" : "25523615"},
		success: function(result) {
			var output = "";
			output += "<tr>";
			output += "<th>별점</th>";
			output += "<th>닉네임</th>";
			output += "<th>리뷰</th>";
			output += "<th>날짜</th>";
			output += "<th></th>";
			output += "</tr>";
			
			$(result).each(function(index, item) {
				console.log(item);
				output += "<tr>";
				output += "<td>" + item.REPLY_STAR + "</td>";
				output += "<td><a href='/getrend/mypage/mypage?user_name="+item.USER_NAME+"'>" + item.USER_NAME + "</a></td>";
				output += "<td>" + item.REPLY_CONTENTS + "</td>";
				output += "<td>" + item.REPLY_INDATE + "</td>";
				if(item.loginemail ==item.USER_EMAIL){
					output += "<td><input type='button' id='removeBtn' value='삭제' onclick='deleteReply("+item.REPLY_NO+")'></td>";
				}
				output += "</tr>";
			});
			
			$("#printTable").html(output);
		},
		error: function() { alert("에러 발생"); }
	});
}

	$(function() {
		$("#writeBtn").on("click", function() {
			var reply_contents = $("#reply_contents").val();
			
			$.ajax({
				url: "/getrend/reply/replyWrite",
				type: "get",
				data: $("#inputForm").serialize(),
				success: printList,
				error: function() { alert("에러 발생"); }
			});
		});

	

	$('.starRev span').on("click",function(){
		console.log($(this));
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		console.log($(this).attr("value"));
		var re = $(this).attr("value");
		$(".starRev").children('input').remove();
		$(".starRev").append("<input type='hidden' name='reply_star' value="+ re + ">");
		return false;
	});

	printList();
});






	
function likeInsert(store_no){
	$.ajax({
		url : "/getrend/likes/likeInsert",
		type : "post",
		data : { "store_no" : store_no},
		success : function(){ 
				var temp = '<input type="button"  id="unlikeButton" value="좋아요 취소"  onclick="likeDelete('+store_no+');">';
				$("#likeDiv").html(temp);
			},
		error : function(){alert("실패");}
	})
}

function likeDelete(store_no){
	$.ajax({
		url : "/getrend/likes/likeDelete",
		type : "post",
		data :  {"store_no" : store_no},
		success : function(){ 
				var temp = '<input type="button"   id="likeButton"  value="좋아요"      onclick="likeInsert('+store_no+');">';
				$("#likeDiv").html(temp);
			},
		error : function(){alert("실패");}
	})
}
			
</script>
</head>

<body>
<body>
<header>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>

1. 가게 좋아요 버튼
${store_name} , ${store_no}

<div id="likeDiv">
	<c:choose>
		<c:when test="${likecheck != 0}">
			<input type="button"  id="unlikeButton" value="좋아요 취소" onclick="likeDelete('${store_no}');">					
		</c:when>
		<c:otherwise>
			<input type="button"   id="likeButton"  value="좋아요"      onclick="likeInsert('${store_no}');">													
		</c:otherwise>							
	</c:choose>						
</div>





	<div>
		<div>
			<form id="inputForm" >
				<table>
					<tr>
						<td>${sessionScope.loginname}</td>
						<td>
							<div class="starRev">
							  <span class="starR1" value="0.5">별1_왼쪽</span>
							  <span class="starR2" value="1">별1_오른쪽</span>
							  <span class="starR1" value="1.5">별2_왼쪽</span>
							  <span class="starR2" value="2">별2_오른쪽</span>
							  <span class="starR1" value="2.5">별3_왼쪽</span>
							  <span class="starR2" value="3">별3_오른쪽</span>
							  <span class="starR1" value="3.5">별4_왼쪽</span>
							  <span class="starR2" value="4">별4_오른쪽</span>
							  <span class="starR1" value="4.5">별5_왼쪽</span>
							  <span class="starR2" value="5">별5_오른쪽</span>
							</div>						
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea id="reply_contents" name="reply_contents" style="word-break:break-all;"placeholder="내용 입력" maxlength="100" ></textarea>
						</td>
						<td>
							<input type="button" id="writeBtn" value="리뷰 등록">
						</td>
						<td>
							
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div> <!-- input form end -->
	<hr>
	<div> <!-- print form -->
		<table id="printTable">
	
		</table>	
	</div>
<hr>

	<a href="/getrend/mypage/mypageSession">내 방 가기</a>
	<a href="/getrend/mypage/modalTest">모달 테스트</a>
<hr>


<footer>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
</body>
</html>