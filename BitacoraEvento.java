package co.edu.elbosque.procureit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora_evento")
public class BitacoraEvento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    public enum TipoEvento { CREACION, ENVIO, APROBACION, RECHAZO, DEVOLUCION, CUMPLIMIENTO, NOTIFICACION, ACTUALIZACION }

    @Enumerated(EnumType.STRING) @Column(name = "tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "actor_id")
    private Empleado actor;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento;

    @Column(columnDefinition = "TEXT")
    private String detalles;

    // getters/setters omitted for brevity
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; }
    public TipoEvento getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(TipoEvento tipoEvento) { this.tipoEvento = tipoEvento; }
    public Empleado getActor() { return actor; }
    public void setActor(Empleado actor) { this.actor = actor; }
    public LocalDateTime getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; }
    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }
}
