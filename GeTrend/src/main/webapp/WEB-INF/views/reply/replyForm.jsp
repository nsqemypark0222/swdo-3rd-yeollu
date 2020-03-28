<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='<c:url value="/resources/js/jquery-3.4.1.js"/>'></script>
<script>
	$(function() {
		$("#writeBtn").on("click", function() {
			var reply_contents = $("#reply_contents").val();
			
			$.ajax({
				url: "replyWrite",
				type: "get",
				data: $("#inputForm").serialize(),
				success: printList,
				error: function() { alert("에러 발생"); }
			});
		});
		
		$("#removeBtn").on("click", function() {
			
			$.ajax({
				url: "replyRemove",
				type: "get",
				data: {"infonum": infonum},
				success: printList,
				error: function() { alert("에러 발생"); }
			});
		});
		
		function printList() {
			$.ajax({
				url: "replyList",
				type: "get",
				data: {"store_no" : $("store_no").val(), "user_email": $("user_email").val()},
				success: function(result) {
					var output = "";
					output += "<tr>";
					output += "<th>댓글</th>";
					output += "<th>삭제</th>";
					output += "<th></th>";
					output += "</tr>";
					
					$(result).each(function(index, item) {
						output += "<tr>";
						output += "<td class='center'>" + item.reply_contents + "</td>";
						
						output += "</tr>";
					});
					
					$("#printTable").html(output);
				},
				error: function() { alert("에러 발생"); }
			});
		}
		
	});
</script>
</head>
<body>
	<div>
		<div>
			<form id="inputForm">
				<table>
					<tr>
						<th>댓글</th>
					</tr>
					<tr>
						<td>${user_email}</td>
						<td>
							<textarea id="reply_contents" name="reply_contents" placeholder="내용 입력"></textarea>
						</td>
						<td>
							<input type="button" id="writeBtn" value="등록">
						</td>
						<td>
							<input type="button" id="removeBtn" value="삭제">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div> <!-- input form end -->
	
	<div> <!-- print form -->
		<table id="printTable">
			<tr>
				<th></th>
			</tr>
		</table>	
	</div>

</body>
</html>