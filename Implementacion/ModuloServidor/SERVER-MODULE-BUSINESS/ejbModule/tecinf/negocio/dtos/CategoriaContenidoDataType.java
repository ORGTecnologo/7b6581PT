package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriaContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private Boolean habilitada;
	private String rutaImagen;
	private List<SubCategoriaContenidoDataType> subcategorias;
	
	public CategoriaContenidoDataType(){
		subcategorias = new ArrayList<SubCategoriaContenidoDataType>();
	}
		
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
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
	public List<SubCategoriaContenidoDataType> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<SubCategoriaContenidoDataType> subcategorias) {
		this.subcategorias = subcategorias;
	}
}
