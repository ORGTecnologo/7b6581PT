package tecinf.persistencia.daos;

import java.util.List;
import java.util.Map;

import tecinf.persistencia.entities.SubCategoriaContenidoEntity;

public interface SubCategoriaContenidoDao extends Dao<Integer, SubCategoriaContenidoEntity> {
	
	public List<SubCategoriaContenidoEntity> getAllByCategoria(Integer idCategoria);
	
	public SubCategoriaContenidoEntity findByName(String name);
	
	public List<SubCategoriaContenidoEntity> findAllByState(Boolean estado);
	
	public List<SubCategoriaContenidoEntity> findAllByFiltros(Map filtros);
}
