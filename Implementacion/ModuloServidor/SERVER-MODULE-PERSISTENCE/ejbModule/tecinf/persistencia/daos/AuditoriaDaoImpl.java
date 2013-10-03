package tecinf.persistencia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.AuditoriaEntity;

public class AuditoriaDaoImpl extends DaoImpl<Integer, AuditoriaEntity> implements AuditoriaDao{

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<AuditoriaEntity> findAll() {
		return null;
	}
	
}