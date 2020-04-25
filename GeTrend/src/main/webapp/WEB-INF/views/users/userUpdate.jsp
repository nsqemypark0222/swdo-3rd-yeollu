<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ Update | GeTrend ]</title>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<link href='<c:url value="/resources/css/userUpdate.css"/>' rel="stylesheet" media="all">

</head>
<body style="background : white;">
	<div class="container">
    	<div class="row">
      		<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        		<div class="card card-signin my-5">
          			<div class="card-body">
        	  			<h3 class="card-title text-center">회원정보 수정</h3>
             			<form class="update" 
             					action="<c:url value='update'/>" 
             					id="updateForm" 
             					method="post"
             					v-on:submit="updateUser"
             					enctype="multipart/form-data" >
             				
							<c:choose>
								<c:when test="${user.user_profile == null}">
									<div class="form-label-group text-center">
										<img class="img-thumbnail rounded-circle" id="avatarPreview" alt="" style="width : 142px;"/>
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-label-group text-center">
										<img class="img-thumbnail rounded-circle" id="avatarPreview" src="${user.user_profile}" style="width : 142px;" />
									</div>
								</c:otherwise>
							</c:choose>
							
							<div class="form-label-group custom-file">
								<input class="custom-file-input" type="file" id="userAvatar" name="userAvatar" />
								<label class="custom-file-label" for="userAvatar">Choose your avatar image...</label>
							</div>
							
							<div class="form-label-group">
		                		<input type="email"
		                				id="user_email"
		                				name="user_email" 
		                				class="form-control" 
		                				v-model="user_email" 
		                				ref="user_email"
		                				placeholder="user_email" 
		                				readonly="readonly" 
		                				required autofocus />
		                		<label for="user_email">이메일</label>
		              		</div>
		              		
		              		<div class="form-label-group" v-if="${user.user_type == 'LOCAL'}">
		                		<input type="password"
		                				id="user_pw" 
		                				name="user_pw"
		                				class="form-control" 
		                				v-model="user_pw" 
		                				ref="user_pw"
		                				placeholder="Password" 
		                				required />
		                		<label for="user_pw">비밀번호</label>
		                		<small>{{ passwordValidation }}</small>
		              		</div>
		              		
		               		<div class="form-label-group" v-if="${user.user_type == 'LOCAL'}">
		                		<input type="password" 
		                				id="pwCheck"
		                				name="pwCheck" 
		                				class="form-control" 
		                				v-model="pwCheck" 
		                				ref="pwCheck"
		                				placeholder="pwCheck" 
		                				required />
		                		<label for="pwCheck">비밀번호 확인</label>
		                		<small>{{ pwCheckValidation }}</small>
		              		</div>
		              		
		              		<div class="form-label-group">
		                		<input type="text" 
		                				id="user_name" 
		                				name="user_name" 
		                				class="form-control" 
		                				v-model="user_name" 
		                				ref="user_name"
		                				placeholder="user_name" 
		                				required />
		                		<label for="user_name">닉네임 </label>
		              		</div>
		              		
		              		<input type="hidden" id="user_type" name="user_type" v-model="user_type" />
		              		
		              		<button class="btn btn-lg btn-warning btn-block text-uppercase" type="submit">회원정보 수정</button>
		              		<button class="btn btn-lg btn-warning btn-block text-uppercase" type="reset" id="cancel" >취소</button>
		              		<button class="btn btn-lg btn-warning btn-block text-uppercase" type="reset" id="userDelete" >회원 탈퇴</button>
	          			</form>
	          		</div>
	        	</div>
	      	</div>
    	</div>
	</div>
</body>

<script>
$(function(){
	$("#cancel").click(function() {
		$(location).attr('href', "<c:url value='/'/>");
	});

	$("#userDelete").click(function() {
		$(location).attr('href', "<c:url value='deleteUser'/>" + "?user_email=" + $("#user_email").val());
	});

	$("#userAvatar").change(function(event) {
		var files = event.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(file) {
				if(!file.type.match("image.*")) {
					alert("확장자는 이미지 확장자만 가능합니다");
					return;
				};
			var reader = new FileReader();
			reader.onload = function(event) {
				$("#avatarPreview").attr("src", event.target.result);
			};
			reader.readAsDataURL(file);
		});
	});
});
</script>
<script>
	const updateForm = new Vue({
		el: "#updateForm",
		data: {
			user_email: "${user.user_email}",
			user_pw: "",
			pwCheck: "",
			user_type: "${user.user_type}",
			user_name: "${user.user_name}"
		},
		computed: {
			passwordValidation: function() {
				if(this.user_type === "LOCAL") {
					if(this.user_pw === "") {
						user_pw.focus();
						return '비밀번호를 입력하세요';
					} else if(this.user_pw.length <= 3 || this.user_pw.length > 10){
						user_pw.focus();
						return '비밀번호는 4~10 글자를 입력하세요';
					} 
				}
				return '';
			},
			pwCheckValidation: function() {
				if(this.user_type === "LOCAL") {
					if(this.pwCheck  === "") {
						pwCheck.focus();
						return '비밀번호 확인을 입력하세요';
					}
					if(this.user_pw !== this.pwCheck) {
						pwCheck.focus();
						return '비밀번호가 일치하지 않습니다';
					}
				}
				return '';
			}
		},
		methods: {
			updateUser: function() {
				
			}
		}
	});
 
</script>
</html>