package Modelo.dto;

import java.util.Date;

public class Producto {

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", caducidad=" + caducidad + ", seccion=" + seccion + "]";
	}
	private int id;
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Date caducidad;
	private Seccion seccion;
	ProductoSupermercado ps;
	
	public ProductoSupermercado getPs() {
		return ps;
	}
	public void setPs(ProductoSupermercado ps) {
		this.ps = ps;
	}
	public Seccion getSeccion() {
		return seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Date getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	
	
	
}
