<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ MyPage | GeTrend ]</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" href='<c:url value="/resources/css/mypage.css"/>'>



</head>
<body style="background-color : #fbfcfc;">

	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div class="container_profile">
		<div class="row">
			<div class="col">
				<input type="hidden" id="user_email" value="${user.user_email}">			
				<input type="hidden" id="login_email" value="${sessionScope.loginemail}">
				<!-- 마이페이지 프로필 테이블 -->			
				<div class="mypage_profile" style="background-color : beige; border-radius:10px;">
					<table class="mypage_profile_table">
						<tr>
							<td>
								<c:choose>
									<c:when test="${user.user_email != sessionScope.loginemail}">
										<c:if test="${user.user_profile != null}">
							        		<img class="user_profile"  style="border-radius:50%;" src="${user.user_profile}" alt="프로필 사진">
							    		</c:if>
							    		<c:if test="${user.user_profile == null}">
											<img class="user_profile_de" style="border-radius:50%;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png" alt="프로필 사진">
							    		</c:if>
									</c:when>					
									<c:otherwise>
										<c:if test="${user.user_profile != null}">
								  			<a href='<c:url value="/users/userUpdate" />'>
							        			<img class="user_profile" src="${user.user_profile}" style="border-radius:50%;" alt="프로필 사진">
							      			</a>
							    		</c:if>
							    		<c:if test="${user.user_profile == null}">
							      			<a href='<c:url value="/users/userUpdate" />'>
												<img class="user_profile_de" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png"  style="border-radius:50%;"alt="프로필 사진">
							      			</a>
							    		</c:if>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
						<tr class="mypage_profile_user_name">
							<td>${user.user_name}</td>
						<tr>
					</table>
							
					<div style="float:right; margin: 13px;" >		
						<!-- 내 페이지 아니면 팔로우버튼 생성 -->	
						<c:if test="${sessionScope.loginemail != null}">
							<c:if test="${user.user_email != sessionScope.loginemail}">
								<c:choose>
									<c:when test="${following}">						
										<td style="padding : 0 25px;">
											<div id="followDiv">
												<input type="button" id="unfollowBtn"  data-toggle="modal" data-target="#exampleModalCenter" value="팔로잉" >
											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding : 0 25px;">
											<div id="followDiv">
												<input type="button" id="followBtn" value="팔로우"  data-toggle="modal" data-target="#followModalCenter">
											</div>
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	
		<!-- 가게, 팔로우, 팔로워 리스트 버튼 3개 -->
		<div class="row">
			<div class="col">
				<div class="mypage_buttons">
					<table class="mypage_buttons_table">
						<tr>
							<td>
								<button class="mypage_button btn_on button-class1" onclick="likeStore('${user.user_email}')">관심있는 가게   ${like}</button>
							</td>
							<td>
								<button class="mypage_button btn_off button-class2" onclick="follow('${user.user_email}')">팔로우   ${follow}</button>
							</td>
							<td>
								<button class="mypage_button btn_off button-class3" onclick="follower('${user.user_email}')">팔로워   ${follower}</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	
		<!-- 버튼 디폴트 ; 관심있는 가게 리스트 -->
		<div class="row">
			<div class="col">
				<div class="mypage_list">
					<div class="row">
						<div class="col" style="padding-top:25px;"><input type="image" style="width : 25px; float:left;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711928/resources/mypage/left-arrow_u89kp5.png"  onclick="leftArrow()">
						</div>
						<div class="col-10" >	
							<div class="scroll"> 
								<div id="scroll_box" style="width: 800px; height: 100px; overflow-x: hidden; overflow-y: hidden; " >
									<table class="mypage_list_table">
										<tr>
											<c:choose>
												<c:when test="${empty likeList}">
													<td style="width : 750px;">
														<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710953/resources/mypage/nostore_ltcnyg.png" alt="프로필 사진">
														</div>													
														<div style="width : 750px; font-size:14px; text-align:center;">관심있는 가게가 아직 없습니다
														</div>
													</td>
												</c:when>
												<c:otherwise>
													<c:forEach var="list" items="${likeList}">
														<td style="width : 200px;">
															<div style="width : 200px;text-align:center;">
																<a class="text-dark" href='<c:url value="/stores/istoreInfo?store_no=${list.STORE_NO}" />'>
																	<c:choose>
																		<c:when test="${list.CATE1 == 'beer.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'bunsik.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/bunsik_dlqnrr.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'cafe.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/cafe_ppr8cf.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'chiken.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chicken_pjtqbs.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'chinese.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chinese_sahxin.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'delivery.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/delivery_wvlds2.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'fastfood.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/fastfood_kebmfo.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'japanese.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/japanese_j8dgc4.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'korean.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																		</c:when>
																		<c:when test="${list.CATE1 == 'western.png'}">
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712598/resources/mypage/cate/western_vos9yf.png" alt="프로필 사진">
																		</c:when>
																		<c:otherwise>
																			<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/others_sixuzg.png" alt="프로필 사진">
																		</c:otherwise>
																	</c:choose>
																</a>
															</div>
															<div style="width : 200px; font-size:14px; text-align:center;">${list.STORE_NAME}
															</div>
														</td>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</tr>
									</table>
								</div>
							</div>
						</div>	
						<div class="col" style="padding-top:25px;"><input type="image" style="width : 25px; float: right;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711965/resources/mypage/right-arrow_bjqswr.png"  onclick="rightArrow()">
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<!--방 주인의 댓글 리스트  -->
		<div class="row">
			<div class="col">
				<div class="mypage_replies">
					<c:choose>
						<c:when test="${empty replyList}">
							<div class="noReply">
								<div style="position:relative;">
									<img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711990/resources/mypage/noreply_kaymuv.png" style="position:relative; width : 100px; left:100px;" alt="댓글없음"><br><br><br>
							 		<div style="text-align : center;">
							 			<h6>남긴 댓글이 아직 없습니다</h6>
							 		</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div> 
								<div>											
									<table class="table table-hover" id="replyTable01">				
										<c:forEach var="reply" items="${replyList}" begin="0" end="2">
											<tr class="table-light">
												<td>
													<div class="reply_outer">						          
														<div class="reply_inner01">		          
															<strong class="d-inline-block mb-2" style="color:#FF8A00;">
																<c:choose>
																	<c:when test="${reply.CATE1 == 'beer.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'bunsik.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/bunsik_dlqnrr.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'cafe.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/cafe_ppr8cf.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'chiken.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chicken_pjtqbs.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'chinese.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chinese_sahxin.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'delivery.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/delivery_wvlds2.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'fastfood.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/fastfood_kebmfo.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'japanese.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/japanese_j8dgc4.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'korean.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'western.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712598/resources/mypage/cate/western_vos9yf.png" alt="프로필 사진">
																	</c:when>
																	<c:otherwise>
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/others_sixuzg.png" alt="프로필 사진">
																	</c:otherwise>
																</c:choose>
															</strong>
															<div class="reply_cate_name">${reply.CATE2}</div>
														</div>						          
														<div class="reply_inner02">
															<h3 class="mb-0">
																<a class="text-dark" href='<c:url value="/stores/istoreInfo?store_no=${reply.STORE_NO}" />'>${reply.STORE_NAME}</a>	
																<a href="https://map.kakao.com/link/to/${reply.STORE_NAME},${reply.STORE_Y},${reply.STORE_X}"><input type="image"  class="reply_map" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712070/resources/mypage/place_uhyw4r.png"></a>
															</h3>			
															<div class="mb-1 text-muted">
																<span class="starMake">${reply.REPLY_STAR}</span>
															</div>
															<p class="card-text mb-auto" style="word-break:break-word;">${reply.REPLY_CONTENTS}</p>		          
															<div class="mb-1 text-muted">${reply.REPLY_INDATE}
															</div>
														</div>	       
													</div>
													<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
														<div class="deleteButton" style="float : right;">
															<img style="width:15px; cursor: pointer;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711360/resources/mypage/delete_vxexcp.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${reply.USER_NAME}')">
														</div> 
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
									<c:if test="${fn:length(replyList) > 3}">
								   		<div class="readAction">
			    							<button id="readMoreBtn" class="btn btn-outline-secondary btn-lg" onclick="moreRead();"> 더보기 + </button>
			    							<img id="readMoreSpin" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712040/resources/mypage/Spinner_rx4yeu.gif" style="display:none; width : 100px;">
		    							</div>
	    							</c:if>
	    							
	    							<table class="table table-hover" id="replyTable02" style="display:none;">				
										<c:forEach var="reply" items="${replyList}">
											<tr class="table-light">
												<td>
													<div class="reply_outer">						          
														<div class="reply_inner01">		          
															<strong class="d-inline-block mb-2" style="color:#FF8A00;">
																<c:choose>
																	<c:when test="${reply.CATE1 == 'beer.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'bunsik.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/bunsik_dlqnrr.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'cafe.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/cafe_ppr8cf.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'chiken.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chicken_pjtqbs.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'chinese.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chinese_sahxin.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'delivery.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/delivery_wvlds2.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'fastfood.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/fastfood_kebmfo.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'japanese.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/japanese_j8dgc4.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'korean.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진">
																	</c:when>
																	<c:when test="${reply.CATE1 == 'western.png'}">
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712598/resources/mypage/cate/western_vos9yf.png" alt="프로필 사진">
																	</c:when>
																	<c:otherwise>
																		<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/others_sixuzg.png" alt="프로필 사진">
																	</c:otherwise>
																</c:choose>
															</strong>
															<div class="reply_cate_name">${reply.CATE2}</div>
														</div>						          
														<div class="reply_inner02">
															<h3 class="mb-0">
																<a class="text-dark" href='<c:url value="/stores/istoreInfo?store_no=${reply.STORE_NO}" />'>${reply.STORE_NAME}</a>				
																<a href="https://map.kakao.com/link/to/${reply.STORE_NAME},${reply.STORE_Y},${reply.STORE_X}"><input type="image" class="reply_map" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712070/resources/mypage/place_uhyw4r.png"></a>
															</h3>			
															<div class="mb-1 text-muted"> <span class="starMake">${reply.REPLY_STAR}</span></div>
															<p class="card-text mb-auto" style="word-break:break-word;">${reply.REPLY_CONTENTS}</p>		          
															<div class="mb-1 text-muted">${reply.REPLY_INDATE}
															</div>
														</div>	       
													</div>
													<c:if test="${reply.USER_EMAIL eq sessionScope.loginemail}">
														<div class="deleteButton" style="float : right;">
															<img style="width:15px; cursor: pointer;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711360/resources/mypage/delete_vxexcp.png"  alt="삭제" onclick="return deleteReply('${reply.REPLY_NO}','${reply.USER_NAME}')">
														</div> 
													</c:if>
												</td>	
											</tr>
										</c:forEach>    								
							   	 </table>		
						    	</div>
							</div>				    
						</c:otherwise>
					</c:choose>	
				</div>				
			</div>
		</div>
	</div>
	
	<!--팔로우 Modal -->
	<div class="modal fade" id="followModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
	      		</div>
	      		<div class="modal-body text-center">
		        	<br>
		        	<c:if test="${user.user_profile != null}">
		        		<h5><img class="reply_user_profile" style="width : 100px;" src="${user.user_profile}" alt="프로필 사진"></h5>
		        	</c:if>
		        	<c:if test="${user.user_profile == null}">
		        		<h5><img class="reply_user_profile" style="width : 100px;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png" alt="프로필 사진"></h5>
		        	</c:if>
					<br><br>
					<p style="font-size : 15px;">${user.user_name}님을 팔로우하시겠어요? </p>
					<br>	
	      		</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-outline-warning" data-dismiss="modal" onclick="insertFollow('${user.user_email}');">팔로우</button>
	      		</div>
	    	</div>
		</div>
	</div>
	
	<!--언팔로우 Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          			<span aria-hidden="true">&times;</span>
	        		</button>
	      		</div>
	      		<div class="modal-body text-center">
		        	<br>
		        	<c:if test="${user.user_profile != null}">
		        		<h5><img class="reply_user_profile" style="width : 100px;" src="${user.user_profile}" alt="프로필 사진"></h5>
		        	</c:if>
		        	<c:if test="${user.user_profile == null}">
		        		<h5><img class="reply_user_profile" style="width : 100px;" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png" alt="프로필 사진"></h5>
	        		</c:if>
					<br><br>
						<p style="font-size : 15px;">${user.user_name}님의 팔로우를 취소하시겠어요? </p>
					<br>	
	      		</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-outline-warning" data-dismiss="modal" onclick="deleteFollow('${user.user_email}');">팔로우 취소</button>
	      		</div>
	    	</div>
		</div>
	</div>
	
	<footer>
			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>
	
	<script type="text/javascript">
	//헤더 그림자
	$(function(){
	  var header = $('header');
	  $(window).scroll(function(e){
	      if(header.offset().top !== 0){
	          if(!header.hasClass('shadow')){
	              header.addClass('shadow');
	          }
	      }else{
	          header.removeClass('shadow');
	      }
	  });
	});
	
	//팔로우 추가
	function insertFollow(follows_following){
		location.href="<c:url value='/insertFollow?follows_following=" + follows_following + "'/>";
	}
	
	//팔로우 취소
	function deleteFollow(follows_following){	
		location.href="<c:url value='/deleteFollow?follows_following=" + follows_following + "'/>";
	}
	
	//별점 png로 구현
	$(function(){
	
		$(".starMake").each(function(index,item){
			var star = $(this).text();
			
			if(star == 0.5) $(this).html("<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710144/resources/mypage/star_off_wpebpm.png' alt='☆'>");
			else if(star == 1) $(this).html("<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>");
			else if(star == 1.5) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710144/resources/mypage/star_off_wpebpm.png' alt='☆'>";
				$(this).html(temp);
			}
			else if(star == 2) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				    temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				$(this).html(temp);
			}
			else if(star == 2.5) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				    temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710144/resources/mypage/star_off_wpebpm.png' alt='☆'>";
				$(this).html(temp);
			}
			else if(star == 3) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				$(this).html(temp);
			}
			else if(star == 3.5) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710144/resources/mypage/star_off_wpebpm.png' alt='☆'>";
				$(this).html(temp);
			}
			else if(star == 4) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				$(this).html(temp);
			}
			else if(star == 4.5) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710144/resources/mypage/star_off_wpebpm.png' alt='☆'>";
				$(this).html(temp);
			}
			else if(star == 5) {
				var temp = "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
					temp += "<img style='width:19px;' src='https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710218/resources/mypage/star_wzffnm.png' alt='★'>";
				$(this).html(temp);
			}
		})
	})
	
	
	/*
	function leftArrow(){
		var rightPos = $('#scroll_box').scrollLeft();
		$("#scroll_box").animate({scrollLeft: rightPos - 270}, 200);
	
	}
	function rightArrow(){
		var leftPos = $('#scroll_box').scrollLeft();
		$("#scroll_box").animate({scrollLeft: leftPos + 270}, 200);
	}
	*/
	
	
	//관심있는가게, 팔로우, 팔로워 버튼 on / off
	$(function() {
	
	    $('.button-class1').click(function(){
	    	 if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
	         if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
	         if( $('.button-class2').hasClass('btn_on') ) $('.button-class2').removeClass('btn_on');
	         if( !$('.button-class2').hasClass('btn_off') ) $('.button-class2').addClass('btn_off');
	         if( $('.button-class3').hasClass('btn_on') ) $('.button-class3').removeClass('btn_on');
	         if( !$('.button-class3').hasClass('btn_off') ) $('.button-class3').addClass('btn_off');	
	    });
	    $('.button-class2').click(function(){
	    	 if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
	         if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
	         if( $('.button-class1').hasClass('btn_on') ) $('.button-class1').removeClass('btn_on');
	         if( !$('.button-class1').hasClass('btn_off') ) $('.button-class1').addClass('btn_off');
	         if( $('.button-class3').hasClass('btn_on') ) $('.button-class3').removeClass('btn_on');
	         if( !$('.button-class3').hasClass('btn_off') ) $('.button-class3').addClass('btn_off');
	    });
	    
	    $('.button-class3').click(function(){
	    	if( $(this).hasClass('btn_off') ) $(this).removeClass('btn_off');
	        if( !$(this).hasClass('btn_on') ) $(this).addClass('btn_on');
	        if( $('.button-class1').hasClass('btn_on') ) $('.button-class1').removeClass('btn_on');
	        if( !$('.button-class1').hasClass('btn_off') ) $('.button-class1').addClass('btn_off');
	        if( $('.button-class2').hasClass('btn_on') ) $('.button-class2').removeClass('btn_on');
	        if( !$('.button-class2').hasClass('btn_off') ) $('.button-class2').addClass('btn_off');	
	    });
	
	});
	
	//관심있는 가게 리스트 
	function likeStore(user_email){     
		$.ajax({
			url : "<c:url value='/mypage/likeStoreList' />",
			type : "post",
			data :  {"user_email" : user_email},
			success : function(result){ 
				$(".mypage_list_table").children().html('');
				if(result.length == 0){			
					var temp = '<td style="width : 750px;">';
						temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710953/resources/mypage/nostore_ltcnyg.png" alt="프로필 사진"></div>';													
						temp += '<div style="width : 750px; font-size:14px; text-align:center;">관심있는 가게가 아직 없습니다</div>';
						temp += '</td>';
						 $(".mypage_list_table").children().append(temp);
				}
				else{
	           		 $(result).each(function(index, item){
						var temp = '<td style="width : 200px;">';
							temp += '<div style="width : 200px; text-align:center;">';
							temp += "<a href='" + '<c:url value="/stores/istoreInfo?store_no=' + item.STORE_NO + '" />' + "'>";
							if(item.CATE1 === 'beer.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/beer_holcie.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'bunsik.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/bunsik_dlqnrr.png" alt="프로필 사진"></a></div>';							
							} else if(item.CATE1 === 'cafe.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/cafe_ppr8cf.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'chicken.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chicken_pjtqbs.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'chinese.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712596/resources/mypage/cate/chinese_sahxin.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'delivery.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/delivery_wvlds2.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'fastfood.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/fastfood_kebmfo.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'japanese.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/japanese_j8dgc4.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'korean.png') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/korean_f9ak06.png" alt="프로필 사진"></a></div>';
							} else if(item.CATE1 === 'western') {
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712598/resources/mypage/cate/western_vos9yf.png" alt="프로필 사진"></a></div>';
							} else { // others
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587712597/resources/mypage/cate/others_sixuzg.png" alt="프로필 사진"></a></div>';
							}
							temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.STORE_NAME+'</div>';
							temp += '</td>';							
						$(".mypage_list_table").children().append(temp);
	                });
				}  
			},
			error : function(){alert("실패");}
		});
	}
	
	//팔로워 리스트
	function follower(user_email){   
		$.ajax({
			url : "<c:url value='/mypage/followerList' />",
			type : "post",
			data :  {"follows_following" : user_email},
			success : function(result){ 
				$(".mypage_list_table").children().html('');
				if(result.length == 0){			
					var temp = '<td style="width : 750px;">';
						temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710737/resources/mypage/nofollow_nhwsnw.png" alt="프로필 사진"></div>';													
						temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로워가 아직 없습니다</div>';
						temp += '</td>';
						 $(".mypage_list_table").children().append(temp);
				}
				else {
					$(result).each(function(index, item){
						var temp = '<td style="width : 200px;">';
							temp += '<div style="width : 200px; text-align:center;">';
							temp += "<a href='" + '<c:url value="/mypage/mypage?user_name=' + item.USER_NAME + '" />' + "'>";
							if(item.USER_PROFILE == undefined){
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png" alt="프로필 사진"></a></div>';								
							} else {
								temp += '<img class="reply_cate_profile" src="'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';								
							}
							temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.USER_NAME+'</div>';
							temp += '</td>';							
					        $(".mypage_list_table").children().append(temp);
		             });
				}
			},
			error : function(){alert("실패");}
		});
	}
	
	//팔로우 리스트
	function follow(user_email){    
		$.ajax({
			url : "<c:url value='/mypage/followList' />",
			type : "post",
			data :  {"user_email" : user_email},
			success : function(result){ 
				$(".mypage_list_table").children().html('');
				if(result.length == 0){			
					var temp = '<td style="width : 750px;">';
						temp += '<div style="width : 750px; text-align:center;"><img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587710737/resources/mypage/nofollow_nhwsnw.png" alt="프로필 사진"></div>';													
						temp += '<div style="width : 750px; font-size:14px; text-align:center;">팔로우가 아직 없습니다</div>';
						temp += '</td>';
						 $(".mypage_list_table").children().append(temp);
				}
				else {
	            $(result).each(function(index, item){
						var temp = '<td style="width : 200px;">';
							temp += '<div style="width : 200px; text-align:center;">';
							temp += "<a href='" + '<c:url value="/mypage/mypage?user_name=' + item.USER_NAME + '" />' + "'>";
							if(item.USER_PROFILE == undefined){
								temp += '<img class="reply_cate_profile" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587709608/resources/header/user_digv92.png" alt="프로필 사진"></a></div>';								
							} else{
								temp += '<img class="reply_cate_profile" src="'+ item.USER_PROFILE +'" alt="프로필 사진"></a></div>';								
							}
							temp += '<div style="width : 200px; font-size:14px; text-align:center;">'+item.USER_NAME+'</div>';
							temp += '</td>';							
							$(".mypage_list_table").children().append(temp);
	                });
				}  
			},
			error : function(){alert("실패");}
		});	
	}
	
	//유저 확인해서 댓글 삭제 버튼 생성
	$(function(){
		var user_email = $("#user_email").val();
		var login_email = $("#login_email").val();
		if(user_email === login_email) {
			$('.table-light').hover(
				function () {
		        	$(this).children().children(".deleteButton").attr("src","https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711360/resources/mypage/delete_vxexcp.png");
		        },
		        function () {
		        	$(this).children().children(".deleteButton").attr("src","https://res.cloudinary.com/dw5oh4ebf/image/upload/v1587711371/resources/mypage/white_ijzzuk.png");
		        }
			);
		} 
	});
	
	//댓글 삭제
	function deleteReply(no,user_name){
		location.href="<c:url value='/mypage/deleteReply?reply_no=" + no + "&user_name=" + user_name + "'/>";
	}
	
	
	//더보기 버튼
	function moreRead(){
		if($("#replyTable02").css("display") == 'none') {
			$("#readMoreBtn").css("display","none");
			$("#readMoreSpin").css("display","");
			setTimeout(function() { 
				$("#readMoreSpin").css("display","none");
				$("#replyTable01").css("display","none");
				$("#replyTable02").css("display","");	
			}, 1000);
		}
	}
	</script>
</body>
</html>