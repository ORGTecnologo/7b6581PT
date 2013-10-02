package tecinf.negocio.utiles;

import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.UsuarioClienteEntity;
import tecinf.persistencia.entities.UsuarioEntity;

public class DataTypesFactory {
	
	public static UsuarioClienteDataType getUsuarioClienteDataType(UsuarioClienteEntity u){
		UsuarioClienteDataType dt = new UsuarioClienteDataType();
		
		dt.setApellidos(u.getUsuario().getApellidos());
		dt.setContrasenia(u.getUsuario().getContrasenia());
		dt.setNombres(u.getUsuario().getNombres());
		dt.setUsuario(u.getUsuario().getUsuario());
		dt.setSexo(u.getUsuario().getSexo().getDescripcion());
		dt.setFechaNacimiento(u.getUsuario().getFechaNacimiento());
		
		return dt;
	}
	
}
