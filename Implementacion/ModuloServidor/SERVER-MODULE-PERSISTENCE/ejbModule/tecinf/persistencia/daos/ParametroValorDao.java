package tecinf.persistencia.daos;

import tecinf.persistencia.entities.ParametroValorEntity;

public interface ParametroValorDao extends Dao<String , ParametroValorEntity> {

	public ParametroValorEntity findById(String id);	

}