<%@page import="model.KicMember"%>
<%@page import="dao.KicMemberDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	
	int gender = Integer.parseInt(request.getParameter("gender"));
	
	
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	String picture = request.getParameter("picture");
	
	
	
	KicMemberDAO kcdao = new KicMemberDAO();
	
	KicMember kic = new KicMember();
	
	kic.setId(id);
	kic.setPw(pw);
	kic.setName(name);
	kic.setGender(gender);
	kic.setTel(tel);
	kic.setEmail(email);
	kic.setPicture(picture);
	
	
	int num = kcdao.insertMember(kic);
	
	String msg = null;
	String url = null;
	if(num>0){
		msg = name + "님 회원가입이 완료됐다";
		url ="Login.jsp";
	}else{
		msg = name + "님 회원가입 실패";
		url ="Join.jsp";
	}

%>
<script>
	alert("<%=msg%>");
	location.href= "<%=url%>";
</script>
<%=id %>
<%=pw %>
<%=name %>
<%=gender %>
<%=tel %>
<%=email %>
<%=picture %>
<%=num %>개 입력되었습니다
</body>
</html>