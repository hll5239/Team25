<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 <%@include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">


    function check() {
        var formname=document.frm;
    	var idOk= formname.memberId.value;
    	var nameOk= formname.memberName.value;
    	var juminOk= formname.memberJumin.value;
    	var pwOk= formname.memberPassword.value;
    	var pw2Ok= formname.memberPassword2.value;
    	
    	
        if (idOk=="") {
            alert("아이디를 입력해주세요");
            formname.memberId.focus(); //커서 위치 
            return;
        }else if(nameOk==""){
        	 alert("이름을 입력해주세요");
        	 formname.memberName.focus(); //커서 위치 
        	 return;
        }else if(juminOk==""){
       	 alert("주민번호를 입력해주세요");
    	 formname.memberJumin.focus(); //커서 위치 
         return;
    	}else if(isNaN(juminOk) == true){
    		alert("숫자만 입력해주세요");
    		document.frm.memberJumin.value=null;
    		formname.memberJumin.focus();
    		
    	 return false;
    	}
        else if(pwOk==""){
       	 alert("비밀번호를 입력해주세요");
    	 formname.memberPassword.focus(); //커서 위치 
         return;
    	}else if(pw2Ok==""){
          	 alert("비밀번호확인을 입력해주세요");
        	 formname.memberPassword2.focus(); //커서 위치 
             return;
        }else if(pwOk != pw2Ok){
        	alert("비밀번호가 일치하지 않습니다.");
        	formname.memberPassword.focus();
        	return;
        }
        var res;
        res= confirm("회원가입을 진행하겠습니까?");
        
       	if(res==true){
       	formname.method="post";
        formname.action="${pageContext.request.contextPath}/MemberJoinActionController";
        formname.submit();
       	}
        return;
    }
    function inputAge(){
    	var today=new Date();
    	var yyyy=today.getFullYear();
    	
    	document.frm.memberAge.value = yyyy-("19"+document.frm.memberJumin.value.substr(0,2));
    	return;
    }

    
</script>
<title>회원가입 페이지</title>
</head>
<body>
<form name="frm" > <!-- post방식으로 action을 통해 jsp에 넘겨준다 onsubmit="return check();" 이용해서 해줘도됨-->
<!-- <input type="hidden" name="hiddenTest" value="하이"> 값을 숨기는 용도 -->
<table border=1  width='500px' height='300px' cellspacing='0' cellpadding='0' > <!-- cell0000여백조정 -->
<tr>
<td>ID</td>
<td colspan=3><input type="text" name="memberId" id="memberId" size=20 maxlength="20"></td> <!-- name변수명에 값을 저장해서 넘겨준다 -->
</tr>

<tr>
<td>이름</td>
<td width=30%><input type="text" name="memberName" id="memberName" size=20 maxlength="20"/></td>
<td>성별</td>
<td width=30%>
<input type="radio" name="memberSex" id="memeberMale" value="M" checked>남 
<input type="radio" name="memberSex" id="memberFemale" value="F">여</td>
</tr>

<tr>
<td>email</td>
<td colspan=3><input type="text" name="memberEmail1" size="20" />
<select name="memberEmail2">
<option value="naver.com">네이버</option>
<option value="daum.net">다음</option>
</select>
</td>
</tr>

<tr>
<td>주소</td>
<td colspan=3><input type="text" name="memberAddr"  size=50/></td>
</tr>

<tr>
<td>주민번호</td>
<td colspan=3>
<input type="text" name="memberJumin" size="13" maxlength="13" onchange="inputAge();"/>
<input type="text" name="memberAge" size="10" readonly disabled></td>
</tr>

<tr>
<td>password</td>
<td colspan=3><input type="password" id="Pw" name="memberPassword"/></td>
</tr>

<tr>
<td>password확인</td>
<td colspan=3><input type="password" id="PwCheck" name="memberPassword2"/></td>
</tr>
<tr>
<td>부서</td>
<td colspan=3><select name="bidx" >
<option value="1">전산실</option>
<option value="2">영업실</option>
<option value="3">운영실</option>
<option value="4">기획실</option>
</select>
</td>
</tr>
<tr>
<td colspan=4>
<!-- <input type="submit" name="submit" value="가입하기" /> --> <!-- html로도 가능하지만 자바스크립트를 사용함 -->
<input type="button"  value="리셋" onclick="reset()" />
<input type="button"  value="가입하기" onclick="check()" />
</td>
</tr>

</table>

</form>


</body>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
</html>