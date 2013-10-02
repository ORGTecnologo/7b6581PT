package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.UsuarioClienteEntity;

@Stateless
public class UsuarioClienteDaoImpl extends DaoImpl<String , UsuarioClienteEntity> implements UsuarioClienteDao {
	
	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<UsuarioClienteEntity> findAll() { 
		
		
		
		return null;
	}

	@Override
	public UsuarioClienteEntity findById(String id) {
		
		return null;
	}

	@Override
	public UsuarioClienteEntity findByUserAndPassword(String id, String pwd) {
		Query namedQuery = em.createNamedQuery("UsuarioClienteEntity.findByUserAndPassword");
		namedQuery.setParameter("id", id);
		namedQuery.setParameter("pwd", pwd);	
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioClienteEntity)namedQuery.getSingleResult();
		return null;
	}



}
