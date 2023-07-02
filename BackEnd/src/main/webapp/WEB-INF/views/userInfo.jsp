<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/confirm.jsp"%>
<c:set var="email" value="${userDto.email}" />
<c:set var="emailid" value="${fn:substringBefore(email, '@')}" />
<c:set var="emaildomain" value="${fn:substringAfter(email, '@')}" />

<div class="container">
	<div class="row justify-content-center mb-2">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">회원 정보 조회 및
				수정</h2>
		</div>
		<div class="px-5">
			<form id="form-modify" method="POST" action="">
				<input id="action" type="hidden" name="action" value="modify">
				<div class="mb-3">
					<label for="username" class="form-label">이름 : </label> <input type="text" 
						class="form-control" id="username" name="username" value="${userinfo.name}"
						readonly />
				</div>
				<div class="mb-3">
					<label for="userid" class="form-label">아이디 : </label> <input type="text" 
						class="form-control" id="userid" name="userid" value="${userinfo.id}"
						readonly />
				</div>
				<div id="idcheck-result"></div>
				<div class="mb-3">
					<label for="userpwd" class="form-label">비밀번호 : </label> <input
						type="password" class="form-control" id="userpwd" name="userpwd"
						value="${userinfo.password}" />
				</div>
				<div class="mb-3">
					<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
						type="password" class="form-control" id="pwdcheck" />
				</div>
				<div class="mb-3">
					<label for="emailid" class="form-label">이메일 : </label>
					<div class="input-group">
						<input type="text" class="form-control" id="emailid"
							name="emailid" value="${emailid}" /> <span
							class="input-group-text">@</span> <select class="form-select"
							id="emaildomain" name="emaildomain" aria-label="이메일 도메인 선택">
							<option selected>선택</option>
							<option value="ssafy.com">ssafy.com</option>
							<option value="google.com">google.com</option>
							<option value="naver.com">naver.com</option>
							<option value="kakao.com">kakao.com</option>
						</select>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="text-center mb-5">
		<button type="button" id="btn-modify" class="btn btn-primary btn-md">회원
			정보 수정</button>
		<button type="button" id="btn-delete"
			class="btn btn-outline-danger btn-md">회원 탈퇴</button>
	</div>
</div>

<script>
	document.querySelector("#btn-modify").addEventListener(
			"click",
			function() {
				if (!document.querySelector("#userpwd").value) {
					alert("비밀번호 입력!!");
					return;
				} else if (document.querySelector("#userpwd").value != document
						.querySelector("#pwdcheck").value) {
					alert("비밀번호 확인!!");
					return;
				} else if (!document.querySelector("#emailid").value) {
					alert("이메일 입력!!");
					return;
				} else if (!document.querySelector("#emaildomain").value) {
					alert("이메일 도메인 선택!!");
					return;
				} else {
					let form = document.querySelector("#form-modify");
					form.setAttribute("action", "${root}/user");
					form.submit();
				}
			});

	document.querySelector("#btn-delete").addEventListener("click", function() {
		document.querySelector("#action").value = "delete";
		console.log(document.querySelector("#action").value);

		let form = document.querySelector("#form-modify");
		form.setAttribute("action", "${root}/user");
		form.submit();
	});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>