package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;

@Stateless
public class UsuarioDescargaContenidoDaoImpl  extends DaoImpl<Integer, UsuarioDescargaContenidoEntity> implements UsuarioDescargaContenidoDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;
	
	@Override
	public List<UsuarioDescargaContenidoEntity> findAll() {

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioDescargaContenidoEntity> getContentDownloads(Integer idContenido){
		
		Query namedQuery = em.createNamedQuery("UsuarioDescargaContenidoEntity.findAllByContenido");
		namedQuery.setParameter("idContenido", idContenido);
		return (List<UsuarioDescargaContenidoEntity>)namedQuery.getResultList();		
		
	}

}
