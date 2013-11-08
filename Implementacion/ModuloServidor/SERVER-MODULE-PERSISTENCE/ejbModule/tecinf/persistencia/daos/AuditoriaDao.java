package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.AuditoriaEntity;

public interface AuditoriaDao extends Dao<Integer, AuditoriaEntity> {

	public List<AuditoriaEntity> getHistoryByOperation(Integer idOperacion);
	
}