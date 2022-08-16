<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">Thanh toán</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="text-center">
					<img
						src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${url}"
						class="img-thumbnail" alt="">
				</div>
			</div>
			<div class="modal-footer">
				<button id="btnSubmit" type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var myModalEl = document.querySelector('#staticBackdrop');
	var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
	modal.show();
</script>