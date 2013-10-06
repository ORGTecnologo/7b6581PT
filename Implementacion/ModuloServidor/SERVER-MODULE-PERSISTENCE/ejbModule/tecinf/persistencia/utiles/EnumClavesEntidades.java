package tecinf.persistencia.utiles;

public class EnumClavesEntidades {
	
	public static final Integer AUDITORIA_OBJETO_USUARIO = 1;
	public static final Integer AUDITORIA_OBJETO_CONTENIDO = 2;
	
	public static final Integer AUDITORIA_OPERACION_LOGIN = 1;
	public static final Integer AUDITORIA_OPERACION_LOGOUT = 2;
	public static final Integer AUDITORIA_OPERACION_ALTA = 3;
	public static final Integer AUDITORIA_OPERACION_BAJA = 4;
	public static final Integer AUDITORIA_OPERACION_MODIFICACION = 5;
	public static final Integer AUDITORIA_OPERACION_PUBLICACION = 6;
	
	public static final String ESTADO_USUARIO_HABILITADO = "H";
	public static final String ESTADO_USUARIO_DESHABILITADO = "D";
	public static final String ESTADO_USUARIO_APROBADO = "A";
	public static final String ESTADO_USUARIO_RECHAZADO = "R";
	
	public static final Integer TIPO_REGISTRO_WEB = 1;
	public static final Integer TIPO_REGISTRO_FACEBOOK = 2;
	public static final Integer TIPO_REGISTRO_TWITTER = 3;
	
}
