<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.AccountRole"%>
<c:set var="title" value="Danh sách sản phẩm" />
<c:url var="nextUrl" value="">
	<c:forEach items="${params}" var="entry">
		<c:if test="${entry.key != 'page'}">
			<c:param name="${entry.key}" value="${entry.value[0]}" />
		</c:if>
	</c:forEach>
	<c:param name="page" value="" />
</c:url>
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
<div class="table-responsive">
	<table class="table table-striped table-hover">
		<tr>
			<th>Id</th>
			<th>Tên</th>
			<th>Mô tả</th>
			<th>Giá</th>
			<th>Số lượng</th>
			<th>Kích thước</th>
			<th>Hệ điều hành</th>
			<th>Độ phân giải</th>
			<th>Loại màn hình</th>
			<th>Hãng</th>
			<th>Người tạo</th>
			<th>Ngày tạo</th>
			<th>Người sửa cuối</th>
			<th>Ngày sửa cuối</th>
			<th>Đã xoá</th>
			<c:if test="${sessionScope.username != null}">
				<th>Hành động</th>
			</c:if>
		</tr>
		<c:forEach items="${listTivi}" var="tivi">
			<tr>
				<td><a href="/PH18485_ASM/tivi?id=${tivi.id}">${tivi.id}</a></td>
				<td>${tivi.name}</td>
				<td>${tivi.description}</td>
				<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
						type="currency" value="${tivi.price}" /></td>
				<td>${tivi.quantity}</td>
				<td>${tivi.screenSize}-inch</td>
				<td>${tivi.os.name}</td>
				<td>${tivi.resolution.name}</td>
				<td>${tivi.screenType.name}</td>
				<td>${tivi.brand.name}</td>
				<td>${tivi.createUser.fullname}</td>
				<td><fmt:formatDate value="${tivi.createdDate}"
						pattern="HH:mm dd/MM/yy" /></td>
				<td>${tivi.lastModifiedUser.fullname}</td>
				<td><fmt:formatDate value="${tivi.lastModifiedDate}"
						pattern="HH:mm dd/MM/yy" /></td>
				<td><c:if test="${tivi.deleted}">Rồi</c:if> <c:if
						test="${!tivi.deleted}">Chưa</c:if></td>
				<c:if test="${sessionScope.username != null}">
					<td>
						<div class="btn-group" role="group"
							aria-label="Basic mixed styles example">
							<a href="/PH18485_ASM/tivi/edit?id=${tivi.id}"
								class="btn btn-warning"><i class="fa-solid fa-pen-to-square"></i></a>
							<c:if test="${sessionScope.role == AccountRole.ADMIN}">
								<a href="/PH18485_ASM/tivi/delete?id=${tivi.id}"
									onclick="return confirm('Bạn có chắc muốn xóa không?')"
									class="btn btn-danger"><i class="fa-solid fa-trash-can"></i></a>
							</c:if>
						</div>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row my-2">
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item ${currPage == 1 ? 'disabled' : ''}"><a
				href="${nextUrl}1" class="page-link"><i class="fa-solid fa-backward-fast"></i></a></li>
			<c:forEach begin="${beginPage}" end="${endPage}" var="i">
				<li class="page-item ${currPage == i ? 'disabled' : ''}"><a
					href="${nextUrl}${i}" class="page-link">${i}</a></li>
			</c:forEach>
			<li class="page-item ${currPage == maxPage ? 'disabled' : ''}"><a
				href="${nextUrl}${maxPage}" class="page-link"><i class="fa-solid fa-forward-fast"></i></a></li>
		</ul>
	</nav>
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
				<form id="myForm" action="/PH18485_ASM/tivi" method="GET">
					<div class="mb-3">
						<label class="form-label">Tên</label> <input type="text"
							name="name" class="form-control" placeholder="Tên cần tìm">
					</div>
					<div class="mb-3">
						<label class="form-label">Giá</label>
						<div class="row">
							<div class="col">
								<input type="number" name="price-min" class="form-control"
									placeholder="Từ">
							</div>
							<div class="col">
								<input type="number" name="price-max" class="form-control"
									placeholder="Đến">
							</div>
						</div>
					</div>
					<div class="mb-3">
						<label class="form-label">Số lượng</label> <select
							class="form-select" name="quantity" id="inputQuantity">
							<option value="all" selected>Sắp xếp theo</option>
							<option value="asc">Tăng dần</option>
							<option value="desc">Giảm dần</option>
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
<!-- 
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
 -->
<%@include file="includes/footer.jsp"%>