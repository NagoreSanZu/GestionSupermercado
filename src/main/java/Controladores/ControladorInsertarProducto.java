package Controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.dao.ModeloProducto;
import Modelo.dto.Producto;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Insertar.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Producto producto = new Producto();
		ModeloProducto modeloProducto = new ModeloProducto();
		
		String codigo = request.getParameter("codigo");
		String nombre= request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		
		String caducidad = request.getParameter("caducidad");
		Date Fechacaducidad = null;
		try {
			Fechacaducidad = new SimpleDateFormat("yyyy-MM-dd").parse(caducidad);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setCaducidad(Fechacaducidad);
	
		modeloProducto.insertarProducto(producto);
		
		response.sendRedirect("ControladorVerProductos");
	
	}

}
