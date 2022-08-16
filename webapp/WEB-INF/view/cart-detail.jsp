<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.CartStatus"%>
<c:set var="title" value="Thông tin đơn hàng" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-10">
		<h3 class="text-center my-3">Thông tin đơn hàng</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Sản phẩm</th>
					<th scope="col">Đơn giá</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Số tiền</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cartDetail" items="${cartDetails}">
					<tr id="tivi${cartDetail.tivi.id}">
						<td><img class="mx-3"
							src="<c:url value="/images/${cartDetail.tivi.image}"/>"
							width="100px" alt=""><a
							href="/PH18485_ASM/tivi?id=${cartDetail.tivi.id}">${cartDetail.tivi.name}</a></td>
						<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
								type="currency" value="${cartDetail.tivi.price}" /></td>
						<td>${cartDetail.quantity}</td>
						<td id="price${cartDetail.tivi.id}"><fmt:setLocale
								value="vi_VN" /> <fmt:formatNumber type="currency"
								value="${cartDetail.tivi.price * cartDetail.quantity}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h5>Thông tin khách hàng</h5>
		<p>- Họ và tên: ${cart.fullname}</p>
		<p>- Số điện thoại: ${cart.phoneNumber}</p>
		<p>- Địa chỉ: ${cart.address}</p>
		<h5>
			Tổng tiền: <span id="totalMoney"><fmt:setLocale value="vi_VN" />
				<fmt:formatNumber type="currency" value="${cart.totalMoney}" /></span>

		</h5>
		<div class="row mt-5">
			<c:if test="${cart.status == CartStatus.COMFIRMED}">
				<div class="d-grid gap-2 col-6 m-auto mb-4">
					<button onclick="checkOut(${cart.id})" class="btn btn-primary"
						type="button">Thanh Toán</button>
				</div>
			</c:if>
		</div>
	</div>
</div>
				<div id="message"></div>
<script type="text/javascript">
	function checkOut(id) {
		$("#btnSubmit").prop("disabled", false);
		$.ajax({
			url : '/PH18485_ASM/cart/check-out',
			type : 'POST',
			data : {
				id: id
			},
			success : function(data) {
				$('#message').html(data);
				$("#btnSubmit").prop("disabled", false);
			}
		});
	}
</script>
<%@include file="includes/footer.jsp"%>