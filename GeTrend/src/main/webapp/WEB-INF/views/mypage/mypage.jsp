<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mypage test ]</title>
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootstrap.css">
<script type="text/javascript">

function insertFollow(follows_following){
	$.ajax({
		url : "/getrend/insertFollow",
		type : "post",
		data : {"follows_following" : follows_following},
		success : function(result){ 
			var	temp = '<input type="button"  id="unfollowBtn" value="언팔로우"  onclick="deleteFollow(\''+follows_following+'\');">';
			$("#followDiv").html(temp);
			$("#followerCnt").html("팔로워 : " + result);
		},
		error : function(){alert("실패");}
	})
}



function deleteFollow(follows_following){
	$.ajax({
		url : "/getrend/deleteFollow",
		type : "post",
		data :  {"follows_following" : follows_following},
		success : function(result){ 
			var temp = '<input type="button"  id="followBtn" value="팔로우"  onclick="insertFollow(\''+follows_following+'\');">';
			$("#followDiv").html(temp);
			$("#followerCnt").html("팔로워 : " + result);
		},
		error : function(){alert("실패");}
	})
}



function likeInsert(store_no){
	$.ajax({
		url : "/getrend/likes/likeInsert",
		type : "post",
		data : { "store_no" : store_no},
		success : function(){ 
				var temp = '<input type="image"  class="reply_heart" id="unlikeButton" src="<c:url value='/resources/img/heart_click.png'/>"   onclick="likeDelete('+store_no+');">';
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
				var temp = '<input type="image" class="reply_heart"  id="likeButton"   src="<c:url value='/resources/img/heart_unclick.png'/>"    onclick="likeInsert('+store_no+');">';
				$("#likeDiv").html(temp);
			},
		error : function(){alert("실패");}
	})
}
		
$(function(){
	$(".mypage_button").click(function() {
		var color = $(this).css("background-color");
		if(color == "rgb(255, 138, 0)")
		$(this).css("background-color", "rgb(255, 193, 7)");
		else
		$(this).css("background-color", "rgb(255, 138, 0)");
	});
})


</script>

<style>

header{

}


.mypage_profile{
	position: absolute;
	width: 900px;
	height: 240px;
	left: 300px;
	top: 145px;
	
}

.mypage_profile_header_img{
	position: absolute; 
	border-radius:20px;
	top:0; 
	left: 0;
	width: 100%;
	height: 100%;
}
.mypage_profile_table{
	position: absolute;
	width: 440px;
	height: 130px;
	left: 50px;
	top: 50px;
}


.mypage_profile_user_name{
	float : left;
	margin : 0 15px;
	font-family: JejuGothic;
	font-weight: bold;
	font-size: 40px;
	line-height: 40px;
}


#followBtn{
   background-color: #fff;
   font-family: JejuGothic;
   font-size: 15px;
   font-weight: bold;
   width:70px; 
   height:40px;
   border: none;
   color:#000;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   cursor: pointer;
   border-radius:20px;
}

#followBtn:hover {
    background-color: #FAAC58;
}

#unfollowBtn{
   background-color: #fff;
   font-family: JejuGothic;
   font-size: 14px;
   font-weight: bold;
   width:70px; 
   height:40px;
   border: none;
   color:#FF8A00;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   cursor: pointer;
   border-radius:20px;
}

#unfollowBtn:hover {
    background-color: #fff;
}

.mypage_buttons_table{
	position: absolute;
}

.mypage_buttons{
	position: absolute;
	width: 900px;
	height: 55px;
	left: 300px;
	top: 390px;
}

.mypage_button{

   background-color: #FF8A00;
   width:295px;
   border: none;
   color:#000;
   padding: 15px 0;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   font-size: 15px;
   margin: 1px;
   cursor: pointer;
   border-radius:10px;
}
.mypage_button:hover{
  color:#fff;
}

.mypage_replies{
	position: absolute;
	width: 900px;
	left: 300px;
	top: 450px;

}
.mypage_reply_one{
	background-color: #F2F2F2; 
	border-radius:20px;
	width: 900px;
	margin : 10px 0;
}
.mypage_reply_tr01{
	width:900px; 
}
.mypage_reply_td01{
	width:65px; 
}
.reply_user_profile{
	width: 55px;
	height: 55px;
	margin : 10px 10px;
}
.mypage_reply_td02{
	width:200px; 
	height:180px;
}
.reply_store_thum{
	width:170px; 
	height:170px;
	border-radius:5px;
	margin : 5px 5px;
}
.height01{height : 80px; padding : 5px 5px;}
.height02{height : 10px; padding : 5px 5px;}
.height03{height : 80px; padding : 5px 5px;}

.mypage_reply_td03{
	width:550px; 
}
.mypage_reply_td04{
	width:75px; 
	height:75px;
}

