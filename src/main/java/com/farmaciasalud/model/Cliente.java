package com.farmaciasalud.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

@Entity @Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String nombre;
    @Column(unique = true, nullable = false, length = 8)
    private String dni;
    @Column(length = 20)
    private String telefono;
    @Column(length = 120)
    private String email;
    @Column(length = 200)
    private String direccion;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(nullable = false)
    private Boolean activo = true;
    @CreationTimestamp @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Cliente() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getDni() { return dni; } public void setDni(String d) { this.dni = d; }
    public String getTelefono() { return telefono; } public void setTelefono(String t) { this.telefono = t; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
    public String getDireccion() { return direccion; } public void setDireccion(String d) { this.direccion = d; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; } public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
