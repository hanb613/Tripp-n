<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<a href="#"
	class="scroll-top d-flex align-items-center justify-content-center"><i
	class="bi bi-arrow-up-short"></i></a>

<div id="preloader"></div>

<!-- 로그인 modal start -->
<div class="modal fade" id="login" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">로그인</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form id="form-login" action="" method="POST">
					<input type="hidden" name="action" value="login" />
					<div class="mb-3">
						<div class="row text-center">
							<div class="col-3">아이디</div>
							<div class="col">
								<input type="text" class="form-control" id="loginId"
									placeholder="아이디" name="userid" />
							</div>
						</div>
					</div>
					<div class="mb-3">
						<div class="row text-center">
							<div class="col-3">비밀번호</div>
							<div class="col">
								<input type="password" class="form-control" id="loginPassword"
									placeholder="비밀번호" name="userpwd" />
							</div>
						</div>
					</div>
				</form>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="btn-login" class="btn btn-primary btn-sm">로그인</button>
				<a href="#" data-bs-toggle="modal" data-bs-target="#bwFinder">
					<button type="button" class="btn btn-secondary btn-sm">비밀번호찾기</button>
				</a>
				<button type="button" class="btn btn-outline-danger btn-sm"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 로그인 modal end -->

<!-- 로그인 modal 실행 start -->
<script>
	document.querySelector("#btn-login").addEventListener("click", function() {
		if (!document.querySelector("#loginId").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#loginPassword").value) {
			alert("비밀번호 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-login");
			form.setAttribute("action", "${root}/user/login");
			form.submit();
		}
	});
</script>
<!-- 로그인 modal 실행 end -->

<!-- 비밀번호찾기 modal start -->
<div class="modal fade" id="bwFinder" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">비밀번호 찾기</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form id="form-find-pwd" action="" method="POST">
				<input type="hidden" name="action" value="findpwd" />
					<div class="mb-3">
						<div class="row text-center">
							<div class="col-3">이름</div>
							<div class="col">
								<input type="text" class="form-control" id="pwFinderName"
									placeholder="이름" name="username" />
							</div>
						</div>
					</div>
					<div class="mb-3">
						<div class="row text-center">
							<div class="col-3">아이디</div>
							<div class="col">
								<input type="text" class="form-control" id="pwFinderId"
									placeholder="아이디" name="userid" />
							</div>
						</div>
					</div>
					<div class="mb-3">
						<div class="row text-center">
							<div class="col-3">이메일</div>
							<div class="col input-group">
								<input type="text" class="form-control" id="pwFinderEmailid"
									name="emailid" placeholder="이메일아이디" /> <span
									class="input-group-text">@</span> <select class="form-select"
									id="pwFinderEDomain" name="emaildomain" aria-label="이메일 도메인 선택">
									<option selected>선택</option>
									<option value="ssafy.com">ssafy.com</option>
									<option value="google.com">google.com</option>
									<option value="naver.com">naver.com</option>
									<option value="kakao.com">kakao.com</option>
								</select>
							</div>
						</div>
					</div>
				</form>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="btn-find-pwd"
					class="btn btn-primary btn-sm">비밀번호찾기</button>
				<button type="button" class="btn btn-outline-danger btn-sm"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 비밀번호찾기 modal end -->

<!-- 비밀번호찾기 modal 실행 start -->
<script>   
  document.querySelector("#btn-find-pwd").addEventListener("click", function () {
    if (!document.querySelector("#pwFinderName").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#pwFinderId").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#pwFinderEmailid").value) {
          alert("이메일 입력!!");
          return;
        } else if (!document.querySelector("#pwFinderEDomain").value) {
          alert("이메일 도메인 선택!!");
          return;
        } else {
          let form = document.querySelector("#form-find-pwd");
          form.setAttribute("action", "${root}/user");
          form.submit();
        }
  });
    </script>
<!-- 비밀번호찾기 modal 실행 end -->


<!-- 회원가입 modal start -->
<div class="modal fade" id="join" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Enjoy Trip 회원가입</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form id="form-join" method="POST" action="">
					<input type="hidden" name="action" value="join">
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="name"
							placeholder="이름..." />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="id"
							placeholder="아이디..." />
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="password"
							placeholder="비밀번호..." />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
							type="password" class="form-control" id="pwdcheck"
							placeholder="비밀번호확인..." />
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="emailid"
								name="emailid" placeholder="이메일아이디" /> <span
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

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="btn-join" class="btn btn-primary btn-sm">회원가입</button>
				<button type="button" class="btn btn-outline-danger btn-sm"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입 modal end -->

<!-- 회원가입 modal 실행 start -->
<script>
    let isUseId = false;
    document.querySelector("#userid").addEventListener("keyup", function () {
    let userid = this.value;
    console.log(userid);
      let resultDiv = document.querySelector("#idcheck-result");
/*       if(userid.length < 5 || userid.length > 16) {
        resultDiv.setAttribute("class", "mb-3 text-dark");
        resultDiv.textContent = "아이디는 5자 이상 16자 이하 입니다.";
        isUseId = false;
      } else {
        fetch("${root}/user?action=idcheck&userid=" + userid)
        .then(response => response.text())
        .then(data => {
          console.log(data);
        if(data == 0) {
            resultDiv.setAttribute("class", "mb-3 text-primary");
              resultDiv.textContent = userid + "는 사용할 수 있습니다.";
              isUseId = true;
        } else {
            resultDiv.setAttribute("class", "mb-3 text-danger");
              resultDiv.textContent = userid + "는 사용할 수 없습니다.";
            isUseId = false;
        }
         });
      } */
    });
    
  document.querySelector("#btn-join").addEventListener("click", function () {
    if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else if (!document.querySelector("#emailid").value) {
          alert("이메일 입력!!");
          return;
        } else if (!document.querySelector("#emaildomain").value) {
          alert("이메일 도메인 선택!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/user/join");
          form.submit();
        }
  });
    </script>
<!-- 회원가입 modal 실행 end -->

