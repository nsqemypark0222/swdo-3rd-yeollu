<!-- 
/**
 * @File 	: searchForm.jsp
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
*/
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<!-- Header Start -->
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>[ ${searchInput} | GeTrend ]</title>
<!-- jQuery and Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!-- SearchForm CSS -->
<link rel="stylesheet" href='<c:url value="/resources/css/searchForm.css"/>'>
</head>
<!-- Header End -->

<!-- Body Start -->
<body style="background-color : #f6f6f6;">
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div class="container-fluid" style="background:#fff; margin-bottom:10px;">	
		<div class="row">
			<div class="col">
			</div>		
		</div>
		<div class="row row_margin"> 
			<div class="col-md-3">				
			</div>
			<div class="col-md-6">
				<h5><img src="/getrend/resources/img/store_recommend.png" width="50">  KEYWORD SEARCH</h5>
				<h3 style="margin-top : 10px; color : #ff8a00;">${searchInput} 인기 검색 순위</h3>
			</div>
			<div class="col-md-4">				
			</div>	
		</div>
	</div>
	
	<div class="container-fluid" style="margin-bottom : 100px;">	
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6" style="background:#fff;">
				<div class="istore-container scrollbar scrollbar-warning" style="margin : 10px;">
					<c:choose>
						<c:when test="${sessionScope.loginemail != null && not empty istores}">
							<c:forEach var="istore" items="${istores}">
								<div class="row mb-3 rounded" style="background-color:#f7f3c8;">
							   		<div class="col-md-6">
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
												    			</div>
															</c:when>
															<c:otherwise>
																<div class="carousel-item">
																	<a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />'>
														      			<img src="${item.image_url}" alt="${istore.instaStore.store_no}" class="d-block img-fluid" >
														      		</a>
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
									</div>
							   		<div class="col-md-6" id="storesinfo">
										 <span class="mb-0" >
											 <a href='<c:url value="/stores/istoreInfo?store_no=${istore.instaStore.store_no}" />' style="color:#00233D; font-size:40px; font-weight:bold;">
											 	${istore.instaStore.store_name}
											 </a>
										 </span>
										 <hr>
										 <div class="mb-1 text-muted">${istore.instaStore.store_cate1}</div>
										 <p class="card-text mb-auto">${istore.instaStore.store_adr1}</p>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<h2>검색결과가 없습니다.</h2>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-md-4">
			</div>
		</div>
	</div>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>	
	
<!-- Script Start -->
<script>
	//헤더 그림자
	$(function() {
	    var header = $('header');
	
	    $(window).scroll(function(e) {
	    	if(header.offset().top !== 0) {
	            if(!header.hasClass('shadow')) {
	                header.addClass('shadow');
	            }
	        } else {
	            header.removeClass('shadow');
	        }
	    });
	});
</script>
<!-- Script End -->
</body>
<!-- Body End -->
</html>