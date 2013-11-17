package tecinf.negocio.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.utiles.EnumTiposContenido;
import tecinf.persistencia.utiles.PersistenciaFactory;

public class FileSystemUtils {
	
	private static Logger logger = Logger.getLogger(FileSystemUtils.class);
	
	public static final String DIRECTORIO_USUARIOS_CLIENTES = "usuarios_clientes";
	public static final String DIRECTORIO_USUARIOS_PROVEEDORES = "usuarios_proveedores";
	public static final String DIRECTORIO_USUARIOS_ADMINISTRADORES = "usuarios_administradores";
	public static final String DIRECTORIO_RECURSOS_SISTEMA = "recursos_aplicacion";
	public static final String DIRECTORIO_CATEGORIAS_SISTEMA = "recursos_aplicacion/categorias";
	public static final String DIRECTORIO_SUBCATEGORIAS_SISTEMA = "recursos_aplicacion/subcategorias";
	
	public static final String DIRECTORIO_CONTENIDO_SOFTWARE = "contenido_software";
	public static final String DIRECTORIO_CONTENIDO_TEMA = "contenido_musica";
	public static final String DIRECTORIO_CONTENIDO_VIDEO = "contenido_video";
	public static final String DIRECTORIO_CONTENIDO_LIBRO = "contenido_libro";
	
	public static final String DIRECTORIO_TEMPORALES = "tmp";
	
	private ParametroValorDao parametroValorDao = null;
	
	public FileSystemUtils() throws NamingException {
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
	}
	
	
	public Boolean crearEstructuraDirectorioUsuarioCliente(String usuario){
		Boolean result = true;
		try {
			ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);

			String rutadirectorioUsuario = rutaBase.getValorParametro() + "/" + DIRECTORIO_USUARIOS_CLIENTES + "/" + usuario;
			
			File directorioUsuario = new File(rutadirectorioUsuario);
			if (!directorioUsuario.exists()) {
				logger.info("Creando directorio " + rutadirectorioUsuario);
				result = directorioUsuario.mkdir();
			}
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
			result = false;
		}
		return result;
	}
	
	public Boolean crearEstructuraDirectorioUsuarioProveedor(String usuario) {
		Boolean result = true;
		try {
			ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
			ParametroValorEntity directoriosProveedores = parametroValorDao.findByID(EnumParametrosValor.DIRECTORIOS_USUARIOS_PROVEEDORES);
			String[] directorios = directoriosProveedores.getValorParametro().split(";");
			
			String rutadirectorioUsuario = rutaBase.getValorParametro() + "/" + DIRECTORIO_USUARIOS_PROVEEDORES + "/" + usuario;
			
			File directorio = null;
			File directorioUsuario = new File(rutadirectorioUsuario);
			
			if (!directorioUsuario.exists()) {
				logger.info("Creando directorio " + rutadirectorioUsuario);
				result = directorioUsuario.mkdir();				
				for (String dir : directorios){
					logger.info("Creando directorio " + rutadirectorioUsuario + "/" + dir);
					directorio = new File(rutadirectorioUsuario + "/" + dir);
					if (!directorio.exists()) {
						result = directorio.mkdir();						
					}
				}			
			}	
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
			result = false; 
		}
		return result;
	}
	
	public Integer getFileSize(String filePath){
		try {
			ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
			File f = new File(rutaBase.getValorParametro() + filePath);
			Long l = f.length();
			return l.intValue();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
			return 0;
		}
	}
	
	public void copyFile(String sourceStr, String destStr) throws Exception {
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		File afile  = new File(rutaBase.getValorParametro() + sourceStr);
		File bfile = new File(rutaBase.getValorParametro() + destStr);
 	    InputStream inStream = new FileInputStream(afile);;
 		OutputStream outStream = new FileOutputStream(bfile);
 	    byte[] buffer = new byte[1024];
 	    int length;
 	    while ((length = inStream.read(buffer)) > 0){
 	    	outStream.write(buffer, 0, length);
 	    }
 	    inStream.close();
 	    outStream.close();
	}
	
	public void deleteFile(String filePath) throws Exception {
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		File file = new File(rutaBase.getValorParametro() + filePath);
		file.delete();
	}
	
	public String crearDirectorioContenido(String usuario, String directorioTipoContenido, String nombreCarpeta){
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		String nuevoDirectorio = "";
		nuevoDirectorio = rutaBase.getValorParametro() + "/" + DIRECTORIO_USUARIOS_PROVEEDORES + "/" + usuario + "/" + directorioTipoContenido + "/" + nombreCarpeta;
		String directorioRelativo = "/" + DIRECTORIO_USUARIOS_PROVEEDORES + "/" + usuario + "/" + directorioTipoContenido  + "/" + nombreCarpeta;
		
		File directorioContenido = new File(nuevoDirectorio);
		if (!directorioContenido.exists()){
			if (!directorioContenido.mkdir()){
				logger.error("Error al crear directorio " + directorioContenido);
			}
		}
		return directorioRelativo;
	}
	
	public String obtenerDirectorioUsuario(String directorioTipoUsuario, String directorio, String usuario ) {	
		String directorioSalida = "/" + directorioTipoUsuario;
		directorioSalida += "/" + usuario;
		directorioSalida += "/" + directorio;
		return directorioSalida;
	}
	
	public String obtenerDirectorioAbsolutoUsuario(String directorioTipoUsuario, String directorio, String usuario ) {	
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		String directorioSalida = rutaBase.getValorParametro();
		directorioSalida += "/" + directorioTipoUsuario;
		directorioSalida += "/" + usuario;
		directorioSalida += "/" + directorio;
		return directorioSalida;
	}
	
}
