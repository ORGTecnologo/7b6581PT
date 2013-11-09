package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.Date;


public class ContenidoLibroDataType extends ContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date fechaPublicacion;	
	private String autor;
	private Integer cantidadPaginas;
	
	public ContenidoLibroDataType(){
		super();
	}
	
	public Integer getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(Integer cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
