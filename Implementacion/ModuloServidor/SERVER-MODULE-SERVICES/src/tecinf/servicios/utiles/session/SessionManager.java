package tecinf.servicios.utiles.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionManager { 
	
	private static SessionManager instance = null;
	
	private List<Session> listaSesiones = null;
	
	private SessionManager(){
		listaSesiones = new ArrayList<Session>();	
	}
	
	public static SessionManager getInstance(){
		if (instance == null)
			instance = new SessionManager();
		return instance;
	}
	
	public void addUserToSession(Session s){
		this.listaSesiones.add(s);
	}
	
	private void removeUserSession(Session s){
		this.listaSesiones.remove(s);
	}
	
	public synchronized Boolean checkUserInSession(String usr, String tkn, Date nw){
		
		for (Session s : this.listaSesiones){
			if (s.getUser().equals(usr) && s.getToken().equals(tkn)){
				if (nw.after(s.getTimeStamp()))
					return false;
				else
					return true;								
			}		
		}	
		return false;
	}

}
