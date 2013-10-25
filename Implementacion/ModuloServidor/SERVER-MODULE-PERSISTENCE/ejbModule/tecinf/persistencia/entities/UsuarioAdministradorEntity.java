package tecinf.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="usuarios_administrador")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

@NamedQueries( {
	
	@NamedQuery(name = "UsuarioAdministradorEntity.findAll", 
			query = "SELECT e FROM UsuarioAdministradorEntity e ORDER BY e.usuario") ,
					
	@NamedQuery(name = "UsuarioAdministradorEntity.findById", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario = :id") ,
	
	@NamedQuery(name = "UsuarioAdministradorEntity.findByUserAndPassword", 
		query = "SELECT e FROM UsuarioClienteEntity e WHERE e.usuario = :id and e.contrasenia = :pwd") ,
})

public class UsuarioAdministradorEntity extends UsuarioEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
}
