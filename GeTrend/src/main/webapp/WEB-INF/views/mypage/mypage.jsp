<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mypage test ]</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">



<script type="text/javascript">

function insertFollow(follows_following){
	$.ajax({
		url : "/getrend/insertFollow",
		type : "post",
		data : {"follows_following" : follows_following},
		success : function(result){ 
			var	temp = '<input type="button"  id="unfollowBtn" value="언팔로우"  data-toggle="modal" data-target="#exampleModalCenter">';
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
		$(this).css("color", "rgb(255, 193, 7)");
	});
})


//별
$(function(){

	$(".starMake").each(function(index,item){
		var star = $(this).text();
		
		if(star == 0.5) $(this).html("<img style='width:10px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>");
		else if(star == 1) $(this).html("<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>");
		else if(star == 1.5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
				temp += "<img style='width:10px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
				$(this).html(temp);
		}
		else if(star == 2) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			    temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			$(this).html(temp);
		}
		else if(star == 2.5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			    temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
				temp += "<img style='width:10px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
			$(this).html(temp);
		}
		else if(star == 3) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
				temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
				temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			$(this).html(temp);
		}
		else if(star == 3.5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:10px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
			$(this).html(temp);
		}
		else if(star == 4) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			$(this).html(temp);
		}
		else if(star == 4.5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:10px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
			$(this).html(temp);
		}
		else if(star == 5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
			$(this).html(temp);
		}
	})
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
	border-radius:5px;
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
	margin : 0 10px;
	font-weight: bold;
	font-size: 40px;
}


#followBtn{
   background-color: #fff;
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

   background-color: #fff;
   font-weight: bold;
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


.mypage_replies{
	position: absolute;
	width: 900px;
	left: 300px;
	top: 450px;

}
.reply_user_profile{
	width: 55px;
	height: 55px;
	margin : 10px 10px;
}
.reply_map{
	width: 28px;
	height: 28px;
	vertical-align: middle;
}
.reply_heart{
	width: 30px;
	height: 30px;
	vertical-align: middle;
}

</style>

</head>
<body>
<header>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>


	<input type="hidden" id="user_email" value="${user.user_email}">	
	
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
									<div style="float:right;margin:0 30px;" id="followDiv"><input type="button" id="unfollowBtn"  data-toggle="modal" data-target="#exampleModalCenter" value="언팔로우" ></div>
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
				<td style="font-size: 15px; font-weight: bold;">&nbsp;&nbsp;&nbsp;<span id="likeStoreList">관심있는 가게 : ${like}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="followList">팔로우 : ${follow}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="followerCnt" style="float:right;"><span id="followerList">팔로워 : ${follower}</span></div></td>
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
			<div class="card flex-md-row mb-4 shadow-sm h-md-250">
		        <div class="card-body d-flex flex-column align-items-start">
		          <strong class="d-inline-block mb-2" style="color:#FF8A00;">
		       			<a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default_2.png'/>" alt="프로필 사진"></a>
		        		  ${reply.USER_NAME}
		          </strong>
		          <h3 class="mb-0">
		            <a class="text-dark" href="#">${reply.STORE_NAME}</a>
		            
		          </h3>
		          <div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
		          <p class="card-text mb-auto">${reply.REPLY_CONTENTS}</p>
		          
		          <div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
		          <c:choose>
					<c:when test="${reply.USER_EMAIL == sessionScope.loginemail}">
						<div id="likeDiv">
							<c:choose>
								<c:when test="${reply.LIKE}">
									<img class="reply_map"src="<c:url value='/resources/img/place.png'/>" alt="지도연결 버튼">	
									<input type="image"  class="reply_heart" id="unlikeButton" src="<c:url value='/resources/img/heart_click.png'/>"  onclick="likeDelete('${reply.STORE_NO}');">					
								</c:when>
								<c:otherwise>
									<img class="reply_map"src="<c:url value='/resources/img/place.png'/>" alt="지도연결 버튼">	
									<input type="image" class="reply_heart"  id="likeButton"   src="<c:url value='/resources/img/heart_unclick.png'/>"   onclick="likeInsert('${reply.STORE_NO}');">													
								</c:otherwise>							
							</c:choose>						
						</div>
					</c:when>
					<c:otherwise>
						<div id="likeDiv">
							<c:choose>
								<c:when test="${reply.LIKE}">
									<img class="reply_map"src="<c:url value='/resources/img/place.png'/>" alt="지도연결 버튼">	
									<input type="image"  class="reply_heart" id="unlikeButton" src="<c:url value='/resources/img/heart_click.png'/>">					
								</c:when>
								<c:otherwise>
									<img class="reply_map"src="<c:url value='/resources/img/place.png'/>" alt="지도연결 버튼">	
									<input type="image" class="reply_heart"  id="likeButton"   src="<c:url value='/resources/img/heart_unclick.png'/>">													
								</c:otherwise>							
							</c:choose>						
						</div>
					</c:otherwise>
				</c:choose>	
		        </div>
		     </div> 
		</c:forEach>
	</div>
	
	
	  


    
	
	
	
<!--언팔로우 Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">언팔로우</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center">
        
        <h5 ><img class="reply_user_profile" class="rounded-circle" src="<c:url value='/resources/img/profile_default_2.png'/>" alt="프로필 사진"></h5>
		<p>${user.user_name}님의 팔로우를 취소하시겠어요? </p>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-outline-warning" onclick="deleteFollow('${user.user_email}');">언 팔로우</button>
      </div>
    </div>
  </div>
</div>
	

</body>
</html>