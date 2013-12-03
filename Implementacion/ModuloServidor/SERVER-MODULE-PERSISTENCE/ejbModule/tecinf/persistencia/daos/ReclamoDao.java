package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.ReclamoEntity;

public interface ReclamoDao extends Dao<Integer , ReclamoEntity> {
	
	public List<ReclamoEntity> findAllByState(String estado);
	
}
