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
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAllByType(String tipo){
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findAllByType");
		namedQuery.setParameter("tipo", tipo);
		return (List<UsuarioEntity>)namedQuery.getResultList();	
	}
	
	public UsuarioEntity findByMail(String mail){
		
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findByMail");
		namedQuery.setParameter("mail", mail);
		
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
	public UsuarioEntity findById(String id){
		
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findById");
		namedQuery.setParameter("id", id);
		
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
	public UsuarioEntity findByUserAndPassword(String usr, String pwd) {
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findByUserAndPassword");
		namedQuery.setParameter("usr", usr);
		namedQuery.setParameter("pwd", pwd);
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
}
