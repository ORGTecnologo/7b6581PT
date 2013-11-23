package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioDescargaContenidoEntity;

public interface UsuarioDescargaContenidoDao extends Dao<Integer, UsuarioDescargaContenidoEntity> {
	
	public List<UsuarioDescargaContenidoEntity> getContentDownloads(Integer idContenido);
	
	public List<UsuarioDescargaContenidoEntity> getContentDonwloadsByState(Integer idContenido, String estado);
	
	public List<UsuarioDescargaContenidoEntity> getDonwloadsByState(String estado);
	
	public List<UsuarioDescargaContenidoEntity> getDonwloadsByUserAndState(String usuario, String estado);
	
	public UsuarioDescargaContenidoEntity getDownloadByUserAndContent(String usuario, Integer idContenido);
	
}
