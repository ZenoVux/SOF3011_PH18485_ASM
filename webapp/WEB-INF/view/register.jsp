<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Đăng ký"/>
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-4">
		<h3 class="text-center my-3">Đăng ký</h3>
		<div id="message"></div>
		<form id="myForm" action="/PH18485_ASM/register" method="POST">
			<div class="mb-2">
				<label class="form-label">Tên đăng nhập:</label> <input
					name="username" type="text" class="form-control">
				<div class="invalid-feedback">Vui lòng nhập tên đăng nhập.</div>
			</div>
			<div class="mb-2">
				<label class="form-label">Họ và tên:</label> <input name="fullname"
					type="text" class="form-control">
				<div class="invalid-feedback">Vui lòng nhập tên đăng nhập.</div>
			</div>
			<div class="mb-2">
				<label class="form-label">Mật khẩu:</label> <input name="password"
					type="password" class="form-control">
				<div class="invalid-feedback">Vui lòng nhập mật khẩu.</div>
			</div>
			<div class="mb-2">
				<label class="form-label">Xác nhận mật khẩu:</label> <input
					name="confirmPassword" type="password" class="form-control">
				<div class="invalid-feedback">Vui lòng nhập mật khẩu.</div>
			</div>
			<hr>
			<div class="mb-4 d-grid gap-2">
				<button id="btnSubmit" type="submit" class="btn btn-primary">Đăng ký</button>
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
				url : '/PH18485_ASM/register',
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