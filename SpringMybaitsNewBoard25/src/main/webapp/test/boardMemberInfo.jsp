<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page import="java.util.ArrayList"%>
<%@ page import= "java.util.HashMap" %>
<%

		ArrayList<HashMap <String, Object>> alist =(ArrayList<HashMap <String, Object>>)request.getAttribute("alist");
		
%>

<title>Insert title here</title>
</head>
<body>
게시물을 작성한 회원 나이 출력

<table border=1>
<tr>
<td>번호</td>
<td>게시글번호</td>
<td>이름</td>
<td>나이</td>
<td>ip</td>
</tr>
 <%	int num=1;
			for (HashMap <String, Object> mbv : alist) {
		%>
		
<tr>
<td><%=num %></td>
<td><%=mbv.get("BBIDX") %></td>
<td><%=mbv.get("MEMBERNAME") %></td>
<td><%=mbv.get("AGE") %></td>
<td><%=mbv.get("MEMBERIP") %></td>
</tr>
<%
	num=num+1;

			} 
%> 
</table>
</body>
</html>