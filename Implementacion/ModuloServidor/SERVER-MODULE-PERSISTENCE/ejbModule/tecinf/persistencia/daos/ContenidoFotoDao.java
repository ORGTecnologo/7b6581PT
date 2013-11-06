package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.ContenidoFotoEntity;

public interface ContenidoFotoDao extends Dao<Integer, ContenidoFotoEntity> {
	
	public List<ContenidoFotoEntity> getAllByContenido(int idContenido);
	
}
