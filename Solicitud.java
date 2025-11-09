package co.edu.elbosque.procureit.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud")
public class Solicitud {
	@Id
	@Column(name = "solicitud_id", length = 20)
	private String solicitudId;

	@Column(name = "codigo_seguimiento", nullable = false, length = 36)
	private String codigoSeguimiento;

	public enum Tipo {
		HARDWARE, SOFTWARE, ACCESO
	}

	public enum Prioridad {
		BAJA, MEDIA, ALTA
	}

	public enum Estado {
		NUEVA, PENDIENTE_APROBACION, APROBADA, RECHAZADA, DEVUELTA_POR_INFO, EN_PROCESO, COMPLETADA, RECHAZADA_POR_TI
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 9)
	private Tipo tipo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 5)
	private Prioridad prioridad;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Estado estado;

	@Column(name = "costo_estimado", nullable = false, precision = 12, scale = 2)
	private BigDecimal costoEstimado = BigDecimal.ZERO;

	@Column(name = "fecha_requerida", nullable = false)
	private LocalDate fechaRequerida;

	@Column(name = "fecha_creacion", nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "fecha_actualizacion", nullable = false)
	private LocalDateTime fechaActualizacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solicitante_id", nullable = false)
	private Empleado solicitante;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String justificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalogo_item_id")
	private CatalogoItem catalogoItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sistema_id")
	private Sistema sistema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_sistema_id")
	private RolSistema rolSistema;

	// getters/setters
	public String getSolicitudId() {
		return solicitudId;
	}

	public void setSolicitudId(String solicitudId) {
		this.solicitudId = solicitudId;
	}

	public String getCodigoSeguimiento() {
		return codigoSeguimiento;
	}

	public void setCodigoSeguimiento(String codigoSeguimiento) {
		this.codigoSeguimiento = codigoSeguimiento;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getCostoEstimado() {
		return costoEstimado;
	}

	public void setCostoEstimado(BigDecimal costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public LocalDate getFechaRequerida() {
		return fechaRequerida;
	}

	public void setFechaRequerida(LocalDate fechaRequerida) {
		this.fechaRequerida = fechaRequerida;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public CatalogoItem getCatalogoItem() {
		return catalogoItem;
	}

	public void setCatalogoItem(CatalogoItem catalogoItem) {
		this.catalogoItem = catalogoItem;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public RolSistema getRolSistema() {
		return rolSistema;
	}

	public void setRolSistema(RolSistema rolSistema) {
		this.rolSistema = rolSistema;
	}
}
