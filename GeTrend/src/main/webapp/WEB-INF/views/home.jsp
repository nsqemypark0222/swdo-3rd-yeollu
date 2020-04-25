<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>[ Home | GeTrend ]</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@kakao['KAKAOMAP_APPKEY']" />&libraries=drawing,services'></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	


<link rel="stylesheet" href='<c:url value="/resources/css/home.css"/>'> 
<link rel="stylesheet" href='<c:url value="/resources/css/loading.css"/>'>

<style type="text/css">

	@font-face { 
		font-family: 'GmarketSansBold'; 
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); 
		font-weight: normal; 
		font-style: normal; 
	}
	
	h1, h2, h3, h4, h5, h6 {
		font-family: 'GmarketSansBold';
	}
	
	hr {
		background-color: #FF8A00;
		width: 82%;
		height: 3px;
	}
	
	.recommend-container {
		margin-top: 100px;
		margin-bottom: 30px;
		
	}
	
	.recommend-title {
		font-family: 'GmarketSansBold';
		margin-top : 20px; 
		margin-bottom : 40px; 
		text-align : center;
		width:100%;
	}
	
	.recommend_row{
		width :100%; 
		margin : 0 auto;
	}
	
	.recommend_list{
		width : 100%;
		text-align : center;
	}
	.img_div_list{
		float : left;
		width : 31.3%;
		margin-right : 2%;
		margin-bottom : 2%;
	}
	.img_div_list h4{
		margin-top : 12px;
	}
	.search_logo_div{
		width : 100%;
		height : 470px;
		float : right;
		background-color: #ffc;
		margin-left : 10px;
	}
	
	header{
		background-color: transparent;
		width : 100%;
		position: fixed;
		z-index : 6;
		border : none !important;
	}

	.shadow{
	  -webkit-box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
	  -moz-box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
	  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
	  background-color : #fff;
	}
	
	.wrap{
		position : relative;
		margin-bottom : 200px;
		background-color : #fff; 
	}
	.main_title{
		padding-top : 130px;
		padding-bottom : 50px;
		background-color : #ffc;
		text-align : center;
	}
	
	.top_btn{
		display : none;
		transition:display .2s ease;
	}
	.wrap_inner2{
		margin-top: 4px;
	    margin-bottom: 100px;
	}
	.map_title{
		border-bottom : #FF8A00 3px solid;
		text-align : center;
		margin-top : 10px;
		margin-bottom : 20px;
	}

	
	.carousel-item img {
	max-height: initial;
}

.carousel-caption{
	bottom : 35% !important;
}
	/*이미지 확대 효과*/
	.sample_image { overflow: hidden; }
	.sample_image  img {
    -webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1); 
    -o-transform:scale(1);  
    transform:scale(1);
    -webkit-transition:.3s;
    -moz-transition:.3s;
    -ms-transition:.3s;
    -o-transition:.3s;
    transition:.3s;
}
.sample_image:hover img {
    -webkit-transform:scale(1.2);
    -moz-transform:scale(1.2);
    -ms-transform:scale(1.2);   
    -o-transform:scale(1.2);
    transform:scale(1.2);
}
	
</style>

