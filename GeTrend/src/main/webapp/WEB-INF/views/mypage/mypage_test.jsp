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
		
		$("#removeBtn").on("click", function() {
			
			$.ajax({
				url: "/getrend/reply/replyRemove",
				type: "get",
				data: {"reply_no": $("reply_no").val(),"user_email": $("user_email").val()},
				success: printList,
				error: function() { alert("에러 발생"); }
			});
		});
		
		function printList() {
			$.ajax({
				url: "/getrend/reply/replyList",
				type: "get",
				data: {"store_no" : $("store_no").val()},
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
						output += "<tr>";
						output += "<td>" + item.REPLY_STAR + "</td>";
						output += "<td><a href='/getrend/mypage/mypage?user_name="+item.USER_NAME+"'>" + item.USER_NAME + "</a></td>";
						output += "<td>" + item.REPLY_CONTENTS + "</td>";
						output += "<td>" + item.REPLY_INDATE + "</td>";
						
						output += "</tr>";
					});
					
					$("#printTable").html(output);
				},
				error: function() { alert("에러 발생"); }
			});
		}
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
	function likeInsert(){
		$.ajax({
						url : "/getrend/likes/likeInsert",
						type : "post",
						data : {"user_email" : $("#user_email").val(), "store_no" : $("#store_no").val()},
						success : function(){ 
								alert("성공");
								var temp =  '<input type="hidden"  id="user_email"   name="user_email" value="tete030tete@gmail.com">';
									temp += '<input type="hidden"  id="store_no"	 name="store_no"   value="25293330">';
									temp += '<input type="button"  id="unlikeButton" value="좋아요 취소"  onclick="likeDelete();">';
								$("#likeForm").html(temp);
							},
						error : function(){alert("실패");}
					})
	}

	function likeDelete(){
		$.ajax({
				url : "/getrend/likes/likeDelete",
				type : "post",
				data :  {"user_email" : $("#user_email").val(), "store_no" : $("#store_no").val()},
				success : function(){ 
						alert("성공");
						var temp =  '<input type="hidden"   id="user_email"  name="user_email" value="tete030tete@gmail.com">';
							temp += '<input type="hidden"   id="store_no"	 name="store_no"   value="25293330">';
							temp += '<input type="button"   id="likeButton"  value="좋아요"      onclick="likeInsert();">';
						$("#likeForm").html(temp);
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

	<div>
		<div>
			<form id="inputForm" >
				<table>
					<tr>
						<th>리뷰</th>
					</tr>
						
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
							<textarea id="reply_contents" name="reply_contents" placeholder="내용 입력"></textarea>
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
			
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>
				<input type="button" id="removeBtn" value="삭제">
				</th>
			</tr>
		</table>	
	</div>

<c:if test="sessionScope.loginemail != null">
	<a href="/getrend/mypage/mypage">내 방 가기</a>
</c:if>	
<hr>
좋아요 insert<br>
	<form id="likeForm">
		<input type="hidden" id="user_email"   name="user_email" value="${sessionScope.loginemail}">
		<input type="hidden" id="store_no"	   name="store_no"   value="25293330">
		<input type="button" id="likeButton"   value="좋아요" onclick="likeInsert();">
	</form>


	
<footer>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
</body>
</html>