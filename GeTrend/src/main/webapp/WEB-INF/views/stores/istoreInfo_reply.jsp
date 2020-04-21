<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>[ ${istore.instaStore.store_name} | GeTrend ]</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>

<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<link rel= "stylesheet" type="text/css" href="../resources/css/reply.css">
<style>
  * {margin: 0; padding: 0;}
		header{height : 100px;}
		header .header_search, header .header_menu{display : none;}		
        #wrap{width: 800px; margin: 100px auto; text-align: center;}
        #banner {width: 100%; height: 400px;   display: inline-block;}
        
           /* 화면 너비 0 ~ 1200px */
        @media (max-width: 1240px){
             #wrap {width: 100%; margin: 100px auto; text-align: center;}
		}
        
        /* 화면 너비 0 ~ 768px */
        @media (max-width: 768px){
        	#banner{width : 100%;} 
        	.text_wrap{width : 98%}
        	#reply_contents{width : 95%;}
		}
        
        /* 화면 너비 0 ~ 480px */
        @media (max-width: 480px){
            #banner{width : 100%; } 
        	.text_wrap{width : 98%}
        	#reply_contents{width : 95%;}
		}
		    
		.starRev {
			display: inline-block;
			margin: 10px auto;
		}
		
		.text_wrap{
		 	display: inline-block;
		    border: 1px solid #DBDBDB;
		    border-radius: 3px;
		    box-sizing: border-box;
		    padding: 16px;
		
		}
		
		#err{
		height : 20px;
		}
		
		.btn_wrap{
		 display: inline-block;
		 margin: 20px auto;
		}
		
	

</style>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	<div id="wrap">
		<section id="banner">
		<form action="/getrend/reply/replyWrite"  method="post" onsubmit="return check()">
		    	<input type="hidden" id="store_no" name="store_no" value="${store_no}" />
					<h3>${sessionScope.loginname}님, <span style="color: #FF8A00;">${store_name}</span>에 댓글을 남겨주세요</h3>
					<div class="starRev">
					  <input type='hidden' name='reply_star' id='reply_star' value="0">
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
					<br>
					<div class="text_wrap">
						<textarea id="reply_contents" name="reply_contents" maxlength="10000" placeholder="좋아하는 가게를 친구들과 공유해 보세요"
						style="overflow: hidden; overflow-wrap: break-word; height: 150px; width : 700px; border:none; "></textarea>						
					</div>
					<div id="err"></div>
					<div class="btn_wrap">
						<input type="submit" id="writeBtn"  class="btn btn-outline-warning" value="등록">
						<a href="javascript:history.back();"><input type="button" class="btn btn-outline-secondary" value="돌아가기"></a>
					</div>
			</form>
		</section>
	</div>


<script type="text/javascript">
function check(){
	var reply_contents = $("#reply_contents").val();
	var reply_star = $("#reply_star").val();
	if(reply_star == 0){
		$("#err").text("별점이 입력되지 않았습니다.");
		$("#err").css("color","black");
		return false;	
		}
	else if(reply_contents.length == 0){
		$("#err").text("글이 입력되지 않았습니다.");
		$("#err").css("color","black");
		return false;		
	}else{
		$("#err").text("");	
	}
}
$(function(){
	$('.starRev span').on("click",function(){
		console.log($(this));
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		console.log($(this).attr("value"));
		var re = $(this).attr("value");
		$(".starRev").children('input').remove();
		$(".starRev").append("<input type='hidden' name='reply_star' id='reply_star' value="+ re + ">");
		return false;
	});
})
</script>	
</body>
</html>