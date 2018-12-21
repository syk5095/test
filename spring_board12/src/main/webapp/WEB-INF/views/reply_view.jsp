<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="reply" method="post">
<table border="1">
	<input type="hidden" name="id" value="${reply_view.id }">
	<input type="hidden" name="idgroup" value="${reply_view.idgroup }">
	<input type="hidden" name="step" value="${reply_view.step }">
	<input type="hidden" name="indent" value="${reply_view.indent }">
	<input type="hidden" name="name" value="${reply_view.name }">
<tr><td>번호</td><td>${reply_view.id }</td></tr>
<tr><td>조회수</td><td>${reply_view.hit }</td></tr>
<tr><td>이름</td><td>${reply_view.name }</td></tr>
<tr><td>제목</td><td><input type="text"name="title"value="${reply_view.title }"></td></tr>
<tr><td>내용</td><td><textarea rows="10" name="content"></textarea></td></tr>
<tr>
<td colspan="2"><input type="submit" value="답변"><a href="list">목록</a></td>
</tr>
</table>
</form>
</body>
</html>