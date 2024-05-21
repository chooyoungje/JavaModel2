<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.KicMember"%>
<%@page import="dao.KicMemberDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	String id = (String) session.getAttribute("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	int gender = Integer.parseInt(request.getParameter("gender"));
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	String picture = request.getParameter("picture");
	
	
	KicMemberDAO dao = new KicMemberDAO();
	KicMember memdb = dao.getMember(id);
	
	KicMember kic = new KicMember();
	kic.setId(id);
	kic.setPw(pw);
	kic.setName(name);
	kic.setGender(gender);
	kic.setTel(tel);
	kic.setEmail(email);
	kic.setPicture(picture);
	
	
	String msg="";
	String url="";
	if(memdb != null){
		if(memdb.getPw().equals(pw)){
			msg="수정하였습니다";
			dao.updateMember(kic);
			url = "JoinInfo.jsp";
		} else{
			msg ="비번틀림";
		}
	}else{
		msg="수정할 수 없습니다";
	}

%>
<%=kic %>
<script>
alert("<%=msg%>");
location.href="<%=url%>"

</script>
</body>
</html>