package co.edu.elbosque.procureit.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_app", nullable = false, length = 12)
    private RolApp rolApp;

    @Column(length = 80)
    private String area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gerente_id")
    private Empleado gerente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Empleado director;

    public enum RolApp { SOLICITANTE, GERENTE, DIRECTOR, TI, ADMIN }

    // getters/setters, equals/hashCode
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public RolApp getRolApp() { return rolApp; }
    public void setRolApp(RolApp rolApp) { this.rolApp = rolApp; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public Empleado getGerente() { return gerente; }
    public void setGerente(Empleado gerente) { this.gerente = gerente; }
    public Empleado getDirector() { return director; }
    public void setDirector(Empleado director) { this.director = director; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado that = (Empleado) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
