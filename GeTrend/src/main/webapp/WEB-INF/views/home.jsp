<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>[ Home | GeTrend ]</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="crossorigin="anonymous"></script>
<script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@kakao['KAKAOMAP_APPKEY']" />&libraries=drawing'></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
<link rel="stylesheet" href='<c:url value="/resources/css/home.css"/>'>
<link rel="stylesheet" href='<c:url value="/resources/css/loading.css"/>'>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
<div class="column-container">
	<!-- column-content시작 -->
			<!-- 로딩이미지 -->
    	<div class="wrap-loading display-none">
    		<div><img src="https://res.cloudinary.com/dw5oh4ebf/image/upload/v1585986827/loader_sxmz3a.gif" /></div>
		</div>
		<!--  컨텐트 시작 -->
 	<div class="column-content">
	<!-- 지도와의 간격 div-->
	<div id="space">
	<div class="container" >
	<div class="col" >
	<div id="map">
	<!-- 지도 위의 버튼 1 음식 카테고리 -->
		<div class="btn-modal">
			<input type="button" class="btn btn-dark" id="btn_category" data-toggle="modal" data-target="#category" value="category">
	          <div class="modal fade" id="category">
	           <div class="modal-dialog">
	           <div class="modal-content">
				<!-- 음식 카테고리 모달 헤더 -->
				<div class="mol-header">
			         <h4 class="modal-title"> Category</h4>
			           <button type="button" class="close" data-dismiss="modal">&times;</button>
			     </div>
			      <!--  음식 카테고리 모달 body -->
			       <div class="modal-body">
			           	<h3>소상공인 db 음식 종류</h3>
			           	<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio">한식
						  </label>
						</div>
						<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio">양식
						  </label>
						</div>
						<div class="form-check-inline disabled">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio" >일식
						  </label>
						</div>
						<div class="form-check-inline disabled">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio" >디저트
						  </label>
						</div>
			       </div>
			     	<!-- 음식 카테고리 모달 footer -->
			        <div class="modal-footer">
			        <button type="button" class="btn btn-dark" data-dismiss="modal">확인</button>
			        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
			        </div>
	    	 	</div>
	    		</div>
	    	 </div>
	    <!-- 지도 위의 버튼 2 영업 확인 -->
    	  <input type="button" class="btn btn-warning" id="btn-opentime" data-toggle="modal" data-target="#opentime" value="영업확인 " >
          <div class="modal fade" id="opentime">
           <div class="modal-dialog">
           <div class="modal-content">
				<!-- 영업확인  모달 헤더 -->
			<div class="mol-header">
		         <h4 class="modal-title"> 영업 확인</h4>
		           <button type="button" class="close" data-dismiss="modal">&times;</button>
		     </div>
		      <!--  영업확인  모달 body -->
		       <div class="modal-body" id="opentime">
		          	<p>식당 영업 요일별 확인</p>
		          	<div class="form-check-inline">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio">월
					  </label>
					</div>
					<div class="form-check-inline">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio">화
					  </label>
					</div>
					<div class="form-check-inline disabled">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio" >수
					  </label>
					</div>
					<div class="form-check-inline disabled">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio" >목
					  </label>
					</div>
					<div class="form-check-inline disabled">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio" >금
					  </label>
					</div>
					<div class="form-check-inline disabled">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio" >토
					  </label>
					</div>
					<div class="form-check-inline disabled">
					  <label class="form-check-label">
					    <input type="radio" class="form-check-input" name="optradio" >일
					  </label>
					</div>
		       </div>
		        
		     	<!-- 영업확인 모달 footer -->
		        <div class="modal-footer">
		        <button type="button" class="btn btn-warning" data-dismiss="modal">확인</button>
		        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
		        </div>   
    	 	</div>
    		</div>
    	 </div>
	    </div>
	    	

    	 <button type="button"  class="btn btn-dark" id="selectOverlay" onclick="selectOverlay('POLYGON')">
    	 범위 선택</button>
    	<button type="button"  class="btn btn-warning" id="drawingMap"  onclick="getDataFromDrawingMap()">	
    	조회 하기</button>  
   		</div>  	 
	</div>
	</div>
	</div>
	<!-- map div 끝 -->	
	
	<!-- 추천 가게 리스트 출력 -->
   		<div id="istoreList" class="istoreList">
	   		<ul class="list-group list-group-horizontal">
	   			<li class="list-group-item">
	   				<img src="resources/img/ok.jpg" alt="동명동 추천 가게 " class="rounded float left" style="	width:380px; height:310px;" >
	   		   		<div class="istore1">
	   		   		<div class="overlay1">
	   				<div class="text" style="color:white;">동명동 추천 가게</div>
	  				</div>
	  				</div>
	   			</li>		
	   			<li class="list-group-item">
	   				<div class="istore2">
	   				<img src="resources/img/ok.jpg" alt="양림동 추천 가게 " class="rounded float left" style="	width:380px; height:310px;" >
	   		   		 <div class="overlay2">
	   				<div class="text">
	   					<a href="<c:url value='/users/istore_test'/>">양림동 추천 가게</a>
	   				</div>
	  				</div>
	  				</div>
	   			</li>
	  	  	</ul>	
  		</div>
