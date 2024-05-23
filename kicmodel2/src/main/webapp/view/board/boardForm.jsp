<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 화면 샘플 - Bootstrap</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}
.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
</style>
</head>

<script>
function chkpw(f){
	if(f.pw.value == f.pw2.value){
		return true;
	} else{
		alert("비밀번호를 확인해주새요")
		f.pw2.focus()
		return false;
	}
}

function win_upload() {
	let op = "width=500, height=150, left=50, top=150";
	open("${pageContext.request.contextPath}/member/pictureimgForm", "", op);
}

</script>
<body>
<br>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">게시판 등록</h4>
				<form class="validation-form" novalidate  action="boardPro"   method="post" onsubmit="return chkpw(this)">
					<div class="row">
						<div class="col-md-9 mb-3">
						<div class="row">
							<div class="col-md-3 mb-3">
							<label for="id">사진</label> <img src=""  width="100px"  height="120px" name="file1">
							<button   class="btn btn-primary  btn-block" onclick="win_upload()">이미지</button>
						</div>
						<div class="col-md-6 mb-3">
							<label for="id">작성자</label> <input type="text"
								class="form-control" id="id" placeholder="작성자" value="" required  name="name">
							<div class="invalid-feedback">작성자을 입력해주세요.</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="name">비밀번호</label> <input type="text"
								class="form-control" id="pw" placeholder="" value=""  name="pw"
								required>
							<div class="invalid-feedback">비밀번호 입력해주세요.</div>
						</div>
					</div></div></div>
					
						<div class="row">
						<div class="col-md-6 mb-3">
							<label for="pass">비밀번호확인</label> <input type="password"
								class="form-control" id="pw2" placeholder="" value="" required  name="pw2">
							<div class="invalid-feedback">비밀번호확인 입력해주세요.</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="pass2">제목</label> <input type="text"
								class="form-control" id="pass2" placeholder="" value=""   name="subject"
								required>
							<div class="invalid-feedback">제목을 입력해주세요.</div>
						</div>
					</div>
					
					
					<div class="mb-3">
						<label for="email">내용</label> <textarea rows="5" cols="80" name="content"
							class="form-control" id="email" placeholder=""
							required></textarea>
						<div class="invalid-feedback">내용을 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="tel">파일업로드</label> <input type="file"
							class="form-control" id="tel" placeholder=""    name="file"
							required>
						<div class="invalid-feedback">파일업로드 입력해주세요.</div>
					</div>	
					<button class="btn btn-primary btn-lg btn-block" type="submit">작성
						완료</button>
				</form>
			</div>
		</div>
	</div>
	<script> window.addEventListener('load', () => { const forms = document.getElementsByClassName('validation-form'); Array.prototype.filter.call(forms, (form) => { form.addEventListener('submit', function (event) { if (form.checkValidity() === false) { event.preventDefault(); event.stopPropagation(); } form.classList.add('was-validated'); }, false); }); }, false); </script>
</body>
</html>
