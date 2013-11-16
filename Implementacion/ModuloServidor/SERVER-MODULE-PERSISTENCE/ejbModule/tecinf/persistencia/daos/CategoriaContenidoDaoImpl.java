package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

@Stateless
public class CategoriaContenidoDaoImpl extends DaoImpl<Integer, CategoriaContenidoEntity> implements CategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaContenidoEntity> findAll() {
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findAll");
		return (List<CategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaContenidoEntity> findAllByState(Boolean estado) {
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findAllByState");
		namedQuery.setParameter("habilitado", estado);
		return (List<CategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	public CategoriaContenidoEntity findByName(String name){
		Query namedQuery = em.createNamedQuery("CategoriaContenidoEntity.findByName");
		namedQuery.setParameter("name", name);
		if (namedQuery.getResultList().size() > 0)
			return (CategoriaContenidoEntity)namedQuery.getSingleResult();
		return null;
	}
	
}
