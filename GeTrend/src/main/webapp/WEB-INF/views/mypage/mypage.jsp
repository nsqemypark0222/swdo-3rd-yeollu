<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mypage test ]</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


<script type="text/javascript">

function insertFollow(follows_following){
	location.href = "/getrend/insertFollow?follows_following=" + follows_following;	
	
}



function deleteFollow(follows_following){	
	location.href = "/getrend/deleteFollow?follows_following=" + follows_following;	
}


//별
$(function(){

	$(".starMake").each(function(index,item){
		var star = $(this).text();
		
		if(star == 0.5) $(this).html("<img style='width:19px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>");
		else if(star == 1) $(this).html("<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>");
		else if(star == 1.5) {
			var temp = "<img style='width:19px;' src='<c:url value="/resources/img/star.png"/>' alt='★'>";
				temp += "<img style='width:19px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
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
				temp += "<img style='width:19px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
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
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
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
			temp += "<img style='width:19px;' src='<c:url value="/resources/img/star_off.png"/>' alt='☆'>";
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


	
function leftArrow(){
	var rightPos = $('#scroll_box').scrollLeft();
	$("#scroll_box").animate({scrollLeft: rightPos - 270}, 200);

}
function rightArrow(){
	var leftPos = $('#scroll_box').scrollLeft();
	$("#scroll_box").animate({scrollLeft: leftPos + 270}, 200);
}


$(function() {

    $('.button-class1').click(function(){
    	 if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
         if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
         if( $('.button-class2').hasClass('btn_on') ) $('.button-class2').removeClass('btn_on');
         if( !$('.button-class2').hasClass('btn_off') ) $('.button-class2').addClass('btn_off');
         if( $('.button-class3').hasClass('btn_on') ) $('.button-class3').removeClass('btn_on');
         if( !$('.button-class3').hasClass('btn_off') ) $('.button-class3').addClass('btn_off');	
    });
    $('.button-class2').click(function(){
    	 if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
         if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
         if( $('.button-class1').hasClass('btn_on') ) $('.button-class1').removeClass('btn_on');
         if( !$('.button-class1').hasClass('btn_off') ) $('.button-class1').addClass('btn_off');
         if( $('.button-class3').hasClass('btn_on') ) $('.button-class3').removeClass('btn_on');
         if( !$('.button-class3').hasClass('btn_off') ) $('.button-class3').addClass('btn_off');
    });
    
    $('.button-class3').click(function(){
    	if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
        if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
        if( $('.button-class1').hasClass('btn_on') ) $('.button-class1').removeClass('btn_on');
        if( !$('.button-class1').hasClass('btn_off') ) $('.button-class1').addClass('btn_off');
        if( $('.button-class2').hasClass('btn_on') ) $('.button-class2').removeClass('btn_on');
        if( !$('.button-class2').hasClass('btn_off') ) $('.button-class2').addClass('btn_off');	
    });

});


function likeStore(user_email){     
	$.ajax({
		url : "/getrend/mypage/likeStoreList",
		type : "post",
		data :  {"user_email" : user_email},
		success : function(result){ 
			$(".mypage_list_table").children().html('');
			if(result.length == 0){			
				var temp = '<td style="width : 750px;">';
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/nostore.png" alt="프로필 사진"></div>';													
					temp += '<div style="width : 750px; font-size:14px; text-align:center;">관심있는 가게가 아직 없습니다</div>';
					temp += '</td>';
					 $(".mypage_list_table").children().append(temp);
				}
			else{
           		 $(result).each(function(index, item){
					var temp = '<td style="width : 200px;">';
						temp += '<div style="width : 200px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/cate/'+ item.CATE1 +'" alt="프로필 사진"></div>';				
						temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.STORE_NAME+'</div>';
						temp += '</td>';							
			$(".mypage_list_table").children().append(temp);
                })
			}  
		},
		error : function(){alert("실패");}
	})
	
}
function follower(user_email){   
	$.ajax({
		url : "/getrend/mypage/followerList",
		type : "post",
		data :  {"follows_following" : user_email},
		success : function(result){ 
			$(".mypage_list_table").children().html('');
			if(result.length == 0){			
				var temp = '<td style="width : 750px;">';
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/nofollow.png" alt="프로필 사진"></div>';													
					temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로워가 아직 없습니다</div>';
					temp += '</td>';
					 $(".mypage_list_table").children().append(temp);
				}
			else{
				$(result).each(function(index, item){
					var temp = '<td style="width : 200px;">';
						temp += '<div style="width : 200px; text-align:center;"><a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'">';
						if(item.USER_PROFILE == undefined){
						temp += '<img class="reply_cate_profile" src="/getrend/resources/img/user.png" alt="프로필 사진"></a></div>';								
						}else{
						temp += '<img class="reply_cate_profile" src="'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';								
						}
						temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.USER_NAME+'</div>';
						temp += '</td>';							
				        $(".mypage_list_table").children().append(temp);
					
	             })
			}
		},
		error : function(){alert("실패");}
	})
	

	
}
function follow(user_email){    
	$.ajax({
		url : "/getrend/mypage/followList",
		type : "post",
		data :  {"user_email" : user_email},
		success : function(result){ 
			$(".mypage_list_table").children().html('');
			if(result.length == 0){			
				var temp = '<td style="width : 750px;">';
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/nofollow.png" alt="프로필 사진"></div>';													
					temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로우가 아직 없습니다</div>';
					temp += '</td>';
					 $(".mypage_list_table").children().append(temp);
				}
			else{
            $(result).each(function(index, item){
					var temp = '<td style="width : 200px;">';
						temp += '<div style="width : 200px; text-align:center;"><a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'">';
						if(item.USER_PROFILE == undefined){
						temp += '<img class="reply_cate_profile" src="/getrend/resources/img/user.png" alt="프로필 사진"></a></div>';								
						}else{
						temp += '<img class="reply_cate_profile" src="'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';								
						}
						temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.USER_NAME+'</div>';
						temp += '</td>';							
					$(".mypage_list_table").children().append(temp);
                })
			}  
		},
		error : function(){alert("실패");}
	})	
}

$(function(){
	var user_email = $("#user_email").val();
	var login_email = $("#login_email").val();
	if(user_email == login_email){
		$('.table-light').hover(
		        function () {
		        	$(this).children().children(".deleteButton").attr("src","/getrend/resources/img/delete.png");
		        },
		        function () {
		        	$(this).children().children(".deleteButton").attr("src","/getrend/resources/img/white.png");
		        }
		    );
	} 
})

function deleteReply(no,user_name){
	location.href="/getrend/mypage/deleteReply?reply_no=" + no + "&user_name=" + user_name;
}

function moreRead(){
	if($("#replyTable02").css("display") == 'none'){
		$("#readMoreBtn").css("display","none");
		$("#readMoreSpin").css("display","");
		setTimeout(function(){ 
			$("#readMoreSpin").css("display","none");
			$("#replyTable01").css("display","none");
			$("#replyTable02").css("display","");	
			},1000);
		
		
	}
}
</script>

<style>

.mypage_profile{
	position: relative;
	width: 900px;
	height: 240px;
	left: 100px;
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
	width: 120px;
	height: 180px;
	left: 390px;
	top: 28px;
}

.mypage_profile_user_name{
	height: 30px;
}
.mypage_profile_user_name{
	text-align : center;
	font-weight: bold;
	font-size: 30px;
}
.user_profile{
	width : 120px;
}
.user_profile_de{
	width : 120px;
}


#followBtn{
   background-color: #fff;
   font-size: 15px;
   font-weight: bold;
   width:70px; 
   height:30px;
   border: none;
   color:#a6a6a6;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   cursor: pointer;
   border-radius:20px;
}
#followBtn:focus{
	border : none;
	outline:none;
}
#followBtn:hover {
    color: #FF8A00;
    transition:color .2s ease;
}

#unfollowBtn{
   background-color: #fff;
   font-family: JejuGothic;
   font-size: 14px;
   font-weight: bold;
   width:70px; 
   height:30px;
   border: none;
   color:#FF8A00;
   text-align: center;
   text-decoration: none;
   display: inline-block;
   cursor: pointer;
   border-radius:20px;
}

#unfollowBtn:focus{
	border : none;
	outline:none;
}
#unfollowBtn:hover {
    background-color: #fff;
    transition:color .2s ease;
}

