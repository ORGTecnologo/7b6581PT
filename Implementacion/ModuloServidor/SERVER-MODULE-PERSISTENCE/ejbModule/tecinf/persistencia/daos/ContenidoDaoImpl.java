package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ContenidoEntity;

@Stateless
public class ContenidoDaoImpl extends DaoImpl<Integer, ContenidoEntity> implements ContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<ContenidoEntity> findAll() {
		Query namedQuery = em.createNamedQuery("ContenidoEntity.findAll");
		return (List<ContenidoEntity>)namedQuery.getResultList();
	}
}
