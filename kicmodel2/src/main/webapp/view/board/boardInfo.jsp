
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

<div class ="container">
		<div class ="input-form-backgroud row">
			<div class ="input-form col-md-12 mx-auto">
			<h4 class="mb-3 text-center">게시글 정보</h4>
			<table class ="table">
			<tr>
				<th>name</th>
				<th>value</th>
			</tr>
			<tr>
				<td>num</td>
				<td>${board.num}</td>
			</tr>
			
			<tr>
				<td>pw</td>
				<td>${board.pw}</td>
			</tr>
			
			<tr>
				<td>subject</td>
				<td>${board.subject}</td>
			</tr>
			<tr>
				<td>content</td>
				<td>${board.content}</td>
			</tr>
			<tr>
				<td>regdate</td>
				<td>${board.regdate}</td>
			</tr>
			<tr>
				<td>readcnt</td>
				<td>${board.readcnt}</td>
			</tr>
			
			<tr>
				<td>picture</td>
				<td><img src="${pageContext.request.contextPath}/img/board/${board.file1}" width="100px" height="120px"></td>
			</tr>
			<tr class="text-center">   
				<td>
				<a class ="btn btn-primary" href="${pageContext.request.contextPath}/board/boardUpdateForm?num=${board.num}">게시판수정</a>
				<a class ="btn btn-primary" href="${pageContext.request.contextPath}/board/boardDeleteForm?num=${board.num}">게시판지우기</a>
				<a class ="btn btn-primary" href="${pageContext.request.contextPath}/board/boardList?boardId=${sessionScope.boardId}">글목록</a>
				</td>
			</tr>
			</table>
			</div>
		</div>
</div>
</body>
</html>