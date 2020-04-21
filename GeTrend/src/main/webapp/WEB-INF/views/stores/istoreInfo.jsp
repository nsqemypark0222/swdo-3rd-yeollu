<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ ${istore.instaStore.store_name} | GeTrend ]</title>
<script src="https://kit.fontawesome.com/980ba882ef.js" crossorigin="anonymous"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>

<link rel= "stylesheet" type="text/css" href="../resources/css/reply.css">

<style type="text/css">
#repImg {
	display: block;
	margin: 0px auto;
}

.profileImg {
	display: block;
	margin: 0px auto;
	width: 100%;
	height: auto;
}

.fas {
	color: rgba(255, 138, 0, 0.78);
}

.starRev {
	display: block;
	margin: 0px auto;
}

</style>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="row">
					<c:forEach var="item" items="${istore.instaImageList}">
						<c:if test="${item.image_type == 'profile'}">
							<img class="rounded-circle" id="repImg" src="${item.image_url}">
						</c:if>
					</c:forEach>
				</div>
				<div class="row">
					<div class="col-md-4">
						<a id="like">
							<i class="fas fa-heart fa-3x"></i>
						</a>
					</div>
					<div class="col-md-4">
						<a href="https://map.kakao.com/link/to/${istore.instaStore.store_name},${istore.instaStore.store_y},${istore.instaStore.store_x}">
							<i class="fas fa-map-marker-alt fa-3x"></i>
						</a>
					</div>
					<div class="col-md-4">
						<a id="kakao-link-btn" href="javascript:sendLink()">
							<i class="fas fa-share-alt fa-3x"></i>
							<!-- <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"/> -->
						</a>
					</div>
				</div>
				<div class="row">
					<div class="container">
						<div class="row">
							<div class="col-12">
								<h1>${istore.instaStore.store_name}</h1>
								<ul class="list-group">
									<li class="list-group-item">${istore.instaStore.store_cate1}</li>
									<li class="list-group-item">${istore.instaStore.store_adr1}</li>
									<li class="list-group-item">${istore.mangoStoreInfo.mango_tel}</li>
									<li class="list-group-item">${istore.mangoStoreInfo.mango_price}</li>
									<li class="list-group-item">${istore.mangoStoreInfo.mango_parking}</li>
									<li class="list-group-item">${istore.mangoStoreInfo.mango_start}</li>
									<li class="list-group-item">${istore.mangoStoreInfo.mango_end}</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="col-md-6">
				<div class="row">
					<div class="card mb-3">
						<!-- <div class="card-header mb-3">
							<h2>상세 페이지</h2>
						</div> -->
						<div class="card-body mb-3">
							<div class="row no-gutters">
								<div class="col-md-4">
									<c:forEach var="item" items="${istore.instaImageList}" begin="1" end="7" step="3">
										<img class="rounded img-fluid" src="${item.image_url}">
									</c:forEach>
								</div>
								<div class="col-md-4">
									<c:forEach var="item" items="${istore.instaImageList}" begin="2" end="8" step="3">
										<img class="rounded img-fluid" src="${item.image_url}">
									</c:forEach>
								</div>
								<div class="col-md-4">
									<c:forEach var="item" items="${istore.instaImageList}" begin="3" end="9" step="3">
										<img class="rounded img-fluid" src="${item.image_url}">
									</c:forEach>
								</div>
							</div>
						</div>
						<%-- <div class="card-footer mb-3">
						<div class="row no-gutters">
							<div class="col-md-2">
								<div class="row">
									<img class="rounded-circle profileImg" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586073032/qjmvvyvokolfefdsb6k9.jpg">
								</div>
								<div class="row">
									${sessionScope.loginname}
								</div>
							</div>
							<div class="col-md-10">
								<form class="form-group" id="inputForm">
									<input type="hidden" id="store_no" name="store_no" value="${istore.instaStore.store_no}" />
									<div class="row">
										<div class="starRev">
											<span class="starR1">0.5</span>
											<span class="starR2">1</span>
											<span class="starR1">1.5</span>
											<span class="starR2">2</span>
											<span class="starR1">2.5</span>
											<span class="starR2">3</span>
											<span class="starR1">3.5</span>
											<span class="starR2">4</span>
											<span class="starR1">4.5</span>
											<span class="starR2">5</span>
										</div>
									</div>
									<div class="row">
										<textarea class="form-control" rows="5" id="reply_contents" name="reply_contents" placeholder="내용 입력"></textarea>
										<button class="form-control" id="writeBtn">리뷰 등록</button>
									</div>
								</form>
							</div>
						</div>
					</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 댓글 시작 -->
	<div class="container">
		<div class="row">
	    <div class="col">
			<form id="inputForm" >
		    	<input type="hidden" id="store_no" name="store_no" value="${istore.instaStore.store_no}" />
				<table>
					<tr>
						<th>리뷰</th>
					</tr>
						
					<tr>
						<td>${sessionScope.loginname}</td>
						<td>
							<div class="starRev">
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
						
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea id="reply_contents" name="reply_contents" placeholder="내용 입력"></textarea>
						</td>
						<td>
							<input type="button" id="writeBtn" value="리뷰 등록">
						</td>
						<td>
							
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div> <!-- input form end -->
	<hr>
	
		<div class="row"> <!-- print form -->
			<div class="col">
				<table id="printTable">
			
				</table>
			</div>	
		</div>
		
		
		
	</div>
	
	<form id="likeForm">
		<input type="hidden" id="store_no" name="store_no" value="${istore.instaStore.store_no}" />
	</form>
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>
	
	
	
