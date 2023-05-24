package Modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.dto.Producto;

public class ModeloProducto extends Conector {

	// para ver todso los producto
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
	
	
	//ver un producto
	
	public Producto verProducto(int id) {
		String sentenciaSelect = "SELECT * FROM productos WHERE id=?";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);
			pstSelect.setInt(1, id);
			ResultSet resultado = pstSelect.executeQuery();
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			resultado.next(); 
				Producto producto = new Producto();

				producto.setId(resultado.getInt("id"));
				producto.setCodigo(resultado.getString("codigo"));
				producto.setNombre(resultado.getString("nombre"));
				producto.setCantidad(resultado.getInt("cantidad"));
				producto.setPrecio(resultado.getDouble("precio"));
				producto.setCaducidad(resultado.getDate("caducidad"));
				producto.setSeccion(modeloSeccion.getSeccion(resultado.getInt("id_seccion")));
			
			return producto;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public int ultimoProducto() {
		String sentenciaSelect = "SELECT max(id) FROM productos ";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);
		
			ResultSet resultado = pstSelect.executeQuery();
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			resultado.next(); 
				Producto producto = new Producto();

				producto.setId(resultado.getInt("max(id)"));
				
			return producto.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}


	// si exist el codigo, te devuelve un boolean
	public Boolean existeCodigo(String codigo) {
		String sentenciaSelect = "SELECT codigo FROM productos WHERE codigo=?";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);
			pstSelect.setString(1, codigo);
			ResultSet resultado = pstSelect.executeQuery();
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			Producto producto = new Producto();

			resultado.next();

			return resultado.getString("codigo").equals(codigo)? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// insertar un producto

	public void insertarProducto(Producto producto) {
		PreparedStatement pstInsert;
		try {
			ModeloSeccion modeloSeccion = new ModeloSeccion();
			pstInsert = super.conexion.prepareStatement(
					"INSERT INTO productos ( codigo, nombre, cantidad, precio,caducidad,id_seccion )VALUES (?,?,?,?,?,?)");

			pstInsert.setString(1, producto.getCodigo());
			pstInsert.setString(2, producto.getNombre());
			pstInsert.setInt(3, producto.getCantidad());
			pstInsert.setDouble(4, producto.getPrecio());
			pstInsert.setDate(5, new Date(producto.getCaducidad().getTime()));
			pstInsert.setInt(6, producto.getSeccion().getId());
			pstInsert.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// modificar producto
	public void modificarProducto(Producto producto) {
		PreparedStatement pstUpdate;

		try {
			pstUpdate = super.conexion.prepareStatement(
					"UPDATE productos SET codigo=?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion=? WHERE id =?");

			pstUpdate.setString(1, producto.getCodigo());
			pstUpdate.setString(2, producto.getNombre());
			pstUpdate.setInt(3, producto.getCantidad());
			pstUpdate.setDouble(4, producto.getPrecio());
			pstUpdate.setDate(5, new Date(producto.getCaducidad().getTime()));
			pstUpdate.setInt(6, producto.getSeccion().getId());
			pstUpdate.setInt(7, producto.getId());
			pstUpdate.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void modificarCantidadProducto(Producto producto) {
		PreparedStatement pstUpdate;

		try {
			pstUpdate = super.conexion.prepareStatement(
					"UPDATE productos SET cantidad=? WHERE id =?");

			pstUpdate.setInt(1, producto.getCantidad());
			pstUpdate.setInt(2, producto.getId());
			pstUpdate.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void eliminarProducto(int id) {
		PreparedStatement pstEliminar;
		
		try {
			pstEliminar = super.conexion.prepareStatement("DELETE FROM productos WHERE id=?");
			pstEliminar.setInt(1, id);
			pstEliminar.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}// fin clase
