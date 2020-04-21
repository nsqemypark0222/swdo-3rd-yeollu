<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>[ ${istore.instaStore.store_name} | GeTrend ]</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>

<script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@kakao['KAKAOMAP_APPKEY']" />'></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script src='<c:url value="/resources/js/owl.carousel.min.js"/>'></script>
<link href='<c:url value="/resources/css/owl.carousel.css"/>' rel="stylesheet" />
<link href='<c:url value="/resources/css/owl.theme.default.min.css"/>' rel="stylesheet" />
<link rel= "stylesheet" type="text/css" href="../resources/css/istoreInfo.css">
</head>

<body>
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	<div id="wrap">
		<!-- 상세페이지 인스타 사진 carousel -->
		<section id="banner">
			<div id="carouselRecommendedStores" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
			    	<li data-target="#carouselRecommendedStores" data-slide-to="0" class="active"></li>
			    	<li data-target="#carouselRecommendedStores" data-slide-to="1" ></li>
			    	<li data-target="#carouselRecommendedStores" data-slide-to="2" ></li>
			  	</ol>
			  	<div class="carousel-inner">
			    	<div class="carousel-item active">
	      				<c:forEach var="item" items="${istore.instaImageList}" begin="1" end="7" step="3">
							<img class="postImg rounded img-fluid" src="${item.image_url}" class="d-block img-fluid" alt="...">
						</c:forEach>
			    	</div>
		    		<div class="carousel-item ">
						<c:forEach var="item" items="${istore.instaImageList}" begin="2" end="8" step="3">
								<img class="postImg rounded img-fluid" src="${item.image_url}" class="d-block img-fluid" alt="...">
						</c:forEach>					      		
			      	</div>
			    	<div class="carousel-item">
						<c:forEach var="item" items="${istore.instaImageList}" begin="3" end="9" step="3">
							<img class="postImg rounded img-fluid" src="${item.image_url}">
						</c:forEach>					      		
			  		</div>
			  	</div>	
			  	<a class="carousel-control-prev" href="#carouselRecommendedStores" role="button" data-slide="prev">
			  		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			  		<span class="sr-only">Previous</span>
			  	</a>
			  	<a class="carousel-control-next" href="#carouselRecommendedStores" role="button" data-slide="next">
			    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
			    	<span class="sr-only">Next</span>
			  	</a>
		   </div>
		</section>
		<!-- 상세페이지 인스타 사진 carousel 끝-->
		
		<!-- 상세페이지 망고플레이트 가게 정보 -->
		<section id="content1">
		   <nav class="nav">
			<hr>
			  <div class="container">
				<div class="row">
				  <div class="col">
				    <table class="store_table">
					   <tr>
						  <td style="width :300px; height : 80px; border-bottom: 1px solid  #e9e9e9;">
							<span style="font-size : 30px; color : #FF8A00;">${istore.instaStore.store_name}</span> / <span> ${scoreAvg}점</span>
						  </td>
						  
						  <!-- 카카오링크 -->
						  <td  style="border-bottom: 1px solid  #e9e9e9;">
							<input type="hidden" value="${istore.instaStore.store_no}" id="store_no">							
							<div id="kakao_link">
								<a id="kakao-link-btn" href="javascript:sendLink()">
									<i class="fas fa-share-alt fa-3x" data-toggle="tooltip" data-placement="top" title="SNS공유"></i>
									<!-- <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"/> -->
								</a>
							</div>
						 </td>

						  <!-- 가게 좋아요 버튼 -->
						  <td  style="border-bottom: 1px solid  #e9e9e9; width : 70px;">
						  <div id="like_div">
								<c:if test="${!isExistedLike}">
									<i class="fa fa-heart fa-3x"  onclick="insertLike();" style="cursor: pointer; color:#d3d3d3;"  data-toggle="tooltip" data-placement="top" title="관심있는 가게"></i>
								</c:if>
								<c:if test="${isExistedLike}">
									<i class="fa fa-heart fa-3x" onclick="deleteLike();"style="cursor: pointer; color:rgba(255, 138, 0, 0.78);" data-toggle="tooltip" data-placement="top" title="관심있는 가게"></i>
								</c:if>
						   </div>
						  </td>
					   </tr>
				    </table>
				    
				    <!-- 상세페이지 망고 플레이트 가게 정보 -->
					<table class="store_table" >
						<tr>
							<td  class="store_table_td1">카테고리</td>
							<td  class="store_table_td2">${istore.instaStore.store_cate1}</td>
						</tr>
						<tr>
							<td>주소</td>
							<td>${istore.instaStore.store_adr1}</td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td>${istore.mangoStoreInfo.mango_tel}</td>
						</tr>
						<tr>
							<td>가격대</td>
							<td>${istore.mangoStoreInfo.mango_price}</td>
						</tr>
						<tr>
							<td>주차</td>
							<td>${istore.mangoStoreInfo.mango_parking}</td>
						</tr>
						<tr>
							<td>시작</td>
							<td>${istore.mangoStoreInfo.mango_start}</td>
						</tr>
						<tr>
							<td>마지막</td>
							<td>${istore.mangoStoreInfo.mango_end}</td>
						</tr>
					</table>
				  </div>
				 </div>
			   </div>
			</nav>
			
			<!-- 상세 페이지 가게 지도 -->
			<article class="article_right1">
				<div id="map" style="width: 100%; height: 100%; background-color: none;">
				</div>
			</article>
		</section>
		<!-- 상세페이지 망고플레이트 가게 정보 끝-->
		
		<!-- 상세페이지 댓글 리스트 -->
		<section id="content2">
			<article class="article_box1">
			  <div class="row"> <!-- print form -->
				<div class="col" style=" border-top: 1px solid  #e9e9e9; padding-top:20px; ">
				    <div id="reply_title" >
				    	<h3 >댓글( ${replyCount} )</h3>
				    </div>	
				    <!-- 댓글 버튼 -->
					<div id="reply_btn">	 
						<a href="/getrend/stores/istoreinfo_reply?store_no=${istore.instaStore.store_no}&store_name=${istore.instaStore.store_name}">
							<button type="button" class="btn btn-outline-warning">
								댓글 남기기
							</button>
						</a>
					</div>
					<div class="mypage_replies">
					<c:choose>
						<c:when test="${empty replyList}">
							<div class="noReply">
								  <img src="/getrend/resources/img/noreply.png" style="display:inline-block; width : 100px;" alt="댓글없음"><br><br><br>
								  <div style="display:inline-block;"><h6>남긴 댓글이 아직 없습니다</h6></div>
							</div>
						</c:when>
						<c:otherwise>
							<table class="table table-hover" id="replyTable01">				
								<c:forEach var="reply" items="${replyList}" begin="0" end="2">
									<tr class="table-light">
										<td>
											<div class="reply_outer">						          
												<div class="reply_inner01">	
												   <a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}">	          
														<c:if test="${reply.USER_PROFILE != null}">
													        <img class="reply_cate_profile" src="${reply.USER_PROFILE}" alt="프로필 사진">
													    </c:if>
													    <c:if test="${reply.USER_PROFILE == null}">
															<img class="reply_cate_profile" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진">
													    </c:if>
													</a>	
												 </div>						          
												 <div class="reply_inner02">
											       <div class="reply_name">
    											      <a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}">
	        										   ${reply.USER_NAME}
    												  </a> 								   
											       </div>
												   <div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
												   <p class="card-text mb-auto" style="word-break:break-word;font-size : 15px; margin : 20px 0;">${reply.REPLY_CONTENTS}</p>		          
												   <div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						    					 </div>	       
											</div>
											<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
												<div class="deleteButton" style="float : right;">
													<img style="width:15px; cursor: pointer;" src="/getrend/resources/img/delete.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${istore.instaStore.store_no}')">
												</div> 
											</c:if>
										</td>	
									 </tr>				
								  </c:forEach>    								
						   	 </table>
						   	 
						   	 <!-- 댓글 3개 초과 이면 더보기 버튼 생성 -->
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
												  <a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}">		          
														<c:if test="${reply.USER_PROFILE != null}">
														
													        <img class="reply_cate_profile" src="${reply.USER_PROFILE}" alt="프로필 사진">
													    </c:if>
													    <c:if test="${reply.USER_PROFILE == null}">
															<img class="reply_cate_profile" src="<c:url value='/resources/img/user.png'/>" alt="프로필 사진">
													    </c:if>
													 </a>
														
												</div>						          
													<div class="reply_inner02">
													<div class="reply_name">
    											      <a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}">
	        										   ${reply.USER_NAME}
    												  </a> 								   
											       </div>
													<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
													<div class="card-text mb-auto" style="word-break:break-word; font-size : 15px;margin : 20px 0;">${reply.REPLY_CONTENTS}</div>		          
													<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						    					 </div>	       
											</div>
											<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
												<div class="deleteButton" style="float : right;">
													<img style="width:15px; cursor: pointer;" src="/getrend/resources/img/delete.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${istore.instaStore.store_no}')">
												</div> 
											</c:if>
										</td>	
									 </tr>				
								 </c:forEach>     								
						   	 </table>		
						   </c:otherwise>
					   </c:choose>	
				 </div>				
		    </div>
	     </div>
	   </article>
	 </section>
	 <!-- 상세페이지 댓글 리스트 끝-->
   </div>
   
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>

