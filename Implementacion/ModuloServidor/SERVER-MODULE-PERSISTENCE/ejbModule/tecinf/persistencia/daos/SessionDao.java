package tecinf.persistencia.daos;

import tecinf.persistencia.entities.SessionEntity;

public interface SessionDao extends Dao<String, SessionEntity> {
	
	public SessionEntity findByUserAndToken(String usr, String tkn);

}