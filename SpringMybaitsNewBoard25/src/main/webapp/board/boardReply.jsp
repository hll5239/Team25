<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page import="com.myin.team25.domain.BoardVo" %>
<%@ page import="com.myin.team25.domain.PageMaker" %>


<script type="text/javascript">


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
        formname.action="${pageContext.request.contextPath}/BoardReplyActionController${pageMaker.makeSearch(pageMaker.scri.page)}&bBidx=${bBidx}";
        formname.submit();
       	}
        return;
    }


</script>

<title>Insert title here</title>
</head>
<body>
<h1>게시판 답변하기 페이지 입니다.</h1>

<form name="frm" > <!-- post방식으로 action을 통해 jsp에 넘겨준다 onsubmit="return check();" 이용해서 해줘도됨-->
<!-- <input type="hidden" name="hiddenTest" value="하이"> 값을 숨기는 용도 -->
<input type="hidden" name="bBidx" value="${bBidx }">	
<input type="hidden" name="oidx" value="${oidx }">	
<input type="hidden" name="updown" value="${updown }">	
<input type="hidden" name="leftright" value="${leftright}">	
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
<input type="button"  value="답변하기" onclick="check()" />
</td>
</tr>
</table>
</form>
</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>