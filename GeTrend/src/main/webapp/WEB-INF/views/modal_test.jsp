<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ autocomplete test ]</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$((function(){
	  var modalLayer = $("#modalLayer");
	  var modalLink = $(".modalLink");
	  var modalCont = $(".modalContent");
	  var marginLeft = modalCont.outerWidth()/2;
	  var marginTop = modalCont.outerHeight()/2; 
	 
	  modalLink.click(function(){
	    modalLayer.fadeIn("slow");
	    modalCont.css({"margin-top" : -marginTop, "margin-left" : -marginLeft});
	    $(this).blur();
	    $(".modalContent > a").focus(); 
	    return false;
	  });
	 
	  $(".modalContent > button").click(function(){
	    modalLayer.fadeOut("slow");
	    modalLink.focus();
	  });        
	});



</script>
<style>
a{color:#000;}
 
.mask{width:100%; height:100%; position:fixed; left:0; top:0; z-index:10; background:#000; opacity:.5; filter:alpha(opacity=50);}
 
#modalLayer{display:none; position:relative;}
#modalLayer .modalContent{width:440px; height:200px; padding:20px; border:1px solid #ccc; position:fixed; left:50%; 
top:50%; z-index:11; background:#fff;}
#modalLayer .modalContent button{position:absolute; right:0; top:0; cursor:pointer;}



</style>
</head>

<body>
<a href="/getrend/mypage/mypageForm" class="modalLink">간단한 모달 창 만들기</a>
<div id="modalLayer">
  <div class="modalContent">
    <a href="/getrend/mypage/mypageForm">모달창 테스트</a> 
    <button type="button">닫기</button>
  </div>
</div>
</body>



</html>