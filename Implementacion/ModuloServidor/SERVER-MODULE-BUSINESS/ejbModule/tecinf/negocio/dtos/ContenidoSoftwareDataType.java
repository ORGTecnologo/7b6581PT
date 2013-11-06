package tecinf.negocio.dtos;

import java.io.Serializable;

public class ContenidoSoftwareDataType extends ContenidoDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean esTrial;
	private String requisitosMinimos;
	
	public ContenidoSoftwareDataType(){
		super();
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
