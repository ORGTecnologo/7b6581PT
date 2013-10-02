package tecinf.negocio.utiles;

import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.UsuarioEntity;

public class DataTypesFactory {
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = new UsuarioDataType();
		
		dt.setApellidos(u.getApellidos());
		dt.setContrasenia(u.getContrasenia());
		dt.setNombres(u.getNombres());
		dt.setUsuario(u.getUsuario());
		
		return dt;
	}
	
}
