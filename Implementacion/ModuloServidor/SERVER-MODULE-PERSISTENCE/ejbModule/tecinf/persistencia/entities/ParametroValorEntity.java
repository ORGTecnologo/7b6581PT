package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="parametros")

@NamedQueries( {
	
	@NamedQuery(name = "ParametroValorEntity.findAll", 
			query = "SELECT e FROM ParametroValorEntity e ORDER BY e.nombreParametro") ,
					
	@NamedQuery(name = "ParametroValorEntity.findById", 
		query = "SELECT e FROM ParametroValorEntity e WHERE e.nombreParametro = :id") ,
})

public class ParametroValorEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="nombre_parametro",length=20)
	private String nombreParametro;
	
	@Column(name="valor_parametro",length=500)
	private String valorParametro;

	public String getNombreParametro() {
		return nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	
}
