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
	
	@SuppressWarnings("unchecked")
	public List<UsuarioDescargaContenidoEntity> getContentDonwloadsByState(Integer idContenido, String estado){
		Query namedQuery = em.createNamedQuery("UsuarioDescargaContenidoEntity.findAllByContenidoAndEstado");
		namedQuery.setParameter("idContenido", idContenido);
		namedQuery.setParameter("estado", estado);
		return (List<UsuarioDescargaContenidoEntity>)namedQuery.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioDescargaContenidoEntity> getDonwloadsByState(String estado){
		Query namedQuery = em.createNamedQuery("UsuarioDescargaContenidoEntity.findAllByEstado");
		namedQuery.setParameter("estado", estado);
		return (List<UsuarioDescargaContenidoEntity>)namedQuery.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioDescargaContenidoEntity> getDonwloadsByUserAndState(String usuario, String estado){
		Query namedQuery = em.createNamedQuery("UsuarioDescargaContenidoEntity.findAllByUsuarioAndEstado");
		namedQuery.setParameter("estado", estado);
		namedQuery.setParameter("usuario", usuario);
		return (List<UsuarioDescargaContenidoEntity>)namedQuery.getResultList();		
	}
	
	public UsuarioDescargaContenidoEntity getDownloadByUserAndContent(String usuario, Integer idContenido) {
		Query namedQuery = em.createNamedQuery("UsuarioDescargaContenidoEntity.findByUsuarioAndContenido");
		namedQuery.setParameter("idContenido", idContenido);
		namedQuery.setParameter("usuario", usuario);
		return (UsuarioDescargaContenidoEntity)namedQuery.getSingleResult();		
	}

}
