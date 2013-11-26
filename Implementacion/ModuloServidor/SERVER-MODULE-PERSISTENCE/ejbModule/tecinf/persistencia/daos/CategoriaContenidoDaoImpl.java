package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

@Stateless
public class CategoriaContenidoDaoImpl extends DaoImpl<Integer, CategoriaContenidoEntity> implements CategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaContenidoEntity> findAll() {
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findAll");
		return (List<CategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaContenidoEntity> findAllByState(Boolean estado) {
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findAllByState");
		namedQuery.setParameter("habilitado", estado);
		return (List<CategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	public CategoriaContenidoEntity findByName(String name){
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findByName");
		namedQuery.setParameter("name", name);
		if (namedQuery.getResultList().size() > 0)
			return (CategoriaContenidoEntity)namedQuery.getSingleResult();
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CategoriaContenidoEntity> findAllByFiltros(Map filtros) {
		String queryString = "select e from CategoriaContenidoEntity e";
		
		String conds = "";
		if (filtros.containsKey("nombre"))
			conds += " (upper(e.nombre) like upper(:nombre)) AND";
		if (filtros.containsKey("descripcion"))
			conds += " (upper(e.descripcion) like upper(:descripcion)) AND";
		if (filtros.containsKey("habilitada")) 
			conds += " (e.habilitado = :habilitada)) AND";
		queryString = queryString + (conds.isEmpty() ? "" : " WHERE " + conds.substring(0 , conds.length() - 3));
		Query query = em.createQuery(queryString);
		
		if (filtros.containsKey("nombre"))
			query.setParameter("nombre", "%" + filtros.get("nombre") + "%");
		if (filtros.containsKey("descripcion"))
			query.setParameter("descripcion", "%" + filtros.get("descripcion") + "%");
		if (filtros.containsKey("habilitada"))
			query.setParameter("habilitada", filtros.get("habilitada"));
		
		return (List<CategoriaContenidoEntity>)query.getResultList();
	}
	
}
