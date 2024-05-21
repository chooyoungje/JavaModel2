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
<body>
<%
request.setCharacterEncoding("utf-8");
String id = (String) session.getAttribute("id");
String pw = request.getParameter("pw");
KicMemberDAO dao = new KicMemberDAO();
KicMember memdb = dao.getMember(id);
String msg = "";
String url = "memberDeleteForm.jsp";
if (memdb != null) {
	if (memdb.getPw().equals(pw)) {
		msg = "탈퇴 하였습니다";
		session.invalidate();
		dao.deleteMember(id);  
		url = "Login.jsp";
	} else {
		msg = "비밀번호가 틀렸습니다";
	}
} else {
	msg = "수정 할 수 없습니다";
}
%>

<script>
	alert("<%=msg%>");
	location.href= "<%=url%>";
</script>
</body>
</html>