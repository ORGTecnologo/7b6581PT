package tecinf.negocio.utiles;

public class ValidationUtil {
	
	public static Boolean isNullOrEmpty(String str){
		return (str == null || str.equals(""));
	}
	
}
