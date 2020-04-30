<!-- 
/**
 * @File 	: footer.jsp
 * @Project : GeTrend
 * @Author	: 문지연
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
*/
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<!-- Header Start -->
<head>
<meta charset="UTF-8">
<!-- jQuery and Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- jQuery UI -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- Footer CSS -->
<link rel="stylesheet" href='<c:url value="/resources/css/footer.css"/>'>
</head>
<!-- Header End -->

<!-- Body Start -->
<body class="d-flex flex-column">

	<div id="page-content">
    	<div class="container text-center">
      		<div class="row justify-content-center">
        		<div class="col-md-7">
          			<h1 class="font-weight-light mt-4 text-white">GeTrend Contents</h1>
          			<p class="lead text-white-50">Get your Trend!</p>
        		</div>
      		</div>
    	</div>
  	</div>
  	
	<div id="sticky-footer" class="py-4 bg-dark text-white-50">
		<div class="container text-center">
	    	<small>
	    		Copyright &copy; Team Yeollu<br>
	     	   	광주광역시 광산구 무진대로 282, 7층<br>
				대표: 박민열, email:Yeollu@gmail.com<br>
	  		</small>
	    </div>
	</div>
	
</body>
<!-- Body End -->

</html>	