<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OWL CAROUSEL TEST</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.js"></script>
<style>
header{
	position:fixed !important;
	top: 0;
	  /* width: 100% */
	  left: 0;
	  right: 0;
	 z-index:900;
}
  ul.nav-menu {
    top: 20px;
    position: fixed;
  }
 div.col-8 div {
    height: 500px;
  }
.stretch-card>.card {
     width: 100%;
     min-width: 100%
 }

 body {
     background-color: #f9f9fa;
       position: relative;
 }
/*navbar style start*/

/*navbar style end*/
 .flex {
     -webkit-box-flex: 1;
     -ms-flex: 1 1 auto;
     flex: 1 1 auto
 }

 @media (max-width:991.98px) {
     .padding {
         padding: 1.5rem
     }
 }

 @media (max-width:767.98px) {
     .padding {
         padding: 1rem
     }
 }

 .padding {
     padding: 3rem
 }

 .owl-carousel .item {
     margin: 3px
 }

 .owl-carousel .item img {
     display: block;
     width: 100%;
     height: auto
 }

 .owl-carousel .item {
     margin: 3px
 }

 .owl-carousel {
     margin-bottom: 15px
 }
</style>
</head>
<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</header>
<body data-spy="scroll" data-target="#myScrollspy" data-offset="1">
<div class="container-fluid">
 	 <div class="row">
	    <nav class="col-sm-3 col-4" id="myScrollspy" >
	      <ul class="nav nav-menu flex-column">
	        <li class="nav-item">
	          <a class="nav-link active" href="#istorePhoto" class="dot active">가게 사진</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#istoreInfo" class="dot">가게 정보</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link" href="#istoreReview" class="dot">가게 리뷰</a>
	        </li>
	         
	      </ul>
    </nav>
    <div class="col-sm-9 col-8">
	      <div id="istorePhoto">    
	        <div class="row container-fluid">
	            <div class="col-lg-12 grid-margin stretch-card">
	                <div class="card">
	                    <div class="card-body">
	                        <h4 class="card-title">검색 결과:</h4>
	                        <div class="owl-carousel">
	                            <div class="item"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1557204140/banner_12.jpg" alt="image" /></div>
	                            <div class="item"> <img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1557204172/banner_2.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1557204663/park-4174278_640.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSR9kZRoODWR0cAXYKLvnRk5xAjPUBzsRFdquxvwcGrxrIRFQFD&usqp=CAU" alt="image" /> </div>
	                            <div class="item"> <img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1557204172/banner_2.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRoyrY-GX2Ah2DTi3aJ5T5QliN_63dK4ujdz70nr9py-SLa9Lyb&usqp=CAU" alt="image" /> </div>
	                            <div class="item"> <img src="https://lh3.googleusercontent.com/proxy/uBIiYJDqQvNv4KfglDRfL6CWQqZXHl-8bb-MdUvdTsTjm9fu90V2D0jmFz2KlPPWwZYirrRPxxztbJyV7B1T3iX2_BT39kj3Tvx7ad5gGAW5tWHN2fxjQdX-Z8PXuoMObHb0bcCIstwnoHVjVCo" alt="image" /> </div>
	                            <div class="item"> <img src="http://www.urbanui.com/fily/template/images/carousel/banner_2.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="http://www.urbanui.com/fily/template/images/carousel/banner_2.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="http://www.urbanui.com/fily/template/images/carousel/banner_2.jpg" alt="image" /> </div>
	                            <div class="item"> <img src="http://www.urbanui.com/fily/template/images/carousel/banner_2.jpg" alt="image" /> </div>

	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	  </div>
  
      <div id="istoreInfo"> 
	      	<div class="row container-fluid">
	            <div class="col-lg-12 grid-margin stretch-card">
	                <div class="card">
	                    <div class="card-body">
				        	<h1>가게 정보</h1>
				        	<p>Try to scroll this section and look at the navigation list while scrolling!</p>
				       </div>
				    </div>
				</div>        
	      </div>
     </div>         
        <div id="istoreReview"> 
	      	<div class="row container-fluid">
	            <div class="col-lg-12 grid-margin stretch-card">
	                <div class="card">
	                    <div class="card-body">
				        	<h1>가게 리뷰</h1>
				        	<p>Try to scroll this section and look at the navigation list while scrolling!</p>
				       </div>
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
	


</body>
</html>

	

<script type="text/javascript">
$(document).ready(function() {

	$(".owl-carousel").owlCarousel({

		autoPlay: 3000,
		items : 2,
		itemsDesktop : [1199,3],
		itemsDesktopSmall : [979,3],
		center: true,
		nav:true,
		loop:true,
		responsive: {
			600: {
			items: 2
			}
		}
	});

});
</script>



<!-- <script type="text/javascript">

	
		$(document).ready(function() {
			$(".owl-carousel").owlCarousel({
	
				autoPlay: 3000,
				items : 4,
				itemsDesktop : [1199,3],
				itemsDesktopSmall : [979,3],
				center: true,
				nav:true,
				loop:true,
				responsive: {
					600: {
					items: 4
					}
				}
			});
	
		});
	
</script>
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
          		imageUrl: "${istore.instaImage.repImg}",
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
</script>-->
