package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;

public interface UsuarioDescargaContenidoDao extends Dao<Integer, UsuarioDescargaContenidoEntity> {
	
	public List<UsuarioDescargaContenidoEntity> getContentDownloads(Integer idContenido);
	
	public List<UsuarioDescargaContenidoEntity> getContentDonwloadsByState(Integer idContenido, Boolean validada);
	
	public List<UsuarioDescargaContenidoEntity> getDonwloadsByState(Boolean validada);
	
}