</div>	
	<!-- content 끝 -->
		
<!--    <div class="">
		<p>
			<button type="button"  class="btn btn-warning" onclick="selectOverlay('POLYGON')">범위 선택</button>
		</p>
		<p class="getdata"> 
	   		<button type="button"  class="btn btn-outline-warning" onclick="getDataFromDrawingMap()">조회 하기</button>
	   	</p>
	   	<div id="print">
	   	</div>
   </div>  -->
      	
  		
 

	<!-- 사이드 컬럼  시작 -->
   	<div class="column-side">
   		<div class="row">
	   		<div id="istore" class="istore">
	   		<h5 style="text-align:center;">주목Trend</h5>
	   		<ul class="list-group">
	   			<li class="list-group-item">
	   				<div class="istore2">
	   				<img src="resources/img/corn.jpg" alt="sampleTest" class="rounded float-right" style="width:100%; height:100%;">
	   		   		 <div class="overlay2">
	   				<div class="text">내일의 추천 가게</div>
	  				</div>
	  				</div>
	   			</li>
	   				<li class="list-group-item">
	   				<div class="istore2">
	   				<img src="resources/img/bluebottle.jpg" alt="sampleTest" class="rounded float-right" style="width:100%; height:100%;">
	   		   		 <div class="overlay2">
	   				<div class="text">이번주 추천 가게</div>
	  				</div>
	  				</div>
	   			</li>
	  	  		</ul>	
	  		</div>
  		</div>
  	</div>
</div>
   						

   	<div id="desc">
   	
   	</div>
   	

   	<div>
   		<table id="table" border="1">
   			<tr>
   				<th>store_no</th>
   				<th>store_name</th>
   				<th>store_name2</th>
   				<th>store_cate1</th>
   				<th>store_cate2</th>
   				<th>store_adr</th>
   				<th>store_adr1</th>
   				<th>store_adr2</th>
   				<th>store_x</th>
   				<th>store_y</th>
   				<th>PROFILE_URL</th>
   			</tr>
   		</table>
   	</div>
   	
   	<div>
   		<table id="mangoTable" border="1">
   			<tr>
   				<th>store_no</th>
   				<th>mango_tel</th>
   				<th>mango_kind</th>
   				<th>mango_price</th>
   				<th>mango_parking</th>
   				<th>mango_time</th>
   				<th>mango_break_time</th>
   				<th>mango_last_order</th>
   				<th>mango_holiday</th>
   			</tr>
   		</table>
   	</div>


   	<div>
		<c:choose>
			<c:when test="${sessionScope.loginemail != null}">
				${sessionScope.loginname}님 환영합니다!<br/>
				<a href="users/userUpdate">회원정보 수정</a>
				
				
				
				<a href="users/logout">로그아웃</a>
				<a href="users/kakaoshare">공유하기</a>
				<a href="reply/replyForm">댓글달기</a>
				<a href="<c:url value='/mypage/mypageForm'/>">팔로우, 댓글, 좋아요 테스트</a>	
			</c:when>
			<c:otherwise>
				<a href="users/userJoin">회원가입</a>
				<a href="users/userLogin">로그인</a>
				<a href="users/userUpdate">회원 수정</a>
				
			</c:otherwise>
		</c:choose>	
	</div>

	
	<a href="<c:url value='/likes/likeForm'/>">좋아요 테스트</a>
	<a href="<c:url value='/crawl/crawlForm'/>">크롤링 테스트</a>
	<a href="<c:url value='/autocomplete/autocompleteForm'/>">자동완성 테스트</a>
	<a href="<c:url value='/mypage/mypageForm'/>">mypage 테스트</a>
	<a href="users/follow">follow</a>
	

	<a href="<c:url value='/likes/likeForm'/>">좋아요 테스트</a>
	<a href="<c:url value='/crawl/crawlForm'/>">크롤링 테스트</a>
	<a href="<c:url value='/autocomplete/autocompleteForm'/>">자동완성 테스트</a>
	<a href="<c:url value='/users/follow'/>">follow</a>
