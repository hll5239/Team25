<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.myin.team25.domain.BoardVo" %>   
<%@ page import = "com.myin.team25.domain.PageMaker" %>   

<%@ include file="/WEB-INF/views/include/header.jsp" %>


<c:if test="${sMemberMidx ne null}">
<c:set var="memberMidx" value=${sMemberMidx }></c:set>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글내용 페이지</title> 
<!--  -->
<link href="/resources/css/comment.css" rel="stylesheet" type="text/css" />



<script type="text/javascript">

//화면이 뜰때 자동으로 실행
$(function(){
	
	$.boardComentList();

	$('#tbl').on('click',"#more",function(){
		
	var block =$("#nextBlock").val();
		//alert("값?"+block);
		$.ajax({
			type : "GET", 

			url  : "/comments/"+block+"/${bv.bBidx}", 
		
			datatype : "json",
			cache : false,
			error : function(){				
				alert("error");
			},
			success : function(data){
			alert("data="+data.nextBlock);	
			
			$("#nextBlock").val(data.nextBlock);
			alert(data);
			var memberMidx= ${memberMidx};
				var str = '';
				var delok='';
				var view='';
				//alert(memberMidx);
				//data.alist는 map안에 들어가있는 alist라는 이름을 사용
				$(data.alist).each(function(){
					
					if(memberMidx==this.memberMidx){
						delok="<input type='button' class='del' value='삭제'>";
					}else {
						delok='';
					}
					
					str += "<ul><li class='sub1'>"+this.cidx  + "</li>" 
						+  "<li class='sub2'>"+this.cwriter + "</li>"
						+  "<li class='sub3'>"+this.ccontent  + "</li>"
						+  delok
						+  "</ul>";		
					
					view="<button id='more' class='btn-primary'>+더보기</button>";
					
				});
				$('#tbl').html("<ul><li class='title1'>번호</li><li class='title2'>작성자</li><li class='title3'>내용</li></ul>"+str+view);				
			}			
		});
	});
	 $('#save').click(function(){
		
			 var cwriter = $("#cwriter").val();			
			 var ccontent = $("#ccontent").val();			
			 var bbidx = $("#bbidx").val();
		
			 $.ajax({
					type : "POST",
					url  : "/comments",
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : "text",
					data : JSON.stringify({				
						cwriter : cwriter,
						ccontent : ccontent,
						bbidx : bbidx
						
					}), 
					error : function(request,status,error){				
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					},
					success : function(data){
						//등록메시지와 헤더값
						alert(data);
						$.boardComentList();
						
						$("#cwriter").val("");
						$("#ccontent").val("");
						
										
					}			
				});	
		});

	 $('#tbl').on('click', ".del", function() {
	
		//var re=$(this).parent().children('#rowid');
		 //var cidx=re.val();
		 var bbidx = $("#bbidx").val();
		 var cidx = $(this).parent().children('.sub1').text();
		

		 $.ajax({
				type : "DELETE",
				url  : "/comments/del",
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				datatype : "text",
				data : JSON.stringify({			
					cidx : cidx,
					bbidx:bbidx
				}), 
				error : function(request,status,error){				
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				},
				success : function(data){
					alert(data);
					$.boardComentList();
				}			
			});	
		});
	
	 
	 
	});

$.boardComentList=function(){
	$.ajax({
		type : "GET", 

		url  : "<%=request.getContextPath()%>/comments/all/${bv.bBidx}", 
	
		datatype : "json",
		cache : false,
		error : function(){				
			alert("error");
		},
		success : function(data){
			var memberMidx= ${memberMidx};
			var str = '';
			var delok='';
			var view='';
			$(data).each(function(){
				
				if(memberMidx==this.memberMidx){
					delok="<input type='button' class='del' value='삭제'>";
				}else {
					delok='';
				}
				
				str += "<ul><li class='sub1'>"+this.cidx  + "</li>" 
					+  "<li class='sub2'>"+this.cwriter + "</li>"
					+  "<li class='sub3'>"+this.ccontent  + "</li>"
					+  delok
					+  "</ul>";		
				view="<button id='more' class='btn-primary'>+더보기</button>";
			});
			$('#tbl').html("<ul><li class='title1'>번호</li><li class='title2'>작성자</li><li class='title3'>내용</li></ul>"+str+view);				
		}				
	});
	}
	




