<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="write_save" method="post">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" size="50"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" size="50"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="저장">&nbsp;&nbsp;
				<a href="list">목록보기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>