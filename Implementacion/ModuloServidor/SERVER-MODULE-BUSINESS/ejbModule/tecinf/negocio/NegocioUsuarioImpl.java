package tecinf.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioClienteDataType;
import tecinf.negocio.dtos.UsuarioDataType;
import tecinf.negocio.dtos.UsuarioProveedorDataType;
import tecinf.negocio.utiles.ConstantesAuditoria;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.Encriptacion;
import tecinf.negocio.utiles.EnumParametrosValor;
import tecinf.negocio.utiles.EnumRespuestas;
import tecinf.negocio.utiles.EnumTipoUsuario;
import tecinf.negocio.utiles.FileSystemUtils;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.negocio.utiles.RandomString;
import tecinf.persistencia.daos.EstadoUsuarioDao;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.SessionDao;
import tecinf.persistencia.daos.TipoRegistroDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.EstadoUsuarioEntity;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.entities.SessionEntity;
import tecinf.persistencia.entities.TipoRegistroEntity;
import tecinf.persistencia.entities.UsuarioClienteEntity;
import tecinf.persistencia.entities.UsuarioEntity;
import tecinf.persistencia.entities.UsuarioProveedorEntity;
import tecinf.persistencia.utiles.EnumClavesEntidades;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario {

	private static Logger logger = Logger.getLogger(NegocioUsuarioImpl.class);
	
	private UsuarioDao usuarioDao = null;
	private EstadoUsuarioDao estadoUsuarioDao = null;
	private TipoRegistroDao tipoRegistroDao = null;
	private SessionDao sessionDao = null;
	private ParametroValorDao parametroValorDao = null;
	private NegocioAuditoria negocioAuditoria = null;

	public NegocioUsuarioImpl() throws NamingException {
		
		estadoUsuarioDao = PersistenciaFactory.getEstadoUsuarioDao();
		tipoRegistroDao = PersistenciaFactory.getTipoRegistroDao();
		usuarioDao = PersistenciaFactory.getUsuarioDao();
		sessionDao = PersistenciaFactory.getSessionDao();
		parametroValorDao = PersistenciaFactory.getParametroValorDao();
		
		negocioAuditoria = NegocioFactory.getNegocioAuditoria();
		
	}

	public List<UsuarioClienteDataType> obtenerTodosClientes() {

		List<UsuarioClienteDataType> listaDtos = new ArrayList<UsuarioClienteDataType>();
		List<UsuarioEntity> listEntities = usuarioDao.findAllByType(EnumTipoUsuario.USUARIO_CLIENTE);
		if (listEntities != null) {
			for (UsuarioEntity ue : listEntities)
				listaDtos.add((UsuarioClienteDataType) DataTypesFactory.getUsuarioDataType(ue));
		}
		return listaDtos;

	}

	public void modificarUsuario(UsuarioDataType u) throws Exception {

		/*
		 * UsuarioEntity ue = usuarioDao.findById(u.getUsuario()); if (ue ==
		 * null) throw new Exception("El usuario no existe");
		 * 
		 * ue.setApellidos(u.getApellidos());
		 * ue.setContrasenia(u.getContrasenia()); ue.setNombres(u.getNombres());
		 * 
		 * usuarioDao.merge(ue);
		 */
	}

	public LoginRespDataType loginUsuario(String usuario, String contrasenia) {

		LoginRespDataType resp = new LoginRespDataType();
		String hashedPassword = Encriptacion.encriptarMD5(contrasenia);
		UsuarioEntity ue = usuarioDao.findByEmailAndPassword(usuario,hashedPassword);
		if (ue == null) {
			resp.setRespuesta(EnumRespuestas.RESPUESTA_FALLA);
		} else {
			
			//Auditoria
			negocioAuditoria.registrarAuditoria(ue.getUsuario(), new Date(), ConstantesAuditoria.ID_OBJETO_USUARIO, ConstantesAuditoria.ID_OPERACION_LOGIN, ue.getUsuario());
					
			//Respuesta
			resp.setRespuesta(EnumRespuestas.RESPUESTA_OK);
			String tkn = (new RandomString(50).nextString()); /* token de 50 caracteres */
			resp.setToken(tkn);
			resp.setUsuario(ue.getUsuario());
			resp.setTipoUsuario(ue.getTipoUsuario());
			
			//Session
			/*
			SessionEntity session = new SessionEntity();
			session.setUsuario(ue.getUsuario()); 
			session.setTimeStamp(new Date());
			session.setTipoUsuario(ue.getTipoUsuario());
			session.setToken(tkn);
			session.setTimeStamp(updateTimeStamp(new Date())); 
			sessionDao.persist(session);
			*/
		}
		return resp;
	}

	@Override
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia) {

		return null;
	}

	public LoginRespDataType registroUsuarioCliente(UsuarioClienteDataType dt) throws Exception {
		
		UsuarioEntity ue;

		if (existeUsuario(dt.getUsuario()))
			throw new Exception("Nick ya utilizado");

		if (existeUsuarioPorMail(dt.getCorreoElectronico()))
			throw new Exception("Email ya utilizado");

		ue = new UsuarioClienteEntity();
		ue.setApellidos(dt.getApellidos());
		String pwdHash = Encriptacion.encriptarMD5(dt.getContrasenia());
		ue.setContrasenia(pwdHash);
		ue.setCorreoElectronico(dt.getCorreoElectronico());
		EstadoUsuarioEntity estado = estadoUsuarioDao.findByID(EnumClavesEntidades.ESTADO_USUARIO_HABILITADO);
		ue.setEstadoUsuario(estado);
		ue.setFechaNacimiento(dt.getFechaNacimientoDate());
		ue.setNombres(dt.getNombres());
		ue.setSexo(dt.getSexo());
		ue.setTelefonoMovil(dt.getTelefonoMovil());
		ue.setUsuario(dt.getUsuario());
		TipoRegistroEntity tipoRegistro = tipoRegistroDao.findByID(EnumClavesEntidades.TIPO_REGISTRO_WEB);
		((UsuarioClienteEntity) ue).setTipoRegistro(tipoRegistro);
		((UsuarioClienteEntity) ue).setRutaImagenPerfil("");
		ue.setTipoUsuario(EnumTipoUsuario.USUARIO_CLIENTE);
		
		//Creo la estructura de directorios para los usuarios cliente
		if (!(new FileSystemUtils()).crearEstructuraDirectorioUsuarioCliente(ue.getUsuario()))
			throw new Exception("Error al crear directorio de usuario");
		negocioAuditoria.registrarAuditoria(ue.getUsuario(), new Date(), ConstantesAuditoria.ID_OBJETO_USUARIO, ConstantesAuditoria.ID_OPERACION_ALTA, ue.getUsuario());
		usuarioDao.persist(ue);
		
		return loginUsuario(dt.getCorreoElectronico(), dt.getContrasenia());
	}
	
	public LoginRespDataType registroUsuarioProveedor(UsuarioProveedorDataType dt) throws Exception {
		
		UsuarioEntity ue;

		if (existeUsuario(dt.getUsuario()))
			throw new Exception("Nick ya utilizado");

		if (existeUsuarioPorMail(dt.getCorreoElectronico()))
			throw new Exception("Email ya utilizado");
		
		if (existeUsuarioProveedorPorSitioWeb(dt.getSitioWeb()))
			throw new Exception("sitioWeb ya utilizado");

		ue = new UsuarioProveedorEntity();
		ue.setApellidos(dt.getApellidos());
		String pwdHash = Encriptacion.encriptarMD5(dt.getContrasenia());
		ue.setContrasenia(pwdHash);
		ue.setCorreoElectronico(dt.getCorreoElectronico());
		EstadoUsuarioEntity estado = estadoUsuarioDao.findByID(EnumClavesEntidades.ESTADO_USUARIO_HABILITADO);
		ue.setEstadoUsuario(estado);
		ue.setFechaNacimiento(dt.getFechaNacimientoDate());
		ue.setNombres(dt.getNombres());
		ue.setSexo(dt.getSexo());
		ue.setTelefonoMovil(dt.getTelefonoMovil());
		ue.setUsuario(dt.getUsuario());
		((UsuarioProveedorEntity) ue).setSitioWeb(dt.getSitioWeb());
		ue.setTipoUsuario(EnumTipoUsuario.USUARIO_PROVEEDOR);		
		
		//Creo la estructura de directorios para los usuarios proveedores
		if (!(new FileSystemUtils()).crearEstructuraDirectorioUsuarioProveedor(ue.getUsuario()))
			throw new Exception("Error al crear directorio de usuario");	
				
		usuarioDao.persist(ue);
		return loginUsuario(dt.getCorreoElectronico(), dt.getContrasenia());
	}

	public Boolean existeUsuario(String usr) {
		UsuarioEntity ue = usuarioDao.findByID(usr);
		if (ue == null)
			return false;
		else
			return true;
	}

	public Boolean existeUsuarioPorMail(String mail) {
		UsuarioEntity ue = usuarioDao.findByMail(mail);
		if (ue == null)
			return false;
		else
			return true;
	}
	
	public Boolean existeUsuarioProveedorPorSitioWeb(String sitioWeb) {
		UsuarioEntity ue = usuarioDao.findByWebSite((sitioWeb));
		if (ue == null)
			return false;
		else
			return true;
	}
	
	public void executeSessionsRefresh() {
		Date now = new Date();
		List<SessionEntity> listaSessiones = sessionDao.findAll();
		if (listaSessiones != null) {
			for (SessionEntity se : listaSessiones) {
				if (now.after(se.getTimeStamp()))
					sessionDao.remove(se);
			}
		}
	}
		
	public Boolean checkUserSession(String usuario, String token) {
		SessionEntity session = sessionDao.findByUserAndToken(usuario, token);
		if (session != null){
			session.setTimeStamp(updateTimeStamp(session.getTimeStamp()));
			sessionDao.persist(session); 
			return true;
		} else {
			return false;
		}			
	}
	
	private Date updateTimeStamp(Date from){		
		ParametroValorEntity timeOutSession = parametroValorDao.findByID(EnumParametrosValor.SESSION_TIME_OUT);
		Integer timeOut = Integer.valueOf(timeOutSession.getValorParametro());
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		cal.add(Calendar.MINUTE, timeOut);
		return cal.getTime();	
	}
	
	public Boolean logout(String usuario, String token){
		try {
			SessionEntity session = sessionDao.findByUserAndToken(usuario, token);
			if (session != null)
				sessionDao.remove(session);
			negocioAuditoria.registrarAuditoria(usuario, new Date(), ConstantesAuditoria.ID_OBJETO_USUARIO, ConstantesAuditoria.ID_OPERACION_LOGOUT, usuario);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
			return false;
		}
	}

}
