package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.SessionEntity;

@Stateless
public class SessionDaoImpl extends DaoImpl<String, SessionEntity> implements SessionDao{

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SessionEntity> findAll() {
		Query namedQuery = em.createNamedQuery("SessionEntity.findAll");
		return (List<SessionEntity>)namedQuery.getResultList();
	}
	
	public SessionEntity findByUserAndToken(String usr, String tkn){
		Query namedQuery = em.createNamedQuery("SessionEntity.findByUserAndToken");
		namedQuery.setParameter("usr",usr);
		namedQuery.setParameter("tkn",tkn);
		if (namedQuery.getResultList().size() > 0)
			return (SessionEntity)namedQuery.getSingleResult();
		return null;
	}
	
	@Override
	public void remove(SessionEntity s){
		em.remove(em.find(SessionEntity.class , s.getUsuario()));
	}
	
}