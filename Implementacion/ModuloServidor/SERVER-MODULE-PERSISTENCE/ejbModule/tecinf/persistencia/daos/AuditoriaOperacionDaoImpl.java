package tecinf.persistencia.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tecinf.persistencia.entities.AuditoriaOperacionEntity;

@Stateless
public class AuditoriaOperacionDaoImpl extends DaoImpl<Integer, AuditoriaOperacionEntity> implements AuditoriaOperacionDao {

	@PersistenceContext(unitName = "SERVER-MODULE-PERSISTENCE")
	EntityManager em;

	@Override
	public List<AuditoriaOperacionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
