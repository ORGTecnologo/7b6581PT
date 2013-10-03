package tecinf.persistencia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.AuditoriaObjetoEntity;

public class AuditoriaObjetoDaoImpl extends DaoImpl<Integer, AuditoriaObjetoEntity> implements AuditoriaObjetoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<AuditoriaObjetoEntity> findAll() {
		return null;
	}
}
