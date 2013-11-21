package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.ContenidoEntity;
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
	
	public UsuarioEntity findByUserAndPassword(String usr, String pwd) {
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findByUserAndPassword");
		namedQuery.setParameter("usr", usr);
		namedQuery.setParameter("pwd", pwd);
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
	public UsuarioEntity findByEmailAndPassword(String email, String pwd) {
		Query namedQuery = em.createNamedQuery("UsuarioEntity.findByEmailAndPassword");
		namedQuery.setParameter("email", email);
		namedQuery.setParameter("pwd", pwd);
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
		
	}
	
	public UsuarioEntity findByWebSite(String sitioWeb){
		Query namedQuery = em.createNamedQuery("UsuarioProveedorEntity.findByWebSite");
		namedQuery.setParameter("sitioWeb", sitioWeb);
		if (namedQuery.getResultList().size() > 0 )
			return (UsuarioEntity)namedQuery.getSingleResult();
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<UsuarioEntity> findAllByFiltros(String tipoUsuario, String nick, String email){
		String queryStr = "SELECT e FROM UsuarioEntity e "
				+ "WHERE (e.tipoUsuario = :tipoUsuario OR :tipoUsuario = 'todos') "
				+ " AND (:nick = '' OR  upper(e.usuario) like upper(:nick))"
				+ " AND (:email = '' OR upper(e.correoElectronico) like upper(:email) )";
		Query query = em.createQuery(queryStr);
		query.setParameter("tipoUsuario", tipoUsuario == null ? "" : tipoUsuario);
		query.setParameter("email", email == null ? "" : "%" + email + "%");
		query.setParameter("nick", nick == null ? "" : "%" + nick + "%");
		return (List<UsuarioEntity>)query.getResultList();
	}
	
}