function modify() {
    var formname=document.frm;
    
    var res;
    res= confirm("수정 하시겠습니까?");
    
   	if(res==true){
   	formname.method="post";
    formname.action="${pageContext.request.contextPath}/BoardModifyController${pageMaker.makeSearch(pageMaker.scri.page)}&bBidx=${bv.bBidx}";
    formname.submit();
   	}
    return;
}

function back() {
    var formname=document.frm;
    
  
   	formname.method="post";
    formname.action="${pageContext.request.contextPath}/BoardSelectController${pageMaker.makeSearch(pageMaker.scri.page)}";
    formname.submit();
   
    return;
}
function del() {
var formname=document.frm;
    
    var res;
    res= confirm("삭제 하시겠습니까?");
    
   	if(res==true){
   	formname.method="post";
   	formname.action="${pageContext.request.contextPath}/BoardDeleteController${pageMaker.makeSearch(pageMaker.scri.page)}&bBidx=${bv.bBidx}";
   	formname.submit();
   	}
    return;
}

function reply() {
	var formname=document.frm;
        
        var res;
        res= confirm("답변 하시겠습니까?");
        
       	if(res==true){
       	formname.method="post";
       	formname.action="${pageContext.request.contextPath}/BoardReplyController${pageMaker.makeSearch(pageMaker.scri.page)}&bBidx=${bv.bBidx}";
       	formname.submit();
       	}
        return;
    }


/* jquery Ajax이용한 댓글 함수 추가  */



</script>
<title>Insert title here</title>
</head>
<body>
<h1>게시판 내용 페이지 입니다.</h1>

<form name="frm" > <!-- post방식으로 action을 통해 jsp에 넘겨준다 onsubmit="return check();" 이용해서 해줘도됨-->
<!-- <input type="hidden" name="hiddenTest" value="하이"> 값을 숨기는 용도 -->
<input type="hidden" name="bBidx" value="${bv.bBidx}">	
<input type="hidden" name="oidx" value="${bv.oidx}">	
<input type="hidden" name="updown" value="${bv.updown}">	
<input type="hidden" name="leftright" value="${bv.leftright}">




<table border=1  width='700px' height='100px' cellspacing='0' cellpadding='0' > <!-- cell0000여백조정 -->
<tr>
<td><label for="writer">작성자:</label></td><td><input type="text"  id="writer" size=20 maxlength="20" value="${bv.writer}" readonly disabled/></td>
<td><label for="writeday">작성일:</label></td><td><input type="text" id="writeday" size=20 maxlength="20" value="${bv.writeday}" readonly disabled/></td>
</tr>
<tr>

</tr>
<tr>
<td><label for="subject">제목:</label></td>
<td colspan=3><input type="text" id="subject" size=50 maxlength="20" value="${bv.subject}" readonly disabled/></td>
</tr>

<tr>
<td><label for="content">내용:</label></td>
<td colspan=3><textarea class="textarea1"rows="20" cols="100" title="내용" id="content"  readonly disabled>${bv.content}</textarea></td>
</tr>
<tr>
<td colspan=4>
<!-- <input type="submit" name="submit" value="가입하기" /> --> <!-- html로도 가능하지만 자바스크립트를 사용함 -->
<input type="button" style="WIDTH: 50pt; HEIGHT: 50pt" value="삭제" onclick="del()" />
<input type="button" style="WIDTH: 50pt; HEIGHT: 50pt" value="수정" onclick="modify()" />
<input type="button" style="WIDTH: 50pt; HEIGHT: 50pt" value="목록" onclick="back()" />
<input type="button" style="WIDTH: 50pt; HEIGHT: 50pt" value="답변" onclick="reply()" />
</td>
</tr>
<tr>
<td colspan=4>
첨부파일:<a href="displayFile?fileName=${bv.filename}" download>${bv.filename}</a>
</td>
</tr>

</table>
</form>
<div class='uploadedList'></div>
<div id="commenttbl">
<input type="hidden" name="bbidx" id="bbidx" value="${bv.bBidx}" />
<ul>

<li><label for="cwriter">작성자:</label><input type="text" width="0px" name="cwriter" id="cwriter"></li>
<div>
<br>
<br>
</div>
<li><textarea name="ccontent" id="ccontent" class="text"></textarea></li> 
<li><input type="button" name="save" id="save" value="저장" class="comment_input"  /></li>
</ul>
</div>

<div>
<br>
<br>
<br>
<br>

</div>

<div id="tbl"></div>
<input id="nextBlock" type="hidden" value='2'>

</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>