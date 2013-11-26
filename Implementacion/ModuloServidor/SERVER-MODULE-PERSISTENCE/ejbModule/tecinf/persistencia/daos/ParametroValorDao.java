package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import tecinf.persistencia.entities.ParametroValorEntity;

public interface ParametroValorDao extends Dao<String , ParametroValorEntity> {
	
	public List<ParametroValorEntity> findAllByFiltros(@SuppressWarnings("rawtypes") Map filtros);
	
}