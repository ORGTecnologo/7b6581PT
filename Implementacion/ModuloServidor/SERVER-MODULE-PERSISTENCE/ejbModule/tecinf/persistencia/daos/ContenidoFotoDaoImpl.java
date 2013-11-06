package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ContenidoFotoEntity;

@Stateless
public class ContenidoFotoDaoImpl extends DaoImpl<Integer, ContenidoFotoEntity> implements ContenidoFotoDao {
	
	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<ContenidoFotoEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ContenidoFotoEntity> getAllByContenido(int idContenido){		
		Query namedQuery = em.createNamedQuery("ContenidoFotoEntity.findByContenido");
		namedQuery.setParameter("idContenido", idContenido);
		return (List<ContenidoFotoEntity>)namedQuery.getResultList();
	}

}
