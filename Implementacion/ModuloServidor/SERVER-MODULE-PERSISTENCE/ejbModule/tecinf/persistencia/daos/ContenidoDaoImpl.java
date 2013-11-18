package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

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
	
	@SuppressWarnings("unchecked")
	public List<ContenidoEntity> findByFiltros(Map<String, Object> filtros){	
			
		String queryStr = "SELECT e FROM ContenidoEntity e";		
		/*
		if (filtros.size() > 0){
			queryStr += " WHERE";
			
			if (filtros.containsKey("libros"))
				;
			
			
		
		}
		*/
		Query query = em.createQuery(queryStr);		
		return (List<ContenidoEntity>)query.getResultList();
	}
	
}