.mypage_buttons{
	position: relative;
	width: 900px;
	height: 55px;
	left: 100px;
	top : 20px;
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
}
.mypage_button:focus{
	outline:none;
	transition:color .2s ease;
}
.btn_on{
	color:#FF8A00;
	border-bottom : 3px solid #FF8A00;
}
.btn_on:focus{
	outline:none;
	transition:border-bottom .2s ease;
}
.btn_off{
	color : #000;
	border : none;
}

.mypage_list{
	position: relative;
	width: 900px;
	height: 130px;
	left: 100px;
	top : 40px;
}

.mapDiv{
	position: relative;
	width: 900px;
	height : 300px;
	left: 100px;
	top: 20px;
	background-color : gray;
}

.noReply{
	position: relative;
	margin : 100px 300px;

}

.mypage_replies{
	position: relative;
	width: 900px;
	left: 100px;
	top:34px;

}
.reply_cate_profile{
	width: 55px;
	height: 55px;
	margin : 10px 10px;
}

.reply_cate_name{
	text-align : center;
	color : #FF8A00;
}
.reply_heart_on{
	width: 28px;
	height: 28px;
}
.reply_heart_off{
	width: 25px;
	height: 25px;
}
.reply_map{
	width: 23px;
	height: 23px;
	margin : 3px 5px;
}

