package tecinf.negocio.utiles;

import java.util.List;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.ContenidoDataType;
import tecinf.negocio.dtos.ContenidoLibroDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.ContenidoEntity;
import tecinf.persistencia.entities.ContenidoFotoEntity;
import tecinf.persistencia.entities.ContenidoLibroEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.utiles.EnumTiposContenido;

public class DataTypesFactory {
	
	public static UsuarioDataType getUsuarioDataType(UsuarioEntity u){
		UsuarioDataType dt = null;		
		if (u.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE)){
			dt = new UsuarioClienteDataType();
			dt.setApellidos(u.getApellidos());
			dt.setContrasenia(u.getContrasenia());
			dt.setCorreoElectronico(u.getCorreoElectronico());
			dt.setFechaNacimientoDate(u.getFechaNacimiento());
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
	
	public static ContenidoDataType getContenidoDataType(ContenidoEntity c, List<ContenidoFotoEntity> fotos){
		ContenidoDataType dt = null;
		
		if (c.getTipoContenido().equals(EnumTiposContenido.TIPO_CONTENIDO_LIBRO)){
			dt = new ContenidoLibroDataType();
			c = (ContenidoLibroEntity)c;
			((ContenidoLibroDataType)dt).setAutor( ((ContenidoLibroEntity)c).getAutor() );
			((ContenidoLibroDataType)dt).setFechaPublicacion(( ((ContenidoLibroEntity)c).getFecha_publicacion()) );
			((ContenidoLibroDataType)dt).setAutor( ((ContenidoLibroEntity)c).getAutor() );
			
		}
		
		if (fotos != null && fotos.size() > 0){
			for (ContenidoFotoEntity f : fotos){
				dt.getFotos().add("/SERVER-MODULE-SERVICES/Images?" + f.getUrlFoto());
			}
		}
		
		dt.setDescripcionContenido(c.getDescripcion());
		dt.setIdContenido(c.getId());
		dt.setNombreContenido(c.getNombre());
		dt.setTamanioContenido(c.getTamanio());
		dt.setUrlArchivoContenido(c.getRutaArchivoContenido());
		
		return dt;
	}
	
}
