<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href='<c:url value="/resources/css/login.css"/>'>
<link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
$(function(){
	$("#cancel").click(function(){
		$(location).attr('href',"<c:url value='/'/>");
	});
});
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row no-gutter">
		
		    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image">
		    </div>
		    
		    <div class="col-md-8 col-lg-6">
				<div class="login d-flex align-items-center py-5">
					<div class="container">
						<div class="row">
							<div class="col-md-9 col-lg-8 mx-auto">
								<h3 class="login-heading mb-4">LOGIN</h3>
							 	<form action="login" 
							 			method="post"
							 			id="loginForm"
							 			v-on:submit="login" >
							 		<div class="form-label-group">
					                	<input type="email" 
					                			id="user_email" 
					                			name="user_email" 
					                			class="form-control"  
					                			v-model="user_email" 
		                						ref="user_email"
					                			required autofocus>
					                	<label for="inputEmail">E-mail address</label>
					                	<small>{{ emailValidation }}</small>
					                </div>
					                
					                <div class="form-label-group">
					                	<input type="password" 
					                			name="user_pw" 
					                			id="user_pw" 
					                			class="form-control" 
					                			v-model="user_pw" 
		                						ref="user_pw"
					                			required>
					                	<label for="inputPassword">Password</label>
					                	<small>{{ passwordValidation }}</small>
					                </div>
					                
					                 <div class="custom-control custom-checkbox mb-3">
					                	<input type="checkbox" 
					                			class="custom-control-input" 
					                			id="remember" 
					                			name="remember" 
					                			value="1">
					                	<label class="custom-control-label" for="remember">Remember E-mail</label>
					                	
					                	<a id="linkjoin" href="<c:url value='userJoin'/>">회원가입</a>
					                </div>
					                
					                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit" >Sign in</button>
							 	</form>
							 	
							 	<div id="socialbtn">
					            	<div id="kakaoIdLogin">
					            		<a id="kakao-login-btn"></a>
					            	</div>
					            	<div id="naverIdLogin">
					            	</div>  <!-- 버튼이 들어갈 위치 선언. ID는 반드시 지정된 값으로 설정하여야 합니다.-->
					            	<div style="clear:both;"></div>
					           </div>
							</div>
						</div>
					</div>
				</div>
			</div>
	    </div>
	</div>
	
	<form action="kakaoLogin" id="kakao" method="post">
		<input type="hidden" name="user_pw">
		<input type="hidden" name="user_name">
		<input type="hidden" name="user_email">
	</form>
		
	<a href="http://developers.kakao.com/logout"></a>
	
	
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: '<spring:eval expression="@naver['CLIENT_ID']" />',
				callbackUrl: '<spring:eval expression="@naver['CALLBACK_URL']" />',
				loginButton: {color: "green", type: 3, height: 49} /* 로그인 버튼의 타입을 지정 */
			}
	);
			
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
</script>

<script type='text/javascript'>
	//<![CDATA[
	// 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('<spring:eval expression="@kakao['KAKAOLOGIN_APPKEY']" />');
	// 카카오 로그인 버튼을 생성합니다.
	Kakao.Auth.createLoginButton({
		
		container: '#kakao-login-btn',
	  	success: function(authObj) {
			Kakao.API.request({
				url:'/v2/user/me',
				success:function(res){
					
					var kakaoLogin = document.getElementById("kakao");
		  			console.log(kakaoLogin.childNodes);
		  			var kakaoId = kakaoLogin.childNodes[1];
		  			var kakaonickname = kakaoLogin.childNodes[3];
		  			var kakaoemail = kakaoLogin.childNodes[5];
		  			kakaoId.value = res.id;
		  			kakaonickname.value = res.properties.nickname;
		  			kakaoemail.value =res.kakao_account.email;
		  			console.log(kakaoId.value);
		  			console.log(kakaonickname.value);
		  			console.log(kakaoemail.value);
		  			console.log(authObj.access_token);
		
		  			kakaoLogin.submit();
				}	
			})	
		},
		fail: function(err) {
			   alert(JSON.stringify(err));
		}
	});
	//]]>
</script>

</body>
<script>
	const loginForm = new Vue({
		el: "#loginForm",
		data: {
			user_email: "${rememberemail}",
			user_pw: "",
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
			passwordValidation: function() {
				if(this.user_pw === "") {
					user_pw.focus();
					return '비밀번호를 입력하세요';
				} else if(this.user_pw.length <= 3 || this.user_pw.length > 10){
					user_pw.focus();
					return '비밀번호는 4~10 글자를 입력하세요';
				}
				return '';
			}
		},
		methods: {
			login: function() {

			}
		}
	});

</script>
</html>