<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="t.o.j.d.*"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/mystyle.css"/>" type="text/css"></link>
<meta charset="UTF-8">
<title>Write</title>
</head>
<body>
<% postVO pvo = (postVO)request.getAttribute("pvo");%>
<div>
	<a id=headline href="http://localhost:8080/j/list/entire" target="_self">친목게시판</a><br>
	<h3>${msg }</h3>
</div>
<div id = right>
	<a id=noline_red href="http://localhost:8080/j/list/logout" target="_self">로그아웃</a>
</div>
<hr>
	
	<div>
	<fieldset>
			
			<form id=form_inline action="http://localhost:8080/j/post/posting" method="post">
			이 름 <input id=input_head type="text" name="name" value="${uvo.username }" readonly ><br>
			제 목 <input id=input_head type="text" name="head" autofocus required value="${pvo.head }" ><hr>
			내 용 <textarea id=input_body name="body" required >${pvo.body }</textarea><br>
			<input type="hidden" name="id" value="${uvo.id }">
			<input type="hidden" name="num" value="${pvo.num }">
			<input type="submit" name="submit" value="게 시">
			</form>
			<input type="button" value = "취 소" onclick="history.back(-1);">
			</fieldset>
	</div>
	
</body>
</html>