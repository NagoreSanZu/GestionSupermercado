package Modelo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelo.dto.ProductoSupermercado;

public class ModeloSupermercadoProducto extends Conector {

	public void insertarSupermercadoProducto(ProductoSupermercado ps) {
		PreparedStatement pstInsert;

		try {
			pstInsert = super.conexion.prepareStatement("INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?,?)");
			ModeloProducto modeloProducto = new ModeloProducto();
			ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
			
			pstInsert.setInt(1, ps.getProducto().getId() );
			pstInsert.setInt(2, ps.getSupermercado().getId() );
			pstInsert.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
