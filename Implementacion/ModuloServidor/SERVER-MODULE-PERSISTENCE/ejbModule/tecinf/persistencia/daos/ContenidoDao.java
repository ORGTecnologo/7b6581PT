package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import tecinf.persistencia.entities.ContenidoEntity;

public interface ContenidoDao extends Dao<Integer, ContenidoEntity> {
	
	
	public List<ContenidoEntity> findAll();
	
	public List<ContenidoEntity> findByFiltros(Map<String, Object> filtros);
	
	public List<ContenidoEntity> findTopContents(Integer cantidad, String tipo);	
	
}
