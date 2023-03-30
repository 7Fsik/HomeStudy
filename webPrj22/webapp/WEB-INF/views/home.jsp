<%@page import="com.kh.app.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>홈페이지</h1>
	<%
		MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
	%>
	<%if(loginMember != null){%>
	
		<h2> <%= loginMember.getNick()%>님 환영합니다.</h2>
		<a href="/app22/member/logout">로그아웃</a>
         <br>
        <a href="/app22/member/edit">마이페이지</a>
	<%} %>
	<hr>
    <br>
    <fieldset style="width: 100px">
        <legend>메뉴</legend>
       
        <a href="/app22/member/join">회원가입</a>
        <br>
        <a href="/app22/member/login">로그인</a>
         <br>


    </fieldset>
	
	<%if(loginMember != null && loginMember.getId().equals("admin")){%>
		<a href="/app22/member/list">회원 목록 조회</a>
	<%}%>
	

</body>
</html>