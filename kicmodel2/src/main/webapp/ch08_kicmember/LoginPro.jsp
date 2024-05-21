<%@page import="model.KicMember"%>
<%@page import="dao.KicMemberDAO"%>
<%@page import="java.sql.ResultSet"%>
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
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	
	KicMemberDAO kcdao = new KicMemberDAO();
	Connection conn = kcdao.getConnection();

	KicMember kic = kcdao.getMember(id);
	String dbpw = kic.getPw();
	String msg = null;
	String url = null;
	if(dbpw != null){
		if(pw.equals(dbpw)){
			// 비번도 일치한다 -> 세션에 저장하기
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			msg = "로그인이 되었다";
			url = "index.jsp";
		}else{
			msg = "비밀번호가 맞지 않다";
			url = "Login.jsp";
		}
	}else{
		
	}
	
	

%>
<%=id %>
<%=pw %>
<script>
	alert("<%=msg %>")
	location.href="<%=url %>"

</script>
</body>
</html>