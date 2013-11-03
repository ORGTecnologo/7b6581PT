package tecinf.negocio.dtos;

import java.io.Serializable;

public class AuditoriaDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fechaOperacion;
	private String usuario;
	private String operacion;
	
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
