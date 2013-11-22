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
		String condicionCategorias = "";
		
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
			
			/* FILTROS POR CATEGORIAS */
			if (filtros.containsKey("categorias")){
				condicionCategorias = " (";
				Integer x;
				String[] categorias = ((String)filtros.get("categorias")).split("\\|");
				for (x = 0; x < categorias.length; x++){
					condicionCategorias += " e.subcategoria.categoria.id = :cat_" + x.toString() + " OR";
				}
				condicionCategorias = condicionCategorias.substring(0 , condicionCategorias.length()-2) + ") AND";
			}
			
			
			filtrosQuery += condicionTipo + condicionKeyWord + condicionCategorias;
		}
		
		if (!filtrosQuery.isEmpty())
			filtrosQuery = filtrosQuery.substring(0 , filtrosQuery.length() - 3);
		
		String completeQuery = queryStr + filtrosQuery;
		Query query = em.createQuery(completeQuery);
		
		//Seteo la palabra clave en caso de corresponder
		if (filtros.containsKey("keyword")){
			query.setParameter("keywordN", (String)filtros.get("keyword"));
			query.setParameter("keywordD", (String)filtros.get("keyword"));
		}
		
		//Seteo las categorias en caso de corresponder
		if (filtros.containsKey("categorias")){
			Integer x;
			String[] categorias = ((String)filtros.get("categorias")).split("\\|");
			for (x = 0; x < categorias.length; x++){
				query.setParameter("cat_" + x.toString(), Integer.valueOf(categorias[x]));
			}
		}
			
		return (List<ContenidoEntity>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContenidoEntity> findTopContents(Integer cantidad, String tipo) {		
		String queryStr = "SELECT E from ContenidoEntity e WHERE e.tipoContenido = :tipoUsuario ORDER BY e.cantidadDescargas";		
		Query query = em.createQuery(queryStr);
		query.setParameter("tipoUsuario", tipo);
		return (List<ContenidoEntity>)query.setMaxResults(cantidad).getResultList();
	}
	
}
