package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ParametroValorEntity;

@Stateless
public class ParametroValorDaoImpl extends DaoImpl<String , ParametroValorEntity> implements ParametroValorDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroValorEntity> findAll() {		
		Query query = em.createNamedQuery("ParametroValorEntity.findAll");
		return (List<ParametroValorEntity>)query.getResultList();
	}

}