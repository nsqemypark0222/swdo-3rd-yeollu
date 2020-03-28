<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ mango test ]</title>

</head>
<body>

<hr>
망플에서 가게 검색 시 광주시에 있는 가게 중 첫번째 가게 정보 가져오기
	<form action="/getrend/crawl/mango_store_info" method="post">
		광주 가게 이름(ex. 헬로우엘) : <input type="text" name="store_name">
		<input type="submit" value="가게 검색">
	</form>	
</body>
</html>