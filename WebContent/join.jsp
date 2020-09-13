<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/mystyle.css" type="text/css"></link>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>실명게시판</header>
	<div>
	<div>
		<form  action="http://localhost:8080/TOJ_free/CTRL?key=join" method="post">
			<fieldset>
				<h3>입력하다 너의 정보</h3>
				<ul>
					<li>계 정 : <input id=input_200 type="text" name="id" autofocus required placeholder="공백없이 입력하세요"></li>
					<li>암 호 : <input id=input_200 type="password" name="passwd" required placeholder="공백없이 입력하세요"></li>
					<li>성 명 : <input id=input_200 type="text" name="username" required placeholder="공백없이 입력하세요"></li>
					<li>번 호 : <input id=input_200 type="text" name="mobile" required placeholder="***.****.****"></li>
					<li>메 일 : <input id=input_200 type="text" name="email" required placeholder="****@****.***"></li>
				</ul>
			
		<br>
		
			<input type="submit" name="submit" value="제출">
			<input type="reset" name="reset" value="재작성">
		</fieldset>
		</form>
		</div>
	</div>

</body>
</html>