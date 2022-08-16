<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Thông tin sản phẩm" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<h3 class="text-center my-3">Thông tin sản phẩm</h3>
	<div class="row col-10 justify-content-center">
		<div class="col-md-6 my-2">
			<img class="img-thumbnail"
				src="<c:url value="/images/${tivi.image}"/>"
				alt="">
		</div>
		<div class="col-md-4 my-2">
			<div id="message"></div>
			<span>Tên: ${tivi.name}</span>
			<br>
			<span>
				Giá:
				<fmt:setLocale value="vi_VN" />
				<fmt:formatNumber type="currency" value="${tivi.price}" />
			</span>
			<br>
			<span>Kích thước màn hình: ${tivi.screenSize} inch</span>
			<br>
			<span>Hệ điều hành: ${tivi.os.name}</span>
			<br>
			<span>Độ phân giải: ${tivi.resolution.name}</span>
			<br>
			<span>Loại màn hình: ${tivi.screenType.name}</span>
			<br>
			<span>Hãng: ${tivi.brand.name}</span>
			<br>
			<br>
			<button type="button" onclick="addToCart(${tivi.id})" class="btn btn-success"
						style="width: 50%">
						<i class="fa-solid fa-cart-plus"></i>
					</button>
			<!-- 
		<p>Số lượng: ${tivi.quantity}</p>
		<p>Người tạo: ${tivi.createUser.fullname}</p>
		<p>
			Ngày tạo:
			<fmt:formatDate value="${tivi.createdDate}"
				pattern="hh:mm dd/MM/yyyy" />
		</p>
		<p>Người sửa cuối: ${tivi.lastModifiedUser.fullname}</p>
		<p>
			Ngày sửa cuối:
			<fmt:formatDate value="${tivi.lastModifiedDate}"
				pattern="hh:mm dd/MM/yyyy" />
		</p>
		<p>
			Đã xoá:
			<c:if test="${tivi.deleted}">Rồi</c:if>
			<c:if test="${!tivi.deleted}">Chưa</c:if>
		</p>
		 -->
		</div>

		<hr>
			<p>Mô tả: ${tivi.description}</p>
	</div>
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