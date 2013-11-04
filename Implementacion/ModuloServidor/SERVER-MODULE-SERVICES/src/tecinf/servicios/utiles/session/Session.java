package tecinf.servicios.utiles.session;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String token;
	private String userType;
	private Date timeStamp;	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
