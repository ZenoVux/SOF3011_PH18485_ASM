<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.CartStatus"%>
<c:set var="title" value="Lịch sử mua hàng" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-10">
		<h3 class="text-center my-3">Lịch sử mua hàng</h3>
		<div id="message"></div>

		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Mã đơn</th>
						<th scope="col">Tổng tiền</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Địa chỉ</th>
						<th scope="col">Trạng thái</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cart" items="${carts}">
						<tr>
							<td>#${cart.id}</td>
							<td><span id="totalMoney"><fmt:setLocale
										value="vi_VN" /> <fmt:formatNumber type="currency"
										value="${cart.totalMoney}" /></span></td>
							<td>${cart.phoneNumber}</td>
							<td>${cart.address}</td>
							<td><c:if test="${cart.status == CartStatus.COMFIRMED}">Chưa thanh toán</c:if>
								<c:if test="${cart.status == CartStatus.COMPLETED}">Hoàn thành</c:if>
								<c:if test="${cart.status == CartStatus.CANCEL}">Đã huỷ</c:if></td>
							<td><a href="/PH18485_ASM/cart/history?id=${cart.id}"
								type="button" class="btn btn-primary">Chi tiết</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${size <= 0}">
			<p class="text-center my-5">Không có dữ liệu</p>
		</c:if>
	</div>
</div>
<%@include file="includes/footer.jsp"%>