package Modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import Modelo.dto.Supermercado;

public class ModeloSupermercado extends Conector {

	public ArrayList<Supermercado>getsupermercados(){
		String senteciaSelect = "SELECT * FROM supermercados";
		
		try {
			
			PreparedStatement pstSelect = super.conexion.prepareStatement(senteciaSelect);
			ResultSet resultado = pstSelect.executeQuery();
			ArrayList<Supermercado> supermercados = new ArrayList <Supermercado>();
			while(resultado.next()) {
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(resultado.getInt("id"));
				supermercado.setNombre(resultado.getString("nombre"));
				supermercados.add(supermercado);
			}
			return supermercados;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	public Supermercado getsupermercado(int id){
		String senteciaSelect = "SELECT * FROM supermercados WHERE id=?";
		
		try {
			PreparedStatement pstSelect = super.conexion.prepareStatement(senteciaSelect);

			pstSelect.setInt(1, id);

			ResultSet resultado = pstSelect.executeQuery();
			resultado.next();
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(resultado.getInt("id"));
				supermercado.setNombre(resultado.getString("nombre"));
			
			return supermercado;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}
