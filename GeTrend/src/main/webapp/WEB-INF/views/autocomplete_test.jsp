<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[ autocomplete test ]</title>


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

//화면에서 배열 가지고 하는거
$(function() {    //화면 다 뜨면 시작
    var searchSource = ["김치 볶음밥", "김치","신라면", "진라면", "라볶이", "팥빙수","너구리","삼양라면","안성탕면","불닭볶음면","짜왕","라면사리" ]; // 배열 형태로 
    $("#searchInput").autocomplete({  //오토 컴플릿트 시작
        source : searchSource,    // source 는 자동 완성 대상
        select : function(event, ui) {    //아이템 선택시 ,{label: "진라면", value: "진라면"}
            //console.log(ui.item.value); //진라면
        },
        focus : function(event, ui) {    //포커스 가면
            return false;//한글 에러 잡기용도로 사용됨
        },
        minLength: 1,// 최소 글자수
        autoFocus: false, //첫번째 항목 자동 포커스 기본값 false
        classes: {    //잘 모르겠음
            "ui-autocomplete": "highlight"
        },
        delay: 500,    //검색창에 글자 써지고 나서 autocomplete 창 뜰 때 까지 딜레이 시간(ms)
//		 disabled: true, //자동완성 기능 끄기
        position: { my : "right top", at: "right bottom" },    //잘 모르겠음
        close : function(event){    //자동완성창 닫아질때 호출
			
         }
    });

    
    $("#searchInput").keydown(function (key) {
 
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)

        	$.ajax({
        		url : "/getrend/autocomplete/keyword",
        		type : "post",
        		data : {"keyword" : $("#searchInput").val()},
        		success : function(){alert("성공");},
        		error : function(){alert("에러");}
        	})
        	            
        }
 
    });
    
});

</script>

</head>
<body>

	
    <input id="searchInput">
</body>



</html>