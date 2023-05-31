package Modelo.dao;

import java.util.Comparator;

import Modelo.dto.Producto;

public class CompararPrecioDesc implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		if(o1.getPrecio()>o2.getPrecio()) {
			return 1;
		}
		
		return -1;
	}

}
