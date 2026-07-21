package com.farmaciasalud.model;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 60)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "nombre_completo", nullable = false, length = 120)
    private String nombreCompleto;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;
    @Column(nullable = false)
    private Boolean activo = true;

    public enum Rol { ADMIN, EMPLEADO }
    public Usuario() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; } public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; } public void setPassword(String p) { this.password = p; }
    public String getNombreCompleto() { return nombreCompleto; } public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public Rol getRol() { return rol; } public void setRol(Rol r) { this.rol = r; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
}
