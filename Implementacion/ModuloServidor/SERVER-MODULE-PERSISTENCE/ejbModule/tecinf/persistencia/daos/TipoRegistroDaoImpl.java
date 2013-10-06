package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.TipoRegistroEntity;

@Stateless
public class TipoRegistroDaoImpl extends DaoImpl<Integer, TipoRegistroEntity> implements TipoRegistroDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<TipoRegistroEntity> findAll() {
		return null;
	}
}