package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecinf.persistencia.entities.SubCategoriaContenidoEntity;

@Stateless
public class SubCategoriaContenidoDaoImpl extends DaoImpl<Integer, SubCategoriaContenidoEntity> implements SubCategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoriaContenidoEntity> findAll() {
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findAll");
		return (List<SubCategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubCategoriaContenidoEntity> getAllByCategoria(Integer idCategoria){
		Query namedQuery = em.createNamedQuery("SubCategoriaContenidoEntity.findByCategoria");
		namedQuery.setParameter("idCategoria", idCategoria);
		return (List<SubCategoriaContenidoEntity>)namedQuery.getResultList();
	}
	
}