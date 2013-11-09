package tecinf.negocio.sesion;

import org.prevayler.Query;

import java.util.Date;

public class GetSession implements Query<SessionDataBase, Session> {

	private String identity;

	public GetSession(String identity) {
		this.identity = identity;
	}

	public Session query(SessionDataBase prevalentSystem, Date executionTime) throws Exception {
		return prevalentSystem.getSessions().get(identity);
	}
}
