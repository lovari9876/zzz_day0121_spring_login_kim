<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<%
		// ContextPath : /board
		String path = request.getContextPath();
	%>
	ContextPath :
	<%=path%>

	<c:if test="${member == null}">
		<form role="form" method="post" autocomplete="off"
			action="<%=path%>/member/login">
			<p>
				<label for="userId">ID</label> <input type="text" id="userId"
					name="id">
			</p>
			<p>
				<label for="userPass">PASSWORD</label> <input type="password"
					id="userPass" name="pw">
			</p>
			<p>
				<button type="submit">로그인</button>
			</p>
			<!-- <p><a href="/member/register">join</a></p> -->
		</form>
	</c:if>

	<!-- F5를 누르면 사라짐 / 1회성  -->
	<c:if test="${msg == false}">
		<p style="color: #f00;">로그인에 실패. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
	</c:if>

	<c:if test="${member != null}">
		<p>${member.userName}님환영합니다 </p>
		<a href="<%=path%>/board/list">게시판 리스트</a>
		<a href="<%=path%>/member/logout">로그아웃</a>
	</c:if>


</body>
</html>