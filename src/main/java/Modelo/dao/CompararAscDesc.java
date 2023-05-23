package Modelo.dao;

import java.util.ArrayList;
import java.util.Comparator;

import Modelo.dto.Producto;

public class CompararAscDesc implements Comparator <Producto> {
	ModeloProducto modeloProducto = new ModeloProducto();
	ArrayList<Producto>productos = modeloProducto.verProductos();
	@Override
	public int compare(Producto o1, Producto o2) {
		
	
		
		return o1.getCodigo().compareTo(o2.getCodigo());
	}

	
	
}
