<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.AccountRole"%>
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
				<td>${tivi.id}</td>
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
						pattern="hh:mm dd/MM/yy" /></td>
				<td>${tivi.lastModifiedUser.fullname}</td>
				<td><fmt:formatDate value="${tivi.lastModifiedDate}"
						pattern="hh:mm dd/MM/yy" /></td>
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
									onclick="return confirm('Bạn có chắc không?')"
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