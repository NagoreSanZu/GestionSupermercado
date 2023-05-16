package Modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.dto.Seccion;

public class ModeloSeccion extends Conector {

	public Seccion getSeccion(int id) {
		String senteciaSelect = "SELECT *FROM secciones WHERE id=?";

		try {

			PreparedStatement pstSelect = super.conexion.prepareStatement(senteciaSelect);
			pstSelect.setInt(1, id);

			ResultSet resultado = pstSelect.executeQuery();
			resultado.next();

			Seccion seccion = new Seccion();
			
			seccion.setId(resultado.getInt("id"));
			seccion.setNombre(resultado.getString("nombre"));
			
			return seccion;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public ArrayList<Seccion> getSecciones (){
		String senteciaSelect = "SELECT *FROM secciones";
		
		try {

			PreparedStatement pstSelect = super.conexion.prepareStatement(senteciaSelect);

			ResultSet resultado = pstSelect.executeQuery();
			ArrayList<Seccion>secciones = new ArrayList<Seccion>();
			while(resultado.next()) {

			Seccion seccion = new Seccion();
			
			seccion.setId(resultado.getInt("id"));
			seccion.setNombre(resultado.getString("nombre"));
			secciones.add(seccion);
			}
			return secciones;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

		
		
	}
}
