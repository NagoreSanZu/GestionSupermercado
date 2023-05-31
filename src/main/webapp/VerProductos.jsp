<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	
	<script src="https://kit.fontawesome.com/5b4d884f86.js" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Productos</title>
</head>
<body>
	<h1>VER PRODUCTOS</h1>
	<div><i class="fa-solid fa-cart-shopping" style="color: #005eff;"></i> ${productosComprados.size() }</div>
	<a href='ControladorInsertarProducto' class="btn btn-primary">
		INSERTAR PRODUCTO</a>

	<!-- BUSCADOR -->
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" method="post"
			action="ControladorVerProductos">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search" name="buscador">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				name="submit" value="nombreCodigo">Search</button>
		</form>
	</nav>
	<!-- FIN BUSCADOR -->
	<!-- ELIMINAR -->

	<form action="ControladorEliminarProducto" method="post">
		<input class="form-control mr-sm-2" type="text"
			placeholder="Elimiar por codigo" aria-label="text"
			name="EliminarPorCodigos"> 
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
			name="submit" value="eliminarBuscador">Eliminar</button>
	</form>

	<!-- FIN ELIMINAR -->



	<!-- ORDEN POR PRECIO -->
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" method="post"
			action="ControladorVerProductos">
			<input type="number" placeholder="Precio Minimo" name="precioMinimo">
			<input type="number" placeholder="Precio Maximo" name="precioMaximo">

			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				name="submit" value="precio">Search</button>
		</form>
	</nav>

	<!-- FIN ORDEN POR PRECIO -->

	<!-- FILTRO POR abc-->
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" method="post"
			action="ControladorVerProductos">
			<input type="text" placeholder="primera letra" name="PrimeraLetra">
			<input type="text" placeholder="Segunda Letra " name="SegundaLetra">

			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				name="submit" value="abcOrdenar">Search</button>
		</form>
	</nav>
	<!-- filtro abc fin -->

	<!-- TABLA -->
	
			<form method ="post" action ="ControladorEliminarProducto">
			
				<a  href="ControladorEliminarProducto?id=${producto.id}"><button class="btn btn-outline-success my-2 my-sm-0" type="submit"
				name="submit" value="eliminar">ELIMINAR</button></a>
			
	
			<table class="table ">
		<tr>
			<th>
			<th>ID</th>

			<th>CODIGO <a href="ControladorVerProductos?codOrdenar=ASC">ASC</a>
				<a href="ControladorVerProductos?codOrdenar=DESC">DESC</a>
			</th>
			<th>NOMBRE</th>
			<th>CANTIDAD</th>
			<th>PRECIO  <a href="ControladorVerProductos?ordenarPrecio=DESC">DESC</a>
			 <a href="ControladorVerProductos?ordenarPrecio=ASC">ASC</a>
			</th>
			<th>CADUCIDAD <a href="ControladorVerProductos?fechaComparar=DESC">DESC</a> 
			<a href="ControladorVerProductos?fechaComparar=ASC">ASC</a></th>
			<th>SECCION</th>

		</tr>

		<c:forEach items="${productos}" var="producto">
			<tr>
		
				<th><input class="form-check-input" type="checkbox" value="${producto.id }" id="flexCheckIndeterminateDisabled" name="borrarProductos"  ></th>
				<th scope="row">${producto.id}</th>
				<td> ${producto.codigo}</td>
				<td>${producto.nombre}</td>
				<td>${producto.cantidad}</td>
				<td>${producto.precio}</td>
				<td>${producto.caducidad}</td>
				<td>${producto.seccion.nombre}</td>

				<td><a class="btn btn-primary"
					href="ControladorModificarProducto?id=${producto.id}">MODIFICAR</a>
				</td>

				<td><a class="btn btn-danger"
					href="ControladorEliminarProducto?id=${producto.id}">ELIMINAR</a></td>
				
				<td><a class="btn btn-primary"
					href="ControladorComprar?id=${producto.id}">COMPRAR</a></td>

			</tr>

		</c:forEach>

	</table>
			
	</form>
	
	

	
	<!-- FIN TABLA -->

</body>
</html>