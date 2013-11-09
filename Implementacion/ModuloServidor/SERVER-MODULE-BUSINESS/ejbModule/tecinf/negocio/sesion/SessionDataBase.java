package tecinf.negocio.sesion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SessionDataBase implements Serializable {

	  /**
	   * java.io.Serializable with a non changing serialVersionUID
	   * will automatically handle backwards compatibility
	   * if you add new non transient fields the the class.
	   **/
	  private static final long serialVersionUID = 1l;
	
	  private Map<String, Session> sessions = new HashMap<String, Session>();
	
	
	  public Map<String, Session> getSessions() {
	    return sessions;
	  }
	
	  public void setSessions(Map<String, Session> sessions) {
	    this.sessions = sessions;
	  }
}
