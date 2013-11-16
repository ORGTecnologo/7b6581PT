package tecinf.negocio.dtos;

import java.io.Serializable;

public class SubCategoriaContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private String rutaImagen;
	private Boolean habilitada;
	private CategoriaContenidoDataType categoria;
	
	public SubCategoriaContenidoDataType(){
		categoria = new CategoriaContenidoDataType();
	}	
	
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
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public CategoriaContenidoDataType getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaContenidoDataType categoria) {
		this.categoria = categoria;
	}	
	public String getHabilitadaStr(){
		if (this.habilitada == null)
			return "";
		else if (this.habilitada == true)
			return "SI";
		else 
			return "NO";
	}
	
	public String getCaptionBtnHabilitarDeshabilitar(){
		return (this.habilitada == null || this.habilitada == false ? "Habilitar" : "Deshabilitar");		
	}
	
}
