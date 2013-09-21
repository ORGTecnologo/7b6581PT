package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.RolEntity;

@Stateless
public class RolDaoImpl extends DaoImpl<Integer,RolEntity> implements RolDao {
	
	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	public RolEntity findById(Integer id) {
			
		Query namedQuery = em.createNamedQuery("RolEntity.findById");
		namedQuery.setParameter("id", id);
		
		if (namedQuery.getResultList().size() > 0 )
			return (RolEntity)namedQuery.getSingleResult();
		
		return null;
		
	}

	@Override
	public List<RolEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
