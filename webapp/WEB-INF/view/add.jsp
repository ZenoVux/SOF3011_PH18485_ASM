<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Thêm sản phẩm" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-5">
		<h3 class="text-center my-3">Thêm sản phẩm</h3>
		<div id="message"></div>
		<form id="myForm" method="POST" enctype="multipart/form-data">
			<div class="mb-2">
				<label class="form-label">Tên:</label> <input class="form-control"
					name="name" type="text" value="${tivi.name}">
			</div>
			<div class="mb-2">
				<label class="form-label">Hình ảnh:</label> <input
					class="form-control" type="file" name="image">
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
				<button id="btnSubmit" type="submit" class="btn btn-primary">Thêm</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").click(function(event) {
			event.preventDefault();
			var form = $('#myForm')[0];
			var data = new FormData(form);
			$("#btnSubmit").prop("disabled", true);
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "/PH18485_ASM/tivi/add",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 800000,
				success : function(data) {
					$('#message').html(data);
					console.log("SUCCESS : ", data);
					$("#btnSubmit").prop("disabled", false);
				},
				error : function(e) {
					$('#message').html(data);
					console.log("ERROR : ", e);
					$("#btnSubmit").prop("disabled", false);
				}
			});
		});
	});
</script>
<%@include file="includes/footer.jsp"%>