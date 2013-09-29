package tecinf.persistencia.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="auditoria")
public class AuditoriaEntity {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =	"auditoria_secuencia") 
	@SequenceGenerator(name = "auditoria_secuencia", sequenceName = "auditoria_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="fecha_operacion")
	private Date fechaOperacion;
	
	@OneToOne
	@JoinColumn(name="id_usuario",nullable=false)
	private UsuarioEntity usuario;
	
	@OneToOne
	@JoinColumn(name="id_operacion",nullable=false)
	private AuditoriaOperacion operacion;
	
	
}
