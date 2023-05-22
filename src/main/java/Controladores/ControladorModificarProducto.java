package Controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.dao.ModeloProducto;
import Modelo.dao.ModeloSeccion;
import Modelo.dto.Producto;
import Modelo.dto.Seccion;

/**
 * Servlet implementation class ControladorModificarProducto
 */
@WebServlet("/ControladorModificarProducto")
public class ControladorModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorModificarProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloSeccion modeloSeccion = new ModeloSeccion();

		ArrayList<Seccion> secciones = modeloSeccion.getSecciones();

		
		Producto producto = new Producto();

		int id = Integer.parseInt(request.getParameter("id"));

		producto = modeloProducto.verProducto(id);
		request.setAttribute("secciones", secciones);
		request.setAttribute("producto", producto);
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Producto producto = new Producto();
		ModeloProducto modeloProducto = new ModeloProducto();
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));

		String caducidad = request.getParameter("caducidad");
		Date FechaCaducidad = null;
		try {
			FechaCaducidad = new SimpleDateFormat("yyyy-MM-dd").parse(caducidad);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));
		Boolean existe = modeloProducto.existeCodigo(codigo);
		Boolean comprobar = true;
//		if (existe == true) {
//			comprobar = false;
//			String mensaje = "Error, codigo ya existe ";
//			request.setAttribute("mensaje", mensaje);
//			request.setAttribute("comprobar", comprobar);
//			doGet(request, response);
//
//		}

		if (precio <= 0 && cantidad <= 0) {
			comprobar = false;
			String mensaje = "Error, cantida o precio igual o menor a cero";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("comprobar", comprobar);
			doGet(request, response);

		}

		if (id_seccion == 0) {
			comprobar = false;
			String mensaje = "Error, id seccion no puede ser igual a cero";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("comprobar", comprobar);
			doGet(request, response);

		}

		if (FechaCaducidad.before(new Date())) {
			comprobar = false;
			String mensaje = "Error, fecha mal insertada";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("comprobar", comprobar);
			doGet(request, response);

		}
		producto.setId(id);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(FechaCaducidad);
		producto.setSeccion(modeloSeccion.getSeccion(id_seccion));

		if (comprobar == true) {
		modeloProducto.modificarProducto(producto);
		response.sendRedirect("ControladorVerProductos");

		}

	}

}
