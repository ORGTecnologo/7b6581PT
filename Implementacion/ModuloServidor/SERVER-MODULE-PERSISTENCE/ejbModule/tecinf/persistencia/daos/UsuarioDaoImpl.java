package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.UsuarioEntity;

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> findAll(){
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findAll");
		return (List<UsuarioEntity>)namedQuery.getResultList();	
	}
	
	
}
