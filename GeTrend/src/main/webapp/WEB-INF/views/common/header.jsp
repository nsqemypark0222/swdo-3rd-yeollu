<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>	
<link href='<c:url value="/resources/css/header.css"/>' rel="stylesheet" media="all">
<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<title>Header</title>
</head>	
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   	 <a class="navbar-brand" id="navbar-brand"  href="#">GeTrend</a>
		<div class="input-group">
			<form class="form-inline" action="/">
	 			<div class="col-md-9">
    				<input class="form-control mr-sm-2" type="text" placeholder="Search" >
    			</div>
    		</form>
            	<div class="menu_list">
  			     	<ul class="nav jus">
  			     	<c:if test="${session.user_email != null }">		     	
  			        <li class="nav-item">
  			          <input type="button" class="btn btn-light" id="mypage" value="내 방">
  			        </li>
  			        <li class="nav-item">
  			          <input type="button" class="btn btn-light" value="Logout">
  			        </li>
  			        <li class="nav-item">
	                  <a class="nav-link" href="users/userUpdate">
	                  <img class="profile" style="border-radius: 60px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png" width="30px" height="30px"  >
  			          </a>
  			        </li>
  			        </c:if>
  			        <li>
  			        <a href="<c:url value="/users/userJoin"/>">
  			          <input type="button" class="btn btn-light" id="userJoin" value="회원가입">
  			          </a>
  			        </li>
  			        <li>
  			        	<a href="<c:url value="/users/userLogin"/>">
  			          <input type="button" class="btn btn-light" id="userLogin" value="로그인">
  			          </a>
  			        </li>
  			      </ul>
  			      
              </div>
             	  
		</div>	 
</nav>
</body>
</html>
