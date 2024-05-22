<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
  src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
  src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class ="container">
<a class="btn btn-primary float-right m-2" href="${pageContext.request.contextPath}/board/boardForm">게시판 입력</a>
  		<table class ="table">
  			<thead>
  				<tr>
  					<th>아이디</th>
  					<th>이름</th>
  					<th>제목</th>
  					<th>내용</th>
  					 <th>이미지</th>
  					<th>날짜</th>
  					<th>조회</th>
  					
  				</tr>
  			</thead>
  			<tbody>
  				<c:forEach var="a" items="${requestScope.boardList}">
  					<tr>
  						<td>${a.num}</td>
  						<td>${a.name}</td>
  						<td>${a.subject}</td>
  						<td>${a.content}</td>
  						<td>${a.file1}</td>
  						<td>${a.regdate}</td>
  						<td>${a.readcnt}</td>
  					</tr>
  				</c:forEach>
  			</tbody>
  		</table>
  </div>
</body>
</html>




