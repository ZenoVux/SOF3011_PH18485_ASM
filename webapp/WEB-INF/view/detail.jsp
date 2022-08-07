<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Thông tin sản phẩm" />
<%@include file="includes/header.jsp"%>
<div class="row justify-content-center">
	<div class="col-md-5 my-2">
		<h3 class="text-center my-3">Thông tin sản phẩm</h3>
		<div id="message"></div>
		<p>Tên: ${tivi.name}</p>
		<p>Mô tả: ${tivi.description}</p>
		<p>
			Giá:
			<fmt:setLocale value="vi_VN" />
			<fmt:formatNumber type="currency" value="${tivi.price}" />
		</p>
		<p>Số lượng: ${tivi.quantity}</p>
		<p>Kích thước màn hình: ${tivi.screenSize} inch</p>
		<p>Hệ điều hành: ${tivi.os.name}</p>
		<p>Độ phân giải: ${tivi.resolution.name}</p>
		<p>Loại màn hình: ${tivi.screenType.name}</p>
		<p>Hãng: ${tivi.brand.name}</p>
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
	</div>
</div>
<%@include file="includes/footer.jsp"%>