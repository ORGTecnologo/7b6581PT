package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.VersionContenidoEntity;

@Stateless
public class VersionContenidoDaoImpl extends DaoImpl<Integer , VersionContenidoEntity> implements VersionContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<VersionContenidoEntity> findAll() {

		return null;
	}

}