</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	<div class="shadow"></div>    	 
	<div class="shadow-cover"></div> 
	<a class="top_btn" style="display:scroll;position:fixed;  bottom:30px;right:20px; z-index:200;" href="#" title="맨 위로">
		<img style="width:40px;"src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587782568/resources/home/top_boeuo8.png">
	</a>
	<div class="wrap">
	<div class="container-fluid wrap_inner">
		
		<div class="row main_title">
			<div class="col">
				<h1><img style="width : 80px;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587782568/resources/home/search_logo_cd0sdp.png"> 지금, 당신과 <span style="color:#ff8a00;">가장 가까운</span> 핫 플레이스</h1>
				<h6>지도를 이용하여 현재 가장 인기 있는 가게를 실시간으로 검색하세요</h6>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-6">
				<div class="row wrap_inner2">
					<!-- map 시작 -->
					<div>
						
					</div>
					<div id="map">
						<div class="btn-modal">
							<!-- 지도 위의 버튼 1 음식 카테고리 -->
							<!-- Button trigger modal -->
							<button type="button" id="category" class="btn btn-dark" data-toggle="modal" data-target="#categoryModal">카테고리</button>
							
							<!-- CategoryModal -->
							<div class="modal fade" id="categoryModal">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- 음식 카테고리 모달 header -->
										<div class="modal-header">
											<h4 class="modal-title">음식 카테고리</h4>
	           								<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<!-- 음식 카테고리 모달 body -->
	      								<div class="modal-body">
	      									<!-- <h3>음식 종류</h3> -->
	           								<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="한식" checked="checked">한식
				 								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="양식" checked="checked">양식
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="일식/수산물" checked="checked">일식/수산물
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="중식" checked="checked">중식
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="카페/디저트" checked="checked">카페/디저트
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="치킨/피자/패스트푸드" checked="checked">치킨/피자/패스트푸드
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="categotyChk" value="기타" checked="checked">기타
				  								</label>
											</div>
	      								</div>
	      								<!-- 음식 카테고리 모달 footer -->
	       								<div class="modal-footer" id="modal-footer">
	       									<button type="button" class="btn btn-dark" data-dismiss="modal">확인</button>
	        								<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
	       								</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="btn-modal">
							<!-- 지도 위의 버튼 2 음식 카테고리 -->
							<!-- Button trigger modal -->
							<button type="button" id="opentime" class="btn btn-warning" data-toggle="modal" data-target="#opentimeModal">영업 확인</button>
							
							<!-- CategoryModal -->
							<div class="modal fade" id="opentimeModal">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- 영업 확인 모달 header -->
										<div class="modal-header">
											<h4 class="modal-title">영업 확인</h4>
	           								<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<!-- 영업 확인 모달 body -->
	      								<div class="modal-body">
	           								<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="일" checked="checked">일
				 								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="월" checked="checked">월
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="화" checked="checked">화
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="수" checked="checked">수
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="목" checked="checked">목
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="금" checked="checked">금
				  								</label>
											</div>
											<div class="form-check">
				  								<label class="form-check-label">
				    								<input type="checkbox" class="form-check-input" name="opentimeChk" value="토" checked="checked">토
				  								</label>
											</div>
	      								</div>
	      								<!-- 영업 확인 모달 footer -->
	       								<div class="modal-footer">
	       									<button type="button" class="btn btn-dark" data-dismiss="modal">확인</button>
	        								<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
	       								</div>
									</div>
								</div>
							</div>
						</div>
						
    	 				<button type="button" class="btn btn-dark" id="selectOverlay" onclick="selectOverlay('POLYGON')" value="on">
    	 				범위 선택</button>
    					<button type="button" class="btn btn-warning" id="drawingMap"  onclick="getDataFromDrawingMap()">	
    					조회 하기</button>
					</div>
					<!-- map 종료 -->
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="row wrap_inner2">	
					<c:choose>
						<c:when test="${sessionScope.loginemail != null && istores != null}">				
							<div class="istore-container scrollbar scrollbar-warning">
							<h3 class="map_title">지도로 찾은 인기 가게</h3>
							  <c:forEach var="istore" items="${istores}">
								<div id="carouselSearchedStores_${istore.instaStore.store_no}" class="carousel slide" data-ride="carousel">
									<ol class="carousel-indicators">
										<c:forEach var="item" items="${istore.instaImageList}" varStatus="status">
											<c:if test="${item.image_type != 'profile' and fn:length(istore.instaImageList) > 1}">
												<c:choose>
													<c:when test="${status.index eq 1}">
														<li data-target="#carouselSearchedStores_${istore.instaStore.store_no}" data-slide-to="${status.index}" class="active" ></li>
													</c:when>
													<c:otherwise>
														<li data-target="#carouselSearchedStores_${istore.instaStore.store_no}" data-slide-to="${status.index}" ></li>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</ol>
									<div class="carousel-inner">
										<c:forEach var="item" items="${istore.instaImageList}" varStatus="status">
											<c:if test="${item.image_type != 'profile' and fn:length(istore.instaImageList) > 1}">
												<c:choose>
													<c:when test="${status.index eq 1}">
														<div class="carousel-item active">
															<a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />'>
												      			<img src="${item.image_url}" alt="${istore.instaStore.store_no}" class="d-block img-fluid" >
												      		</a>
											      			<div class="carousel-caption d-none d-md-block">
												        		<h1>${istore.instaStore.store_name}</h1>
												      		</div>
												    	</div>
													</c:when>
													<c:otherwise>
														<div class="carousel-item">
															<a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />'>
												      			<img src="${item.image_url}" alt="${istore.instaStore.store_no}" class="d-block img-fluid" >
												      		</a>
											      			<div class="carousel-caption d-none d-md-block">
											      				<h1>${istore.instaStore.store_name}</h1>
												      		</div>
												    	</div>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</div>
									<a class="carousel-control-prev" href="#carouselSearchedStores_${istore.instaStore.store_no}" role="button" data-slide="prev">
								  		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								  		<span class="sr-only">Previous</span>
								  	</a>
								  	<a class="carousel-control-next" href="#carouselSearchedStores_${istore.instaStore.store_no}" role="button" data-slide="next">
								    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
								    	<span class="sr-only">Next</span>
								  	</a>
								</div>
							</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div class="istore-container2">
							<h3 class="map_title">현재 내 주위의 인기 가게</h3>
							 <!-- 내 주변 추천 시작 -->
								<div id="carouselRecommendedStores" class="carousel slide" data-ride="carousel">
									<ol class="carousel-indicators">
								    	<li data-target="#carouselRecommendedStores" data-slide-to="0" class="active"></li>
								    	<li data-target="#carouselRecommendedStores" data-slide-to="1"></li>
								    	<li data-target="#carouselRecommendedStores" data-slide-to="2"></li>
								    	<li data-target="#carouselRecommendedStores" data-slide-to="3"></li>
								    	<li data-target="#carouselRecommendedStores" data-slide-to="4"></li>
								    	<li data-target="#carouselRecommendedStores" data-slide-to="5"></li>
								  	</ol>
								  	<div class="carousel-inner">
								    	<div class="carousel-item active">
								    		<a href="javascript:recommendByAccessLocation('양식');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="...">
								      		</a>
							      			<div class="carousel-caption d-none d-md-block">
								        		<h1>양식 TOP 5</h1>
								      		</div>
								    	</div>
							    		<div class="carousel-item">
							    			<a href="javascript:recommendByAccessLocation('카페/디저트');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="양림동_카페/디저트">
								      		</a>
								      		<div class="carousel-caption d-none d-md-block">
								        		<h2>카페/디저트 TOP 5</h2>
								      		</div>
								    	</div>
								    	<div class="carousel-item">
								    		<a href="javascript:recommendByAccessLocation('한식');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="...">
								      		</a>
							      			<div class="carousel-caption d-none d-md-block">
								        		<h1>한식 TOP 5</h1>
								      		</div>
								    	</div>
								    	<div class="carousel-item">
								    		<a href="javascript:recommendByAccessLocation('일식/수산물');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="...">
								      		</a>
							      			<div class="carousel-caption d-none d-md-block">
								        		<h2>일식/수산물 TOP 5</h2>
								      		</div>
								    	</div>
								    	<div class="carousel-item">
								    		<a href="javascript:recommendByAccessLocation('중식');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="...">
								      		</a>
							      			<div class="carousel-caption d-none d-md-block">
								        		<h1>중식 TOP 5</h1>
								      		</div>
								    	</div>
								    	<div class="carousel-item">
								    		<a href="javascript:recommendByAccessLocation('치킨/피자/패스트푸드');">
								      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="...">
								      		</a>
							      			<div class="carousel-caption d-none d-md-block">
								        		<h2>치킨/피자/패스트푸드 TOP 5</h2>
								      		</div>
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
								<!-- 내 주변 추천 종료 -->
							</div>
						</c:otherwise>
				   </c:choose>
				</div>
			</div>
			<div class="col-md-1">
			</div>
		</div>
	</div>
	

	
	<div class="container-fluid recommend-container">
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
				<div class="row recommend_row">
					<div class="recommend-title">
						<h4><img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587782659/resources/home/store_recommend_x5vzsc.png" width="50">  오늘의 인기 동네</h4>
						<h1 style="color:#ff8a00">#양림동</h1>
					</div>
					<!-- 양림동 맛집 추천 시작 -->
						<div class="recommend_list">
							<div class="img_div_list">
							   <div class="sample_image">
					    		<a href="javascript:recommend('양림동', '양식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="양림동_양식">
					      		</a>
					      		</div>
					        		<h4>양식 TOP 5</h4>
					    	</div>
				    		<div class="img_div_list">
				    		   <div class="sample_image">
				    			<a href="javascript:recommend('양림동', '카페/디저트');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586776555/ariel-kwon-OvqJ4LaLo20-unsplash_ebjrxe.jpg" class="d-block img-fluid" alt="양림동_카페/디저트">
					      		</a>
					      		</div>
					        		<h4>카페/디저트 TOP 5</h4>
					    	</div>
				    		<div class="img_div_list">
				    		   <div class="sample_image">
					    		<a href="javascript:recommend('양림동', '한식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529638/jakub-kapusnak-4f4YZfDMLeU-unsplash_e7of7o.jpg" class="d-block img-fluid" alt="양림동_한식">
					      		</a>
					      		</div>
					        		<h4>한식 TOP 5</h4>
					  		</div>
					  		<div class="img_div_list">
					  		   <div class="sample_image">
					    		<a href="javascript:recommend('양림동', '일식/수산물');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530818/choi-ben-PRM1RuIIQvI-unsplash_m382ma.jpg" class="d-block img-fluid" alt="양림동_일식/수산물">
					      		</a>
					      		</div>
					        		<h4>일식/수산물 TOP 5</h4>
					  		</div>
							<div class="img_div_list">
							   <div class="sample_image">
					    		<a href="javascript:recommend('양림동', '중식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530809/yeh-xintong-qd9NpQKmC2c-unsplash_t82cnq.jpg" class="d-block img-fluid" alt="양림동_중식">
					      		</a>
					      		</div>
					        		<h4>중식 TOP 5</h4>
					  		</div>
					  		<div class="img_div_list">
					  		   <div class="sample_image">
					    		<a href="javascript:recommend('양림동', '치킨/피자/패스트푸드');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530823/jonathan-borba-8l8Yl2ruUsg-unsplash_zceo5s.jpg" class="d-block img-fluid" alt="양림동_치킨/피자/패스트푸드">
					      		</a>
					      	   </div>
					        		<h4>치킨/피자/패스트푸드 TOP 5</h4>
					  		</div>
					  </div>
					<!-- 양림동 맛집 추천 종료 -->
				</div>
			</div>
			<div class="col-md-1">
			</div>
		</div>
	</div>
	
	<div class="container-fluid recommend-container">
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
				<div class="row recommend_row">
					<div class="recommend-title">
						<h4><img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587782659/resources/home/store_recommend_x5vzsc.png" width="50">  오늘의 추천 동네</h4>
						<h1 style="color:#ff8a00">#풍암동</h1>
					</div>
					<!-- 풍암동 맛집 추천 시작 -->
					<div class="recommend_list">
							<div class="img_div_list">
							   <div class="sample_image">
					    		<a href="javascript:recommend('풍암동', '양식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529393/lily-banse--YHSwy6uqvk-unsplash_tsdfpg.jpg" class="d-block img-fluid" alt="풍암동_양식">
					      		</a>
					      		</div>
					        		<h4>양식 TOP 5</h4>
					    	</div>
				    		<div class="img_div_list">
				    		   <div class="sample_image">
				    			<a href="javascript:recommend('풍암동', '카페/디저트');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586776555/ariel-kwon-OvqJ4LaLo20-unsplash_ebjrxe.jpg"   class="d-block img-fluid" alt="풍암동_카페/디저트">
					      		</a>
					      		</div>
					        		<h4>카페/디저트 TOP 5</h4>
					    	</div>
				    		<div class="img_div_list">
				    		   <div class="sample_image">
					    		<a href="javascript:recommend('풍암동', '한식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587529638/jakub-kapusnak-4f4YZfDMLeU-unsplash_e7of7o.jpg" class="d-block img-fluid" alt="풍암동_한식">
					      		</a>
					      		</div>
					        		<h4>한식 TOP 5</h4>
					  		</div>
					  		<div class="img_div_list">
					  		   <div class="sample_image">
					    		<a href="javascript:recommend('풍암동', '일식/수산물');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530818/choi-ben-PRM1RuIIQvI-unsplash_m382ma.jpg"   class="d-block img-fluid" alt="풍암동_일식/수산물">
					      		</a>
					      		</div>
					        		<h4>일식/수산물 TOP 5</h4>
					  		</div>
							<div class="img_div_list">
							   <div class="sample_image">
					    		<a href="javascript:recommend('풍암동', '중식');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530809/yeh-xintong-qd9NpQKmC2c-unsplash_t82cnq.jpg" class="d-block img-fluid" alt="풍암동_중식">
					      		</a>
					        	</div>
					        		<h4>중식 TOP 5</h4>
					  		</div>
					  		<div class="img_div_list">
					      	  <div class="sample_image">
					    		<a href="javascript:recommend('풍암동', '치킨/피자/패스트푸드');">
					      			<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587530809/yeh-xintong-qd9NpQKmC2c-unsplash_t82cnq.jpg" class="d-block img-fluid" alt="풍암동_치킨/피자/패스트푸드">
					      		</a>
					      	  </div>
					        		<h4>치킨/피자/패스트푸드 TOP 5</h4>
					  		</div>
					  </div>
					<!-- 풍암동 맛집 추천 종료 -->
				</div>
			</div>
			<div class="col-md-1">
			</div>
		</div>
	</div>
	</div>
	


	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>


	<!-- 로딩이미지 -->
	<div class="wrap-loading display-none">
	  	<div>
	  		<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587731259/resources/home/Loadingbar_ykfxnr.gif"/>
	  	</div>
	</div>
	
	<div>
		<form id="recommendForm" action="recommend" method="get">
			<input type="hidden" id="store_adr" name="store_adr" />
			<input type="hidden" id="store_cate1" name="store_cate1" />
		</form>
	</div>

