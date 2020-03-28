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

//서버에서 배열 가져오기
 $(function() {    //화면 다 뜨면 시작
        $("#searchInput").autocomplete({
            source : function( request, response ) {
                 $.ajax({
                        type: 'get',
                        url: "/getrend/autocomplete/json",
                        dataType: "json",
                        data: {"param":$("#searchInput").val()},
                        success: function(data) {
                            //서버에서 json 데이터 response 후 목록에 추가
                            response(
                                $.map(data, function(item) {    
                                    return {
                                        label: item,   
                                        value: item,    
                                        test : item+"test"   
                                    }
                                })//map
                            );//response
                        }//success            
                   });//ajax
                },    // source 는 자동 완성 대상
            select : function(event, ui) {    //아이템 선택시
                console.log(ui);//사용자가 오토컴플릿이 만들어준 목록에서 선택을 하면 반환되는 객체
                console.log(ui.item.value);    //김치 볶음밥
                
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
            position: { my : "right top", at: "right bottom" },    //잘 모르겠음
            close : function(event){    //자동완성창 닫아질때 호출
                console.log(event);
            }

        });



     //검색어 엔터치면 컨트롤러로 이동
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
        <input id="searchInput" type="text">
</body>



</html>