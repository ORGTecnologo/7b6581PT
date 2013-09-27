package tecinf.persistencia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ParametroValorEntity;

public class ParametroValorDaoImpl extends DaoImpl<String , ParametroValorEntity> implements ParametroValorDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<ParametroValorEntity> findAll() {		
		return null;
	}

	@Override
	public ParametroValorEntity findById(String id) {
		Query namedQuery = em.createNamedQuery("ParametroValorEntity.findById");
		namedQuery.setParameter("id", id);		
		if (namedQuery.getResultList().size() > 0 )
			return (ParametroValorEntity)namedQuery.getSingleResult();
		return null;
	}
	
	

}
