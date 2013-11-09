package tecinf.negocio.dtos;

import java.io.Serializable;

public class ContenidoSoftwareDataType extends ContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean esTrial;
	private String requisitosMinimos;
	private String desarrollador;
	
	public ContenidoSoftwareDataType(){
		super();
	}
	
	
	public String getDesarrollador() {
		return desarrollador;
	}


	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}


	public Boolean getEsTrial() {
		return esTrial;
	}
	public void setEsTrial(Boolean esTrial) {
		this.esTrial = esTrial;
	}
	public String getRequisitosMinimos() {
		return requisitosMinimos;
	}
	public void setRequisitosMinimos(String requisitosMinimos) {
		this.requisitosMinimos = requisitosMinimos;
	}
	

}
