package Controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.dao.CompararAscDesc;
import Modelo.dao.CompararDESC;
import Modelo.dao.ModeloProducto;
import Modelo.dto.Producto;

/**
 * Servlet implementation class ControladorVerProductos
 */
@WebServlet("/ControladorVerProductos")
public class ControladorVerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorVerProductos() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModeloProducto modeloProducto = new ModeloProducto();

		ArrayList<Producto> productos = modeloProducto.verProductos();
		modeloProducto.cerrarConexion();

		String codOrdenar = request.getParameter("codOrdenar");
		if (codOrdenar == null) {
			codOrdenar = "ASC";
		}

		if (codOrdenar.equals("ASC")) {
			productos.sort(new CompararAscDesc());

		}

		if (codOrdenar.equals("DESC")) {
			productos.sort(new CompararDESC());

		}

		request.setAttribute("productos", productos);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModeloProducto modeloProducto = new ModeloProducto();

		ArrayList<Producto> productos = modeloProducto.verProductos();
		ArrayList<Producto> productoEncontrados = new ArrayList<Producto>();

		String submit = request.getParameter("submit");

		modeloProducto.cerrarConexion();
		if (submit.equals("nombreCodigo")) {
			String buscado = request.getParameter("buscador");
			for (Producto producto : productos) {
				if (producto.getCodigo().contains(buscado) || producto.getNombre().contains(buscado)) {
					productoEncontrados.add(producto);

				}
			}

			request.setAttribute("productos", productoEncontrados);
			request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
		}

		if (submit.equals("precio")) {
			// para diferenciar por precio
			Double precioMinimo = Double.parseDouble(request.getParameter("precioMinimo"));
			Double precioMaximo = Double.parseDouble(request.getParameter("precioMaximo"));

			ArrayList<Producto> productoPrecios = new ArrayList<Producto>();
			for (Producto producto : productos) {
				if (producto.getPrecio() > precioMinimo && producto.getPrecio() < precioMaximo) {
					productoPrecios.add(producto);

				}
			}

			request.setAttribute("productos", productoPrecios);
			request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

		}

	}

}
