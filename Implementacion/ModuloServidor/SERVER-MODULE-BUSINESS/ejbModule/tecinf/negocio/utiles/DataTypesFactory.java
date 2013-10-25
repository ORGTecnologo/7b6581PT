package tecinf.negocio.utiles;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.entities.UsuarioEntity;

public class DataTypesFactory {
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = null;		
		if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE)){
			dt = new UsuarioClienteDataType();
			dt.setApellidos(u.getApellidos());
			dt.setContrasenia(u.getContrasenia());
			dt.setCorreoElectronico(u.getCorreoElectronico());
			dt.setFechaNacimiento(u.getFechaNacimiento());
			dt.setNombres(u.getNombres());
			dt.setSexo(u.getSexo());
			dt.setTelefonoMovil(u.getTelefonoMovil());		
		}
		return dt;
	}
	
	public static CategoriaContenidoDataType getCategoriaContenidoDataType(CategoriaContenidoEntity e){
		CategoriaContenidoDataType dt = new CategoriaContenidoDataType();
		
		dt.setDescripcion(e.getDescripcion());
		dt.setId(e.getId());
		dt.setNombre(e.getNombre());
		
		return dt;
	}
	
	public static SubCategoriaContenidoDataType getSubCategoriaContenidoDataType(SubCategoriaContenidoEntity e){
		SubCategoriaContenidoDataType dt = new SubCategoriaContenidoDataType();
		
		dt.setDescripcion(e.getDescripcion());
		dt.setId(e.getId());
		dt.setNombre(e.getNombre());
		
		return dt;
	} 
	
}
