package tecinf.negocio.sesion;

import org.prevayler.TransactionWithQuery;

import java.io.Serializable;
import java.util.Date;

public class DeleteSession implements TransactionWithQuery<SessionDataBase, Session>, Serializable {

  /**
   * java.io.Serializable with a non changing serialVersionUID
   * will automatically handle backwards compatibility
   * if you add new non transient fields the the class.
   **/
  private static final long serialVersionUID = 1l;

  private String identity;

  public DeleteSession() {
  }

  public DeleteSession(String identity) {
    this.identity = identity;
  }

  public Session executeAndQuery(SessionDataBase prevalentSystem, Date executionTime) throws Exception {
    return prevalentSystem.getSessions().remove(identity);
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }
}
