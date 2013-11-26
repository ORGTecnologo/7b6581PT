package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;

@Stateless
public class SubCategoriaContenidoDaoImpl extends DaoImpl<Integer, SubCategoriaContenidoEntity> implements SubCategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoriaContenidoEntity> findAll() {
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findAll");		
		return (List<SubCategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubCategoriaContenidoEntity> getAllByCategoria(Integer idCategoria){
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findByCategoria");
		namedQuery.setParameter("idCategoria", idCategoria);
		return (List<SubCategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubCategoriaContenidoEntity> findAllByState(Boolean estado){
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findAllByState");
		namedQuery.setParameter("habilitado", estado);
		return (List<SubCategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	public SubCategoriaContenidoEntity findByName(String name){
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findByName");
		namedQuery.setParameter("name", name);
		if (namedQuery.getResultList().size() > 0)
			return (SubCategoriaContenidoEntity)namedQuery.getSingleResult();
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SubCategoriaContenidoEntity> findAllByFiltros(Map filtros) {
		String queryString = "select e from SubCategoriaContenidoEntity e";
		
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
		
		return (List<SubCategoriaContenidoEntity>)query.getResultList();
	}
	
}