<script type="text/javascript">
<<<<<<< HEAD

//가게 좋아요 
=======
>>>>>>> 7c95c6552248688866edd3ce4c1efe2bc4f034c0
function insertLike(){
	var no = $("#store_no").val();
		$.ajax({
			url : "/getrend/likes/likeInsert",
			type : "post",
			data : {store_no : no},
			success : function(){
			var temp = '<i class="fa fa-heart fa-3x" onclick="deleteLike();"style="cursor: pointer; color:rgba(255, 138, 0, 0.78);" data-toggle="tooltip" data-placement="top" title="관심있는 가게"></i>';
			 $("#like_div").html(temp);   
				},
			error : function(){alert("실패");}
			})
}
//가게 좋아요 취소		
function deleteLike(){
	var no = $("#store_no").val();
		$.ajax({
			url : "/getrend/likes/likeDelete",
			type : "post",
			data : {store_no : no},
			success : function(){
			var temp = '<i class="fa fa-heart fa-3x"onclick="insertLike();" style="cursor: pointer; color:#d3d3d3;" data-toggle="tooltip" data-placement="top" title="관심있는 가게"></i>';
			 $("#like_div").html(temp);   
				},
			error : function(){alert("실패");}
			})
}
<<<<<<< HEAD

//별점 png로 구현
=======
//별
>>>>>>> 7c95c6552248688866edd3ce4c1efe2bc4f034c0
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
<<<<<<< HEAD

