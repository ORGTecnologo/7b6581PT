package com.example.controller;

public class HandlerView {

	private String IniciarSesion;
	private String activityFullScreen;
	
	
	public HandlerView(){
		IniciarSesion = "ini_sesion";
		activityFullScreen = "activity_fullscreen";
	}


	public String getIniciarSesion() {
		return IniciarSesion;
	}


	public void setIniciarSesion(String iniciarSesion) {
		IniciarSesion = iniciarSesion;
	}


	public String getActivityFullScreen() {
		return activityFullScreen;
	}


	public void setActivityFullScreen(String activityFullScreen) {
		this.activityFullScreen = activityFullScreen;
	}
	
	
}
