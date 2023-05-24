<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>INSERTAR UN PRODUCTO</h1>



<c:if test="${comprobar==false}">
	<div class="alert alert-danger" role="alert">${mensaje}</div>
	
</c:if>
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
	

<c:forEach items="${supermercados }" var="supermercado">
<input class="form-check-input" type="checkbox" value="${supermercado.id }" id="flexCheckIndeterminateDisabled" name="supermercados" >
<label class="form-check-label" for="flexCheckIndeterminateDisabled">
   ${supermercado.nombre}</label>
</c:forEach>	

<br>
<br>



	<input type=submit> 
	<a class="btn btn-primary" href='ControladorVerProductos'> VOLVER</a>
</form>

</body>
</html>