package co.edu.elbosque.procureit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aprobacion")
public class Aprobacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    @Column(nullable = false)
    private Byte orden;

    public enum RolAprobador { GERENTE, DIRECTOR, TI }
    public enum Estado { PENDIENTE, APROBADA, RECHAZADA, DEVUELTA_POR_INFO }

    @Enumerated(EnumType.STRING) @Column(name = "rol_aprobador", nullable = false)
    private RolAprobador rolAprobador;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "aprobador_id", nullable = false)
    private Empleado aprobador;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Estado estado;

    @Column(length = 500)
    private String comentario;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDateTime fechaAsignacion;

    @Column(name = "fecha_decision")
    private LocalDateTime fechaDecision;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; }
    public Byte getOrden() { return orden; }
    public void setOrden(Byte orden) { this.orden = orden; }
    public RolAprobador getRolAprobador() { return rolAprobador; }
    public void setRolAprobador(RolAprobador rolAprobador) { this.rolAprobador = rolAprobador; }
    public Empleado getAprobador() { return aprobador; }
    public void setAprobador(Empleado aprobador) { this.aprobador = aprobador; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public LocalDateTime getFechaDecision() { return fechaDecision; }
    public void setFechaDecision(LocalDateTime fechaDecision) { this.fechaDecision = fechaDecision; }
}
