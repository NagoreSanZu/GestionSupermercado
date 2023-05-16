<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>INSERTAR UN PRODUCTO</h1>

<div class="alert alert-danger" role="alert">

<c:if test="${comprobar==false}">
	${mensaje}
</c:if>
</div>
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