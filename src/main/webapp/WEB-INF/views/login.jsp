<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/mystyle.css"/>" type="text/css"></link>
<meta charset="UTF-8">
<c:remove var="IAM" scope="session"/>
<title>Login Page</title>
</head>
<body>
	<header>친목게시판</header>
	<div>
	<div>
		<form action="http://localhost:8080/j/account/login" method="post">
			<fieldset>
				<h3>${message}</h3>
				<ul>
					<li>계 정 : <input id=input_200 type="text" name="id" autofocus required placeholder=계정 value="<%=request.getAttribute("id")%>"></li>
					<li>암 호 : <input id=input_200 type="password" name="passwd" required placeholder="비밀번호"></li>
				</ul><br>
			<input type="submit" name="submit" value="로그인">
			<br>
		</fieldset>
		</form>
		</div>
		<br>
	<a id=noline href="http://localhost:8080/j/account/join" target="_self">"여기를 누르세요 회원가입을 위해"</a><br>
	<br><a id=noline_red href="http://localhost:8080/j/" target="_self">나가기</a>
	</div>

</body>
</html>