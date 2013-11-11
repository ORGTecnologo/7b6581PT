package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.ContenidoEntity;

public interface ContenidoDao extends Dao<Integer, ContenidoEntity> {
	
	
	public List<ContenidoEntity> findAll();
	
}
