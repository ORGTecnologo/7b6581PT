package tecinf.negocio.utiles;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.entities.UsuarioClienteEntity;

public class DataTypesFactory {
	
	public static UsuarioClienteDataType getUsuarioClienteDataType(UsuarioClienteEntity u){
		UsuarioClienteDataType dt = new UsuarioClienteDataType();
		
		dt.setApellidos(u.getUsuario().getApellidos());
		dt.setContrasenia(u.getUsuario().getContrasenia());
		dt.setNombres(u.getUsuario().getNombres());
		dt.setUsuario(u.getUsuario().getUsuario());
		dt.setSexo(u.getUsuario().getSexo());
		dt.setFechaNacimiento(u.getUsuario().getFechaNacimiento());
		
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
