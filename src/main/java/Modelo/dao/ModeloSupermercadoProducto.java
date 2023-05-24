package Modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.dto.Producto;
import Modelo.dto.ProductoSupermercado;

public class ModeloSupermercadoProducto extends Conector {

	public void insertarSupermercadoProducto(ProductoSupermercado ps) {
		PreparedStatement pstInsert;

		try {
			pstInsert = super.conexion.prepareStatement(
					"INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?,?)");
			ModeloProducto modeloProducto = new ModeloProducto();
			ModeloSupermercado modeloSupermercado = new ModeloSupermercado();

			pstInsert.setInt(1, ps.getProducto().getId());
			pstInsert.setInt(2, ps.getSupermercado().getId());
			pstInsert.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarSupermercadoPro(int id) {
		PreparedStatement pstEliminar;

		try {
			pstEliminar = super.conexion.prepareStatement("DELETE FROM productos_supermercados WHERE id_producto=? ");
			pstEliminar.setInt(1, id);
			pstEliminar.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProductoSupermercado> verProductosSuper() {
		String sentenciaSelect = "SELECT * FROM productos_supermercados";

		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(sentenciaSelect);
			ModeloProducto modeloProducto = new ModeloProducto();
			ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
			ArrayList<ProductoSupermercado> productosSuper = new ArrayList<ProductoSupermercado>();
			ResultSet resultado = pstSelect.executeQuery();
			
			while (resultado.next()) {
				ProductoSupermercado productoSuper = new ProductoSupermercado();
				
				productoSuper.setId(resultado.getInt("id"));
				productoSuper.setProducto(modeloProducto.verProducto(resultado.getInt("id_producto")));
				productoSuper.setSupermercado(modeloSupermercado.getsupermercado(resultado.getInt("id_supermercado")));
				productosSuper.add(productoSuper);

			
			}
			return productosSuper;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