.reply_outer{
	width : 720px;
	margin-left : 70px;
}
.reply_inner01{
	float : left;
	width : 15%;
}
.reply_inner02{
	float : left;
	width : 85%;
}

.readAction{
	position: relative;
	left : 390px;
	width : 300px;
}



@media(max-width: 1360px){
	.container{
		width : 100%;
	}
}

@media (max-width: 1100px){
	.mypage_profile{width : 100%; left:0;}
	.mypage_buttons{width : 100%; left:0;}
	.mypage_list{width : 100%; left:0;}
	.mypage_replies{width : 100%; left:0;}
}





#scroll_box2{width: 900px; height: 420px; overflow-y: scroll; }
#scroll_box2::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  border: 3px solid #fff;
}
#scroll_box2::-webkit-scrollbar-button:start:decrement,
::-webkit-scrollbar-button:end:increment {
  display: block;
  height: 5px;
  background: #fff;
}
#scroll_box2::-webkit-scrollbar-track {
  background: #fff;
  -webkit-border-radius: 10px;
  border-radius:10px;
}
#scroll_box2::-webkit-scrollbar-thumb {
  height: 50px;
  width: 50px;
  background: rgba(0,0,0,.2);
  -webkit-border-radius: 8px;
  border-radius: 8px;
}


</style>

</head>
<body>



<header>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>


