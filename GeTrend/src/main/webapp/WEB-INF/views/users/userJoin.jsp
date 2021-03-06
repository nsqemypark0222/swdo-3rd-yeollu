<!-- 
/**
 * @File 	: userJoin.jsp
 * @Project : GeTrend
 * @Author	: 오선미, 문지연
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
*/
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<!-- Header Start -->
<head>
<meta charset="UTF-8">
<title>[ Join | GeTrend ]</title>
<!-- Vue.js -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.16.1/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<!-- jQuery and Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!-- UserJoin CSS -->
<link href='<c:url value="/resources/css/userJoin.css"/>' rel="stylesheet" media="all">
</head>
<!-- Header End -->

<!-- Body Start -->
<body>

	<div class="container">
		<div class="row">
      		<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        		<div class="card card-signin my-5">
          			<div class="card-body">
              			<h3 class="card-title text-center">회원 가입</h3>
						<form class="join" 
								action="<c:url value='join'/>" 
								id="joinForm"
								method="post" 
								v-on:submit="join" >
				  			<div class="form-label-group row">
				   	 			<div class="col-md-9">
					 				<input type="email" 
					 						class="form-control mb-2" 
					 						name="user_email" 
					 						id="user_email" 
					 						v-model="user_email" 
					 						ref="user_email"
					 						placeholder="이메일" 
					 						required autofocus />
					 				<small>{{ emailValidation }}</small>
					  			</div>
				    			<div class="col-md-3">
					 				<input type="button" 
					 						class="btn btn-mg btn-warning mb-2" 
					 						value="발송" 
					 						name="emailSend" 
					 						id="emailSend" />
				  	 			</div>
				  			</div>
				  			
				 			<div class="form-label-group row">
				 				<div class="col-md-9">
					  				<input type="text" 
					  						class="form-control mb-2" 
					  						name="joinCode" 
					  						id="joinCode" 
					  						placeholder="인증번호 입력" 
					  						required autofocus />
					  				<small>{{ joinCodeValidation }}</small>
					 			</div>
					 			<div class="col-md-3"> 
					    			<input type="button" 
					    					class="btn btn-outline-warning mb-2" 
					    					value="확인" 
					    					name="joinCodeCheck" 
					    					id="joinCodeCheck" /><br/>
					 			</div> 
							</div>	
							
							<div class="form-label-group row">
								<div class="col-md-12">
									<input type="password" 
										class="form-control mb-2" 
										name="user_pw" 
										id="user_pw" 
										v-model="user_pw" 
										placeholder="비밀번호 입력" />
									<small>{{ passwordValidation }}</small>
								</div>
							</div>
							
							<div class="form-label-group row">
								<div class="col-md-12">
									<input type="password" 
										class="form-control mb-2" 
										name="pwCheck" 
										id="pwCheck" 
										v-model="pwCheck" 
										placeholder="비밀번호 확인" />
									<small>{{ pwCheckValidation }}</small>
								</div>
							</div>	
							
							<div class="form-label-group row">
								<div class="col-md-12">
									<input type="text" 
											class="form-control" 
											name="user_name" 
											id="user_name" 
											v-model="user_name" 
											placeholder="이름" />
									<small>{{ nameValidation }}</small>
								</div>
							</div>
							
							<div class="form-label-group row">
								<div class="col-md-6">
									<input type="submit" 
											class="btn btn-lg btn-warning mb-2" 
											value="가입"  
											id="join" />
								</div>
								<div class="col-md-6">	
									<input type="reset" 
											class="btn btn-lg btn-outline-warning mb-2" 
											value="취소" 
											id="cancel" />
								</div>
								<hr class="my-4">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!-- Script Start -->
<script>
$(function(){
	$("#cancel").click(function() {
		location.href="<c:url value='/'/>";
	});
	
	$("#emailSend").click(function() {
		var email = $("#user_email").val();
		console.log(email);
		$.ajax({
			url: "<c:url value='emailCheck'/>",
			type: "get",
			data : {"user_email": email},
			success: function(result) {
				console.log(result);
				if(result === "1"){
					alert("이미 가입된 EMAIL입니다.");
				} else{
					sendEmail(email);
				}
			},
			error: function() {
				alert("에러");
			}
		});
	});
	
	function sendEmail(email) {
		console.log("send email");
		$.ajax({
			url: "<c:url value='emailAuth'/>",
			type: "post",
			data : {"user_email": email},
			success: function(result) {
				console.log(result);
				alert("이메일 발송");
			},
			error: function() {
				alert("에러");
			}
		});
	};
	
	$("#joinCodeCheck").click(function() {
		console.log("auth");
		var joinCode = $("#joinCode").val();
		$.ajax({
			url: "<c:url value='joinCodeCheck'/>",
			type: "post",
			data: {"joinCode": joinCode},
			success: function(result) {
				console.log(result);
				if(result === "fail") {
					var email = $("#user_email").val();
					sendEmail(email);
				} else{
					alert("인증번호 확인");
				}
			},
			error: function() {
				alert("에러");
			}
		});
	});
});
</script>

<script>
	const joinForm = new Vue({
		el: "#joinForm",
		data: {
			user_email: "",
			joinCode: "",
			user_pw: "",
			pwCheck: "",
			user_name: "",
			reg: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/
		},
		computed: {
			emailValidation: function() {
				if(this.user_email === "") {
					user_email.focus();
					return '이메일을 입력하세요';
				}
				if(!(this.reg.test(this.user_email))) {
					user_email.focus();
					return '이메일 형식에 맞게 입력하세요';
				}
				return '';
			},
			joinCodeValidation: function() {
				if(this.joinCode === "") {
					joinCode.focus();
					return '인증번호를 입력하세요';
				}
				return '';
			},
			passwordValidation: function() {
				if(this.user_pw === "") {
					user_pw.focus();
					return '비밀번호를 입력하세요';
				} else if(this.user_pw.length <= 3 || this.user_pw.length > 10){
					user_pw.focus();
					return '비밀번호는 4~10 글자를 입력하세요';
				}
				return '';
			},
			pwCheckValidation: function() {
				if(this.pwCheck === "") {
					return '비밀번호 확인을 입력하세요';
				} else if(this.pwCheck !== this.user_pw) {
					return '비밀번호가 일치하지 않습니다';
				}
			},
			nameValidation: function() {
				if(this.user_name === "") {
					user_name.focus();
					return '이름을 입력하세요';
				}
				return '';
			}
		},
		methods: {
			join: function() {

			}
		}
	});
</script>
<!-- Script End -->
</body>
<!-- Body End -->
</html>