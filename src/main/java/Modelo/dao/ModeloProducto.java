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
	
	
	public Boolean existeCodigo(String codigo) {
		String sentenciaSelect = "SELECT codigo FROM productos WHERE codigo=?";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);
			pstSelect.setString(1, codigo);
			ResultSet resultado = pstSelect.executeQuery();
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			Producto producto = new Producto();

			resultado.next();

		
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	//insertar
	
	public void insertarProducto (Producto producto) {
		PreparedStatement pstInsert;
		try {
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			pstInsert=super.conexion.prepareStatement("INSERT INTO productos ( codigo, nombre, cantidad, precio,caducidad,id_seccion )VALUES (?,?,?,?,?,?)")	;
			
			pstInsert.setString(1, producto.getCodigo());
			pstInsert.setString(2, producto.getNombre());
			pstInsert.setInt(3, producto.getCantidad());
			pstInsert.setDouble(4, producto.getPrecio());
			pstInsert.setDate(5, new Date(producto.getCaducidad().getTime()));
			pstInsert.setInt(6, producto.getSeccion().getId());
			pstInsert.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}// fin clase
