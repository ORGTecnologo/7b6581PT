package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_software")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoSoftwareEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "es_trial", nullable = false)
	private Boolean esTrial;
	
	@Column(name = "requisitos_minimos", length=1000, nullable = true)
	private String requisitosMinimos;

	@Column(name = "desarrollador", length=100, nullable = true)
	private String desarrollador;
	
	public ContenidoSoftwareEntity(){
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