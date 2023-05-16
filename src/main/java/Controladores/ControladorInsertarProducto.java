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
 * Servlet implementation class ControladorInsertarProducto
 */
@WebServlet("/ControladorInsertarProducto")
public class ControladorInsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorInsertarProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModeloSeccion modeloSeccion = new ModeloSeccion();

		ArrayList<Seccion> secciones = modeloSeccion.getSecciones();

		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("Insertar.jsp").forward(request, response);

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
		System.out.println(codigo);
		Boolean existe = modeloProducto.existeCodigo(codigo);
		System.out.println(existe);
		Boolean comprobar = true;
		if (existe == true) {
			comprobar = false;
			String mensaje = "Error, codigo ya existe ";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("comprobar", comprobar);
			doGet(request, response);

		}

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
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(FechaCaducidad);
		producto.setSeccion(modeloSeccion.getSeccion(id_seccion));

		if (comprobar == true) {
			modeloProducto.insertarProducto(producto);
			response.sendRedirect("ControladorVerProductos");

		}

	}

}
