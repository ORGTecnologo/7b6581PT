package tecinf.negocio.dtos;

import java.io.Serializable;

public class AuditoriaDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fechaOperacion;
	private String usuario;
	private String operacion;
	private String objeto;
	private String objetoSistema;
	
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
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getObjetoSistema() {
		return objetoSistema;
	}
	public void setObjetoSistema(String objetoSistema) {
		this.objetoSistema = objetoSistema;
	}
	

}
