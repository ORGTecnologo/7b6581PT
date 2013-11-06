package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contenido_software")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ContenidoSoftwareEntity extends ContenidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="fk_contenido",unique=true,nullable=false)
	private ContenidoEntity contenido;	
	
	@Column(name = "es_trial", nullable = false)
	private Boolean esTrial;
	
	@Column(name = "requisitos_minimos", length=1000, nullable = true)
	private String requisitosMinimos;

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
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