package tecinf.negocio.utiles;

import java.io.File;
import java.nio.file.Files;

import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

public class FileSystemUtils {
	
	private static Logger logger = Logger.getLogger(FileSystemUtils.class);
	
	private static final String DIRECTORIO_USUARIOS_CLIENTES = "usuarios_clientes";
	private static final String DIRECTORIO_USUARIOS_PROVEEDORES = "usuarios_proveedores";
	private static final String DIRECTORIO_USUARIOS_ADMINISTRADORES = "usuarios_administradores";
	private static final String DIRECTORIO_RECURSOS_SISTEMA = "recursos_aplicacion";
	private static final String DIRECTORIO_CATEGORIAS_SISTEMA = "recursos_aplicacion/categorias";
	private static final String DIRECTORIO_SUBCATEGORIAS_SISTEMA = "recursos_aplicacion/subcategorias";
	private static final String DIRECTORIO_TEMPORALES = "tmp";
	
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
			String pathDesencriptado = CripterDecripter.decrypt(filePath);
			File f = new File(rutaBase.getValorParametro() + pathDesencriptado);
			Long l = f.length();
			return l.intValue();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
			return 0;
		}
	}
	
	public void copyFile(String sourceStr, String destStr) throws Exception {
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		File source  = new File(rutaBase.getValorParametro() + sourceStr);
		File dest = new File(rutaBase.getValorParametro() + destStr);
		Files.copy(source.toPath(), dest.toPath());
	}
	
	public void deleteFile(String filePath) throws Exception {
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);
		File file = new File(rutaBase.getValorParametro() + filePath);
		file.delete();
	}
	
}
