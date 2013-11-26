package tecinf.persistencia.daos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.AuditoriaEntity;

@Stateless
public class AuditoriaDaoImpl extends DaoImpl<Integer, AuditoriaEntity> implements AuditoriaDao{

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<AuditoriaEntity> findAll() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoriaEntity> getHistoryByOperation(Integer idOperacion){
		Query namedQuery = em.createNamedQuery("AuditoriaEntity.findByOperacion");
		namedQuery.setParameter("idOperacion", idOperacion);
		return (List<AuditoriaEntity>)namedQuery.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<AuditoriaEntity> getAccesosByFiltos(Map filtros) {
		String queryString = "SELECT e FROM AuditoriaEntity e";
		String conds = "";
		if (filtros.containsKey("usuario"))
			conds += " (upper(e.usuario.usuario) like upper(:usuario)) AND";
		if (filtros.containsKey("desde"))
			conds += " (e.fechaOperacion >= :desde) AND";
		if (filtros.containsKey("hasta"))
			conds += " (e.fechaOperacion <= :hasta) AND";
		queryString = queryString + (conds.isEmpty() ? "" : " WHERE " + conds.substring(0 , conds.length() - 3));
		Query query = em.createQuery(queryString);
		
		if (filtros.containsKey("usuario"))
			query.setParameter("usuario",  "%" + filtros.get("usuario") + "%");
		if (filtros.containsKey("desde"))
			query.setParameter("desde", filtros.get("desde"));
		if (filtros.containsKey("hasta"))
			query.setParameter("hasta", filtros.get("hasta"));
		return (List<AuditoriaEntity>)query.getResultList();
	}
	
}