<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Operations</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class = "container">
		<h1 class="text-center">Student Directory</h1>
		<hr/>
		<div class = "row">
			<div class = "col-md-4">
				<form action = "${pageContext.request.contextPath}/StudentController" method="POST">
									
					<div class="form-group">
					  	<input type = "text" class = "form-control" name = "rno" placeholder = "Enter Roll No" value = "${student.rno}"/>
					</div>
					
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "name" placeholder = "Enter Name" value = "${student.name}"/>
					</div>
				
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "city" placeholder = "Enter city" value = "${student.city}"/>
					</div>
				
					<input type = "hidden" name = "id" value = "${student.id}"/>
				
					<button type = "submit" class = "btn btn-primary">Save</button>
				</form>
			</div>
		</div>
		<a href = "${pageContext.request.contextPath}/StudentController?action=LIST">Back to List</a>
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>