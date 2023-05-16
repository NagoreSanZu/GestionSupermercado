<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>INSERTAR UN PRODUCTO</h1>
<div class="alert alert-danger" role="alert">
ERROR</div>
<form method="post" action = "ControladorInsertarProducto">
	<p>codigo: <input type="text" name="codigo" size="40"></p>
	<p>nombre: <input type="text" name="nombre" size="40"></p>
	<p>cantidad: <input type="number" name="cantidad" size="40"></p>
	<p>precio: <input type="number" name="precio" size="40"></p>
	<p>caducidad <input type="date" name="caducidad" size="40"></p>
	
	<label>SECCION</label>
	  <select class="form-select" name="id_seccion">
	  
                <c:forEach items="${secciones}" var="seccion">
                    <option value="${seccion.id}">${seccion.nombre}</option>
                </c:forEach>
      </select>
	<br>
	<br>
	
	<br>
	<br>
	<input type=submit> 
	<button><a href='ControladorVerProductos'> VOLVER</a></button>
	
</form>

</body>
</html>