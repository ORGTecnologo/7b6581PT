package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

public interface CategoriaContenidoDao extends Dao<Integer, CategoriaContenidoEntity> {
	
	public CategoriaContenidoEntity findByName(String name);
	
	public List<CategoriaContenidoEntity> findAllByState(Boolean estado);
	
	public List<CategoriaContenidoEntity> findAllByFiltros(Map filtros);

}
