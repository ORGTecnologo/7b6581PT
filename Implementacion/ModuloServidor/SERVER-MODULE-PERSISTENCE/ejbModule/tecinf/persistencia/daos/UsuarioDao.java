package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioEntity;


public interface UsuarioDao extends Dao<String , UsuarioEntity> {
	
	public List<UsuarioEntity> findAll();
	
	public List<UsuarioEntity> findAllByType(String tipo);	
	
	public UsuarioEntity findByUserAndPassword(String usr, String pwd);
	
	public UsuarioEntity findByEmailAndPassword(String email, String pwd);
	
	public UsuarioEntity findByMail(String mail);
	
	public UsuarioEntity findByWebSite(String sitioWeb);
	
	public List<UsuarioEntity> findAllByFiltros(String tipoUsuario, String nick, String email);
	
}
