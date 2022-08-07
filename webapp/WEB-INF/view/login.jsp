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
				<button type="submit" class="btn btn-primary">Đăng nhập</button>
			</div>
			<div class="text-center">
				<a href="/PH18485_ASM/register">Quên mật khẩu</a> / <a
					href="/PH18485_ASM/register">Tạo tài khoản</a>
			</div>
			<hr>
			<div class="mb-4 d-grid gap-4">
				<a href="/PH18485_ASM/register" class="btn btn-success">Đăng ký</a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myForm').on('submit', function(e) {
			e.preventDefault();
			$.ajax({
				url : '/PH18485_ASM/login',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data) {
					$('#message').html(data);
				}
			});
		});
	});
</script>
<%@include file="includes/footer.jsp"%>