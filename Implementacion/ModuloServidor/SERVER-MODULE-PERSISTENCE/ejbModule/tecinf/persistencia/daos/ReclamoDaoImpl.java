package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ReclamoEntity;

@Stateless
public class ReclamoDaoImpl extends DaoImpl<Integer , ReclamoEntity> implements ReclamoDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<ReclamoEntity> findAll() {

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamoEntity> findAllByState(String estado) {
		Query namedQuery = em.createQuery("SELECT e FROM ReclamoEntity e WHERE e.estado = :estado");
		namedQuery.setParameter("estado", estado);
		return (List<ReclamoEntity>)namedQuery.getResultList();		
	}

}
