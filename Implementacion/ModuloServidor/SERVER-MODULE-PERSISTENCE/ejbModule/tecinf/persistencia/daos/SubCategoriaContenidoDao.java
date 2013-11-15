package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.SubCategoriaContenidoEntity;

public interface SubCategoriaContenidoDao extends Dao<Integer, SubCategoriaContenidoEntity> {
	
	public List<SubCategoriaContenidoEntity> getAllByCategoria(Integer idCategoria);
	
	public SubCategoriaContenidoEntity findByName(String name);
	
}
