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
		String filtrosQuery = "";
		String condicionTipo = "";
		String condicionKeyWord = "";
		
		if (filtros.size() > 0){
			filtrosQuery += " WHERE";
			
			/* FILTROS DE TIPO DE CONTENIDO */
			condicionTipo += " (";
			if (filtros.containsKey("libros"))
				condicionTipo += " e.tipoContenido = '" + (String)filtros.get("libros") + "' OR";
			if (filtros.containsKey("software"))
				condicionTipo += " e.tipoContenido = '" + (String)filtros.get("software") + "' OR";
			if (filtros.containsKey("temas"))
				condicionTipo += " e.tipoContenido = '" + (String)filtros.get("temas") + "' OR";
			if (filtros.containsKey("videos"))
				condicionTipo += " e.tipoContenido = '" + (String)filtros.get("videos") + "' OR";
			if (condicionTipo.length() > 1)
				condicionTipo = condicionTipo.substring(0 , condicionTipo.length() - 2) + ") AND";
			else
				condicionTipo = "";
			
			/* FILTROS POR PALABRA CLAVE */		
			if (filtros.containsKey("keyword"))
				condicionKeyWord += " ( (upper(e.nombre) like upper(:keywordN)) or (upper(e.descripcion) like upper(:keywordD)) ) AND";
			
			filtrosQuery += condicionTipo + condicionKeyWord;
		}
		
		if (!filtrosQuery.isEmpty())
			filtrosQuery = filtrosQuery.substring(0 , filtrosQuery.length() - 3);
		
		String completeQuery = queryStr + filtrosQuery;
		Query query = em.createQuery(completeQuery);
		
		if (filtros.containsKey("keyword")){
			query.setParameter("keywordN", (String)filtros.get("keyword"));
			query.setParameter("keywordD", (String)filtros.get("keyword"));
		}
			
			
		return (List<ContenidoEntity>)query.getResultList();
	}
	
}
