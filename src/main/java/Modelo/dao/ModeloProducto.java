package Modelo.dao;

import java.sql.Date;
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
	
	//insertar
	
	public void insertarProducto (Producto producto) {
		PreparedStatement pstInsert;
		try {
			pstInsert=super.conexion.prepareStatement("INSERT INTO productos ( codigo, nombre, cantidad, precio,caducidad)")	;
			
			pstInsert.setInt(1, producto.getId());
			pstInsert.setString(2, producto.getCodigo());
			pstInsert.setString(3, producto.getNombre());
			pstInsert.setInt(4, producto.getCantidad());
			pstInsert.setDouble(5, producto.getPrecio());
			pstInsert.setDate(6, new Date(producto.getCaducidad().getTime()));
			pstInsert.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}// fin clase
