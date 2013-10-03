package tecinf.persistencia.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.SubCategoriaContenidoEntity;

public class SubCategoriaContenidoDaoImpl extends DaoImpl<Integer, SubCategoriaContenidoEntity> implements SubCategoriaContenidoDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<SubCategoriaContenidoEntity> findAll() {
		return null;
	}
}