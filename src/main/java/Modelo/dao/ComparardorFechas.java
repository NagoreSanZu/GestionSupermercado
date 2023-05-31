package Modelo.dao;

import java.util.Comparator;

import Modelo.dto.Producto;

public class ComparardorFechas implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		
		if(o1.getCaducidad().before(o2.getCaducidad())) {
			return 1;
		}
		
		return -1;
	}


}
