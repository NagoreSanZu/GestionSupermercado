package Controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ArrayList<Producto>productoEncontrados= new ArrayList<Producto>();
		
		modeloProducto.cerrarConexion();
		
		
		String buscado = request.getParameter("buscador");
		for (Producto producto : productos) {
			if(producto.getCodigo().contains(buscado)) {
				productoEncontrados.add(producto);
					
			}
		}
		
		request.setAttribute("productos", productoEncontrados);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

		

	}

}
