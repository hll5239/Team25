<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/header.jsp" %>
<style>
body {
		background-image: url("/resources/dist/img/cat-1.jpg");
	}

</style>

<section class="content">
<div class="content">
<div class="col-md-12">

<div class="box">
<div class="box-header with-border">
	<h3 class="box-title">밥줘</h3><a href="<%=request.getContextPath() %>/BoardMemberInfoController">관리자리스트</a>
 	<a href="<%=request.getContextPath() %>/BoardList_AjaxController">Ajax로 페이징</a>

</div>
<img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image"/>
<img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image"/>
<br>
<img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image"/>
<img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image"/>
</div>
</div>
</div>
</section>
<br>
<br>

<%@include file="/WEB-INF/views/include/footer.jsp" %>