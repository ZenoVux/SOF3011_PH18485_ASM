<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>jQuery Ajax Form Submit with FormData Example -
		NiceSnippets.com</h1>
	<form method="POST" enctype="multipart/form-data" id="my-form">
		<input type="text" name="title" /><br />
		<br /> <input type="file" name="files" /><br />
		<br /> <input type="submit" value="Submit" id="btnSubmit" />
	</form>
	<span id="output"></span>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").click(function(event) {
				//stop submit the form, we will post it manually.
				event.preventDefault();

				// Get form
				var form = $('#my-form')[0];

				// FormData object 
				var data = new FormData(form);

				// If you want to add an extra field for the FormData
				data.append("CustomField", "This is some extra data, testing");

				// disabled the submit button
				$("#btnSubmit").prop("disabled", true);

				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : "/PH18485_ASM/upload",
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					timeout : 800000,
					success : function(data) {
						$("#output").text(data);
						console.log("SUCCESS : ", data);
						$("#btnSubmit").prop("disabled", false);
					},
					error : function(e) {
						$("#output").text(e.responseText);
						console.log("ERROR : ", e);
						$("#btnSubmit").prop("disabled", false);
					}
				});
			});
		});
	</script>
</body>
</html>