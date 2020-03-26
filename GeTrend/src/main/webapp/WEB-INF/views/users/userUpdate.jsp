<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
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
		$(location).attr('href',"<c:url value='/'/>");
	});

	var user_email = $("#user_email").val();
	$("#UserDelete").click(function(){
		$(location).attr('href',"DeleteUser?user_email="+user_email);	
	}); 
	
});
</script>
</head>
<body>
	<h1>회원정보수정</h1>
		<form action="update" method="post">
			
			이메일 : <input type="email" name="user_email" id="user_email" value="${user.user_email}" readonly="readonly"><br/>
			비밀번호 : <input type="password" name="user_pw" id="user_pw" ><br/>
			<input type="password" name="pwCheck" id="pwCheck" placeholder="비밀번호 확인" ><br/>
			이름 : <input type="text" name="user_name" id="user_name" value="${user.user_name}"><br/>
			<input type="submit" value="저장"  id="update" onclick="return formCheck()" >
			<input type="reset" value="취소" id="cancel">	
			<input type="button" value="회원탈퇴" id="UserDelete">
		</form>
</body>
</html>