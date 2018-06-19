
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
    color: #00000;
    background: #fffff ;
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
<link href="/resources/css/comment.css" rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 리스트</title>
</head>
<script type="text/javascript">
$(function(){
	
	$.boardComentList(1);
	
	$("#pagination").on("click","li a", function(event){
		//a링크 페이지넘기는기능 중지
		event.preventDefault();
		//href에 담겨져 있는 속성의 값을 page에 넣는다.
		page= $(this).attr("href");
		
		$.boardComentList(page);
	});
});

$.boardComentList=function(page){
	$.ajax({
		type : "GET", 
		url  : "/BoardListAjaxController/"+page, 
	
		datatype : "json",
		cache : false,
		error : function(){				
			alert("error");
		},
		success : function(data){
			var str = '';
			var str2='';
			$(data.alist).each(function(){
				
				str += "<tr><th>"+this.bBidx + "</th>" 
					+  "<th>"+this.subject + "</th>"
					+  "<th>"+this.writer  + "</th>"
					+  "<th>"+this.writeday  + "</th>"
					+  "</tr>";
			});
			$('#boardList').html("<table class='type11' border=1 style='margin-top:10px; margin-left:auto; margin-right:auto;'><tr>"
			+"<th>번호</th>"
			+"<th align= 'left'>제목</th>"
			+"<th>작성자</th>"
			+"<th>작성일</th></tr>"+str+"<table>");	
			
				
				if(data.pageMaker.prev){ 
					str2+="<li><a href='"+(data.pageMaker.startPage-1)+"'>"
						+"<span class='glyphicon glyphicon-chevron-left'></span>"
						+"</a></li>"
					}
					 for(var i= data.pageMaker.startPage; i<=data.pageMaker.endPage; i++){
							var strClass=data.pageMaker.scri.page == i?'class=active':'';
						 str2+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>"			
							 } 
					 
					 if(data.pageMaker.next && data.pageMaker.endPage>0){ 			
						str2+="<li><a href='"+(data.pageMaker.endPage+1)+"'>"
							+"<span class='glyphicon glyphicon-chevron-right'></span></a></li>"
							}	
				
		
			$('#pagination').html("<div style='text-align: center;'><div class='jb-center'><ul class='pagination'>"+str2+"</ul></div></div>");
		}			
	});
	}




 function writer() {
 
	 var res;
     res= confirm("글을 작성하겠습니까?");
	 
	 if(res==true){
		 document.frm.method="post";
		 document.frm.action="<%=request.getContextPath() %>/BoardWriteController";
		 document.frm.submit();
		
	       	}
 }

 </script>



<body>
<h1>Ajax용 게시판 리스트 페이지 입니다</h1>


<form name=frm>
<div id="boardList"></div>
<div id="pagination"></div>

	<input type="button"  value="글쓰기" onclick="writer()"  />
</form>

</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>