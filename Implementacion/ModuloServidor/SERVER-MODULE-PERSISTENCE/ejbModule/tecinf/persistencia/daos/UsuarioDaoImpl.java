package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.UsuarioEntity;

@Stateless
public class UsuarioDaoImpl extends DaoImpl<String , UsuarioEntity> implements UsuarioDao {
	
	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAll(){
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findAll");
		return (List<UsuarioEntity>)namedQuery.getResultList();	
	}
	
	public UsuarioEntity findById(String id){
		
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findById");
		namedQuery.setParameter("id", id);
		
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
	public void persist(UsuarioEntity e){
		em.persist(e);
	}
	
	public void remove(UsuarioEntity e){
		em.remove(e);
	}
	
	public void merge(UsuarioEntity e){
		em.merge(e);
	}
	
	
}
