<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.*, java.util.List"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/mystyle.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% postVO pvo = (postVO)request.getAttribute("pvo");%>
<div>
	<a id=headline href="http://localhost:8080/TOJ_free/CTRL?key=search&keyword=all&index=0" target="_self">실명게시판</a><br>
	<h3>${msg }</h3>
</div>
<div id = right>
	<a id=noline_red href="http://localhost:8080/TOJ_free/CTRL?key=logout" target="_self">로그아웃</a>
</div>
<hr>

<div>
	<fieldset id =left>
		<h2>${pvo.head }</h2><br>
		<p>${pvo.name} < ${pvo.id } ></p><hr>
		<p id=p_1><%=pvo.getBody().replace("\n", "<br>") %></p><br>
		
	</fieldset>
	<br>
	<form id=form_inline action="http://localhost:8080/TOJ_free/CTRL?key=search" method="post">
				<input type="hidden" name="user_input" value="${keyword }">
				<input type="hidden" name="opt" value="${opt }">
				<input type="hidden" name="index" value="${index }">
				
				<input type="submit" name="submit" value="목 록">
	</form>
	
	<c:if test="${pvo.id == sessionScope.IAM.id }">
				
	<form id=form_inline action="http://localhost:8080/TOJ_free/CTRL?key=modify" method="post">
				<input type="hidden" name="id" value="${pvo.id }">
				<input type="hidden" name="name" value="${pvo.name }">
				<input type="hidden" name="head" value="${pvo.head }">
				<input type="hidden" name="body" value="${pvo.body }">
				<input type="hidden" name="num" value="${pvo.num }">
				<input type="submit" name="submit" value="수 정">
		
	</form>
	<form id=form_inline action="http://localhost:8080/TOJ_free/CTRL?key=delete" method="post">
				<input type="hidden" name="id" value="${pvo.id }">
				<input type="hidden" name="num" value="${pvo.num }">
				<input type="submit" name="submit" value="삭 제">
	</form>
	</c:if>
</div>
<br>
<div> 
	<div>
		<form id=form_inline action="http://localhost:8080/TOJ_free/CTRL?key=comment" method="post">
			<input type = "hidden" name = "num" value="${pvo.num }" >
			<input id=input_name type="text" name="name" value="<%=((userVO)session.getAttribute("IAM")).getUsername() %>" disabled>
			<input id=input_head type="text" name="comment" autofocus required placeholder="하지마라 욕설 및 비방">
			<input id=input_name type="submit" name="submit" value="등 록"><hr>
		</form>
	
	</div>

</div>
<br>
<div><div id=div_left>
			<c:forEach var="vo" items="${cvoList }">
			<a id = a_short>${vo.name } : </a>
			${vo.comment }<br>
			</c:forEach>			
</div></div>
</body>
</html>