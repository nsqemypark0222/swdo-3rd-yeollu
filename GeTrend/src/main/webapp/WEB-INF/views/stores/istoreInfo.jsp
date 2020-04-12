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
					<img class="rounded-circle" id="repImg" src="${istore.instaImage.repImg}">
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
				<div class="card mb-3">
					<!-- <div class="card-header mb-3">
						<h2>상세 페이지</h2>
					</div> -->
					<div class="card-body mb-3">
						<div class="row no-gutters">
							<div class="col-md-4">
								<c:forEach var="item" items="${istore.instaImage.postImgList}" begin="1" end="7" step="3">
									<img class="rounded img-fluid" src="${item.imgUrl}">
								</c:forEach>
							</div>
							<div class="col-md-4">
								<c:forEach var="item" items="${istore.instaImage.postImgList}" begin="2" end="8" step="3">
									<img class="rounded img-fluid" src="${item.imgUrl}">
								</c:forEach>
							</div>
							<div class="col-md-4">
								<c:forEach var="item" items="${istore.instaImage.postImgList}" begin="3" end="9" step="3">
									<img class="rounded img-fluid" src="${item.imgUrl}">
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="card-footer mb-3">
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
					</div>
				</div>
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
	$(function() {
		$("#like").click(function() {
			$.ajax({
				url: "<c:url value='/likes/likeInsert'/>",
				type: "post",
				data: $("#likeForm").serialize(),
				success: function() {
					alert("성공");
				},
				error: function(request, status, error) {
		            alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
		        }
			});
		});

		$("#writeBtn").on("click", function() {
			var reply_contents = $("#reply_contents").val();
			console.log(reply_contents);
			
			$.ajax({
				url: "<c:url value='/reply/replyWrite'/>",
				type: "get",
				data: $("#inputForm").serialize(),
				success: printList,
				error: function() {
					alert("에러 발생");
				}
			});
		});

		$('.starRev span').on("click", function() {
			console.log($(this));
			$(this).parent().children('span').removeClass('on');
			$(this).addClass('on').prevAll('span').addClass('on');
			var re = $(this)[0].innerText;
			$(".starRev").children('input').remove();
			$(".starRev").append("<input type='hidden' name='reply_star' value='" + re + "'/>");
			return false;
		});

		function deleteReply(no){
			$.ajax({
				url: "<c:url value='/reply/replyRemove'/>",
				type: "get",
				data: {
					"reply_no": no
				},
				success: printList,
				error: function() {
					alert("에러 발생");
				}
			});
		}

		function printList() {
			$.ajax({
				url: "<c:url value='/reply/replyList'/>",
				type: "get",
				data: {
					"store_no": $("#store_no").val()
				},
				success: function(result) {
					console.log(result);
				},
				error: function() { alert("에러 발생"); }
			});
		}
	
		printList();
	});
</script>
<script type='text/javascript'>
	//<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('<spring:eval expression="@kakao['KAKAOLOGIN_APPKEY']" />');
    // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    function sendLink() {
    	Kakao.Link.sendDefault({
        	objectType: 'location',
        	address: '광주 동구 동명동 53',
        	addressTitle: '마녀카롱',
        	content: {
          		title: '마녀카롱',
          		description: '먹고싶어요',
          		imageUrl: 'https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/s320x320/70603410_548437122614133_7847071735609294848_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_ohc=GBJClD1StbQAX-ITUzk&oh=6377821eb6415f9b986fb093445c9073&oe=5EA6BF76',
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