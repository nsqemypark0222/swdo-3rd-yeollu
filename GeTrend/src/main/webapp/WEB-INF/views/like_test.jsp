<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ like test ]</title>
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function likeInsert(){
	$.ajax({
					url : "/getrend/likes/likeInsert",
					type : "post",
					data : {"user_email" : $("#user_email").val(), "store_no" : $("#store_no").val()},
					success : function(){ 
							alert("성공");
							var temp =  '<input type="hidden"  id="user_email"   name="user_email" value="tete030tete@gmail.com">';
								temp += '<input type="hidden"  id="store_no"	 name="store_no"   value="25293330">';
								temp += '<input type="button"  id="unlikeButton" value="좋아요 취소"  onclick="likeDelete();">';
							$("#likeForm").html(temp);
						},
					error : function(){alert("실패");}
				})
}

function likeDelete(){
	$.ajax({
			url : "/getrend/likes/likeDelete",
			type : "post",
			data :  {"user_email" : $("#user_email").val(), "store_no" : $("#store_no").val()},
			success : function(){ 
					alert("성공");
					var temp =  '<input type="hidden"   id="user_email"  name="user_email" value="tete030tete@gmail.com">';
						temp += '<input type="hidden"   id="store_no"	 name="store_no"   value="25293330">';
						temp += '<input type="button"   id="likeButton"  value="좋아요"      onclick="likeInsert();">';
					$("#likeForm").html(temp);
				},
			error : function(){alert("실패");}
		})
}
	
</script>


</head>
<body>

<hr>
좋아요 insert<br>
	<form id="likeForm">
		<input type="hidden" id="user_email"   name="user_email" value="${sessionScope.loginemail}">
		<input type="hidden" id="store_no"	   name="store_no"   value="25293330">
		<input type="button" id="likeButton"   value="좋아요" onclick="likeInsert();">
	</form>


<hr>

</body>
</html>