package tecinf.servicios.utiles;
 
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import sun.misc.*;
 
public class CripterDecripter {	
	
	private static String keyValue = "askjdlakdaasdasdasd";
 	
	//Encriptacion y desencriptacion usando algoritmo AES
	/*
	 public CripterDecripter(byte[] password) {
		 keyValue = password;
	 }
	 */
	 
	public static String encrypt(String cadena,String clave) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(clave); 
        return s.encrypt(cadena); 
    } 
 
    public static String encrypt(String cadena) { 
        return encrypt(cadena,keyValue); 
    }
    
    public static String decrypt(String cadena,String clave) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(clave); 
        String devuelve = ""; 
        try { 
            devuelve = s.decrypt(cadena); 
        } catch (Exception e) { 
        } 
        return devuelve; 
    } 
    public static String decrypt(String cadena) { 
        return decrypt(cadena,keyValue); 
    }
    
    public static String encryptURL(String cadena) { 
        String encrypt = encrypt(cadena,keyValue);
        String encode="";
        try {
            encode = URLEncoder.encode(encrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode; 
    }
	 
	 public static void main(String[] args){	 
		 try {
			 String ruta= "/ruta/extrania/a/archivo";
			 System.out.println("original: " + ruta);
			 String encRuta = encrypt(ruta);
			 System.out.println("cifrada: " + encRuta);
			 System.out.println("decifrada: " + decrypt(encRuta));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
 
}