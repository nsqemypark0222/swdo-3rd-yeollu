<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script>
function formCheck() {
	var user_pw = $('#user_pw').val();
	var pwCheck = $('#pwCheck').val();
	
	if(user_pw.length <= 3 || user_pw.length > 10) {
		alert("비밀번호는 4~10 글자를 입력하세요");
		return false;
	}
	
	if(user_pw !== pwCheck) {
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	return true;
}

$(function(){
	$("#cancel").click(function(){
		location.href="<c:url value='/'/>";
	});
	$("#emailSend").click(function(){
		var email = $("#user_email").val();
		console.log(email);
		$.ajax({
			url: "<c:url value='emailCheck'/>",
			type: "get",
			data : {"user_email": email},
			success: function(result){
				console.log(result);

				if(result === "1"){
					alert("이미 가입된 EMAIL입니다.");
				}else{
					sendEmail(email);
				}
			},
			error:function(){
				alert("에러");
			}
		});
	});
function sendEmail(email){
	console.log("send email");
	$.ajax({
		url: "<c:url value='emailAuth'/>",
		type: "post",
		data : {"user_email": email},
		success: function(result){
			console.log(result);
			alert("이메일 발송");
		},
		error:function(){
			alert("에러");
		}
	});
};
$("#joinCodeCheck").click(function(){
	console.log("auth");
	var joinCode = $("#joinCode").val();
	$.ajax({
		url: "<c:url value='joinCodeCheck'/>",
		type: "post",
		data: {"joinCode": joinCode},
		success: function(result){
			console.log(result);
			if(result === "fail") {
				var email = $("#user_email").val();
				sendEmail(email);
			}else{
				alert("인증번호 확인");
			}
		},
		error:function(){
			alert("에러");
		}
	});
});
});

</script>
</head>
<body>
	<h1>회원가입</h1>
		<form action="join" method="post" name="joinFrm" id="joinFrm">
			<label>이메일</label>
			<input type="email" name="user_email" id="user_email" placeholder="이메일">
			<input type="button" value="발송" name="emailSend" id="emailSend"><br/>
			<input type="text" name="joinCode" id="joinCode" placeholder="인증번호 입력">
			<input type="button" value="확인" name="joinCodeCheck" id="joinCodeCheck"><br/>
			
			<label>비밀번호</label>
			<input type="password" name="user_pw" id="user_pw"><br/>
			<input type="password" name="pwCheck" id="pwCheck" placeholder="비밀번호 확인" ><br/>
			
			<label>이름</label>
			<input type="text" name="user_name" id="user_name"><br/>
			
			<input type="submit" value="가입"  id="signup" onclick="return formCheck()" >
			<input type="reset" value="취소" id="cancel">	
		</form>
</body>
</html>