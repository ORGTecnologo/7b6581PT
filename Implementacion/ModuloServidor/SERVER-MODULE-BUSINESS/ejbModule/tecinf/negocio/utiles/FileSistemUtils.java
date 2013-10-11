package tecinf.negocio.utiles;

import java.io.File;

import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

public class FileSistemUtils {
	
	private static Logger logger = Logger.getLogger(FileSistemUtils.class);
	
	private static final String DIRECTORIO_USUARIOS_CLIENTES = "usuarios_cliente";
	private static final String DIRECTORIO_USUARIOS_PROVEEDORES = "usuarios_proveedores";
	private static final String DIRECTORIO_USUARIOS_ADMINISTRADORES = "usuarios_administradores";
	
	private ParametroValorDao parametroValorDao = null;
	
	public FileSistemUtils() throws NamingException {
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
	}
	
	
	public void crearEstructuraDirectorioUsuarioCliente(String usuario){
		
		ParametroValorEntity rutaBase = parametroValorDao.findByID(EnumParametrosValor.RUTA_BASE_SISTEMA_ARCHIVOS);

		String rutadirectorioUsuario = rutaBase + DIRECTORIO_USUARIOS_CLIENTES + usuario;
		File directorioUsuario = new File(rutadirectorioUsuario);
		if (!directorioUsuario.exists()) {
			logger.error("Creando directorio " + rutadirectorioUsuario);
			boolean result = directorioUsuario.mkdir();		
			if(result) {
				logger.info("Directorio " + rutadirectorioUsuario + " creado");
		    }
		}

	}
	
}
