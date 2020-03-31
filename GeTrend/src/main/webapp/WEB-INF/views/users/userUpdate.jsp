<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href='<c:url value="/resources/css/userUpdate.css"/>' rel="stylesheet" media="all">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>
$(function(){
	  $("#upload").change(function(){
	   if(this.files && this.files[0]) {
	    var reader = new FileReader;
	    reader.onload = function(data) {
	     $(".select_img img").attr("src", data.target.result).width(200);
	    }
	    reader.readAsDataURL(this.files[0]);
	   }
	  });
	})

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
 <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">회원 수정</h5>
             <form class="update" method="post" id="userUpdate">
				<input type="file" id="upload" name="upload">
					<div class="select_img"  style="text-align: left;">
						<img src="" class="rounded-circle" id="upload" />
					</div>
					<c:if test="${sessionScope.user_email != null}">
						<div class="select_img" class="rounded-circle" style="text-align: left;">
							<img alt="file" style="width: 200px;"src="/img/${users}">
						</div>
					</c:if>
						<input type="file" id="upload" name="upload" accept="image/gif, image/jpeg, image/png">
					<div class="contaniner-fluid text-center bg-sub">	
						<p>${users.user_name}</p>
					</div>	
						
		              <div class="form-label-group">
		                <input type="email" id="user_email" class="form-control" value="${users.user_email}"  placeholder="user_email" readonly="readonly" required autofocus>
		                <label for="user_email">이메일 </label>
		              </div>
		              <div class="form-label-group">
		                <input type="password" id="user_pw" class="form-control" value="${users.user_pw}"placeholder="Password" v-model="user_pw" required>
		                <label for="user_pw">Password</label>
		              </div>
		               <div class="form-label-group">
		                <input type="password" id="pwCheck" class="form-control" placeholder="pwCheck" v-model="pwCheck" required>
		                <label for="pwCheck">비밀번호 확인</label>
		              </div>
		              <div class="form-label-group">
		                <input type="text" name="user_name" id="user_name" class="form-control" value="${users.user_name}" v-model="user_name" placeholder="user_name" required>
		                <label for="user_name">닉네임 </label>
		              </div>
		        
		              <button class="btn btn-lg btn-warning btn-block text-uppercase" type="submit">Update Info</button>
		              <button class="btn btn-lg btn-warning btn-block text-uppercase" type="reset">취소</button>
		              <button class="btn btn-lg btn-warning btn-block text-uppercase" type="submit">회원탈퇴</button>
		
		              <hr class="my-4">
		          
		        </form>
		          </div>
		        </div>
		      </div>
		    </div>
</div>
</body>
<script>
/* function formCheck() {
	var user_id = $('#user_id').val();//user_email
	var user_pw = $('#user_pw').val();
	var pwCheck = $('#pwCheck').val();
	if(user_id.length <= 3 || user_id.length > 10) {
		alert("아이디는 4~10 글자를 입력하세요");
		return false;
	}
	if(user_pw.length <= 3 || user_pw.length > 10) {
		alert("비밀번호는 4~10 글자를 입력하세요");
		return false;
	}
	if(user_pw !== pwCheck) {
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	return true;
} */
//Vue로 수정 user_pw, user_name,pwCheck 
 	const userUpdate = new Vue({
		el:'#userUpdate',
		data:{
			user_pw:'',
			user_name:'',
			pwCheck:''
		},
		methods:{
			userUpdate(){
				this.Update = !this.Update;
				if(!this.user_pw){
					user_pw.focus();
					return false;
				}else if(this.user_pw.length <= 3 || this.user_pw.length > 10){
					alert("비밀번호는 4~10 글자를 입력하세요");
					user_pw.focus();
					return false;
				 } 
				if(!this.pwCheck){
					pwCheck.focus();
					return false;
				}
				if(this.user_pw != this.pwCheck){
					alert("비밀번호가 일치하지 않습니다.");
					return false;
				}		
				return true;
			}
		}
	 });
 
</script>
</html>