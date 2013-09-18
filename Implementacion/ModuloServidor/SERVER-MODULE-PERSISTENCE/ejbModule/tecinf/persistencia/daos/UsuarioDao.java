package tecinf.persistencia.daos;

import java.util.List;

import tecinf.persistencia.entities.UsuarioEntity;


public interface UsuarioDao {
	
	public List<UsuarioEntity> findAll();
	
}
