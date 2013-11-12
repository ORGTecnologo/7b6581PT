package tecinf.persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "promociones")
public class PromocionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promocion_secuencia")
	@SequenceGenerator(name = "promocion_secuencia", sequenceName = "promocion_seq", allocationSize = 1)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="fk_contenido")
	private ContenidoEntity contenido;
	
	@Column(name="nombre_promocion", nullable=false, length=100)
	private String nombrePromocion;
	
	@Column(name="descripcion_promocion", nullable=false, length=500)
	private String descripcionPromocion;
	
	@Column(name="descuento_promocion", nullable=false, length=50)
	private Float descuentoPromocion;
	
	@Column(name="fecha_inicio_promocion", nullable=false)
	private Date fechaInicioPromocion;
	
	@Column(name="fecha_fin_promocion", nullable=false)
	private Date fechaFinPromocion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombrePromocion() {
		return nombrePromocion;
	}

	public void setNombrePromocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}

	public String getDescripcionPromocion() {
		return descripcionPromocion;
	}

	public void setDescripcionPromocion(String descripcionPromocion) {
		this.descripcionPromocion = descripcionPromocion;
	}

	public Float getDescuentoPromocion() {
		return descuentoPromocion;
	}

	public void setDescuentoPromocion(Float descuentoPromocion) {
		this.descuentoPromocion = descuentoPromocion;
	}

	public Date getFechaInicioPromocion() {
		return fechaInicioPromocion;
	}

	public void setFechaInicioPromocion(Date fechaInicioPromocion) {
		this.fechaInicioPromocion = fechaInicioPromocion;
	}

	public Date getFechaFinPromocion() {
		return fechaFinPromocion;
	}

	public void setFechaFinPromocion(Date fechaFinPromocion) {
		this.fechaFinPromocion = fechaFinPromocion;
	}

	public ContenidoEntity getContenido() {
		return contenido;
	}

	public void setContenido(ContenidoEntity contenido) {
		this.contenido = contenido;
	}
	

}