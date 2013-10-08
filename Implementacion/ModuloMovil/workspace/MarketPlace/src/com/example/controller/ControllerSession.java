package com.example.controller;

public class ControllerSession {
	
	private static ControllerSession instance;
	
	private ControllerSession (){
		
	}
	
	public static ControllerSession getInstance(){
		
		if(instance==null){
			instance= new ControllerSession();
		}
		
		return instance;
	}
	

}
