package tecinf.persistencia.daos;

import tecinf.persistencia.entities.UsuarioClienteEntity;

public interface UsuarioClienteDao extends Dao<String , UsuarioClienteEntity>  {
	
	public UsuarioClienteEntity findByUserAndPassword(String id, String pwd); 
	
}
