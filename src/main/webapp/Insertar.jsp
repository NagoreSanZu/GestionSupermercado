<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>INSERTAR UN PRODUCTO</h1>

<form method="post" action = "ControladorInsertarProducto">
	<p>codigo: <input type="text" name="codigo" size="40"></p>
	<p>nombre: <input type="text" name="nombre" size="40"></p>
	<p>cantidad: <input type="number" name="cantidad" size="40"></p>
	<p>precio: <input type="number" name="precio" size="40"></p>
	<p>caducidad <input type="date" name="caducidad" size="40"></p>

	<br>
	<br>
	
	<br>
	<br>
	<input type=submit> 
	<button><a href='ControladorVerProductos'> VOLVER</a></button>
	
</form>

</body>
</html>