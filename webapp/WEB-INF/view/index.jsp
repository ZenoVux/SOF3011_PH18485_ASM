<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Trang chủ" />
<%@include file="includes/header.jsp"%>
<c:url var="nextUrl" value="">
	<c:forEach items="${params}" var="entry">
		<c:if test="${entry.key != 'page'}">
			<c:param name="${entry.key}" value="${entry.value[0]}" />
		</c:if>
	</c:forEach>
	<c:param name="page" value="" />
</c:url>
<div class="card my-2">
	<div class="card-body">
		<form action="/PH18485_ASM/index" method="GET">
			<div class="row">
				<div class="col-sm-3 my-1">
					<label class="visually-hidden">Name</label> <input type="text"
						class="form-control" name="name"
						placeholder="Tên sản phẩm cần tìm">
				</div>
				<div class="col-auto my-1">
					<button type="submit" class="btn btn-primary">Tìm kiếm</button>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div id="message"></div>
	<c:forEach items="${listTivi}" var="tivi">
		<div class="col-md-4 my-2">
			<div class="card text-center">
				<!-- 
				<div class="card-body">
				</div>
					<a class="link-dark" href="#">aaaaaaaaaaaaaaaaaaaaaa ${i}</a> <br>
				 -->
				<img class="card-img-top"
					src="<c:url value="/images/${tivi.image}"/>" alt="" width="400px"
					height="250px">
				<div class="card-footer">
					<h5 class="card-title">${tivi.name}</h5>
					<p class="card-text">
						<fmt:setLocale value="vi_VN" />
						<fmt:formatNumber type="currency" value="${tivi.price}" />
					</p>
					<div class="row">
						<div class="col-md-9 my-1">
							<a href="/PH18485_ASM/tivi?id=${tivi.id}" class="btn btn-primary" style="width: 100%">Xem
								chi tiết</a>
						</div>
						<div class="col-md-3 my-1">
							<button type="button" onclick="addToCart(${tivi.id})"
								class="btn btn-success" style="width: 100%">
								<i class="fa-solid fa-cart-plus"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div class="row my-2">
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item ${currPage == 1 ? 'disabled' : ''}"><a
				href="${nextUrl}1" class="page-link"><i
					class="fa-solid fa-backward-fast"></i></a></li>
			<c:forEach begin="${beginPage}" end="${endPage}" var="i">
				<li class="page-item ${currPage == i ? 'disabled' : ''}"><a
					href="${nextUrl}${i}" class="page-link">${i}</a></li>
			</c:forEach>
			<li class="page-item ${currPage == maxPage ? 'disabled' : ''}"><a
				href="${nextUrl}${maxPage}" class="page-link"><i
					class="fa-solid fa-forward-fast"></i></a></li>
		</ul>
	</nav>
</div>
<script type="text/javascript">
	function addToCart(id) {
		$.ajax({
			url : '/PH18485_ASM/cart/add',
			type : 'POST',
			data : {
				id: id,
				quantity: 1
			},
			success : function(data) {
				$('#message').html(data);
			}
		});
	}
</script>
<%@include file="includes/footer.jsp"%>