<script>
	$(function() {
		
		function search(points) {
			let categoryValues = [];
			$("input[name=categotyChk]:checked").each(function() {
				categoryValues.push($(this).val());
			});
	//		console.log(categoryValues);
			let opentimeValues = [];
			$("input[name=opentimeChk]:checked").each(function() {
				opentimeValues.push($(this).val());
			});
	//		console.log(opentimeValues);
			let reqParm = {
				"points": points,
				"categoryValues": categoryValues,
				"opentimeValues": opentimeValues
			};
			$.ajax({
				url: "<c:url value='/search' />",
				type: "post",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(reqParm),
				dataType: "json",
				beforeSend: function() {
					$('.wrap-loading').removeClass('display-none');
					$('.wrap-loading').on('scroll touchmove mousewheel', function(event) {
						event.preventDefault();
					  	event.stopPropagation();
					  	return false;
					});
				},
				complete: function() {
					$('.wrap-loading').addClass('display-none');
					$('.wrap-loading').off('scroll touchmove mousewheel');
				},
				success: function(result) {
					//alert("성공");
					printMarker(result);
	
					$(".istore-container").empty();
	
					let str = "";
					$(result).each(function(index, item) {
						str += '<div id="carouselSearchedStores_' + item.instaStore.store_no + '" class="carousel slide" data-ride="carousel">';
						str += 		'<ol class="carousel-indicators">';
						for(let i = 0; i < item.instaImageList.length; i++) {
							if(item.image_type !== 'profile' && item.instaImageList.length > 1) {
								if(i === 1) {
									str += '<li data-target="#carouselSearchedStores_' + item.instaStore.store_no + '" data-slide-to="' + i + '" class="active" ></li>';
								} else {
									str += '<li data-target="#carouselSearchedStores_' + item.instaStore.store_no + '" data-slide-to="' + i + '" ></li>';
								}
							}
						}
						str += 		'</ol>';
						str += 		'<div class="carousel-inner">';
						for(let i = 0; i < item.instaImageList.length; i++) {
							if(item.image_type !== 'profile' && item.instaImageList.length > 1) {
								if(i === 1) {
									str += '<div class="carousel-item active">';
									str +=		"<a href='" + '<c:url value="/stores/istoreInfo?store_no=' + item.instaStore.store_no + '" />' + "'>";
									str +=			'<img src="' + item.instaImageList[i].image_url + '" alt="' + item.instaStore.store_no + '" class="d-block img-fluid" >';
									str +=		'</a>';
									str +=		'<div class="carousel-caption d-none d-md-block">';
									str += 			'<h5>' + item.instaStore.store_name + '</h5>';
									str +=		'</div>';
						      		str += '</div>';
								} else {
									str += '<div class="carousel-item">';
									str +=		"<a href='" + '<c:url value="/stores/istoreInfo?store_no=' + item.instaStore.store_no + '" />' + "'>";
									str +=			'<img src="' + item.instaImageList[i].image_url + '" alt="' + item.instaStore.store_no + '" class="d-block img-fluid" >';
									str +=		'</a>';
									str +=		'<div class="carousel-caption d-none d-md-block">';
									str += 			'<h5>' + item.instaStore.store_name + '</h5>';
									str +=		'</div>';
						      		str += '</div>';
								}
							}
						}
						str += 		'</div>';
						str += 		'<a class="carousel-control-prev" href="#carouselSearchedStores_' + item.instaStore.store_no + '" role="button" data-slide="prev">';
						str +=			'<span class="carousel-control-prev-icon" aria-hidden="true"></span>';
						str +=			'<span class="sr-only">Previous</span>';
						str +=		'</a>';
						str +=		'<a class="carousel-control-next" href="#carouselSearchedStores_' + item.instaStore.store_no + '" role="button" data-slide="next">';
						str +=			'<span class="carousel-control-next-icon" aria-hidden="true"></span>';
						str +=			'<span class="sr-only">Next</span>';
						str +=		'</a>';
						str += '</div>';
					});
					//console.log(str);
					$(".istore-container").append(str);
		        },
		        error: function(request, status, error){
		            alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
		        }
			});
		}
		searchFunc = search;
		
	});

	function search(points) {
		searchFunc(points);
	};
	
	const mapContainer = document.getElementById("map");
	
	let mapOption = {
			center: new kakao.maps.LatLng(35.15113, 126.924584),
			level: 1 // 지도의 확대 레벨
	};
	
	const map = new kakao.maps.Map(mapContainer, mapOption);
	
	const printMarker = (result) => {
		
		// 마커를 표시할 위치와 title 객체 배열입니다
		let positions = [];
		
		for(let n in result) {
			const position = {
				title: result[n].instaStore.store_name,
				latlng: new kakao.maps.LatLng(result[n].instaStore.store_y, result[n].instaStore.store_x)
			}
			positions.push(position);
		}
		
		// 마커 이미지의 이미지 주소입니다
		const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		
		for(let i = 0; i < positions.length; i++) {
			// 마커 이미지의 이미지 크기 입니다
			const imageSize = new kakao.maps.Size(24, 35);
			// 마커 이미지를 생성합니다    
		    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		 	// 마커를 생성합니다
		    const marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
	};
	
	const options = {
	        // Drawing Manager를 생성할 때 사용할 옵션입니다
			map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
	        drawingMode: [
	          	// drawing manager로 제공할 그리기 요소 모드입니다
	          	kakao.maps.drawing.OverlayType.POLYGON
	        ],
	        // 사용자에게 제공할 그리기 가이드 툴팁입니다
	        // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
	        guideTooltip: ["draw", "drag", "edit"],
	        polygonOptions: {
	          	draggable: true,
	          	removable: true,
	          	editable: true,
	          	strokeColor: "#39f",
	          	fillColor: "#39f",
	          	fillOpacity: 0.5,
	          	hintStrokeStyle: "dash",
	          	hintStrokeOpacity: 0.5
	        }
	};
	
	// 위에 작성한 옵션으로 Drawing Manager를 생성합니다
	const manager = new kakao.maps.drawing.DrawingManager(options);
	manager.addListener('drawend', function(data) {
		document.getElementById("selectOverlay").value = 'on';
		document.getElementById("selectOverlay").innerHTML = '범위 선택';
	});
	
	// 버튼 클릭 시 호출되는 핸들러 입니다
	const selectOverlay = (type) => {
		let btn = document.getElementById("selectOverlay");
		if(btn.value === 'on') {
			btn.value = 'off';
			btn.innerHTML = '선택 취소';
	    	// 클릭한 그리기 요소 타입을 선택합니다
	    	manager.select(kakao.maps.drawing.OverlayType[type]);
	  	} else {
	  		btn.value = 'on';
	 		btn.innerHTML = '범위 선택';
	  		// 그리기 중이면 그리기를 취소합니다
		   		manager.cancel();
	  	}
	};
	
	// 가져오기 버튼을 클릭하면 호출되는 핸들러 함수입니다
	const getDataFromDrawingMap = () => {
		// Drawing Manager에서 그려진 데이터 정보를 가져옵니다
	    const data = manager.getData();
	            
	    let points = [];
	    for(let i in data.polygon[0].points) {
			const point = {
		        x: data.polygon[0].points[i]["x"],
		        y: data.polygon[0].points[i]["y"]
			};
			points.push(point);
		}
		search(points);
	};
	   
	const setCenter = (lat, lng) => {
		const moveLatLng = new kakao.maps.LatLng(lat, lng);
		map.setCenter(moveLatLng);
	};
	   
	 const panTo = (lat, lng) => {
		const moveLatLng = new kakao.maps.LatLng(lat, lng);
		map.panTo(moveLatLng);
	};

	const setMapCenterByAccessLocation = () => {
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				latitude = position.coords.latitude;
			    longitude = position.coords.longitude;

			    setCenter(latitude, longitude);

				const customMarker = '<i class="fas fa-map-marker-alt fa-3x" style="color: rgba(255, 138, 0, 0.78);"></i>';

				const maker_pos = new kakao.maps.LatLng(latitude, longitude);  

				const customOverlay = new kakao.maps.CustomOverlay({
					position: maker_pos,
					content: customMarker   
				});

				customOverlay.setMap(map);
			});
		}
	}

	const recommendByAccessLocation = (category) => {
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				const geocoder = new kakao.maps.services.Geocoder();
				const callback = function(result, status) {
					if(status === kakao.maps.services.Status.OK) {
						let adr = result[0].address.address_name;
						let store_adr = '';
					    let tmp = adr.split(' ');
					    for(let i = 0; i < tmp.length; i++) {
					    	if(tmp[i].includes('동')) {
					    		store_adr = tmp[i];
								break;
							}
						}
					    recommend(store_adr, category);
					}
				}
				geocoder.coord2Address(position.coords.longitude, position.coords.latitude, callback);
			});
		}
	}

	const recommend = (adr, category) => {
		console.log(adr);
		console.log(category);
		$("#store_adr").val(adr);
		$("#store_cate1").val(category);
		$("#recommendForm").submit();
	}

	const init = () => {
		setMapCenterByAccessLocation();
	}
	init();
	
	//스크롤 시 헤더 그림자 & 배경 색
	$(function(){
	    var header = $('header');
	    $(".header_search").css("display","none");
	    $(window).scroll(function(e){
	        if(header.offset().top !== 0){
	            if(!header.hasClass('shadow')){
	                header.addClass('shadow');
	                $(".top_btn").css("display","block");
	            $(".header_search").css("display","block");
	            }
	        }else{
	            header.removeClass('shadow');
	            $(".top_btn").css("display","none");
	            $(".header_search").css("display","none");


	        }
	      
	    });

	});
</script>
</body>
</html>