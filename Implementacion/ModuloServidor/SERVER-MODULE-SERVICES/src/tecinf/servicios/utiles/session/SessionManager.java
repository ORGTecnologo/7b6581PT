package tecinf.servicios.utiles.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SessionManager { 
	
	private static SessionManager instance = null;	
	private List<Session> listaSesiones = null;
	public static final Integer timeOut = 30;
	
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
	
	public synchronized Boolean checkUserInSession(String usr, String tkn, Date nw){		
		executeSessionsRefresh();		
		for (Session s : this.listaSesiones){
			if (s.getUser().equals(usr) && s.getToken().equals(tkn)){
				if (nw.after(s.getTimeStamp()))
					return false;
				else {
					updateTimeStamp(s,timeOut);
					return true;
				}
			}		
		}	
		return false;
	}
	
	private synchronized void executeSessionsRefresh(){
		Date now = new Date();
		List<Session> listaSesionesAEliminar = new ArrayList<Session>();
		for (Session s : this.listaSesiones){
			if (now.after(s.getTimeStamp()))
				listaSesionesAEliminar.add(s);
		}
		for (Session s : listaSesionesAEliminar)
			this.listaSesiones.remove(s);
	}
	
	public void updateTimeStamp(Session s, Integer to){
		Calendar cal = Calendar.getInstance();
		cal.setTime(s.getTimeStamp());
		cal.add(Calendar.MINUTE, to);
		s.setTimeStamp(cal.getTime());		
	}

}
