package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.ParametroValorEntity;

@Stateless
public class ParametroValorDaoImpl extends DaoImpl<String , ParametroValorEntity> implements ParametroValorDao {

	@PersistenceContext(unitName="SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<ParametroValorEntity> findAll() {		
		return null;
	}

}