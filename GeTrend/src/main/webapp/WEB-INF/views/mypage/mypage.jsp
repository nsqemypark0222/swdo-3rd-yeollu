<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mypage test ]</title>
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
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

<style>

header{
	position: absolute;
	width: 1130px;
	height: 120px;
	left: 168px;
	top: 0px;

}


.mypage_profile{
	background : yellow;
	position: absolute;
	width: 900px;
	height: 240px;
	left: 300px;
	top: 145px;
	
}

.mypage_buttons{
	background : green;
	position: absolute;
	width: 900px;
	height: 55px;
	left: 300px;
	top: 385px;
}
.mypage_comments{
	background : yellow;
	position: absolute;
	width: 900px;
	height: 600px;
	left: 300px;
	top: 440px;

}

footer{
	position: absolute;
	width: 1130px;
	height: 80px;
	left: 168px;
	top: 1040px;
}
</style>

</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>
	
	<div class="mypage_profile">
		<table border="1">
			<tr>
				<td rowspan="2">${user.user_profile}</td>
				<td>${user.user_name}</td>
				<td>
					<c:if test="${sessionScope.loginemail != null}">
						<c:if test="${user.user_email != sessionScope.loginemail}">
							<button id="followButton">팔로우</button>
						</c:if>
					</c:if>				
				</td>
				<td></td>
			</tr>
			<tr>
				<td>좋아요 : ${like}</td>
				<td>팔로워 : ${follow}</td>
				<td>팔로우 : ${follower}</td>
			</tr>
		</table>
	</div>
	<div class="mypage_buttons">
		<table border="1">
			<tr>
				<td><button id="myComment">내 코멘트</button></td>
				<td><button id="friendsComment">친구들의 코멘트</button></td>
				<td><button id="likeStores">내가 좋아하는 가게들</button></td>
			</tr>
		</table>
	</div>
	
	<div class="mypage_comments">
		<c:if test="${replyList != null}">
			<c:forEach var="reply" items="${replyList}">
				<div class="mypage_comment_one">
					<table border="1">
						<tr>
							<td>${reply.user_profile}</td>
							<td rowspan="3">가게 썸네일 - 아직없음</td>
							<td>${reply.store_name}</td>
							<td>지도 버튼</td>
						</tr>
						<tr>
							<td></td>
							<td>동명동${reply.store_adr}</td>
							<td>가게 하트 버튼
								<form id="likeForm">
									<input type="hidden" id="user_email"   name="user_email" value="${reply.user_email}">
									<input type="hidden" id="store_no"	   name="store_no"   value="${reply.store_no}">
									<input type="button" id="likeButton"   value="좋아요" onclick="likeInsert();">
								</form>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>가게 정보${reply.reply_star}</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2">코멘트${reply.reply_contents}</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>코멘트 입력 날짜${reply.reply_indate}</td>
							<td>좋아요 갯수 - 아직없음</td>
							<td></td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</c:if>
	</div>
	
	
<footer>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
</body>
</html>