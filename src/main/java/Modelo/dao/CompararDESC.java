package Modelo.dao;

import java.util.Comparator;

import Modelo.dto.Producto;

public class CompararDESC implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		return o2.getCodigo().compareTo(o1.getCodigo());
	}

}
