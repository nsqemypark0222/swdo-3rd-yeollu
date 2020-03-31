<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function insertFollow(){
	$.ajax({
		url : "/getrend/insertFollow",
		type : "post",
		data : {"follows_following" : $("#follows_following").val()},
		success : function(){ 
			alert("성공");
			var	temp = '<input type="hidden"  id="follows_following"   name="follows_following" value="aaa@gmail.com">';
				temp += '<input type="button"  id="unfollowButton" value="팔로우 취소"  onclick="deleteFollow();">';
			$("#followForm").html(temp);
		},
		error : function(){alert("실패");}
	})
}

function deleteFollow(){
	$.ajax({
		url : "/getrend/deleteFollow",
		type : "post",
		data :  {"follows_following" : $("#follows_following").val()},
		success : function(){ 
			alert("성공");
			var temp = '<input type="hidden"  id="follows_following"   name="follows_following" value="aaa@gmail.com">';
				temp += '<input type="button"  id="followBtn" value="팔로우"  onclick="insertFollow();">';
			$("#followForm").html(temp);
		},
		error : function(){alert("실패");}
	})
}
	
</script>

</head>
<body>
<form id="followForm">
	<input type="hidden" id="follows_following" name="follows_following" value="aaa@gmail.com">
	<input type="button" id="followBtn" value="팔로우" onclick="insertFollow();">
</form>
</body>
</html>