package tecinf.persistencia.daos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tecinf.persistencia.entities.AuditoriaEntity;

public interface AuditoriaDao extends Dao<Integer, AuditoriaEntity> {

	public List<AuditoriaEntity> getHistoryByOperation(Integer idOperacion);
	
	public List<AuditoriaEntity> getAccesosByFiltos(Map filtros);
	
}