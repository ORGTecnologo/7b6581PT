package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries( {
	
	@NamedQuery(name = "SessionEntity.findAll", 
		query = "SELECT e FROM SessionEntity e") ,	
		
	@NamedQuery(name = "SessionEntity.findByUserAndToken", 
		query = "SELECT e FROM SessionEntity e WHERE e.usuario = :usr and e.token = :tkn") ,	
})


@Entity
@Table(name="sesiones")
public class SessionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private String usuario;
	
	@Column(nullable=false)
	private String token;
	
	@Column(nullable=false)
	private Date timeStamp;
	
	@Column(nullable=false)
	private String tipoUsuario;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
		
	
}
