package tecinf.persistencia.daos;

import tecinf.persistencia.entities.RolEntity;

public interface RolDao extends Dao<Integer , RolEntity> {
	
	public RolEntity findById(Integer id);
	
}
