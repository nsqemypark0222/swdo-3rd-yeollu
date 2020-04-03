<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ modal test ]</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
function openModal(modalname){
	  document.get
	  $("#modal").fadeIn(300);
	  $("."+modalname).fadeIn(300);
	}

	$("#modal, .close").on('click',function(){
	  $("#modal").fadeOut(300);
	  $(".modal-con").fadeOut(300);
	});
</script>
<style>
*{margin:0; padding:0;}
a.button{display:inline-block; padding: 10px 20px; text-decoration:none; color:#fff; background:#000; margin:20px;}
#modal{
  display:none;
  position:fixed; 
  width:100%; height:100%;
  top:0; left:0; 
  background:rgba(0,0,0,0.3);
}
.modal-con{
  display:none;
  position:fixed;
  top:50%; left:50%;
  transform: translate(-50%,-50%);
  max-width: 60%;
  min-height: 30%;
  background:#fff;
}
.modal-con .title{
  font-size:20px; 
  padding: 20px; 
  background : gold;
}
.modal-con .con{
  font-size:15px; line-height:1.3;
  padding: 30px;
}
.modal-con .close{
  display:block;
  position:absolute;
  width:30px; height:30px;
  border-radius:50%; 
  border: 3px solid #000;
  text-align:center; line-height: 30px;
  text-decoration:none;
  color:#000; font-size:20px; font-weight: bold;
  right:10px; top:10px;
}
</style>
</head>

<body>


<div id="wrap">
  <a href="javascript:openModal('modal1');" class="button modal-open">모달열기1</a>
  <a href="javascript:openModal('modal2');" class="button modal-open">모달열기2</a>
</div>

<div id="modal"></div>
  <div class="modal-con modal1">
    <a href="javascript:;" class="close">X</a>
    <p class="title">제목</p>
    <div class="con">
		모달테스트으
    </div>
  </div>
  
   <div class="modal-con modal2">
    <a href="javascript:;" class="close">X</a>
    <p class="title">제목2</p>
    <div class="con">
		모달투
    </div>
  </div>


</body>



</html>