<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	$.ajax({
		url : "/getrend/insertFollow",
		type : "post",
		data : {"follows_following" : follows_following},
		success : function(result){ 
			var	temp = '<input type="button"  id="unfollowBtn" value="팔로잉"  data-toggle="modal" data-target="#exampleModalCenter">';
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
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/" alt="프로필 사진"></div>';													
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
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/" alt="프로필 사진"></div>';													
					temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로워가 아직 없습니다</div>';
					temp += '</td>';
					 $(".mypage_list_table").children().append(temp);
				}
			else{
				$(result).each(function(index, item){
					var temp = '<td style="width : 200px;">';
						temp += '<div style="width : 200px; text-align:center;"><a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'"><img class="reply_cate_profile" src="/getrend/resources/img/'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';								
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
					temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/" alt="프로필 사진"></div>';													
					temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로우가 아직 없습니다</div>';
					temp += '</td>';
					 $(".mypage_list_table").children().append(temp);
				}
			else{
            $(result).each(function(index, item){
					var temp = '<td style="width : 200px;">';
						temp += '<div style="width : 200px; text-align:center;"><a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'"><img class="reply_cate_profile" src="/getrend/resources/img/'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';				
						temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.USER_NAME+'</div>';
						temp += '</td>';							
					$(".mypage_list_table").children().append(temp);
                })
			}  
		},
		error : function(){alert("실패");}
	})
	

	
}

</script>

<style>


.mypage_profile{
	position: relative;
	width: 900px;
	height: 240px;
	left: 300px;
	top: 15px;
	border-radius:5px;
	background-color: #FF8A00;
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

.mypage_buttons{
	position: relative;
	width: 900px;
	height: 55px;
	left: 300px;
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

.btn_on{
	color:#FF8A00;
	border-bottom : 3px solid #FF8A00;
}

.btn_off{
	color : #000;
	border : none;
}

.mypage_list{
	position: relative;
	width: 900px;
	height: 130px;
	left: 300px;
	top : 40px;
}

.mapDiv{
	position: relative;
	width: 900px;
	height : 300px;
	left: 300px;
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
	left: 300px;
	top:20px;

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


<div class="conainter">
	<div class="row">
		<div class="col">
			<input type="hidden" id="user_email" value="${user.user_email}">			
			<div class="mypage_profile">
				<table class="mypage_profile_table">
					<tr>
						<tr><td><a href="/getrend/users/userUpdate"><img class="user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></a></td></tr>
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
											<td style="padding : 0 25px;"><div id="followDiv"><input type="button" id="followBtn" value="팔로우" onclick="insertFollow('${user.user_email}');"></div></td>
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
				<div class="col" style="padding-top:25px;"><button class="btn btn-outline-warning btn-sm" onclick="leftArrow()">◁</button></div>
				<div class="col-10" >	
					<div class="scroll"> 
					<div id="scroll_box" style="width: 800px; height: 100px; overflow-x: hidden; overflow-y: hidden; " >
						
						<table class="mypage_list_table">
							<tr>
								<c:choose>
									<c:when test="${empty likeList}">
									<td style="width : 750px;">
										<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="/getrend/resources/img/'+ item.USER_PROFILE +'" alt="프로필 사진"></div>													
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
				<div class="col" style="padding-top:25px;"><button  class="btn btn-outline-warning btn-sm"  onclick="rightArrow()">▷</button></div>
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row">
		<div class="col">
			<div class="mapDiv">
				지도
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
						<img src="/getrend/resources/img/cate/others.png" style="position:relative; width : 100px; left:100px;" alt="댓글없음"><br><br><br>
						 <div style="text-align : center;"><h6>남긴 댓글이 아직 없습니다</h6></div>
						 </div>
					</div>
				</c:when>
				<c:otherwise>
					  <div class="scroll2"> 
						<div id="scroll_box2" >											
							<table class="table table-hover">				
								<c:forEach var="reply" items="${replyList}" >
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
															<a class="text-dark" href="#">${reply.STORE_NAME}</a>					
															<input type="image"  class="reply_map"src="<c:url value='/resources/img/place.png'/>">			            		            
														</h3>			
													<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
													<p class="card-text mb-auto">${reply.REPLY_CONTENTS}</p>		          
													<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						    					 </div>		       
											</div> 
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

<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</footer>
	
</body>
</html>