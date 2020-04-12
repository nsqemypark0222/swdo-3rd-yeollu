<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
<link href='<c:url value="/resources/css/header.css"/>' rel="stylesheet" media="all">
<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
	li:nth-of-type(n+10) { display: none; }
	.ui-autocomplete .highlight {color: orange;}
</style>

<title>Header</title>
</head>	
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" id="navbar-brand"  href="<c:url value='/'/>" >GeTrend</a>
	<div class="input-group">
		<form class="form-inline" >
	 		<div class="col-md-9">
	 			<input class="form-control mr-sm-2" type="text" id="searchInput" name="searchInput" placeholder="Search">
    		</div>
    	</form>
        <div class="menu_list">
  			<ul class="nav jus">
  			<c:choose>
  				<c:when test="${sessionScope.loginemail != null}">
  					<li class="nav-item">
  						<a href="<c:url value="/mypage/mypageForm"/>">
	  			    		<input type="button" class="btn btn-light" id="mypage" value="내 방">
 			    		</a>
	  			    </li>
	  			    <li class="nav-item">
	  			    	<a href="<c:url value="/users/logout"/>">
	  			        	<input type="button" class="btn btn-light" value="Logout">
	  			        </a>
	  			    </li>
	  			    <li class="nav-item">
		                <a class="nav-link" href="<c:url value='/users/userUpdate'/>">
		                	<img class="profile" style="border-radius: 60px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png" width="30px" height="30px"  >
	  			        </a>
	  			    </li>
  				</c:when>
  				<c:otherwise>
  					<li>
	  			        <a href="<c:url value="/users/userJoin"/>">
	  			        	<input type="button" class="btn btn-light" id="userJoin" value="회원가입">
	  			        </a>
	  			    </li>
	  			    <li>
	  			        <a href="<c:url value="/users/userLogin"/>">
	  			        	<input type="button" class="btn btn-light" id="userLogin" value="로그인">
	  			        </a>
	  			    </li>
  				</c:otherwise>
  			</c:choose>
  			</ul> 
    	</div>     	  
	</div>	 
</nav>
	
    	 

	
	
<script type="text/javascript">
	$(function() {

		function highlightText(text, $node) {
			var searchText = $.trim(text).toLowerCase(), currentNode = $node.get(0).firstChild, matchIndex, newTextNode, newSpanNode;
			while ((matchIndex = currentNode.data.toLowerCase().indexOf(searchText)) >= 0) {
				newTextNode = currentNode.splitText(matchIndex);
				currentNode = newTextNode.splitText(searchText.length);
				newSpanNode = document.createElement("span");
				newSpanNode.className = "highlight";
				currentNode.parentNode.insertBefore(newSpanNode, currentNode);
				newSpanNode.appendChild(newTextNode);
			}
		}

		$("#searchInput").autocomplete({
	           source : function( request, response ) {
	               $.ajax({
	                   type: 'get',
	                   url: "<c:url value='/autocomplete/source'/>",
	                   dataType: "json",
	                   data: {"param" : $("#searchInput").val()},
	                   success: function(data) {
	                       response(
	                           $.map(data, function(item) {    
	                               return {
	                                   label: item,   
	                                   value: item,    
	                                   test : item+"test"   
	                               }
	                           })
	                       );
	                   }          
	              });
	           }, 
	           
	       focus : function(event, ui) {   
	           return false;
	       },
	       minLength: 1,
	       autoFocus: false
//	       delay: 500
	       }).data("ui-autocomplete")._renderItem = function(ul, item) {
			var $a = $("<b></b>").text(item.value);
			highlightText(this.term, $a);
			return $("<li></li>").append($a).appendTo(ul);
		};

		$("#searchInput").keydown(function (key) {    	 
	        if(key.keyCode == 13){
	        	event.preventDefault();
	        	$.ajax({
	        		url : "<c:url value='/autocomplete/keyword'/>",
	        		type : "post",
	        		data : {"keyword" : $("#searchInput").val()},
	        		dataType: "json",
					success: function(result) {
						alert("성공");
						printMarker(result);
						console.log(result);
						$(result).each(function(index, item) {
							panTo(item.instaStore.store_y, item.instaStore.store_x);
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
	        };
	    });
	});
</script>
</body>
</html>
