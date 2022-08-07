<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.AccountRole"%>
<c:set var="title" value="Danh sách sản phẩm" />
<%@include file="includes/header.jsp"%>
<div class="card my-2">
	<div class="card-body">
		<c:if test="${sessionScope.username != null}">
			<a class="btn btn-success" href="/PH18485_ASM/tivi/add"><i
				class="fa-solid fa-plus"></i> Thêm mới</a>
		</c:if>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModalPopovers">
			<i class="fa-solid fa-filter"></i>
		</button>
	</div>
</div>
<div id="data">
	<%@include file="includes/table.jsp"%>
</div>
<div class="modal fade" id="exampleModalPopovers" tabindex="-1"
	aria-labelledby="exampleModalPopoversLabel" style="display: none;"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalPopoversLabel">Lọc sản
					phẩm</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="myForm" action="/PH18485_ASM/tivi/edit" method="POST">
					<div class="mb-3">
						<label class="form-label">Tên</label> <input type="text"
							name="name" class="form-control" placeholder="Tên cần tìm">
					</div>
					<div class="mb-3">
						<label class="form-label">Giá</label>
						<div class="row">
							<div class="col">
								<input type="number" name="priceMin" class="form-control"
									placeholder="Từ">
							</div>
							<div class="col">
								<input type="number" name="priceMax" class="form-control"
									placeholder="Đến">
							</div>
						</div>
					</div>
					<div class="mb-3">
						<label class="form-label">Số lượng</label> <select
							class="form-select" name="quantity" id="inputQuantity">
							<option value="ALL" selected>Sắp xếp theo</option>
							<option value="ASC">Tăng dần</option>
							<option value="DESC">Giảm dần</option>
						</select>
					</div>
					<c:if test="${sessionScope.role == AccountRole.ADMIN}">
						<div class="mb-3">
							<label class="form-label">Trạng thái</label> <select
								class="form-select" name="deleted">
								<option value="all" selected>Tất cả</option>
								<option value="true">Đã xoá</option>
								<option value="false">Chưa xoá</option>
							</select>
						</div>
					</c:if>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" form="myForm">
					<i class="fa-solid fa-check"></i> Lọc
				</button>
				<button class="btn btn-secondary" data-bs-dismiss="modal">
					<i class="fa-solid fa-xmark"></i> Đóng
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myForm').on('submit', function(e) {
			e.preventDefault();
			$.ajax({
				url : '/PH18485_ASM/tivi/filter',
				type : 'GET',
				data : $(this).serialize(),
				success : function(data) {
					$('#data').html(data);
				}
			});
		});
	});
</script>
<%@include file="includes/footer.jsp"%>