<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@kakao['KAKAOMAP_APPKEY']" />&libraries=drawing,services'></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
<style>

#storesinfo{
top: 0; 
bottom:0; 
margin-top:auto; 
margin-bottom:auto;
font-size:x-large;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col-md-2">
			</div>
			<div class="col-md-4">
				<div class="row">
					<div class="istore-container scrollbar scrollbar-warning">
						<c:choose>
							<c:when test="${sessionScope.loginemail != null && sessionScope.istores != null}">
								<c:forEach var="istore" items="${istores}">
									<div id="carouselSearchedStores_${istore.instaStore.store_no}" class="carousel slide" data-ride="carousel">
										<ol class="carousel-indicators">
											<c:forEach var="item" items="${istore.instaImage.postImgList}" varStatus="status">
												<c:choose>
													<c:when test="${status.first}">
														<li data-target="#carouselSearchedStores_${istore.instaStore.store_no}" data-slide-to="${status.index}" class="active" ></li>
													</c:when>
													<c:otherwise>
														<li data-target="#carouselSearchedStores_${istore.instaStore.store_no}" data-slide-to="${status.index}" ></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ol>
										<div class="carousel-inner">
											<c:forEach var="item" items="${istore.instaImage.postImgList}" varStatus="status">
												<c:choose>
													<c:when test="${status.first}">
														<div class="carousel-item active">
															<a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />'>
												      			<img src="${item.imgUrl}" alt="${istore.instaStore.store_no}" class="d-block img-fluid" >
												      		</a>
											      			<div class="carousel-caption d-none d-md-block">
												        		<h5>${istore.instaStore.store_name}</h5>
												        		<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
												      		</div>
												    	</div>
													</c:when>
													<c:otherwise>
														<div class="carousel-item">
															<a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />'>
												      			<img src="${item.imgUrl}" alt="${istore.instaStore.store_no}" class="d-block img-fluid" >
												      		</a>
											      			<div class="carousel-caption d-none d-md-block">
											      				<h5>${istore.instaStore.store_name}</h5>
												        		<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
												      		</div>
												    	</div>
													</c:otherwise>
												</c:choose>
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
							</c:when>
							<c:otherwise>
							
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="col-md-4" id="storesinfo">
				<c:forEach var="istore" items="${istores}">
					 <h1 class="mb-0">${istore.instaStore.store_name}</h1>
					 <hr>
					 <div class="mb-1 text-muted">${istore.instaStore.store_cate1}</div>
					 <p class="card-text mb-auto">${istore.instaStore.store_adr1}</p>
				</c:forEach>
			</div>
			<div class="col-md-2">
			</div>
		</div>
	</div>
	
</body>
</html>