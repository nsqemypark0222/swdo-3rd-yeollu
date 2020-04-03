<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ modal test ]</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	var user_email = "aaa@getrend.com";
	 $("#followerList").click(function(e) {
		 e.stopPropagation();
			$.ajax({
				url : "/getrend/mypage/followerList",
				type : "post",
				data : {"follows_following" : user_email},
				success : function(result){ 
					$("#modal li").html('<h3>팔로워</h3>');
					$(result).each(function(index, item){
						
						var temp =  '<a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></a>';
							temp +=  item.USER_NAME + ' 게시물수';							
						if(item.FOLLOWING != 'me'){
							if(item.FOLLOWING == 'yes'){
								temp +=	 '<input type="button"  id="unfollowBtn" value="언팔로우"  onclick="deleteFollow(\''+item.USER_EMAIIL+'\');">';						
							 }
							else{
								temp +=	 '<input type="button" id="followBtn" value="팔로우" onclick="insertFollow(\''+item.USER_EMAIIL+'\');">';
							 }
					   }
						$("#modal li").append(temp);
					});
						
						$("#modal li").show();	
				},
				error : function(){alert("실패");}
			})    
		 });

	$("#followList").click(function(e) {
		 e.stopPropagation();
			$.ajax({
				url : "/getrend/mypage/followList",
				type : "post",
				data : {"user_email" : user_email},
				success : function(result){ 
					$("#modal li").html('<h3>팔로우</h3>');
					$(result).each(function(index, item){
					
						var temp =  '<a href="/getrend/mypage/mypage?user_name='+item.USER_NAME+'"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default.png'/>" alt="프로필 사진"></a>';
							temp +=  item.USER_NAME + ' 게시물수';							
						if(item.FOLLOWING != 'me'){
							if(item.FOLLOWING == 'yes'){
								temp +=	 '<input type="button"  id="unfollowBtn" value="언팔로우"  onclick="deleteFollow(\''+item.USER_EMAIIL+'\');">';						
							 }
							else{
								temp +=	 '<input type="button" id="followBtn" value="팔로우" onclick="insertFollow(\''+item.USER_EMAIIL+'\');">';
							 }
					   }
						$("#modal li").append(temp);
					});
						
						$("#modal li").show();	
				},
				error : function(){alert("실패");}
			})    
		 });
	$("#likeStoreList").click(function(e) {
		 e.stopPropagation();
			$.ajax({
				url : "/getrend/mypage/likeStoreList",
				type : "post",
				data : {"user_email" : user_email},
				success : function(result){ 
					$("#modal li").html('<h3>관심있는 가게</h3>');
					$(result).each(function(index, item){
						
						var temp =  '<a href=""><img class="reply_user_profile" src="" alt="가게 사진"></a>';
							temp +=  item.STORE_NAME + ' 팔로워수';	
						if(item.FOLLOWING != 'me'){						
							if(item.FOLLOWING == 'yes'){
								temp +=	 '<input type="button"  id="unfollowBtn" value="언팔로우"  onclick="deleteFollow(\''+item.USER_EMAIIL+'\');">';						
							 }
							else{
								temp +=	 '<input type="button" id="followBtn" value="팔로우" onclick="insertFollow(\''+item.USER_EMAIIL+'\');">';
							 }
					   }
						$("#modal li").append(temp);
					});
						
						$("#modal li").show();	
				},
				error : function(){alert("실패");}
			})    
		 });

											 
$(document).click(function() {
         $("#modal li").hide();
    }) 
});

</script>
<style>
* {
        margin: 0;
        padding: 0;
        list-style: none;
        box-sizing: border-box;
    }

    #title {
        height: 300px;
        background: #fff;
        text-align: center;
        
    }

    #title li {
        color: #000;
        font-weight: bold;
        font-size: 26px;
        display: inline-block;
        cursor: pointer;
        width: 22%;
        margin: 50px 10px;
    }

    #title li:hover {
        color: #000;
    }

    #modal>li {

        position: fixed;
        background: #999;
        width: 30%;
        min-height: 60%;
        z-index: 9999;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        padding: 15px;
        border-radius: 30px;
        display: none;
        
    }

    #modal li h3 {
        text-align: center;
        font-size: 26px;
        color: #FF8A00;
        border-bottom: 1px solid #ddd;
        padding: 1rem 1rem;
    }

    #modal li div {
        text-align: left;
        margin: 10px 10px;
        line-height: 30px;
        color: #000;
        overflow-y: scroll;
    }
</style>
</head>

<body>

    <div id="content">
        <ul id="title">
            <li id="followerList">팔로워</li>
            <li id="followList">팔로우</li>
            <li id="likeStoreList">관심있는 가게</li>
        </ul>
        <ul id="modal">
            <li>             
            </li>
        </ul>
    </div>


</body>



</html>