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
	font-family: JejuGothic;
	font-weight: bold;
	font-size: 40px;
	line-height: 40px;
}


#followButton{
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

#followButton:hover {
    background-color: #FFC107;
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

.mypage_button_on{

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

.mypage_button_off{
   background-color: #FFC107;
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

.mypage_button_off:hover {
    background-color: #FF8A00;
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
	width:75px; 
}
.reply_user_profile{
	position: absolute;
	width: 55px;
	height: 55px;
	left : 15px;
	top : 20px;
	margin : 10px 5px;
}
.mypage_reply_td02{
	width:200px; 
	height:200px;
	padding : 0 15px;
}
.reply_store_thum{
	position: absolute;
	top : 20px;
	left : 75x;
	width:170px; 
	height:170px;
	border-radius:5px;
	margin : 10px 5px;
}
.height01{height : 60px; padding : 5px 5px;}
.height02{height : 10px; padding : 5px 5px;}
.height03{height : 110px; padding : 5px 5px;}

.mypage_reply_td03{
	width:550px; 
}
.mypage_reply_td04{
	width:75px; 
	height:75px;
}

.reply_map{
	position: absolute;
	top : 40px;
	right : 10px;
	width: 75px;
	height: 70px;
}
.reply_heart{
	position: absolute;
	top : 140px;
	width: 50px;
	height: 50px;
}

footer{
	position: absolute;
	top: 550px;
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
				<td class="mypage_profile_user_name">&nbsp;${user.user_name}
					
					<c:if test="${sessionScope.loginemail != null}">
						<c:if test="${user.user_email != sessionScope.loginemail}">
							<button id="followButton">팔로우</button>
						</c:if>
					</c:if>	
				</td>
				<td>								
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" style="font-size: 15px; font-weight: bold;">&nbsp;&nbsp;&nbsp;좋아요 : ${like}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 팔로워 : ${follow}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 팔로우 : ${follower}</td>
			</tr>
		</table>
	</div>
	<div class="mypage_buttons">
		<table class="mypage_buttons_table">
			<tr>
				<td><button class="mypage_button_on">내 코멘트</button></td>
				<td><button  class="mypage_button_off">친구들의 코멘트</button></td>
				<td><button  class="mypage_button_off">내가 좋아하는 가게들</button></td>
			</tr>
		</table>
	</div>
	
	<div class="mypage_replies">
				<div>
					<table class="mypage_reply_one" >
						<tr class="mypage_reply_tr01">
							<td class="mypage_reply_td01"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default_2.png'/>" alt="프로필 사진"></td>
							<td class="mypage_reply_td02" rowspan="4" >
								<img class="reply_store_thum"src="<c:url value='/resources/img/thum_test.JPG'/>" alt="가게 썸네일 - 아직없음">							
							</td>
							<td class="mypage_reply_td03, height01">가게이름${reply.store_name} 가게 정보${reply.reply_star}</td>
							<td  class="mypage_reply_td04">
								<img class="reply_map"src="<c:url value='/resources/img/map_logo.png'/>" alt="지도연결 버튼">		
							</td>
						</tr>
						<tr class="mypage_reply_tr02">
							<td></td>
							<td class="height02">동명동 ${reply.store_adr}</td>
							<td rowspan="2">
								<form id="likeForm">
									<input type="hidden" id="user_email"   name="user_email" value="${reply.user_email}">
									<input type="hidden" id="store_no"	   name="store_no"   value="${reply.store_no}">
									<input type="image" class="reply_heart"  id="likeButton"   value="좋아요" onclick="likeInsert();"  src="<c:url value='/resources/img/heart_unclick.png'/>">
								</form>
							</td>
						</tr>
						<tr class="mypage_reply_tr03">
							<td></td>
							<td class="height03" rowspan="2">
							${reply.reply_contents}
							<br>
							<div>코멘트 입력 날짜${reply.reply_indate} 좋아요 갯수 - 아직없음</div>
							</td>
						</tr>
					</table>
				</div>
	</div>
	
	
<footer>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
</body>
</html>