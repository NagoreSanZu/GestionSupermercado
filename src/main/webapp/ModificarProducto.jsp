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
<title>ModificarProducto</title>
</head>
<body>

	<h1>MODIFICAR UN PRODUCTO</h1>



	<c:if test="${comprobar==false}">
		<div class="alert alert-danger" role="alert">${mensaje}</div>

	</c:if>

	<c:set var="producto" value="${ requestScope.producto }" />
	<form method="post" action="ControladorModificarProducto">
		<input type="hidden" name="id" value="${producto.id}">
		<p>
			codigo: <input type="text" name="codigo" size="40"
				value="${producto.codigo}">
		</p>
		<p>
			nombre: <input type="text" name="nombre" size="40"
				value="${producto.nombre }">
		</p>
		<p>
			cantidad: <input type="number" name="cantidad" size="40"
				value="${producto.cantidad }">
		</p>
		<p>
			precio: <input type="number" name="precio" size="40"
				value="${producto.precio}">
		</p>
		<p>
			caducidad <input type="date" name="caducidad" size="40"
				value="${producto.caducidad}">
		</p>

		<label>SECCION</label> <select class="form-select" name="id_seccion">


			<c:forEach items="${secciones}" var="seccion">
				<c:if test="${ producto.seccion.id == seccion.id }">
					<option value="${producto.seccion.id}" selected="selected">${producto.seccion.nombre}</option>
				</c:if>
				<c:if test="${ producto.seccion.id != seccion.id }">
					<option value="${seccion.id}">${seccion.nombre}</option>
				</c:if>
			</c:forEach>
		</select>


		<c:forEach items="${supermercados }" var="supermercado">
			<c:forEach items="${productosSuper }" var="ps">
				<c:if test="${supermercado.id == ps.supermercado.id }">
					<input class="form-check-input"  type="checkbox"
						value="${supermercado.id }"   id="flexCheckIndeterminateDisabled"
						name="supermercados" checked>
					<label class="form-check-label"
						for="flexCheckIndeterminateDisabled" >
						${supermercado.nombre}</label>
				</c:if>

			</c:forEach>
			<input class="form-check-input" type="checkbox"
				value="${supermercado.id }" id="flexCheckIndeterminateDisabled"
				name="supermercados">
			<label class="form-check-label" for="flexCheckIndeterminateDisabled">
				${supermercado.nombre}</label>
		</c:forEach>



		<br> <br> <br> <br> <input type=submit> <a
			class="btn btn-primary" href='ControladorVerProductos'> VOLVER</a>
	</form>

</body>
</html>