package Controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.dto.Producto;

/**
 * Servlet implementation class ControladorComprar
 */
@WebServlet("/ControladorComprar")
public class ControladorComprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorComprar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Producto producto = new Producto();
		HttpSession session = request.getSession();
		int idProducto = Integer.parseInt(request.getParameter("id"));
		ArrayList<Producto> productosComprados = (ArrayList<Producto>) session.getAttribute("productosComprados");
		producto.setId(idProducto);
		productosComprados.add(producto);
	
		session.setAttribute("productosComprados", productosComprados);
		response.sendRedirect("ControladorVerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
