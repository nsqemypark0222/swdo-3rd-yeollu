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
 $(function() {   

	function highlightText(text, $node) {
		var searchText = $.trim(text).toLowerCase(), currentNode = $node.get(0).firstChild, matchIndex, newTextNode, newSpanNode;
		while ((matchIndex = currentNode.data.toLowerCase().indexOf(searchText)) >= 0) {
			newTextNode = currentNode.splitText(matchIndex);
			currentNode = newTextNode.splitText(searchText.length);
			newSpanNode = document.createElement("span");
			newSpanNode.className = "highlight";
			currentNode.parentNode.insertBefore(newSpanNode, currentNode);
			newSpanNode.appendChild(newTextNode);
		}
	}
		
    $("#searchInput").autocomplete({
           source : function( request, response ) {
                $.ajax({
                       type: 'get',
                       url: "/getrend/autocomplete/source",
                       dataType: "json",
                       data: {"param" : $("#searchInput").val()},
                       success: function(data) {
                           response(
                               $.map(data, function(item) {    
                               	chosung = "";
               					full = Hangul.disassemble(item).join("").replace(/ /gi, "");	
               					Hangul.d(item, true).forEach(function(strItem, index) {
               						
               						if(strItem[0] != " "){	
               							chosung += strItem[0];
               						}
               					});
               					
               					return {
               						label : chosung + "|" + (item).replace(/ /gi, "") +"|" + full, 
               						value : item,
               						chosung : chosung,	
               						full : full
               					}
                               })
                           );
                       }          
                  });
               }, 
               
           focus : function(event, ui) {   
               return false;
           },
           minLength: 1,
           autoFocus: false,
           delay: 500
       }).data("ui-autocomplete")._renderItem = function(ul, item) {
		var $a = $("<b></b>").text(item.value);
		highlightText(this.term, $a);
		return $("<li></li>").append($a).appendTo(ul);
	};

	$("#searchInput").on("keyup",function(){	
		input = $("#searchInput").val();
		$( "#searchInput" ).autocomplete( "search", Hangul.disassemble(input).join("").replace(/ /gi, "") );	
	});

    $("#searchInput").keydown(function (key) {    	 
        if(key.keyCode == 13){
        	$.ajax({
        		url : "/getrend/autocomplete/keyword",
        		type : "post",
        		data : {"keyword" : $("#searchInput").val()},
        		success : function(){alert("성공");},
        		error : function(){alert("에러");}
        	});         	            
        };
    });
});

</script>

<style>
	li:nth-of-type(n+10) { display: none; }
	.ui-autocomplete .highlight {color: orange;}
</style>
</head>

<body>

자동완성   <input id="searchInput" type="text">

</body>



</html>