package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.AuditoriaEntity;

@Stateless
public class AuditoriaDaoImpl extends DaoImpl<Integer, AuditoriaEntity> implements AuditoriaDao{

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<AuditoriaEntity> findAll() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoriaEntity> getHistoryByOperation(Integer idOperacion){
		Query namedQuery = em.createNamedQuery("AuditoriaEntity.findByOperacion");
		namedQuery.setParameter("idOperacion", idOperacion);
		return (List<AuditoriaEntity>)namedQuery.getResultList();		
	}
	
}