<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러 페이지</h1>
	<%
		String x = (String)request.getAttribute("errorMsg");	
	%>
	<h3>
		<%=x %>
	</h3>
	
	
	<a href="/app22/home">첫 화면으로</a>
</body>
</html>