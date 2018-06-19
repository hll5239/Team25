<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.fileDrop{
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
	}
small{
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script type="text/javascript">

/* function checkImageType(fileName){
	  
	var pattern =/jpg$|gif$|png$|jpeg$|i;
	  
	  return fileName.match(pattern);
	} 


 */
  function check() {
      var formname=document.frm;
      var writerOk= formname.writer.value;
  	var pwOk= formname.password.value;
  	var subjectOk= formname.subject.value;
  	var contentOk= formname.content.value;
  	
  	
  	if(writerOk==""){
        	 alert("작성자를 입력해주세요");
     	 formname.writer.focus(); //커서 위치 
          return;
     	}else if (pwOk=="") {
          alert("비밀번호를 입력해주세요");
          formname.password.focus(); //커서 위치 
          return;
      }
  	else if (subjectOk=="") {
          alert("제목을 입력해주세요");
          formname.subject.focus(); //커서 위치 
          return;
      }else if(contentOk==""){
      	 alert("내용을 입력해주세요");
      	 formname.content.focus(); //커서 위치 
      	 return;
      }
      var res;
      res= confirm("글작성이 완료되었습니까?");
      
     	if(res==true){
     	formname.method="post";
      formname.action="${pageContext.request.contextPath}/BoardWriteActionController";
      formname.submit();
     	}
      return;
  }
    
 function checkImageType(fileName){
	 
	 var pattern = /jpg$|gif$|png$/i;
	 
	 return fileName.match(pattern);
 }

 function getOriginalName(fileName){
	 if(checkImageType(fileName)){
		 return;
	 }

	 var idx= fileName.indexOf("_")+1;
	 return fileName.substr(idx);
 }
 
 function getLink(fileName){
	 if(!checkImageType(fileName)){
		 return;
	 }
	 
	 var idx= fileName.indexOf("_")+1;
	 return fileName.substr(idx);
 }
 
 function getImageLink(fileName){
	 
	 if(!checkImageType(fileName)){
		 return;
	 }
	 var front = fileName.substr(0,12);
	 var end = fileName.substr(14);
 
	 return front+end;
 }
 
 $(function(){
 	$(".fileDrop").on("dragenter dragover", function(event){
 		event.preventDefault();
 	});
 	$(".fileDrop").on("drop", function(event){
 		
 		event.preventDefault();
 		
 		var files =event.originalEvent.dataTransfer.files;
 		
 		var file= files[0];
 		
 		console.log(file);
 		
 		var formData= new FormData();
 		
 		formData.append("file",file);
 		
 		$.ajax({
 			url: '/uploadAjax',
 			data: formData,
 			dataType:'text',
 			processData: false,
 			contentType: false,
 			type: 'POST',
 			success: function(data){
 				alert(data.substr(14));
 				
 				$("#filename").val(getImageLink(data));
 				/* $(".uploadedList").html("<img src='/resources/images/"+data+"'>"); */
 				   var str='';
 				  //alert("넘겨져"+$("#uploadfile").val(data.substr(14)));
 				  
 				 if(checkImageType(data)){
 					str="<div>"
 						+"<a href='displayFile?fileName="+getImageLink(data)+"' download>"	
 						+"<img src='displayFile?fileName="+data+"'/>"
 							+getLink(data)+"</a><small data-src="+data+">X</small></div>";
 				}else {
 					str="<div><a href='displayFile?fileName="+data+"'>"
 							+getOriginalName(data)+"</a><small data-src="+data+">X</small></div>";	
 				}	
 				$(".uploadedList").append(str);   
 				 
 			}
 		});
 	});

 	$(".uploadedList").on("click", "small",function(event){
 		
 		var that =$(this);
 		
 		$.ajax({
 			url:"/deleteFile",
 			type:"POST",
 			data: {fileName:$(this).attr("data-src")},
 			dataType:"text",
 			success:function(result){
 				if(result == 'deleted'){
 					that.parent("div").remove();
 				}
 			}
 			
 		});
 	});
 });
 
</script>

<title>Insert title here</title>
</head>
<body>
<h1>게시판 글쓰기 페이지 입니다.</h1>

<form name="frm" id="frm" enctype="multipart/form-data" > <!-- target="team25" --> <!-- post방식으로 action을 통해 jsp에 넘겨준다 onsubmit="return check();" 이용해서 해줘도됨-->
<!-- <input type="hidden" name="hiddenTest" value="하이"> 값을 숨기는 용도 -->
<table border=1  width='200px' height='100px' cellspacing='0' cellpadding='0' > <!-- cell0000여백조정 -->

<tr>
<td>작성자</td><td><input type="text" name="writer" id="writer" size=20 maxlength="20"/></td>
<td>비밀번호</td><td><input type="password" name="password" id="password" size=20 maxlength="20"/></td>
</tr>
<tr>
<td>제목</td>
<td colspan=3><input type="text" name="subject" id="subject" size=50 maxlength="20"/></td>
</tr>

<tr>
<td>내용</td>
<td colspan=3><textarea rows="20" cols="100" title="내용" id="content" name="content"></textarea></td>
</tr>
<tr>
<td colspan=4>
<!-- <input type="submit" name="submit" value="가입하기" /> --> <!-- html로도 가능하지만 자바스크립트를 사용함 -->
<input type="button"  value="리셋" onclick="reset()" />
<input type="button"  value="글쓰기" onclick="check()" />
</td>
</tr>
</table>

<input type="hidden" id="filename" name="filename">  
<div class='fileDrop'></div>
<div class='uploadedList'></div>
</form>

<script>


 
</script>

<!-- <iframe name="team25"></iframe> -->

<!-- <script type="text/javascript">
function addFilePath(msg){
	alert(msg);
	
}
</script>
 -->
</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>