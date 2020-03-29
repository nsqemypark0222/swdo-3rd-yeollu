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
<script src="<c:url value='/resources/js/hangul.js'/>"></script>
<script>

//기본 자동완성
 $(function() {   
        $("#searchInput").autocomplete({
            source : function( request, response ) {
                 $.ajax({
                        type: 'get',
                        url: "/getrend/autocomplete/json01",
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




//2. 초성 중성 종성 나눠서
$(function() { 
		$.ajax({
			type : 'get',
			url : "/getrend/autocomplete/json02",
			dataType : "json",
			success : function(data) {
				
				//이부분이 초성 검색이 가능하게 만들어 주는 부분
				let source = $.map(data, function(item) { //json[i] 번째 에 있는게 item 임.
					chosung = "";
					full = Hangul.disassemble(item).join("").replace(/ /gi, "") ;	//공백제거된 ㄱㅣㅁㅊㅣㅂㅗㄲㅇㅡㅁㅂㅏㅂ
					
					Hangul.d(item, true).forEach(function(strItem, index) {
						
						if(strItem[0] != " "){	//띄어 쓰기가 아니면
							chosung += strItem[0];//초성 추가
						}
					});
					
					
					return {
						label : chosung + "|" + (item).replace(/ /gi, "") +"|" + full, //실제 검색어랑 비교 대상 ㄱㅊㅂㅇㅂ|김치볶음밥|ㄱㅣㅁㅊㅣㅂㅗㄲㅇㅡㅁㅂㅏㅂ 이 저장된다.
						value : item, //김치 볶음밥
						chosung : chosung,	//ㄱㅊㅂㅇㅂ,
						full : full//ㄱㅣㅁㅊㅣㅂㅗㄲㅇㅡㅁㅂㅏㅂ
					}
				});
				
				
				$("#searchInput02").autocomplete({
					source : source,	// source 는 자동 완성 대상
					select : function(event, ui) {	//아이템 선택시
						console.log(ui.item.value + " 선택 완료");	
						
					},
					focus : function(event, ui) {	//포커스 가면
						return false;//한글 에러 잡기용도로 사용됨
					},
					
					
				}).autocomplete( "instance" )._renderItem = 
					function( ul, item ) {
						var cnt = 0;
						if (cnt > 5) return false;
			       		return $( "<li>" ).append( "<div>" + item.value + "</div>" ).appendTo( ul );
			       		cnt++;	
			    	};
			},
			
		});
 
	
		//여기가 한글 초중종성 검색 방법
		$("#searchInput02").on("keyup",function(){	//검색창에 뭔가가 입력될 때마다
			input = $("#searchInput02").val();	//입력된 값 저장
			$( "#searchInput02" ).autocomplete( "search", Hangul.disassemble(input).join("").replace(/ /gi, "") );	//자모 분리후 띄어쓰기 삭제
		})

	     //검색어 엔터치면 컨트롤러로 이동
	     $("#searchInput02").keydown(function (key) {
	     	 
	         if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)

	         	$.ajax({
	         		url : "/getrend/autocomplete/keyword",
	         		type : "post",
	         		data : {"keyword" : $("#searchInput02").val()},
	         		success : function(){alert("성공");},
	         		error : function(){alert("에러");}
	         	})
	         	            
	         }
	  
	     });
	});












</script>

</head>
<body>
자동완성01 ; 기본   <input id="searchInput" type="text"><br>
자동완성02 ; 초중종성   <input id="searchInput02" type="text">
</body>



</html>