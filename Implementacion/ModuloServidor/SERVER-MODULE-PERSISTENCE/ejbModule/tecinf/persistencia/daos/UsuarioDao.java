package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioEntity;


public interface UsuarioDao extends Dao<String , UsuarioEntity> {
	
	public List<UsuarioEntity> findAll();
	
	public UsuarioEntity findById(String id);
	
	public UsuarioEntity findByMail(String mail);
	
}
