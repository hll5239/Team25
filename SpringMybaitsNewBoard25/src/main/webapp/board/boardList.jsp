
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myin.team25.domain.BoardVo" %>
<%@ page import="com.myin.team25.domain.PageMaker" %>


<style rel="stylesheet">
table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table.type11 th {
    width: 200px;
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
<title>게시판 리스트</title>
</head>
<script type="text/javascript">
 function writer() {
 
	 var res;
     res= confirm("글을 작성하겠습니까?");
	 
	 if(res==true){
		 document.frm.method="post";
		 document.frm.action="${pageContext.request.contextPath}/BoardWriteController";
		 document.frm.submit();
		
	       	}
 }

 </script>



<body>
<h1>게시판 리스트 페이지 입니다</h1>


<form name="searchform" action="${pageContext.request.contextPath}/BoardSelectController">
<table align="right">
<tr>
<td><select name="searchType" >
<option value="subject">제목</option>
<option value="writer">작성자</option>
</select></td>
<td><input type="text" name="keyword" id="keyword"></td>
<td><input type="submit"  value="검색"  /></td>
</tr>
</table>
</form>
<br>
<form name=frm>
	<table  class="type11" border=1 style="margin-top:10px; margin-left:auto; margin-right:auto;">
		<tr>
			<th>번호</th>
			<th align= "left">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			
		</tr>
		
		<c:set var="num" value="${pageMaker.totalCount-((pageMaker.scri.page-1)*pageMaker.scri.perPageNum) }"/>
		
		<c:forEach var="bv" items="${alist }">
		<tr>
			<td>${num }</td>
			
			
			<td align= "left"><c:forEach var="i" begin="0" end="${bv.leftright }" step="1" >
					&nbsp&nbsp
					<c:if test="${i eq bv.leftright}">
					<c:out value="☞"/>
					</c:if>
					
			</c:forEach> 
			
				
				<c:set var="data" value="${bv.subject }"/>
				<c:set var="data1" value="${bv.writer }"/>
				<c:set var="before" value="${pageMaker.scri.keyword }"/>
				<c:set var="after" value="<span style='color:yellow'>${pageMaker.scri.keyword}</span>"/>
				
				<a href="${pageContext.request.contextPath}/BoardContentController${pageMaker.makeSearch(pageMaker.scri.page) }&bBidx=${bv.bBidx}">
				<c:choose>
				<c:when test="${pageMaker.scri.searchType ne null }">
					<c:choose>
						<c:when test="${pageMaker.scri.searchType eq 'subject' }">
							<c:out value="${fn:replace(data,before,after) }" escapeXml="false"/>
				
						</c:when>
				
						<c:otherwise>
						<c:out value="${bv.subject }"/>
						</c:otherwise>
				</c:choose>
				
				</c:when>
				
				<c:otherwise>
					<c:out value="${bv.subject }"/>
				</c:otherwise>
				</c:choose>
				</a>
				

				</td>
			
			<td>
			<c:choose>
				<c:when test="${pageMaker.scri.searchType ne null }">
					<c:choose>
						<c:when test="${pageMaker.scri.searchType eq 'writer' }">
							<c:out value="${fn:replace(data1,before,after) }" escapeXml="false"  />
				
						</c:when>
				
						<c:otherwise>
						<c:out value="${bv.writer }"/>
						</c:otherwise>
				</c:choose>
				
				</c:when>
				
				<c:otherwise>
					<c:out value="${bv.writer }"/>
				</c:otherwise>
				</c:choose>
				</a>
				

				</td>
			<td>${bv.writeday }</td>
		</tr>
		<c:set var="num" value="${num-1}"></c:set>
		
		</c:forEach>
	</table>
	<input type="button"  value="글쓰기" onclick="writer()"  />
<!--페이징 관련 css 및 코드들  -->
	 <div style="text-align: center;">
		<div class="jb-center">
			<ul class="pagination">
				
				<c:if test="${pageMaker.prev}">
				
				<li>
				<a href="${pageContext.request.contextPath}/BoardSelectController${pageMaker.makeSearch(pageMaker.startPage-1) }">
				<span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				</li>
				</c:if>
				
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage }" step="1">
				<li <c:if test="${pageMaker.scri.page eq i }"> class="active" </c:if>>	
				<a href="${pageContext.request.contextPath}/BoardSelectController${pageMaker.makeSearch(i) }">${i}</a>
				</li>
				</c:forEach>
				
			
				<c:if test="${pageMaker.next && pageMaker.endPage>0 }">
				<li>
				<a href="${pageContext.request.contextPath}/BoardSelectController${pageMaker.makeSearch(pageMaker.endPage+1) }">
				<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
	</form>


</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>