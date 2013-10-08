package Entities;

public class Session {
	
	private String NombreUsuario;
	private String Password;
	private int RecordarSession;
	
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getRecordarSession() {
		return RecordarSession;
	}
	public void setRecordarSession(int recordarSession) {
		RecordarSession = recordarSession;
	}
	
	

}
