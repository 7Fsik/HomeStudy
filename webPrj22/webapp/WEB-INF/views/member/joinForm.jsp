<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	#wrap{
		width: 400px;

		display: grid;
		grid-template-columns: 1fr 3fr;
	}

	#wrap > div {
		
		display: flex;
		justify-content: center;
	}
	#wrap> div:last-child{
		grid-column: span 2;
		
	}


	#wrap> div:last-child >input{
		width: 60%;
	}
</style>
</head>
<body>
	<h1>회원가입</h1>
	
	<fieldset style="width: 200px">
	<br>
	<form action="/app22/member/join" method="post">

		<div id="wrap">
			<div>아이디</div>
			<div><input type="text" name="memberId" placeholder="아이디를 입력하세요"></div>
	
			<div>비밀번호</div>
			<div><input type="password" name="memberPwd" placeholder="비밀번호를 입력하세요"></div>
	
			<div>닉네임</div>
			<div><input type="text" name="memberNick" placeholder="닉네임을 입력하세요"></div>
	
			<div><input type="submit" value="회원가입!"></div>
	
	
		</div>
	</form>
	<br>
	
	</fieldset>


</body>
</html>