package tecinf.persistencia.daos;

import tecinf.persistencia.entities.CategoriaContenidoEntity;

public interface CategoriaContenidoDao extends Dao<Integer, CategoriaContenidoEntity> {
	
	public CategoriaContenidoEntity findByName(String name);

}
