<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Productos</title>
</head>
<body>
<h1>VER PRODUCTOS</h1>
	<a href='ControladorInsertarProducto'class="btn btn-primary"> INSERTAR PRODUCTO</a>

<table class="table ">

<tr>
<th>ID</th>
<th>CODIGO</th>
<th>NOMBRE</th>
<th>CANTIDAD</th>
<th>PRECIO</th>
<th>CADUCIDAD</th>
<th>SECCION</th>

</tr>

<c:forEach items="${productos}" var="producto">
<tr>	
<th scope="row">${producto.id}</th>
<td>${producto.codigo}</td>
<td>${producto.nombre}</td>
<td>${producto.cantidad}</td>
<td>${producto.precio}</td>
<td>${producto.caducidad}</td>
<td>${producto.seccion.nombre}</td>

<td><a  class="btn btn-primary"href="ControladorModificarProducto?id=${producto.id}">MODIFICAR</a> </td>

</tr>

</c:forEach>

</table>
</body>
</html>