</div>

	
<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</footer>

<script type="text/javascript">

		$(function() {
			function search(points) {
				$.ajax({
					url: "<c:url value='/search' />",
					type: "post",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(points),
					dataType: "json",
					beforeSend:function(){
						$('.wrap-loading').removeClass('display-none');
					},
					complete:function(){
						$('.wrap-loading').addClass('display-none');
					},
					success: function(result) {
						alert("성공");
						printMarker(result);
						console.log(result);
						$(result).each(function(index, item) {
							$("#table").append(
								"<tr>" + "<td>" + item.instaStore.store_no + "</td>"
										+ "<td>" + item.instaStore.store_name + "</td>"
										+ "<td>" + item.instaStore.store_name2 + "</td>"
										+ "<td>" + item.instaStore.store_cate1 + "</td>"
										+ "<td>" + item.instaStore.store_cate2 + "</td>"
										+ "<td>" + item.instaStore.store_adr + "</td>"
										+ "<td>" + item.instaStore.store_adr1 + "</td>"
										+ "<td>" + item.instaStore.store_adr2 + "</td>"
										+ "<td>" + item.instaStore.store_x + "</td>"
										+ "<td>" + item.instaStore.store_y + "</td>"
										+ '<td><img src=' + item.instaImage.repImg + ' /></td>' + "</tr>"
							);
							$("#mangoTable").append(
									"<tr>" + "<td>" + item.mangoStore.store_no + "</td>"
										+ "<td>" + item.mangoStore.mango_tel + "</td>"
										+ "<td>" + item.mangoStore.mango_kind + "</td>"
										+ "<td>" + item.mangoStore.mango_price + "</td>"
										+ "<td>" + item.mangoStore.mango_parking + "</td>"
										+ "<td>" + item.mangoStore.mango_time + "</td>"
										+ "<td>" + item.mangoStore.mango_break_time + "</td>"
										+ "<td>" + item.mangoStore.mango_last_order + "</td>"
										+ "<td>" + item.mangoStore.mango_holiday + "</td>" + "</tr>"
							);
							$(item.instaImage.postImgList).each(function(idx, itm) {
								$("#imageContainer").append(
										"<div name='imgPanel'>"
											+ '<img src=' + itm.imgUrl + ' />'
											+ '<b>' + itm.like + '</b>'
								);
							});
							$('#imageContainer').append("<hr />");
						});
						
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
		mapOption = {
				center: new kakao.maps.LatLng(35.15113, 126.924584),
				level: 1 // 지도의 확대 레벨
		};

		const map = new kakao.maps.Map(mapContainer, mapOption);




		
		const printMarker = (result) => {
			
			// 마커를 표시할 위치와 title 객체 배열입니다
			let positions = [];
			
			for(let n in result) {
				const posotion = {
					title: result[n].instaStore.store_name,
					latlng: new kakao.maps.LatLng(result[n].instaStore.store_y, result[n].instaStore.store_x)
				}
				positions.push(posotion);
			}
			/* let positions = [
				{
					title: '카카오',
					latlng: new kakao.maps.LatLng(35.15113, 126.924584)
				}
			]; */

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

		// 버튼 클릭 시 호출되는 핸들러 입니다
  		selectOverlay = type => {
	    	// 그리기 중이면 그리기를 취소합니다
	   		manager.cancel();
	    	// 클릭한 그리기 요소 타입을 선택합니다
	    	manager.select(kakao.maps.drawing.OverlayType[type]);
  		};

	   // 가져오기 버튼을 클릭하면 호출되는 핸들러 함수입니다
	   const getDataFromDrawingMap = () => {
		   // Drawing Manager에서 그려진 데이터 정보를 가져옵니다
	        const data = manager.getData();
	                
	        let points = [];
	        let row = "";
	        for(let i in data.polygon[0].points) {
				const point = {
			        	x: data.polygon[0].points[i]["x"],
			        	y: data.polygon[0].points[i]["y"]
			    };
				points.push(point);
				row += "<p>위도 : " + point["x"] + " 경도 : " + point["y"] + "</p>";
			}
	        const printContainer = document.getElementById("print");
	        printContainer.innerHTML = row;
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
		
</script>
</body>
</html>