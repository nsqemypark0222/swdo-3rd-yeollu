<!-- callback.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>NaverLoginTest</title>
</head>
<body>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
	<script>
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "mhTruKtoEj6RAeMJZQfY",
				callbackUrl: "http://localhost:8880/getrend/user/callback",
				isPopup: false,
				callbackHandle: true
			}
		);

		naverLogin.init();

		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					var naver= document.getElementById("naver");
					console.log(naver.childNodes);
		  			var pw = naver.childNodes[1];
		  			var name = naver.childNodes[3];
		  			var email = naver.childNodes[5];
		  			
		  			pw.value = naverLogin.user.getId();
					name.value = naverLogin.user.getName();
					email.value= naverLogin.user.getEmail();

					naver.submit();
					
					console.log(email);
					if( email == undefined || email == null) {
						alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
						naverLogin.reprompt();
						return;
					} 
					
		  			
				//	location.href = "http://localhost:8880/getrend/login?user_email=" + email + "&user_name="+;
				} else {
					console.log("callback 처리에 실패하였습니다.");
				}
			});
		});
	</script>
	<form action="naverLogin" id="naver" method="post">
		<input type="hidden" name="user_pw">
		<input type="hidden" name="user_name">
		<input type="hidden" name="user_email">
	</form>
</body>

</html>