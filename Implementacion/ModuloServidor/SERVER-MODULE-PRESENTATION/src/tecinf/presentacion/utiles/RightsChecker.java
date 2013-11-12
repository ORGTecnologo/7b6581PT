package tecinf.presentacion.utiles;

import tecinf.negocio.dtos.UserSession;
import tecinf.negocio.utiles.EnumTipoUsuario;

public class RightsChecker {
	
	public void checkAdminRights(UserSession session) throws Exception {	
		if (session == null)
			throw new Exception("USUARIO_NO_AUTENTICADO");
		if (session.getTipoUsuario() == null || session.getUsuario() == null)
			throw new Exception("SESION_INVALIDA");
		if (session.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_ADMINISTRADOR))
			throw new Exception("OPERACION_RESTRINGIDA_A_ADMINISTRADORES");		
	}
	
	public void checkSupplierRights(UserSession session) throws Exception {	
		if (session == null)
			throw new Exception("USUARIO_NO_AUTENTICADO");
		if (session.getTipoUsuario() == null || session.getUsuario() == null)
			throw new Exception("SESION_INVALIDA");
		if (session.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_PROVEEDOR))
			throw new Exception("OPERACION_RESTRINGIDA_A_PROVEEDORES");		
	}
		
	public void checkCustomerRights(UserSession session) throws Exception {	
		if (session == null)
			throw new Exception("USUARIO_NO_AUTENTICADO");
		if (session.getTipoUsuario() == null || session.getUsuario() == null)
			throw new Exception("SESION_INVALIDA");
		if (session.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE))
			throw new Exception("OPERACION_RESTRINGIDA_A_CLIENTES");
	}
	
}
