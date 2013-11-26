package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ParametroValorEntity> findAllByFiltros(Map filtros) {	
		String queryString = "select e from ParametroValorEntity e";
		if (filtros.containsKey("nombre"))
			queryString += " WHERE (upper(e.nombreParametro) like upper(:nombre))";
		
		Query query = em.createQuery(queryString);	
		if (filtros.containsKey("nombre"))
			query.setParameter("nombre", "%" + filtros.get("nombre") + "%");
			
		return (List<ParametroValorEntity>)query.getResultList();
	}

}