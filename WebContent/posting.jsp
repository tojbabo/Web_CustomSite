<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.*, java.util.List"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<link rel="stylesheet" href="resources/mystyle.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<a id=headline href="http://localhost:8080/TOJ_free/CTRL?key=search&keyword=all&index=0" target="_self">실명게시판</a><br>
</div>
<div id = right>
	<a id=noline_red href="http://localhost:8080/TOJ_free/CTRL?key=logout" target="_self">로그아웃</a>
</div>
<hr>
<div>
<fieldset>
<table>
		<a id=noline href="http://localhost:8080/TOJ_free/CTRL?key=search&keyword=all&index=0" target="_self">전체보기</a><br><br>
		<tr><th id=th_1>제 목</th><th id=th_2>이 름</th></tr>
		
		<c:forEach var="vo" items="${postList }">
			<tr>
			<td id=td_short><a id=noline href="http://localhost:8080/TOJ_free/CTRL?key=open&keyword=${vo.num }" target="_self">${vo.head }</a></td>
			<td id=td_long><a id=noline href="http://localhost:8080/TOJ_free/CTRL?key=search1&keyword=${vo.num }&index=0" target="_self">${vo.name }</a></td>
		</tr>
		</c:forEach>		
</table>
</fieldset>
</div>
<br>
<div>
	<c:forEach var="i" begin="0" end="${size-1 }">
			<a href="http://localhost:8080/TOJ_free/CTRL?key=search2&keyword=${keyword }&index=${i}" target="_self">${i+1 }</a>
	</c:forEach><hr>
		
		
		
		
		
			<form id=form_inline action="http://localhost:8080/TOJ_free/CTRL?key=search" method="post">
			<select name = "opt">
				<option>이 름</option>
				<option>내 용</option>
			</select>
			<input id=input_200 type="text" name="user_input" autofocus required placeholder="">
			<input id=input_name type="submit" name="submit" value="검 색">
			<input type="hidden" name="index" value="0">
			</form>
			<button id=input_name type="button" onclick="location.href='http://localhost:8080/TOJ_free/CTRL?key=write' ">글작성</button>
</div>	
</body>
</html>