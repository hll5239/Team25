<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %>
<%@ page import="com.myin.team25.domain.BoardVo" %>
<%@ page import="com.myin.team25.domain.PageMaker" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript">


    function del() {
    	 var formname=document.frm;
        var res;
        res= confirm("글을 진짜삭제 하시겠습니까?");
        
       	if(res==true){
       	formname.method="post";
        formname.action="${pageContext.request.contextPath}/BoardDeleteActionController${pageMaker.makeSearch(pageMaker.scri.page)}&bBidx=${bv.bBidx}";
        formname.submit();
       	}
        return;
    }

    
</script>
<title>삭제 페이지</title>
</head>
<body>
<form name=frm>
<h4>작성하신 글을 지우시겠습니까?</h4>
<h3>리얼? 진짜로?</h3>
<h2>지울거야??</h2>
<br>
<h1>그렇다면 비밀번호를 입력 하고 삭제버튼을 눌러!</h1>
<br>
<input type="password" name="password" id="password" size=10 maxlength="20"/>
<input type="button"  value="삭제" onclick="del()" />
</form>

</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>