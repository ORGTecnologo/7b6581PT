package com.example.controller;

import Entities.Session;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName="demoDB";
	static final String SessionTable="Session";
	static final String NombreUsuario="NombreUsuario";
	static final String Password="Password";
	static final String RecordarSession="RecordarSession";
	static final String IdSession="IdSession";
	
//	static final String deptTable="Dept";
//	static final String colDeptID="DeptID";
//	static final String colDeptName="DeptName";
	
	static final String viewEmps="ViewEmps";
	
	
	public DatabaseHelper(Context context) {
		super(context, dbName, null,33);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE "+SessionTable+" ("+IdSession+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				NombreUsuario+" TEXT, "+Password+" Text, "+RecordarSession+" INTEGER NOT NULL );");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
//		db.execSQL("DROP TABLE IF EXISTS "+employeeTable);
//		db.execSQL("DROP TABLE IF EXISTS "+deptTable);
//		
//		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
//		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
//		db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
//		db.execSQL("DROP VIEW IF EXISTS "+viewEmps);
//		onCreate(db);
	}

	 
	 void InsertSession(Session s)
	 {
	 	SQLiteDatabase db= this.getWritableDatabase();
	 
	 	ContentValues cv=new ContentValues();
		cv.put(NombreUsuario, s.getNombreUsuario());
		cv.put(Password, s.getPassword());
		cv.put(RecordarSession, 1);
		db.insert(SessionTable, IdSession, cv);
	
	 }
	 
	 public Cursor GetSession(String nomUsuario, String contrasenia)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 
		 String[] params=new String[]{nomUsuario};
		 Cursor c=db.rawQuery("SELECT * FROM"+ SessionTable+" WHERE "+NombreUsuario+"=?",params);
		
		 return c;
	 }
	 

	 

}
