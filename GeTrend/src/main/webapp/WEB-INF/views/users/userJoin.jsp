<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.16.1/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href='<c:url value="/resources/css/userJoin.css"/>' rel="stylesheet" media="all">
<script>

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
 <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
              <h2 class="card-title text-center">Sign In</h2>
				<div id="joinFrm">
					<form action="Join" method="post" name="joinFrm" @submit="formCheck" id="joinFrm">
					  <div class="form-label-group row">
					   	 <div class="col-md-9">
						 	<input type="email" class="form-control mb-2" name="user_email" id="user_email" v-model="user_email" placeholder="이메일" required autofocus>
						  </div>
					    <div class="col-md-3">
						 <input type="button" class="btn btn-mg btn-warning mb-2" value="발송" name="emailSend" id="emailSend">
					  	 </div>
					  </div>
					 <div class="form-label-group row">
					 	<div class="col-md-9">
						  <input type="text" class="form-control mb-2" name="joinCode" id="joinCode" placeholder="인증번호 입력" required autofocus>
						 </div>
						 <div class="col-md-3"> 
						    <input type="button" class="btn btn-outline-warning mb-2" value="확인" name="joinCodeCheck" id="joinCodeCheck"><br/>
						 </div> 
					</div>	
					<div class="form-label-group">
						<input type="password" class="form-control mb-2" name="user_pw" id="user_pw" v-model="user_pw" placeholder="비밀번호 입력">
					
						<input type="password" class="form-control mb-2" name="pwCheck" id="pwCheck" v-model="pwCheck" placeholder="비밀번호 확인" >
					</div>	
				   <div class="form-label-group text-center">
						<input type="text" class="form-control" name="user_name" id="user_name" v-model="user_name" placeholder="이름">
					</div>
					 <div class="form-label-group row">
						<div class="col-md-6">
						<input type="submit" class="btn btn-lg btn-warning mb-2" value="가입"  id="join" >
						</div>
						<div class="col-md-6">	
						<input type="reset" class="btn btn-lg btn-outline-warning mb-2" value="취소" id="cancel" >
						</div>		
						  <hr class="my-4">
					</div>
					</form>
					</div> 
				</div>
			</div>
		</div>
	</div>
   </div>		
</body>
<script>
const joinFrm = new Vue({
	el:'#joinFrm',
	data:{
		joinFrm:false,
		user_email:'',
		user_pw:'',
		pwCheck:'',
		user_name:''	
	},
		methods:{
			formCheck(e){
			this.Join != this.Join;
			if(!this.user_email){
				alert("이메일을 확인해주세요");
				user_email.focus();
				return false;
			}else if(!this.validEmail(this.user_email)){
				alert("이메일을 확인해주세요");
				return false;
			}
			if(!this.user_pw){
				alert("비밀번호를 확인해주세요");
				return false;
			}else if(this.user_pw.length <= 3 || this.user_pw.length > 10){
				alert("비밀번호는 4~10 글자를 입력하세요");
				return false;
			}
			if(!this.user_pw != this.pwCheck){
				alert("비밀번호를 확인해주세요.")
				return false;
			}
			if(!this.user_name){
				alert("이름을 확인해주세요.")
				return false;
			}
			return true;
		},
		 validEmail: function(user_email){
			var re =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			return re.test(user_email);
		 }	
		}
	})
</script>
</html>