.reply_map{
	width: 75px;
	height: 70px;
}
.reply_heart{
	width: 50px;
	height: 50px;
	margin : 0 10px;
}




</style>

</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>
	
	<div class="mypage_profile">
		<img class="mypage_profile_header_img" src="<c:url value='/resources/img/profile_header.png'/>" alt="profile header">
		<table class="mypage_profile_table">
			<tr>
				<td rowspan="2"><img class="user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></td>
				<td class="mypage_profile_user_name">${user.user_name}	
					<c:if test="${sessionScope.loginemail != null}">
						<c:if test="${user.user_email != sessionScope.loginemail}">
						<c:choose>
							<c:when test="${following}">						
									<div style="float:right;margin:0 30px;" id="followDiv"><input type="button" id="unfollowBtn" value="언팔로우" onclick="deleteFollow('${user.user_email}');"></div>
							</c:when>
							<c:otherwise>
									<div style="float:right;margin:0 30px;"id="followDiv"><input type="button" id="followBtn" value="팔로우" onclick="insertFollow('${user.user_email}');"></div>
							</c:otherwise>
						</c:choose>
						</c:if>
					</c:if>	
				</td>
			</tr>
			<tr>
				<td style="font-size: 15px; font-weight: bold;">&nbsp;&nbsp;&nbsp;좋아요 : ${like}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a>팔로우 : ${follow}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <div id="followerCnt" style="float:right;margin:0 20px;" ><a>팔로워 : ${follower}</a></div></td>
			</tr>
		</table>
	</div>
	<div class="mypage_buttons">
		<table class="mypage_buttons_table">
			<tr>
				<td><button class="mypage_button" onclick="location.href='/getrend/mypage/mypage?user_name=${user.user_name}'">${user.user_name}님의 코멘트</button></td>
				<td><button class="mypage_button" onclick="location.href='/getrend/mypage/mypageFriendReply?user_name=${user.user_name}'">이웃들의 코멘트</button></td>
				<td><button class="mypage_button" onclick="location.href='/getrend/mypage/mypageLikedStore?user_name=${user.user_name}'">좋아하는 가게들</button></td>
			</tr>
		</table>
	</div>
	
	<div class="mypage_replies">
		<c:forEach var="reply" items="${replyList}">
				<div>
					<table class="mypage_reply_one">
						<tr class="mypage_reply_tr01">
							<td class="mypage_reply_td01"><a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></a></td>
							<td class="mypage_reply_td02" rowspan="4" >
								<img class="reply_store_thum"src="<c:url value='/resources/img/thum_test.JPG'/>" alt="가게 썸네일 - 아직없음">							
							</td>
							<td class="mypage_reply_td03, height01"><span style="font-size:30px; font-weight : bold;">${reply.STORE_NAME}</span> <span style="font-size:15px;">별점 : ${reply.REPLY_STAR}점</span></td>
							<td  class="mypage_reply_td04">
								<img class="reply_map"src="<c:url value='/resources/img/place.png'/>" alt="지도연결 버튼">		
							</td>
						</tr>
						<tr class="mypage_reply_tr02">
							<td></td>
							<td class="height02"><span style="font-size:20px;">${reply.STORE_ADR}</span></td>
							<td rowspan="2">	
							<c:choose>
								<c:when test="${reply.USER_EMAIL == sessionScope.loginemail}">
									<div id="likeDiv">
										<c:choose>
											<c:when test="${reply.LIKE}">
												<input type="image"  class="reply_heart" id="unlikeButton" src="<c:url value='/resources/img/heart_click.png'/>"  onclick="likeDelete('${reply.STORE_NO}');">					
											</c:when>
											<c:otherwise>
												<input type="image" class="reply_heart"  id="likeButton"   src="<c:url value='/resources/img/heart_unclick.png'/>"   onclick="likeInsert('${reply.STORE_NO}');">													
											</c:otherwise>							
										</c:choose>						
									</div>
								</c:when>
								<c:otherwise>
									<div id="likeDiv">
										<c:choose>
											<c:when test="${reply.LIKE}">
												<input type="image"  class="reply_heart" id="unlikeButton" src="<c:url value='/resources/img/heart_click.png'/>">					
											</c:when>
											<c:otherwise>
												<input type="image" class="reply_heart"  id="likeButton"   src="<c:url value='/resources/img/heart_unclick.png'/>">													
											</c:otherwise>							
										</c:choose>						
									</div>
								</c:otherwise>
							</c:choose>	
							</td>
						</tr>
						<tr class="mypage_reply_tr03">
							<td></td>
							<td class="height03" rowspan="2">
							<span style="font-size:17px;word-break:break-all; ">${reply.REPLY_CONTENTS}</span>
							<br>
							<div style="font-size:12px; ">${reply.REPLY_INDATE}</div>
							</td>
						</tr>
					</table>
				</div>
		</c:forEach>
	</div>
	
	

</body>
</html>