package Controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.dao.ModeloProducto;
import Modelo.dao.ModeloSupermercadoProducto;
import Modelo.dto.Producto;
import Modelo.dto.ProductoSupermercado;

/**
 * Servlet implementation class ControladorEliminarProducto
 */
@WebServlet("/ControladorEliminarProducto")
public class ControladorEliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorEliminarProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModeloProducto modeloProducto = new ModeloProducto();
		int id = Integer.parseInt(request.getParameter("id"));
		Producto producto = modeloProducto.verProducto(id);

		int cantidad = 0;
		if (producto.getCantidad() > 0) {
			cantidad = producto.getCantidad() - 1;
			producto.setCantidad(cantidad);
			modeloProducto.modificarCantidadProducto(producto);
		}

		if (producto.getCantidad() == 0) {
			System.out.println(producto.getCantidad());
			ModeloSupermercadoProducto modeloSP = new ModeloSupermercadoProducto();

			ArrayList<ProductoSupermercado> sps = modeloSP.verProductosSuper();
			for (ProductoSupermercado productoSupermercado : sps) {

				if (productoSupermercado.getProducto().getId() == producto.getId()) {
					System.out.println(producto.getId());
					modeloSP.eliminarSupermercadoPro(producto.getId());
				}
				if (productoSupermercado.getProducto().getId() != producto.getId()) {
					modeloProducto.eliminarProducto(producto.getId());
				}
			}
		}

		request.getRequestDispatcher("ControladorVerProductos").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModeloProducto modeloProducto = new ModeloProducto();

		String eliminar = request.getParameter("submit");

		if (eliminar.equals("eliminar")) {
			String[] borrarProductos = request.getParameterValues("borrarProductos");

			for (int i = 0; i < borrarProductos.length; i++) {
				modeloProducto.eliminarProducto(Integer.parseInt(borrarProductos[i]));
			}

		} else if (eliminar.equals("eliminarBuscador")) {
			String codigos = request.getParameter("EliminarPorCodigos");
			String[] productoCodigo = codigos.split(",");
			Boolean existe = true;
			for (int i = 0; i < productoCodigo.length; i++) {
				if (modeloProducto.existeCodigo(productoCodigo[i]) == false) {
					existe = false;
				}

			}

			if (existe == true) {
				for (int i = 0; i < productoCodigo.length; i++) {
					modeloProducto.eliminarProductoCodigo(productoCodigo[i]);

				}
			}

		}

		response.sendRedirect("ControladorVerProductos");

	}

}
