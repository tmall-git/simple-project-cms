<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<form action="doLogin" method="POST">
		<div>
			<input type="text" name="userName">
		</div>
		<div>
			<input type="text" name="password">
		</div>
		<div>
			<input type="submit" value="提交" />
		</div>
	</form>
</body>
</html>