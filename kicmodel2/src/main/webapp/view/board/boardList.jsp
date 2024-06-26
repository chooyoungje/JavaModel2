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
<h2 class ="text-center">${boardName}</h2>
<h2 class ="text-center">${boardCount}개</h2>

<a class="btn btn-primary float-right m-2" href="${pageContext.request.contextPath}/board/boardForm?boardId=${sessionScope.boardId}">게시판 입력</a>
  		<table class ="table">
  			<thead>
  				<tr>
  					<th>ser</th>
  					<th>번호</th>
  					<th>이름</th>
  					<th>제목</th>
  					<th>내용</th>
  					 <th>이미지</th>
  					<th>날짜</th>
  					<th>조회</th>
  				</tr>
  			</thead>
  			<tbody>
				<c:set var="count" value="${boardCount}"/>
  				<c:forEach var="a" items="${requestScope.boardList}">
  					<tr>
  						<td>${count}</td>
  						<c:set var="count" value="${boardCount-1}"/>
  						<td>${a.num}</td>
  						<td>${a.name}</td>
  						<td><a href="boardInfo?num=${a.num}">${a.subject}</a></td>
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