<script type="text/javascript">
		function printList() {
			$.ajax({
				url: "/getrend/reply/replyList",
				type: "get",
				data: {"store_no" : $("#store_no").val()},
				success: function(result) {
					var output = "";
					output += "<tr>";
					output += "<th>별점</th>";
					output += "<th>닉네임</th>";
					output += "<th>리뷰</th>";
					output += "<th>날짜</th>";
					output += "<th></th>";
					output += "</tr>";
					
					$(result).each(function(index, item) {
						console.log(item);
						output += "<tr>";
						output += "<td>" + item.REPLY_STAR + "</td>";
						output += "<td><a href='/getrend/mypage/mypage?user_name="+item.USER_NAME+"'>" + item.USER_NAME + "</a></td>";
						output += "<td>" + item.REPLY_CONTENTS + "</td>";
						output += "<td>" + item.REPLY_INDATE + "</td>";
						if(item.loginemail ==item.USER_EMAIL){
							output += "<td><input type='button' id='removeBtn' value='삭제' onclick='deleteReply("+item.REPLY_NO+")'></td>";
						}
						output += "</tr>";
					});
					
					$("#printTable").html(output);
				},
				error: function() { alert("에러 발생"); }
			});
		}

			$(function() {
				$("#writeBtn").on("click", function() {
					var reply_contents = $("#reply_contents").val();
					
					$.ajax({
						url: "/getrend/reply/replyWrite",
						type: "get",
						data: $("#inputForm").serialize(),
						success: printList,
						error: function() { alert("에러 발생"); }
					});
				});

			

			$('.starRev span').on("click",function(){
				console.log($(this));
				$(this).parent().children('span').removeClass('on');
				$(this).addClass('on').prevAll('span').addClass('on');
				console.log($(this).attr("value"));
				var re = $(this).attr("value");
				$(".starRev").children('input').remove();
				$(".starRev").append("<input type='hidden' name='reply_star' value="+ re + ">");
				return false;
			});

			printList();
		});
			
		function deleteReply(no){
			$.ajax({
				url: "/getrend/reply/replyRemove",
				type: "get",
				data: {"reply_no": no},
				success: printList,
				error: function() { alert("에러 발생"); }
			});
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
          		imageUrl: document.getElementById("repImg").src,
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

</body>
</html>