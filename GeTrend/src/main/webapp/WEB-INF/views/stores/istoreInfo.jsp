<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ ${istore.instaStore.store_name} | GeTrend ]</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<div class="row">
					<img class="rounded-circle" src="${istore.instaImage.repImg}">
				</div>
				<div class="row">
					<div class="col-md-4">
						좋아요
					</div>
					<div class="col-md-4">
						위치
					</div>
					<div class="col-md-4">
						공유?
					</div>
				</div>
				<div class="row">
					${istore.instaStore.store_cate1} <br />
					${istore.instaStore.store_adr} <br />
					${istore.instaStore.store_adr1} <br />
				</div>
				<div id="socialbtn" class="row mx-auto">
	            	<div id="kakaoIdLogin" class="col-md-6">
	            		<a id="kakao-custom-login-btn">
	            			<img class="img-fluid" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586494683/kakao_login_btn_bkouot.png">
	            		</a>
	            	</div>
	            	<div id="naverIdLogin" class="col-md-6">
	            		<a id="naver-custom-login-btn">
	            			<img class="img-fluid" src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586507185/naver_login_btn_omkvf5.png">
	            		</a>
	            	</div>
	            </div>
			</div>
			<div class="col-md-6">
				<div class="card mb-3">
					<div class="row no-gutters">
						<div class="row">
							<div class="card-body">
								<c:forEach var="item" items="${istore.instaImage.postImgList}">
									<img class="rounded img-fluid" src="${item.imgUrl}">
								</c:forEach>
						    </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>
</body>
</html>