package com.farmaciasalud.dto.response;
import java.time.LocalDateTime;

public class ProveedorResponse {
    private Long id;
    private String nombre;
    private String ruc;
    private String direccion;
    private String telefono;
    private String email;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProveedorResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getRuc() { return ruc; } public void setRuc(String r) { this.ruc = r; }
    public String getDireccion() { return direccion; } public void setDireccion(String d) { this.direccion = d; }
    public String getTelefono() { return telefono; } public void setTelefono(String t) { this.telefono = t; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime u) { this.updatedAt = u; }
}