<div class="container">
	<div class="row">
		<div class="col">
			<input type="hidden" id="user_email" value="${user.user_email}">			
			<input type="hidden" id="login_email" value="${sessionScope.loginemail}">			
			<div class="mypage_profile">
				<table class="mypage_profile_table">
					<tr>
						<tr><td>
						<c:choose>
							<c:when test="${user.user_email != sessionScope.loginemail}">
								<c:if test="${user.user_profile != null}">
							        <img class="user_profile" src="${user.user_profile}" alt="프로필 사진">
							    </c:if>
							    <c:if test="${user.user_profile == null}">
									<img class="user_profile_de" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진">
							    </c:if>
							</c:when>					
							<c:otherwise>
								<c:if test="${user.user_profile != null}">
								  <a href="/getrend/users/userUpdate">
							        <img class="user_profile" src="${user.user_profile}" alt="프로필 사진">
							      </a>
							    </c:if>
							    <c:if test="${user.user_profile == null}">
							      <a href="/getrend/users/userUpdate">
									<img class="user_profile_de" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진">
							      </a>
							    </c:if>
							</c:otherwise>
						</c:choose>
						</td></tr>
						
						
						<tr class="mypage_profile_user_name">
							<td>${user.user_name}</td>
						<tr>	
							<c:if test="${sessionScope.loginemail != null}">
								<c:if test="${user.user_email != sessionScope.loginemail}">
								<c:choose>
									<c:when test="${following}">						
											<td style="padding : 0 25px;"><div id="followDiv"><input type="button" id="unfollowBtn"  data-toggle="modal" data-target="#exampleModalCenter" value="팔로잉" ></div></td>
									</c:when>
									<c:otherwise>
											<td style="padding : 0 25px;"><div id="followDiv"><input type="button" id="followBtn" value="팔로우"  data-toggle="modal" data-target="#followModalCenter"></div></td>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:if>	
					   </tr>
				</table>
			</div>
		</div>
	</div>

	
	
	<div class="row">
		<div class="col">
			<div class="mypage_buttons">
				<table class="mypage_buttons_table">
					<tr>
						<td><button class="mypage_button btn_on button-class1" onclick="likeStore('${user.user_email}')">관심있는 가게   ${like}</button></td>
						<td><button class="mypage_button btn_off button-class2" onclick="follow('${user.user_email}')">팔로우   ${follow}</button></td>
						<td><button class="mypage_button btn_off button-class3" onclick="follower('${user.user_email}')">팔로워   ${follower}</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="row">
	   <div class="col">
			<div class="mypage_list">
				<div class="row">
				<div class="col" style="padding-top:25px;"><input type="image" style="width : 25px; float:left;" src="/getrend/resources/img/left-arrow.png"  onclick="leftArrow()"></div>
				<div class="col-10" >	
					<div class="scroll"> 
					<div id="scroll_box" style="width: 800px; height: 100px; overflow-x: hidden; overflow-y: hidden; " >
						
						<table class="mypage_list_table">
							<tr>
								<c:choose>
									<c:when test="${empty likeList}">
									<td style="width : 750px;">
										<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/nostore.png" alt="프로필 사진"></div>													
										<div style="width : 750px; font-size:14px; text-align:center;">관심있는 가게가 아직 없습니다</div>
									</td>
									</c:when>
									<c:otherwise>
										<c:forEach var="list" items="${likeList}">
											<td style="width : 200px;">
												<div style="width : 200px;text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/cate/${list.CATE1}" alt="프로필 사진"></div>
												<div style="width : 200px; font-size:14px; text-align:center;">${list.STORE_NAME}</div>								
											</td>
										</c:forEach>
									</c:otherwise>
								</c:choose>							
							</tr>
						</table>
						
					</div>
					</div>
				</div>	
				<div class="col" style="padding-top:25px;"><input type="image" style="width : 25px; float: right;" src="/getrend/resources/img/right-arrow.png"  onclick="rightArrow()"></div>
				</div>
			</div>
		</div>	
	</div>

	<div class="row">
		<div class="col">
			<div class="mypage_replies">
			<c:choose>
				<c:when test="${empty replyList}">
					<div class="noReply">
						<div style="position:relative;">
						<img src="/getrend/resources/img/noreply.png" style="position:relative; width : 100px; left:100px;" alt="댓글없음"><br><br><br>
						 <div style="text-align : center;"><h6>남긴 댓글이 아직 없습니다</h6></div>
						 </div>
					</div>
				</c:when>
				<c:otherwise>
					  <div> 
						<div>											
							<table class="table table-hover" id="replyTable01">				
								<c:forEach var="reply" items="${replyList}" begin="0" end="2">
									<tr class="table-light">
										<td>
											<div class="reply_outer">						          
												<div class="reply_inner01">		          
													<strong class="d-inline-block mb-2" style="color:#FF8A00;">
														<img class="reply_cate_profile" src="/getrend/resources/img/cate/${reply.CATE1}" alt="프로필 사진">
														<div class="reply_cate_name">${reply.CATE2}</div>
													</strong>
												</div>						          
													<div class="reply_inner02">
														<h3 class="mb-0">
															<a class="text-dark" href="/getrend/stores/istoreInfo?store_no=${reply.STORE_NO}">${reply.STORE_NAME}</a>					
															<a href="https://map.kakao.com/link/to/${reply.STORE_NAME},${reply.STORE_Y},${reply.STORE_X}"><input type="image"  class="reply_map"src="<c:url value='/resources/img/place.png'/>"></a>
														</h3>			
													<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
													<p class="card-text mb-auto" style="word-break:break-word;">${reply.REPLY_CONTENTS}</p>		          
													<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						    					 </div>	       
											</div>
											<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
												<div class="deleteButton" style="float : right;">
													<img style="width:15px; cursor: pointer;" src="/getrend/resources/img/delete.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${reply.USER_NAME}')">
												</div> 
											</c:if>
										</td>	
									 </tr>				
								    </c:forEach>    								
						   	 </table>
						   	 <c:if test="${fn:length(replyList) > 3}">
							   	<div class="readAction">
		    						<button id="readMoreBtn" class="btn btn-outline-secondary btn-lg" onclick="moreRead();"> 더보기 + </button>
		    						<img id="readMoreSpin" src="/getrend/resources/img/Spinner.gif" style="display:none; width : 100px;">
	    						</div>
						   	 </c:if>
    						
    						<table class="table table-hover" id="replyTable02" style="display:none;">				
								<c:forEach var="reply" items="${replyList}">
									<tr class="table-light">
										<td>
											<div class="reply_outer">						          
												<div class="reply_inner01">		          
													<strong class="d-inline-block mb-2" style="color:#FF8A00;">
														<img class="reply_cate_profile" src="/getrend/resources/img/cate/${reply.CATE1}" alt="프로필 사진">
														<div class="reply_cate_name">${reply.CATE2}</div>
													</strong>
												</div>						          
													<div class="reply_inner02">
														<h3 class="mb-0">
															<a class="text-dark" href="/getrend/stores/istoreInfo?store_no=${reply.STORE_NO}">${reply.STORE_NAME}</a>					
															<a href="https://map.kakao.com/link/to/${reply.STORE_NAME},${reply.STORE_Y},${reply.STORE_X}"><input type="image"  class="reply_map"src="<c:url value='/resources/img/place.png'/>"></a>
														</h3>			
													<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
													<p class="card-text mb-auto" style="word-break:break-word;">${reply.REPLY_CONTENTS}</p>		          
													<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						    					 </div>	       
											</div>
											<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
												<div class="deleteButton" style="float : right;">
													<img style="width:15px;" src="/getrend/resources/img/delete.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${reply.USER_NAME}')">
												</div> 
											</c:if>
										</td>	
									 </tr>				
								    </c:forEach>    								
						   	 </table>		
					    	</div>
						</div>				    
					</c:otherwise>
				</c:choose>	
			</div>				
		</div>
	</div>
