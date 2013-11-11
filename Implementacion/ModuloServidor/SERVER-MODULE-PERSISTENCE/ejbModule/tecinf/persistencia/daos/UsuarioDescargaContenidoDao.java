package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;

public interface UsuarioDescargaContenidoDao extends Dao<Integer, UsuarioDescargaContenidoEntity> {
	
	public List<UsuarioDescargaContenidoEntity> getContentDownloads(Integer idContenido);
	
}