//리플 삭제
function deleteReply(reply_no,store_no){
	location.href="/getrend/stores/deleteReply?reply_no=" + reply_no + "&store_no="+ store_no;
}

//더 보기 버튼
=======
function deleteReply(reply_no,store_no){
	location.href="/getrend/stores/deleteReply?reply_no=" + reply_no + "&store_no="+ store_no;
}
>>>>>>> 7c95c6552248688866edd3ce4c1efe2bc4f034c0
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



<script type='text/javascript'>
	//<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
  Kakao.init('<spring:eval expression="@kakao['KAKAOLOGIN_APPKEY']" />');
    // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    function sendLink() {
    	Kakao.Link.sendDefault({
        	objectType: 'location',
        	address: "${istore.instaStore.store_adr1}",
        	addressTitle: "${istore.instaStore.store_name}",
        	content: {
          		title: "${istore.instaStore.store_name}",
          		description: "",
          		imageUrl: "${istore.instaImageList[0].image_url}",
          		link: {
            		mobileWebUrl: 'https://developers.kakao.com',
            		webUrl: 'https://developers.kakao.com'
				}
			},
       		social: {
          		likeCount: 286,
          		commentCount: 45,
          		sharedCount: 845
        	},
        	buttons: [
          		{
            		title: '웹으로 보기',
            		link: {
              			mobileWebUrl: 'https://developers.kakao.com',
              			webUrl: 'https://developers.kakao.com'
            		}
          		}
        	]
       	});
    }
	//]]>
</script>

<script>
	$(function() {
		const mapContainer = document.getElementById("map");
		
		mapOption = {
			center: new kakao.maps.LatLng("${istore.instaStore.store_y}", "${istore.instaStore.store_x}"),
			level: 1 // 지도의 확대 레벨
		};

		const map = new kakao.maps.Map(mapContainer, mapOption);

		// 커스텀 오버레이에 표시할 내용입니다     
		// HTML 문자열 또는 Dom Element 입니다 
		const customMarker = '<a href="https://map.kakao.com/link/to/${istore.instaStore.store_name},${istore.instaStore.store_y},${istore.instaStore.store_x}"><i class="fas fa-map-marker-alt fa-3x"></i></a>';

		// 커스텀 오버레이가 표시될 위치입니다 
		const position = new kakao.maps.LatLng("${istore.instaStore.store_y}", "${istore.instaStore.store_x}");  

		// 커스텀 오버레이를 생성합니다
		const customOverlay = new kakao.maps.CustomOverlay({
			position: position,
			content: customMarker   
		});

		// 커스텀 오버레이를 지도에 표시합니다
		customOverlay.setMap(map);
	});
</script>

</body>
</html>