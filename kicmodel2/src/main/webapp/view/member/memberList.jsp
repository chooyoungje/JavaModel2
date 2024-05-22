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
  		<table class ="table">
  			<thead>
  				<tr>
  					<th>아이디</th>
  					<th>이름</th>
  					<th>이메일</th>
  					<th>전화번호</th>
  					<th>성별</th>
  				</tr>
  			</thead>
  			<tbody>
  				<c:forEach var="a" items="${requestScope.memList}">
  					<tr>
  						<td>${a.id}</td>
  						<td>${a.name }</td>
  						<td>${a.email }</td>
  						<td>${a.tel }</td>
  						<td>${a.gender == 1 ? "남자" : "여자"}</td>
  					</tr>
  				</c:forEach>
  			</tbody>
  		</table>
  </div>
</body>
</html>




