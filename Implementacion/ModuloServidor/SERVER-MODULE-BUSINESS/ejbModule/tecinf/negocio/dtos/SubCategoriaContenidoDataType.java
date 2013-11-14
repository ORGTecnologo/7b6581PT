package tecinf.negocio.dtos;

import java.io.Serializable;

public class SubCategoriaContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private String rutaImagen;
	
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
