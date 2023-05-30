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
import Modelo.dao.ModeloSupermercado;
import Modelo.dao.ModeloSupermercadoProducto;
import Modelo.dto.Producto;
import Modelo.dto.ProductoSupermercado;
import Modelo.dto.Seccion;
import Modelo.dto.Supermercado;

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
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
		ModeloSupermercadoProducto ps = new ModeloSupermercadoProducto();

		ArrayList<Seccion> secciones = modeloSeccion.getSecciones();

		
		Producto producto = new Producto();

		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Supermercado> supermercados = modeloSupermercado.getsupermercados();

		producto = modeloProducto.verProducto(id);
		
		ArrayList<ProductoSupermercado> productosSuper = ps.getProductosSuperId(id);
		request.setAttribute("supermercados", supermercados);
		request.setAttribute("productosSuper", productosSuper);
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

		producto.setId(id);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(FechaCaducidad);
		producto.setSeccion(modeloSeccion.getSeccion(id_seccion));


		modeloProducto.modificarProducto(producto);
		
		ModeloSupermercadoProducto ModeloSP = new ModeloSupermercadoProducto();
		String[] supermercadosP = request.getParameterValues("supermercados");
		int idUltimo = modeloProducto.ultimoProducto();
		Producto producto2 = modeloProducto.verProducto(idUltimo);
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
		
		for (int i = 0; i < supermercadosP.length; i++) {
			ProductoSupermercado ps = new ProductoSupermercado();
			ps.setProducto(producto2);
			ps.setSupermercado(modeloSupermercado.getsupermercado(Integer.parseInt(supermercadosP[i])));
			ModeloSP.actualizarSupermercadoProducto(ps);
		}

		response.sendRedirect("ControladorVerProductos");

	}

}
