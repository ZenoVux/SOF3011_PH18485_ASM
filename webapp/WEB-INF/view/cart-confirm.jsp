<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Xác nhận đơn hàng" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-5">
		<h3 class="text-center my-3">Xác nhận đơn hàng</h3>
		<div id="message"></div>
		<form id="myForm" action="/PH18485_ASM/cart/confirm" method="POST">
			<div class="mb-2">
				<label class="form-label">Họ và tên:</label> <input name="fullname"
					type="text" value="${sessionScope.fullname}" class="form-control">
			</div>
			<div class="mb-2">
				<label class="form-label">Số điện thoại:</label> <input
					name="phoneNumber" type="text" class="form-control">
			</div>
			<div class="mb-2">
				<label class="form-label">Địa chỉ:</label>
				<textarea class="form-control" name="address" rows="3"></textarea>
			</div>
			<h5>
				Tổng tiền: <span id="totalMoney"><fmt:setLocale value="vi_VN" />
					<fmt:formatNumber type="currency" value="${totalMoney}" /></span>
			</h5>
			<div class="d-grid gap-2 mb-4">
				<button id="btnSubmit" class="btn btn-primary" type="submit">Xác nhận</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myForm').on('submit', function(e) {
			e.preventDefault();
			$("#btnSubmit").prop("disabled", true);
			$.ajax({
				url : '/PH18485_ASM/cart/confirm',
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