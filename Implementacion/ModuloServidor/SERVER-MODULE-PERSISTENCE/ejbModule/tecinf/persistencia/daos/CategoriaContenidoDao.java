package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

public interface CategoriaContenidoDao extends Dao<Integer, CategoriaContenidoEntity> {
	
	public CategoriaContenidoEntity findByName(String name);
	
	public List<CategoriaContenidoEntity> findAllByState(Boolean estado);

}
