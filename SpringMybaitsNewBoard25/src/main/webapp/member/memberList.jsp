<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %>
<!-- model1패키지의 MemberDao 임포트 -->

<!-- model1패키지의 MemberVo 임포트 -->
<%@ page import="com.myin.team25.domain.MemberVo" %>
<%@ page import="java.util.ArrayList"%>

<style rel="stylesheet">
table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table.type11 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background: #ce4869 ;
}
table.type11 td {
    width: 170px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
</style>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
 <script type="text/javascript">
 function modify() {
 
	 var res;
     res= confirm("정보를 수정하겠습니까?");
	 
	 if(res==true){
		 document.frm.method="post";
		 document.frm.action="${pageContext.request.contextPath}/MemberModifyController";
		 document.frm.submit();
		
	 }
 }
 </script>
</head>
<body>
	<c:set var="smemberId" value=""/>
	
	<c:if test="${sMemberId ne null}">
	<c:set var="smemberId" value="${sMemberId}"/>
	</c:if>
	
	<c:choose>
		<c:when test="${smemberId ne null && smemberId eq ''}">
		<c:out value="로그인하세요"></c:out>
		<a href="${pageContext.request.contextPath}/MemberLoginCheckController">로그인</a>
		</c:when>
		
		<c:otherwise>
		<c:out value="${smemberId}님 안녕하세요"></c:out>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/BoardSelectController">게시판 목록</a>
		
		
		<input type="button"  value="수정하기" onclick="modify()" />
		</c:otherwise>
	
	</c:choose>
	
	
		
	
<form name=frm>
	<table class="type11" border=1 style="margin-top:10px; margin-left:auto; margin-right:auto;">
		<tr>
			<th width=30px height=30px>회원번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>주민번호</th>
			<th>주소</th>
			<th>이메일</th>
			<th>IP</th>
			<th>부서번호</th>
			<th>가입일</th>
			<th>수정일</th>
		</tr>
		<c:forEach var="mvo" items="${av}">
		<tr>
			<td>${mvo.memberMidx}</td>
			<td>${mvo.memberId}</td>
			<td>${mvo.memberPassword}</td>
			<td>${mvo.memberName}</td>
			<td>${mvo.memberSex}</td>
			<td>${mvo.memberJumin}</td>
			<td>${mvo.memberAddr}</td>
			<td>${mvo.memberEmail}</td>
			<td>${mvo.memberIp}</td>
			<td>${mvo.bidx}</td>
			<td>${mvo.writeday}</td>
			<td>${mvo.modifyday}></td>
		</tr>
		</c:forEach>
		
	</table>
</form>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>