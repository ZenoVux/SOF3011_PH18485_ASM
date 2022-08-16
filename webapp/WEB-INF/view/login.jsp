<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Đăng nhập" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-4">
		<h3 class="text-center my-3">Đăng nhập</h3>
		<div id="message"></div>
		<form id="myForm" action="/PH18485_ASM/login" method="POST">
			<div class="mb-2">
				<label class="form-label">Tên đăng nhập:</label> <input
					name="username" type="text" class="form-control">
			</div>
			<div class="mb-2">
				<label class="form-label">Mật khẩu:</label> <input name="password"
					type="password" class="form-control">
			</div>
			<div class="mb-2 d-grid gap-2">
				<button id="btnSubmit" type="submit" class="btn btn-primary">Đăng nhập</button>
			</div>
			<div class="mb-4 text-center">
				Bạn chưa có tài khoản? <a href="/PH18485_ASM/register">Đăng ký</a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myForm').on('submit', function(e) {
			e.preventDefault();
			$("#btnSubmit").prop("disabled", false);
			$.ajax({
				url : '/PH18485_ASM/login',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data) {
					$('#message').html(data);
					$("#btnSubmit").prop("disabled", false);
				}
			});
		});
	});
</script>
<%@include file="includes/footer.jsp"%>