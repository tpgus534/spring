<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp" %>
<%
Cookie cookie[] = request.getCookies();
String svid = "";
String ckid = "";
if(cookie != null) {
	for(Cookie c : cookie) {
		if("kid_inf".equals(c.getName())) {
			svid = c.getValue();
			ckid = " checked";
			break;
		}
	}
}
%>
<script type="text/javascript">
$(document).ready(function() {
	$('#loginBtn').click(function() {
		login();
	});
	
	$('#moveRegisterBtn').click(function() {
		$(location).attr("href", "${root}/user/register.kitri");
	});
});

function login() {
	if($('#id').val().trim() == "") {
		alert("아이디 입력!!!!");
		return;
	} else if($('#pass').val().trim() == "") {
		alert("비밀번호 입력!!!!");
		return;
	} else {
		$('#loginform').attr("action","${root}/user/login.kitri");
		$('#loginform').submit();
	}
}
</script>

<div class="container" align="center">
	<div class="col-lg-6" align="center">
		<h2>로그인</h2>
		<form id="loginform" method="post" action="">
			<div class="form-group" align="right">
				<label for=""><input type="checkbox" class="form-control" name="idsave" value="idsave"<%=ckid%>>아이디저장</label>
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" value="<%=svid%>" placeholder="">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="pass" name="pass" placeholder="">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" id="loginBtn">로그인</button>
				<button type="button" class="btn btn-primary" id="moveRegisterBtn"">회원가입</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/template/footer.jsp" %>