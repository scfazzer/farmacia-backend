package com.farmaciasalud.dto.response;
import java.time.LocalDateTime;

public class CategoriaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoriaResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getDescripcion() { return descripcion; } public void setDescripcion(String d) { this.descripcion = d; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime u) { this.updatedAt = u; }
}
