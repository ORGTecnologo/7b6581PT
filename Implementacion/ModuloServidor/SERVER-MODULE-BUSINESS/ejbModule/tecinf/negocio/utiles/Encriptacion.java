package tecinf.negocio.utiles;

import java.security.MessageDigest;

public class Encriptacion {
	/**
	* Encripta un String con el algoritmo MD5.
	* @return El algoritmo encriptado
	* @param palabra
	*/
	public static String encriptarMD5(String palabra){
		String pe="";
		try {
			pe = hash(palabra);
		}
		catch (Exception e) {
		}
		return pe;
	}

	private static String hash(String clear) throws Exception {		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u<16){
				h.append("0"+Integer.toHexString(u));
			}
			else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	} 
	
	public static void main (String[] args){
		System.out.print(encriptarMD5("cliente"));		
	}
	
}