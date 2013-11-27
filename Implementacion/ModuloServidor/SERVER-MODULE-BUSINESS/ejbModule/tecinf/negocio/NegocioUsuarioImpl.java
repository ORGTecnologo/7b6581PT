package tecinf.negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.CambiarContraseniaDataType;
import tecinf.negocio.dtos.EditarUsuarioDataType;
import tecinf.negocio.dtos.LoginRespDataType;
import tecinf.negocio.dtos.UsuarioAdministradorDataType;
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
import tecinf.negocio.utiles.ValidationUtil;
import tecinf.persistencia.daos.EstadoUsuarioDao;
import tecinf.persistencia.daos.ParametroValorDao;
import tecinf.persistencia.daos.SessionDao;
import tecinf.persistencia.daos.TipoRegistroDao;
import tecinf.persistencia.daos.UsuarioDao;
import tecinf.persistencia.entities.EstadoUsuarioEntity;
import tecinf.persistencia.entities.ParametroValorEntity;
import tecinf.persistencia.entities.SessionEntity;
import tecinf.persistencia.entities.TipoRegistroEntity;
import tecinf.persistencia.entities.UsuarioAdministradorEntity;
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

	public LoginRespDataType loginUsuario(String usuario, String contrasenia) {

		LoginRespDataType resp = new LoginRespDataType();
		String hashedPassword = Encriptacion.encriptarMD5(contrasenia);
		UsuarioEntity ue = usuarioDao.findByEmailAndPassword(usuario,hashedPassword);
		if (ue == null) {
			resp.setRespuesta(EnumRespuestas.RESPUESTA_FALLA + "|" + "Usuario o contraseña inválidos");
		} else {
			
			if (ue.getHabilitado()){
			
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
			
			} else {
				resp.setRespuesta(EnumRespuestas.RESPUESTA_FALLA + "|" + "Usuario no habilitado");
			}
		}
		return resp;
	}

	@Override
	public Boolean loginUsuarioAdmin(String usuario, String contrasenia) {

		return null;
	}

	public LoginRespDataType registroUsuarioCliente(UsuarioClienteDataType dt) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		ue.setFechaNacimiento(sdf.parse(dt.getFechaNacimiento()));
		ue.setNombres(dt.getNombres());
		ue.setSexo(dt.getSexo());
		ue.setTelefonoMovil(dt.getTelefonoMovil());
		ue.setUsuario(dt.getUsuario());
		TipoRegistroEntity tipoRegistro = tipoRegistroDao.findByID(EnumClavesEntidades.TIPO_REGISTRO_WEB);
		((UsuarioClienteEntity) ue).setTipoRegistro(tipoRegistro);
		((UsuarioClienteEntity) ue).setRutaImagenPerfil("");
		ue.setTipoUsuario(EnumTipoUsuario.USUARIO_CLIENTE);
		ue.setHabilitado(true);
		
		//Creo la estructura de directorios para los usuarios cliente
		if (!(new FileSystemUtils()).crearEstructuraDirectorioUsuarioCliente(ue.getUsuario()))
			throw new Exception("Error al crear directorio de usuario");
		negocioAuditoria.registrarAuditoria(ue.getUsuario(), new Date(), ConstantesAuditoria.ID_OBJETO_USUARIO, ConstantesAuditoria.ID_OPERACION_ALTA, ue.getUsuario());
		usuarioDao.persist(ue);
		
		return loginUsuario(dt.getCorreoElectronico(), dt.getContrasenia());
	}
	
	public LoginRespDataType registroUsuarioProveedor(UsuarioProveedorDataType dt) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		ue.setFechaNacimiento(sdf.parse(dt.getFechaNacimiento()));
		ue.setNombres(dt.getNombres());
		ue.setSexo(dt.getSexo());
		ue.setTelefonoMovil(dt.getTelefonoMovil());
		ue.setUsuario(dt.getUsuario());
		((UsuarioProveedorEntity) ue).setSitioWeb(dt.getSitioWeb());
		ue.setTipoUsuario(EnumTipoUsuario.USUARIO_PROVEEDOR);		
		ue.setHabilitado(true);
		
		//Creo la estructura de directorios para los usuarios proveedores
		if (!(new FileSystemUtils()).crearEstructuraDirectorioUsuarioProveedor(ue.getUsuario()))
			throw new Exception("Error al crear directorio de usuario");	
				
		usuarioDao.persist(ue);
		return loginUsuario(dt.getCorreoElectronico(), dt.getContrasenia());
	}
	
	public void registroUsuarioAdministrador(UsuarioAdministradorDataType dt) throws Exception {
		UsuarioEntity uAdmin = new UsuarioAdministradorEntity();
		
		if (dt == null)
			throw new Exception("Parametro invalido.");
		if (ValidationUtil.isNullOrEmpty(dt.getCorreoElectronico()))
			throw new Exception("Email obligatorio");
		if (ValidationUtil.isNullOrEmpty(dt.getUsuario()))
			throw new Exception("Usuario obligatorio");
		if (ValidationUtil.isNullOrEmpty(dt.getContrasenia()) || ValidationUtil.isNullOrEmpty(dt.getContrasenia2()))
			throw new Exception("Contraseñas obligatorias");
		if (!dt.getContrasenia().equals(dt.getContrasenia2()))
			throw new Exception("Las contraseñas no coinciden");
		if (ValidationUtil.isNullOrEmpty(dt.getFechaNacimiento()))
			throw new Exception("Fecha de nacimiento obligatoria");
				
		UsuarioEntity uAux = usuarioDao.findByID(dt.getUsuario());
		if (uAux != null)
			throw new Exception("Nick ya utilizado.");
		uAux = usuarioDao.findByMail(dt.getCorreoElectronico());
		if (uAux != null)
			throw new Exception("Email ya utilizado.");
				
		uAdmin.setApellidos(dt.getApellidos());
		String hashedPass = Encriptacion.encriptarMD5(dt.getContrasenia());
		uAdmin.setContrasenia(hashedPass);
		uAdmin.setCorreoElectronico(dt.getCorreoElectronico());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		uAdmin.setFechaNacimiento( sdf.parse(dt.getFechaNacimiento()) );
		EstadoUsuarioEntity estado = estadoUsuarioDao.findByID(EnumClavesEntidades.ESTADO_USUARIO_HABILITADO);
		uAdmin.setEstadoUsuario(estado); 
		uAdmin.setHabilitado(true);
		uAdmin.setNombres(dt.getNombres());
		uAdmin.setSexo(dt.getSexo());
		uAdmin.setTelefonoMovil(dt.getTelefonoMovil());
		uAdmin.setTipoUsuario(EnumTipoUsuario.USUARIO_ADMINISTRADOR);
		uAdmin.setUsuario(dt.getUsuario());
		
		usuarioDao.persist(uAdmin); 
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

	public List<UsuarioDataType> buscarPorFiltros(String tipoUsuario, String nick, String email){
		List<UsuarioDataType> listaUsuarios = new ArrayList<UsuarioDataType>();
		
		List<UsuarioEntity> listaUsuariosE = usuarioDao.findAllByFiltros(tipoUsuario, nick, email);
		if (listaUsuariosE != null){
			for (UsuarioEntity e : listaUsuariosE)
				listaUsuarios.add(DataTypesFactory.getUsuarioDataType(e));
		}
		
		return listaUsuarios;
	}
	
	public void cambiarEstadoUsuario(UsuarioDataType u) throws Exception {
		UsuarioEntity ue = usuarioDao.findByID(u.getUsuario());
		if (ue == null)
			throw new Exception("Usuario no encontrado en la base de datos");
		ue.setHabilitado(u.getHabilitado() == null || !u.getHabilitado() ? true : false);
		usuarioDao.merge(ue);		
	}
	
	public UsuarioDataType verInfoUsuario(String nick) throws Exception {
		if (ValidationUtil.isNullOrEmpty(nick))
			throw new Exception("PARAMETRO_NO_VALIDO");
		UsuarioEntity usrE = usuarioDao.findByID(nick);
		if (usrE == null )
			throw new Exception("USUARIO_NO_ENCONTRADO");
		return DataTypesFactory.getUsuarioDataType(usrE);
	}
	
	public void editarPerfilUsuario(String nick, EditarUsuarioDataType datos) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		if (ValidationUtil.isNullOrEmpty(nick))
			throw new Exception("PARAMETRO_NO_VALIDO");
		
		UsuarioEntity usrE = usuarioDao.findByID(nick);
		if (usrE == null )
			throw new Exception("USUARIO_NO_ENCONTRADO");
			
		if (datos.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE)){
			
		} else if (datos.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_PROVEEDOR)){
			((UsuarioProveedorEntity)usrE).setSitioWeb(ValidationUtil.isNullOrEmpty(datos.getSitioWeb()) ? "Sin definir" : datos.getSitioWeb());
		}
		
		usrE.setApellidos(ValidationUtil.isNullOrEmpty(datos.getApellidos()) ? "" : datos.getApellidos());
		usrE.setNombres(ValidationUtil.isNullOrEmpty(datos.getNombres()) ? "" : datos.getNombres());
		usrE.setSexo(ValidationUtil.isNullOrEmpty(datos.getSexo()) ? "" : datos.getSexo()); 
		usrE.setTelefonoMovil(ValidationUtil.isNullOrEmpty(datos.getTelefonoMovil()) ? "Sin definir" : datos.getTelefonoMovil()); 
		usrE.setFechaNacimiento(ValidationUtil.isNullOrEmpty(datos.getFechaNacimiento()) ? new Date() : sdf.parse(datos.getFechaNacimiento())); 

		usuarioDao.merge(usrE);
	}
	
	public void cambiarContrasenia(String nick, CambiarContraseniaDataType dt) throws Exception {
		
		if (dt == null)
			throw new Exception("PARAMETRO_INVALIDO");
		
		if (ValidationUtil.isNullOrEmpty(dt.getConfirmacionContraseniaNueva()) ||
			ValidationUtil.isNullOrEmpty(dt.getContraseniaNueva()) ||
			ValidationUtil.isNullOrEmpty(dt.getConfirmacionContraseniaNueva()))
				throw new Exception("Contraseña anterior, nueva y confirmacion obligatorias");
			
		UsuarioEntity usuario = usuarioDao.findByID(nick);
		if (usuario == null)
			throw new Exception("Usuario no encontrado");
		
		String hashedPwd = Encriptacion.encriptarMD5(dt.getContraseniaAnterior());
		if (!usuario.getContrasenia().equals(hashedPwd))
			throw new Exception("Contrasña anterior inválida");
		if (!dt.getConfirmacionContraseniaNueva().equals(dt.getContraseniaNueva()))
			throw new Exception("Las nuevas contraseñas no coinciden.");
		
		hashedPwd = Encriptacion.encriptarMD5(dt.getContraseniaNueva());
		usuario.setContrasenia(hashedPwd);
		
		usuarioDao.merge(usuario);
	}
	
}
