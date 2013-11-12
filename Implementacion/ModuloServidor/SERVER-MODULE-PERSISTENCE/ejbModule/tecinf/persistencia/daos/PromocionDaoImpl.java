package tecinf.persistencia.daos;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.PromocionEntity;

@Stateless
public class PromocionDaoImpl extends DaoImpl<Integer , PromocionEntity> implements PromocionDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<PromocionEntity> findAll() {

		return null;
	}
	
	public PromocionEntity getPromocionVigenteAFechaPorContenido(Integer idContenido, Date fecha){
		
		Query namedQuery = em.createNamedQuery("PromocionEntity.findPromocionVigenteAFechaPorContenido");
		namedQuery.setParameter("idContenido", idContenido);
		namedQuery.setParameter("fecha", fecha);		
		if (namedQuery.getResultList().size() == 1 )
			return (PromocionEntity)namedQuery.getSingleResult();
		
		return null;
		
	}

}
