package Modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.dto.Producto;

public class ModeloProducto extends Conector {

	public ArrayList<Producto> verProductos() {
		String sentenciaSelect = "SELECT * FROM productos";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);

			ArrayList<Producto> productos = new ArrayList<Producto>();
			ResultSet resultado = pstSelect.executeQuery();
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			while (resultado.next()) {
				Producto producto = new Producto();

				producto.setId(resultado.getInt("id"));
				producto.setCodigo(resultado.getString("codigo"));
				producto.setNombre(resultado.getString("nombre"));
				producto.setCantidad(resultado.getInt("cantidad"));
				producto.setPrecio(resultado.getDouble("precio"));
				producto.setCaducidad(resultado.getDate("caducidad"));
				producto.setSeccion(modeloSeccion.getSeccion(resultado.getInt("id_seccion")));
				productos.add(producto);
			}
			return productos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}// fin clase
