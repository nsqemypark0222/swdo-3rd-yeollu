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
</head>
<style>

.pageTitle {position: fixed; left: 0; top: 0; width: 100%; height: 52px; line-height: 52px; color: #fff; text-align: center; background: #111;}
article {padding: 52px 3% 0;}
article .block {padding: 20px; min-height: 500px;}
article .block p {line-height: 22px; color: #000; font-size: 16px; font-weight: 600;}


</style>

<script type="text/javascript">
//Javascript
var count = 0;
//스크롤 바닥 감지
window.onscroll = function(e) {
    //추가되는 임시 콘텐츠
    //window height + window scrollY 값이 document height보다 클 경우,
    if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
    	//실행할 로직 (콘텐츠 추가)
        count++;
        var addContent = '<div class="block"><p>'+ count +'번째로 추가된 콘텐츠</p></div>';
        //article에 추가되는 콘텐츠를 append
        $('article').append(addContent);
    }
};

</script>

<body>


<!-- HTML -->
<header>                
    <h1 class="pageTitle">Infinity scroll</h1>
</header>
<article>
    <div class="block">
        <p>
			sdfasdf
        </p>
    </div>
    <div class="block">
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatem mollitia accusamus sequi ipsa, rerum nam laboriosam, ipsam aperiam deleniti beatae expedita id quisquam veritatis corporis, voluptates ducimus molestiae eum adipisci.
        </p>
    </div>
    <!-- 반복 -->
</article>


	
<div class="row">
	<div class="col">
		<div class="mypage_replies">	
			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<c:forEach var="reply" items="${replyList}" varStatus="status">
						<c:choose>
							<c:when test="${status.first}">
								<div class="carousel-item active">
		 							<div class="card flex-md-row mb-4 shadow-sm h-md-250 ">
										<div class="card-body d-flex flex-column align-items-start">     
		  									 <div class="reply_outer">						          
		    									<div class="reply_inner01">		          
		     										<strong class="d-inline-block mb-2" style="color:#FF8A00;">
														<a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default_2.png'/>" alt="프로필 사진"></a>
															<div class="reply_user_name"><a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}" style="color:#FF8A00;">${reply.USER_NAME}</a></div>
		 											</strong>
												</div>						          
		  										<div class="reply_inner02">
		 											<h3 class="mb-0">
		   												<a class="text-dark" href="#">${reply.STORE_NAME}</a>
															<c:choose>
																<c:when test="${reply.LIKE}">
																	<input type="image"  class="reply_heart_on"  src="<c:url value='/resources/img/heart_click.png'/>">
																</c:when>
																<c:otherwise>
																	<input type="image" class="reply_heart_off"    src="<c:url value='/resources/img/heart_unclick.png'/>">
																</c:otherwise>							
															</c:choose>						
														<input type="image"  class="reply_map"src="<c:url value='/resources/img/place.png'/>">			            		            
													</h3>
		
													<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
													<p class="card-text mb-auto">${reply.REPLY_CONTENTS}</p>		          
													<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
		         								</div>		       
		  									   </div>	        
		   								 </div>
									</div> 			
								</div>			
						   </c:when>
						<c:otherwise>
							<div class="carousel-item">
								<div class="card flex-md-row mb-4 shadow-sm h-md-250">
									<div class="card-body d-flex flex-column align-items-start">     
						  			   <div class="reply_outer">						          
						    				<div class="reply_inner01">		          
						     					<strong class="d-inline-block mb-2" style="color:#FF8A00;">
													<a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}"><img class="reply_user_profile" src="<c:url value='/resources/img/profile_default_2.png'/>" alt="프로필 사진"></a>
													<div class="reply_user_name"><a href="/getrend/mypage/mypage?user_name=${reply.USER_NAME}" style="color:#FF8A00;">${reply.USER_NAME}</a></div>
						 						</strong>
											</div>						          
						  					<div class="reply_inner02">
						 						<h3 class="mb-0">
							   						<a class="text-dark" href="#">${reply.STORE_NAME}</a>
													<c:choose>
														<c:when test="${reply.LIKE}">
															<input type="image"  class="reply_heart_on"  src="<c:url value='/resources/img/heart_click.png'/>">
														</c:when>
														<c:otherwise>
															<input type="image" class="reply_heart_off"    src="<c:url value='/resources/img/heart_unclick.png'/>">
														</c:otherwise>							
													</c:choose>						
													<input type="image"  class="reply_map"src="<c:url value='/resources/img/place.png'/>">			            		            
												</h3>
						
												<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
												<p class="card-text mb-auto">${reply.REPLY_CONTENTS}</p>		          
												<div class="mb-1 text-muted">${reply.REPLY_INDATE}</div>
						         			</div>		       
						   			  </div>	        
						          </div>
						      </div> 
						   </div>
						</c:otherwise>	
					</c:choose>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			  <span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			  <span class="carousel-control-next-icon" aria-hidden="true"></span>
			  <span class="sr-only">Next</span>
			</a>
		  </div>
		</div>
	</div>
</div>	
















</body>
</html>