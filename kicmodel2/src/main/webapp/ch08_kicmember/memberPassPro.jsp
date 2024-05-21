<%@page import="model.KicMember"%>
<%@page import="dao.KicMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String msg=null;
	String url=null;
	
	String id = (String) session.getAttribute("id");
	String pw = request.getParameter("pw");
	String newPw = request.getParameter("newPw");
	
	KicMemberDAO dao = new KicMemberDAO();
	KicMember dbUser = dao.getMember(id);
	
	
	if(dbUser != null){
		if(dbUser.getPw().equals(pw)){
			session.invalidate();
			int num = dao.updatePassword(id, newPw);
			if(num>0){
				msg="비밀번호를 성공적으로 수정하였습니다";
				url="Login.jsp";
			} else{
				msg="비밀번호 수정 실패";
				url="memberPassForm.jsp";
			}
		}else{
			msg="비밀번호가 틀렸습니다";
			url="memberPassForm.jsp";
		}
	}
	
%>
<body>
<script>
	alert("<%=msg%>");
	location.href= "<%=url%>";
</script>
</body>
</html>