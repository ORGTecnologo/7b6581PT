package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.EstadoUsuarioEntity;

@Stateless
public class EstadoUsuarioDaoImpl extends DaoImpl<String, EstadoUsuarioEntity> implements EstadoUsuarioDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<EstadoUsuarioEntity> findAll() {
		return null;
	}
}
