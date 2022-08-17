<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Giỏ hàng" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-10">
		<h3 class="text-center my-3">Giỏ hàng</h3>
		<div id="message"></div>

		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<!-- 
					<th scope="col">#</th>
						<th scope="row">1</th>
					 -->
						<th scope="col">Sản phẩm</th>
						<th scope="col">Đơn giá</th>
						<th scope="col">Số lượng</th>
						<th scope="col">Số tiền</th>
						<th scope="col"></th>
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
							<td><input class="form-control" type="number"
								id="quantity${cartDetail.tivi.id}"
								value="${cartDetail.quantity}" max="${cartDetail.tivi.quantity}"
								onchange="changeQuantity(${cartDetail.tivi.id},this)"></td>
							<td id="price${cartDetail.tivi.id}"><fmt:setLocale
									value="vi_VN" /> <fmt:formatNumber type="currency"
									value="${cartDetail.tivi.price * cartDetail.quantity}" /></td>
							<td><button type="button" class="btn btn-danger"
									onclick="remove(${cartDetail.tivi.id})">Xóa</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${size <= 0}">
			<p class="text-center my-5">Không có sản phẩm trong giỏ hàng</p>
		</c:if>
		<h5>
			Tổng tiền: <span id="totalMoney"><fmt:setLocale value="vi_VN" />
				<fmt:formatNumber type="currency" value="${totalMoney}" /></span>

		</h5>
		<div class="row mt-5">
			<div class="d-grid gap-2 col-6 m-auto mb-4">
				<a href="/PH18485_ASM/cart/confirm" class="btn btn-primary"
					type="button">Đặt hàng</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function changeQuantity(id,input) {
		if (input.value < 1) {
			if (remove(id)) {
				return;
			}
			input.value = 1;
		}
		$.ajax({
			url : '/PH18485_ASM/cart/edit',
			type : 'POST',
			data : {
				id : id,
				quantity : input.value
			},
			success : function(data) {
				$('#message').html(data);
			}
		});
	}
	function remove(id) {
		if (confirm("Bạn muốn xóa sản phẩm khỏi giỏ hàng?")) {
			$.ajax({
				url : '/PH18485_ASM/cart/delete',
				type : 'POST',
				data : {
					id : id
				},
				success : function(data) {
					$('#message').html(data);
				}
			});
			return true;
		}
		return false;
	}
</script>
<%@include file="includes/footer.jsp"%>