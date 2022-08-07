<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Thêm sản phẩm" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-5">
		<h3 class="text-center my-3">Thêm sản phẩm</h3>
		<div id="message"></div>
		<form id="myForm" action="/PH18485_ASM/tivi/add" method="POST">
			<div class="mb-2">
				<label class="form-label">Tên:</label> <input class="form-control"
					name="name" type="text" value="${tivi.name}">
			</div>
			<div class="mb-2">
				<label class="form-label">Mô tả:</label>
				<textarea class="form-control" name="description">${tivi.description}</textarea>
			</div>
			<div class="mb-2">
				<label class="form-label">Giá:</label> <input class="form-control"
					name="price" type="number" value="${tivi.price}">
			</div>
			<div class="mb-2">
				<label class="form-label">Số lượng:</label> <input
					class="form-control" name="quantity" type="number"
					value="${tivi.quantity}">
			</div>
			<div class="mb-2">
				<label class="form-label">Kích thước màn hình:</label> <input
					class="form-control" name="screenSize" type="number"
					value="${tivi.screenSize}">
			</div>
			<div class="mb-2">
				<label class="form-label">Hệ điều hành:</label> <select
					class="form-select" name="os">
					<c:forEach var="os" items="${operatingSystems}">
						<option value="${os.id}">${os.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-2">
				<label class="form-label">Độ phân giải:</label> <select
					class="form-select" name="resolution">
					<c:forEach var="resolution" items="${resolutions}">
						<option value="${resolution.id}">${resolution.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-2">
				<label class="form-label">Loại màn hình:</label> <select
					class="form-select" name="screenType">
					<c:forEach var="screenType" items="${screenTypes}">
						<option value="${screenType.id}">${screenType.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mb-2">
				<label class="form-label">Hãng:</label> <select class="form-select"
					name="brand">
					<c:forEach var="brand" items="${brands}">
						<option value="${brand.id}">${brand.name}</option>
					</c:forEach>
				</select>
			</div>
			<hr>
			<div class="mb-2 d-grid gap-2">
				<button type="submit" class="btn btn-primary">Thêm</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myForm').on('submit', function(e) {
			e.preventDefault();
			$.ajax({
				url : '/PH18485_ASM/tivi/add',
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