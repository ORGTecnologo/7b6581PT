package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenidoMinimalDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombreContenido;
	private List<String> listaFotos;
	private String precio;
	
	public ContenidoMinimalDataType(){
		listaFotos = new ArrayList<String>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreContenido() {
		return nombreContenido;
	}

	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}

	public List<String> getListaFotos() {
		return listaFotos;
	}

	public void setListaFotos(List<String> listaFotos) {
		this.listaFotos = listaFotos;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}	
}
