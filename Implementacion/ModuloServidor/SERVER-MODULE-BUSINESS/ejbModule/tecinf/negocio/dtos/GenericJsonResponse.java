package tecinf.negocio.dtos;

public class GenericJsonResponse {
	
	private String resultadoOperacion;
	private String idObjeto;
	private String mensajeOperacion;
	
	public String getResultadoOperacion() {
		return resultadoOperacion;
	}
	public void setResultadoOperacion(String resultadoOperacion) {
		this.resultadoOperacion = resultadoOperacion;
	}
	public String getIdObjeto() {
		return idObjeto;
	}
	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}
	public String getMensajeOperacion() {
		return mensajeOperacion;
	}
	public void setMensajeOperacion(String mensajeOperacion) {
		this.mensajeOperacion = mensajeOperacion;
	}
	
	
}
