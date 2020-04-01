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
	width: 413.96px;
	height: 130px;
	left: 50px;
	top: 50px;
}


.mypage_profile_user_name{
	font-family: JejuGothic;
	font-style: normal;
	font-weight: normal;
	font-size: 40px;
	line-height: 40px;
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
   width:290px;
   border: none;
   color:#000;
   padding: 15px 0;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   font-size: 15px;
   margin: 4px;
   cursor: pointer;
   border-radius:10px;
}

.mypage_button_off{
   background-color: #FFC107;
   width:290px;
   border: none;
   color:#000;
   padding: 15px 0;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   font-size: 15px;
   margin: 4px;
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
	top: 460px;

}
.mypage_reply_one{
	position: absolute;
	background-color: #FAFAFA; 
	border-radius:20px;
	width: 900px;
	height: 300px;
	top: 10px;
}
.mypage_reply_tr01{
	width:900px; 
}
.mypage_reply_td01{
	width:75px; 
	height:75px;
}
.reply_user_profile{
	position: absolute;
	width: 60px;
	height: 60px;
	left : 10px;
	top : 10px;

}
.mypage_reply_td02{
	width:200px; 
	height:200px;
}
.reply_store_thum{
	margin:10px; 
	width:190px; 
	height:190px;
	border-radius:10px;
}

.height01{height : 120px;}
.height02{height : 35px;}
.height03{height : 35px;}

.mypage_reply_td03{
	width:550px; 
}
.reply_store_adr{
	width:550px; 
	text-align : left;
	padding : 5px;
}

.mypage_reply_td04{
	width:75px; 
	height:75px;
}

.mypage_reply_contents{
	width:750px;
}
.mypage_reply_tr02{
}
.mypage_reply_tr03{
}
.mypage_reply_tr04{
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
				<td class="mypage_profile_user_name">${user.user_name}</td>
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
				<td colspan="3">좋아요 : ${like}&nbsp;&nbsp; 팔로워 : ${follow}&nbsp;&nbsp; 팔로우 : ${follower}</td>
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
		<c:if test="${replyList != null}">
			<c:forEach var="reply" items="${replyList}">
				<div >
					<table class="mypage_reply_one"border="1">
						<tr class="mypage_reply_tr01">
							<td class="mypage_reply_td01"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></td>
							<td class="mypage_reply_td02" rowspan="3" >
								<img class="reply_store_thum"src="<c:url value='/resources/img/thum_test.JPG'/>" alt="가게 썸네일 - 아직없음">							
							</td>
							<td class="mypage_reply_td03, height01">가게이름${reply.store_name}</td>
							<td class="mypage_reply_td04">지도 버튼</td>
						</tr>
						<tr class="mypage_reply_tr02">
							<td></td>
							<td class="height02">동명동${reply.store_adr}</td>
							<td>
								<form id="likeForm">
									<input type="hidden" id="user_email"   name="user_email" value="${reply.user_email}">
									<input type="hidden" id="store_no"	   name="store_no"   value="${reply.store_no}">
									<input type="button" id="likeButton"   value="좋아요" onclick="likeInsert();">
								</form>
							</td>
						</tr>
						<tr class="mypage_reply_tr03">
							<td></td>
							<td class="height03">가게 정보${reply.reply_star}</td>
							<td></td>
						</tr>
						<tr class="mypage_reply_tr04">
							<td></td>
							<td colspan="2" class="mypage_reply_contents">코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트
							코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트
							코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트코멘트
							${reply.reply_contents}</td>
							<td></td>
						</tr>
						<tr class="mypage_reply_tr05">
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