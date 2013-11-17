package tecinf.persistencia.daos;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;


public abstract class DaoImpl<K, E> implements Dao<K, E>{

	private static Logger logger = Logger.getLogger(DaoImpl.class);
	
	protected Class<E> entityClass;

	@PersistenceContext
	private EntityManager em;

	public void flush() {
		em.flush();
	}

	@SuppressWarnings("unchecked")
	public DaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];		
	}

	public void persist(E entity) {
		em.persist(entity);		
	}

	public void merge(E entity) {
		em.merge(entity);
	}

	public void remove(E entity) {
		em.remove(entity);
	}

	public E findByID(K id) {
		return em.find(entityClass, id);
	}

	public void rollBack(){
		em.getTransaction().rollback();
	}
	
	public Boolean contains(E entity){
		return em.contains(entity);
	}


}