<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script>
$(function(){
	$("#cancel").click(function(){
		$(location).attr('href',"<c:url value='/'/>");
	});
});
</script>
</head>
<body>
	<h1>LOGIN :)</h1>
	<form action="login" method="post">
		<table>
		<tr>
			<td><label>ID</label></td>
			<td><input type="text" id="user_id" name="user_id" value="${rememberId}"></td>
			<td><input type="checkbox" name="remember" value="1"> 아이디 기억하기</td>
		</tr>
		<c:if test='${!errMsg1.equals("")}'>
			 <tr>
				<td colspan="2"> ${errMsg1}</td>
			 </tr>
		 </c:if>
		 <tr>
		 <td><label>Password</label></td> 
		 <td><input type="password" name="user_pw" id="user_pw"></td>
		 </tr>
		 <c:if test='${!errMsg2.equals("")}'>
			 <tr> 
				<td colspan="2"> ${errMsg2}</td>
			 </tr>
		 </c:if>
		 <tr>
			<td><input type="submit" value="로그인" id="login">	</td>
			<td><input type="reset" value="취소"id="cancel">	</td>
			<td><a href="userJoin">회원가입</a></td>
		 </tr>
		</table>
	</form>
		
		<a id="kakao-login-btn"></a>
		<a href="http://developers.kakao.com/logout"></a>
		<script type='text/javascript'>
			//<![CDATA[
			// 사용할 앱의 JavaScript 키를 설정해 주세요.
			Kakao.init('<spring:eval expression="@api['KAKAOLOGIN_APPKEY']" />');
			
			// 카카오 로그인 버튼을 생성합니다.
			Kakao.Auth.createLoginButton({
			  container: '#kakao-login-btn',
			  success: function(authObj) {
				Kakao.API.request({
					url:'/v2/user/me',
					success:function(res){
				  		//alert(JSON.stringify(res));
				  			var userId = res.id;
				  			var userEmail = res.kakao_account.email;
				  			var kakaonickname = res.properties.nickname;
				  			console.log(userId);
				  			console.log(userEmail);
				  			console.log(res.properties['nickname']);
				  			console.log(authObj.access_token);
			    		//alert(JSON.stringify(authObj));
				  			 document.write(res.properties.nickname+"님 환영합니다.");
						}	
					})	
			  },
			  fail: function(err) {
			     alert(JSON.stringify(err));
			  }
			});
			//]]>
		</script>
		
		<div id="naverIdLogin"></div>  <!-- 버튼이 들어갈 위치 선언. ID는 반드시 지정된 값으로 설정하여야 합니다.-->

		<script type="text/javascript">
			var naverLogin = new naver.LoginWithNaverId(
				{
					clientId: '{<spring:eval expression="@api['NAVERLOGIN_APPKEY']" />}',
					callbackUrl: "{http://localhost:8880}",
					loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
				}
			);
			
		   /* 설정정보를 초기화하고 연동을 준비 */
			naverLogin.init();
		</script>
		
	

</body>
</html>