</div>

<!--팔로우 Modal -->
<div class="modal fade" id="followModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center">
        <br>
        <c:if test="${user.user_profile != null}">
        <h5><img class="reply_user_profile" style="width : 100px;" src="${user.user_profile}" alt="프로필 사진"></h5>
        </c:if>
        <c:if test="${user.user_profile == null}">
        <h5><img class="reply_user_profile" style="width : 100px;" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진"></h5>
        </c:if>
		<br><br>
		<p style="font-size : 15px;">${user.user_name}님을 팔로우하시겠어요? </p>
		<br>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-warning" data-dismiss="modal" onclick="insertFollow('${user.user_email}');">팔로우</button>
      </div>
    </div>
  </div>
</div>

<!--언팔로우 Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center">
        <br>
        <c:if test="${user.user_profile != null}">
        <h5><img class="reply_user_profile" style="width : 100px;" src="${user.user_profile}" alt="프로필 사진"></h5>
        </c:if>
        <c:if test="${user.user_profile == null}">
        <h5><img class="reply_user_profile" style="width : 100px;" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진"></h5>
        </c:if>
		<br><br>
		<p style="font-size : 15px;">${user.user_name}님의 팔로우를 취소하시겠어요? </p>
		<br>	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-warning" data-dismiss="modal" onclick="deleteFollow('${user.user_email}');">팔로우 취소</button>
      </div>
    </div>
  </div>
</div>




<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
	
</body>
</html>