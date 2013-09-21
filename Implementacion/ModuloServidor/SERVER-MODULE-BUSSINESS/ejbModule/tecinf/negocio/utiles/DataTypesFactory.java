package tecinf.negocio.utiles;

import tecinf.negocio.dtos.RolDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.RolEntity;
import tecinf.persistencia.entities.UsuarioEntity;

public class DataTypesFactory {
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = new UsuarioDataType();
		
		dt.setApellidos(u.getApellidos());
		dt.setContrasenia(u.getContrasenia());
		dt.setNombres(u.getNombres());
		dt.setRol(getRolDataType(u.getRol()));
		dt.setUsuario(u.getUsuario());
		
		return dt;
	}
	
	public static RolDataType getRolDataType(RolEntity r){
		RolDataType dt = new RolDataType();;
		
		dt.setDescripcion(r.getDescripcion());
		dt.setId(r.getId());
		
		return dt;
	}
	
}
