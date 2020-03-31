<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>[ Home | GeTrend ]</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript" src='//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@api['KAKAOMAP_APPKEY']" />&libraries=drawing'></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<body>
test

	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	</header>
	
	<div id="map" style="width: 850px; height: 350px;">

	</div>
	
	<p>
		<button onclick="selectOverlay('POLYGON')">범위 선택</button>
	</p>
	
	<p class="getdata">
   		<button onclick="getDataFromDrawingMap()">조회 하기</button>
   	</p>
   	
   	<div id="print">
   	
   	</div>
   	

   	
   	

   	<div>
   		<table id="table" border="1">
   			<tr>
   				<th>store_no</th>
   				<th>store_name</th>
   				<th>sgtore_name2</th>
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
   				<th>mango_indate</th>
   			</tr>
   		</table>
   	</div>
   	
   	<div id="imageContainer">
   	
   	</div>

   	

   	
   	<div>
		<c:choose>
			<c:when test="${sessionScope.loginemail != null}">
				${sessionScope.loginname}님 환영합니다!<br/>
				<a href="users/userUpdate">회원정보 수정</a>
				<a href="users/logout">로그아웃</a>
				<a href="users/kakaoshare">공유하기</a>
				<a href="reply/replyForm">댓글달기</a>
				
			</c:when>
			<c:otherwise>
				<a href="users/userJoin">회원가입</a>
				<a href="users/userLogin">로그인</a>
			</c:otherwise>
		</c:choose>	
	</div>
	
	
	<a href="<c:url value='/likeForm'/>">좋아요 테스트</a>
	<a href="<c:url value='/crawlForm'/>">크롤링 테스트</a>
	<a href="<c:url value='/autocompleteForm'/>">자동완성 테스트</a>
	
	
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
					success: function(result) {
						alert("성공");
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

			$("#naturalLanguageProcessing").click(function(){
				location.href="<c:url value='/naturalLanguageProcessing'/>";
			});

			$("#naverTest").click(function(){
				location.href="<c:url value='/naverTest'/>";
			});
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
		
</script>
</body>
</html>