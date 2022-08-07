<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon"
	href="http://zenoz.ml/upload/files/favicon.ico">
<title><c:out value="${title}"></c:out></title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<!-- ajax -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a5676e3fab.js"
	crossorigin="anonymous"></script>
</head>

<body class="bg-secondary">
	<div class="container bg-light">
		<nav class="row">
			<div class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="container-fluid">
					<a class="navbar-brand" href="/PH18485_ASM/tivi">FPT
						Polytechnic</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<!-- 
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-info-sign"></span> Giới thiệu
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-envelope"></span> Liên hệ
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-send"></span> Góp ý
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									class="glyphicon glyphicon-question-sign"></span> Hỏi - Đáp
							</a></li>
						 -->
						</ul>
						<div class="d-flex">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" id="taiKhoan"
									role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
										class="fa-solid fa-user"></i> ${sessionScope.username == null ? "Tài khoản" : sessionScope.fullname}
								</a>
									<ul class="dropdown-menu" aria-labelledby="taiKhoan">
										<c:if test="${sessionScope.username == null}">
											<li><a class="dropdown-item"
												href="/PH18485_ASM/register">Đăng ký</a></li>
											<li><a class="dropdown-item" href="/PH18485_ASM/login">Đăng
													nhập</a></li>
											<li><a class="dropdown-item" href="/PH18485_ASM/quen-mk">Quên
													mật khẩu</a></li>
										</c:if>
										<c:if test="${sessionScope.username != null}">
											<li><a class="dropdown-item" href="/PH18485_ASM/logout">Đăng
													xuất</a></li>
										</c:if>

									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>