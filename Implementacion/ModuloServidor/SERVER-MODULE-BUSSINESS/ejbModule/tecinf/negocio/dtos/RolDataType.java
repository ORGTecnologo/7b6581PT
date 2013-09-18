package tecinf.negocio.dtos;

import java.io.Serializable;

public class RolDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descripcion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
}
