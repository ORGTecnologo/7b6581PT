package tecinf.persistencia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

public class CategoriaContenidoDaoImpl extends DaoImpl<Integer, CategoriaContenidoEntity> implements CategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<CategoriaContenidoEntity> findAll() {
		return null;
	}
}
