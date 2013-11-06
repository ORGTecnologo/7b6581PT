package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ContenidoDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer idContenido;
	protected String nombreContenido;
	protected String descripcionContenido;
	protected Integer cantidadDescargas;
	protected Integer tamanioContenido;
	protected String urlArchivoContenido;
	protected List<String> fotos;
	
	public ContenidoDataType(){
		fotos = new ArrayList<String>();
		cantidadDescargas = 0;
	}
	
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public String getNombreContenido() {
		return nombreContenido;
	}
	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}
	public String getDescripcionContenido() {
		return descripcionContenido;
	}
	public void setDescripcionContenido(String descripcionContenido) {
		this.descripcionContenido = descripcionContenido;
	}
	public Integer getCantidadDescargas() {
		return cantidadDescargas;
	}
	public void setCantidadDescargas(Integer cantidadDescargas) {
		this.cantidadDescargas = cantidadDescargas;
	}
	public Integer getTamanioContenido() {
		return tamanioContenido;
	}
	public void setTamanioContenido(Integer tamanioContenido) {
		this.tamanioContenido = tamanioContenido;
	}

	public String getUrlArchivoContenido() {
		return urlArchivoContenido;
	}

	public void setUrlArchivoContenido(String urlArchivoContenido) {
		this.urlArchivoContenido = urlArchivoContenido;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}
	
		
}
